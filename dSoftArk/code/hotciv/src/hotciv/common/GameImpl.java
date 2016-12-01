package hotciv.common;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class GameImpl implements GameModifiable
{
    private Player                       playerInTurn = Player.RED;
    private WorldModifiable              gameWorld;

    // Strategies
    private AgeStrategy                  ageStrategy;
    private BattleStrategy               battleStrategy;
    private CityFactory                  cityFactory;
    private CityProductionAmountStrategy cityProductionAmountStrategy;
    private ProductionUsageStrategy      productionUsageStrategy;
    private UnitFactory                  unitFactory;
    private WinStrategy                  winStrategy;

    // Constructor
    // Responsible for the setup of the tiles and the initial units.
    public GameImpl(StrategyFactory strategies)
    {
        // Strategy impls
        WorldGenStrategy worldGenStrategy;

        worldGenStrategy                  = strategies.getWorldGenStrategy();
        this.winStrategy                  = strategies.getWinStrategy();
        this.ageStrategy                  = strategies.getAgeStrategy();
        this.unitFactory                  = strategies.getUnitFactory();
        this.battleStrategy               = strategies.getBattleStrategy();
        this.cityFactory                  = strategies.getCityFactory();
        this.cityProductionAmountStrategy =
            strategies.getCityProductionAmountStrategy();
        this.productionUsageStrategy      = strategies.getProductionUsageStrategy();

        // Generate the world (aka list of tiles with cities and units).
        gameWorld = new WorldImpl(worldGenStrategy, unitFactory, cityFactory);
    }

    @Override
    public int getAge()
    {
        return ageStrategy.getAge();
    }

    @Override
    public CityModifiable getCityAt(Position p)
    {
        assertPositionWithinMap(p);

        return gameWorld.getCityAt(p);
    }

    @Override
    public Player getPlayerInTurn()
    {
        return playerInTurn;
    }

    @Override
    public TileModifiable getTileAt(Position p)
    {
        assertPositionWithinMap(p);

        return gameWorld.getTileAt(p);
    }

    @Override
    public UnitModifiable getUnitAt(Position p)
    {
        assertPositionWithinMap(p);

        return gameWorld.getUnitAt(p);
    }

    @Override
    public Player getWinner()
    {
        return winStrategy.getWinner(this);
    }

    @Override
    public void changeProductionInCityAt(Position p, String unitType)
    {
        CityModifiable c = getCityAt(p);
        // Test preconditions
        assert(c != null);
        c.setProduction(unitType);
    }

    @Override
    public void changeWorkForceFocusInCityAt(Position p, String balance)
    {
        CityModifiable c = getCityAt(p);
        // Test preconditions
        assert(c != null);
        c.setWorkforceFocus(balance);
    }

    @Override
    public void endOfTurn()
    {
        if (playerInTurn == Player.RED)
        {
            playerInTurn = Player.BLUE;
        }
        else
        {
            roundElapsed();

            playerInTurn = Player.RED;
        }
    }

    @Override
    public boolean moveUnit(Position from, Position to)
    {
        // Test preconditions
        assertPositionWithinMap(from);
        assertPositionWithinMap(to);

        assert(getUnitAt(from) != null);

        // Get the unit we're moving
        // Precondition states that the unit exists
        UnitModifiable unitFrom = gameWorld.getUnitAt(from);

        // Get the unit we're moving ontop of (if any)
        Tile           tTo    = getTileAt(to);
        UnitModifiable unitTo = gameWorld.getUnitAt(to);
        CityModifiable cityTo = gameWorld.getCityAt(to);

        // Ensure that we're allowed to move the unit
        // (ie. that the unit is ours (the player in turn)
        if (unitFrom.getOwner() != getPlayerInTurn())
        {
            return false;
        }

        // Ensure that the unit is not out of movement, aka. it is moveable.
        if (unitFrom.getMoveCount() < 1)
        {
            // TODO: Hardcoded to check for 1
            // TODO: One could possibly implement a dijkstra like algorithm
            // for movement.
            return false;
        }

        // If there's a unit, at the tile we're moving to, then ensure that
        // we can move there anyways.
        if (unitTo != null)
        {
            // Unit on the tile is ours, and therefore we can't move there,
            // as teamkilling and/or unit stacking is not allowed
            if (unitTo.getOwner() == getPlayerInTurn())
            {
                return false;
            }
            else // Unit is not ours, therefore we can attack it
            {
                // Battle commenses here.
                // Resolve the winning unit and swipe the tile of units,
                // so the winner can move here.
                unitFrom = battleStrategy.resolveBattleWinner(unitFrom, unitTo);

                gameWorld.removeUnitAt(to);
            }
        }

        // If there's a city, at the tile we're moving to, then ensure that
        // we take it, if it's not ours.
        if ((cityTo != null) && (cityTo.getOwner() != getPlayerInTurn()))
        {
            // Set the ownership to the attacking player.
            cityTo.setOwner(getPlayerInTurn());
        }

        // Ensure that we don't move to some invalid tile.
        if (!gameWorld.isWalkable(tTo))
        {
            return false;
        }

        // TODO: Movement reduction faked at 1
        unitFrom.modMoveCount(-1);

        return gameWorld.moveUnit(from, to);
    }

    @Override
    public void performUnitActionAt(Position p)
    {
        Unit u = gameWorld.getUnitAt(p);

        // Test preconditions
        assert(u != null);

        // If we do not own the unit, we cannot perform an Action with it.
        if (u.getOwner() != getPlayerInTurn())
        {
            return;
        }

        gameWorld.unitAction(p);
    }

    /**
     * Asserts that p is within the world map
     *     Compiling without assertions, should result in this function
     *     call being optimized away.
     * @param p the position in the world that must be checked.
     */
    private void assertPositionWithinMap(Position p)
    {
        assert(p.getRow() > -1);
        assert(p.getRow() < GameConstants.WORLDSIZE);
        assert(p.getColumn() > -1);
        assert(p.getColumn() < GameConstants.WORLDSIZE);
    }

    private void cityProduction()
    {
        // Loop though the current cities;
        // * Update their production.
        // * Build units
        //
        // Even though we're running though all the tiles, we're skipping
        // a lot of them, so the effective time getAllCities is not O(tiles),
        // but O(cities)
        for (Position p : gameWorld.getAllCities())
        {
            CityModifiable c = gameWorld.getCityAt(p);

            // Produce productions.
            int productionAmount =
                cityProductionAmountStrategy.calculateProductionAmount(p,
                    gameWorld);

            c.setProductionAmount(c.getProductionAmount() + productionAmount);

            // Run all our production strategy.
            int productionAmountUsed = productionUsageStrategy.useProduction(p,
                                           gameWorld, unitFactory);

            // Make the town pay for whatever
            c.setProductionAmount(c.getProductionAmount()
                                  - productionAmountUsed);
        }
    }

    /**
     * Internal worker, function that takes care of round specific events.
     * Should be called each time a round has elapsed.
     * (a round is defined a when all players have had a turn).
     */
    private void roundElapsed()
    {
        // Age the world a little.
        ageStrategy.advanceAge();
        cityProduction();

        for (Position p : gameWorld.getAllUnits())
        {
            // Unit moveablity
            UnitModifiable u = getUnitAt(p);

            // reset unit movement.
            u.resetMoves();
        }
    }

    @Override
    public WorldModifiable getWorld()
    {
        return gameWorld;
    }
}
