package hotciv.variants.epsilonciv;

import hotciv.framework.GameConstants;
import hotciv.framework.Player;
import hotciv.framework.strategy.CityFactory;
import hotciv.framework.strategy.UnitFactory;
import hotciv.framework.TileModifiable;

import hotciv.variants.defaultciv.DefaultWorldGenStrategy;

/**
 * Extends DefaultWorld, because to lazy to set up new world
 * Just adding lots of units for testing reasons.
 */
public class TestWorld extends DefaultWorldGenStrategy
{
    @Override
    public TileModifiable[][] createMap(UnitFactory unitFactory,
            CityFactory cityFactory)
    {
        TileModifiable[][] tiles = super.createMap(unitFactory, cityFactory);
        
        // Add 5 pairs of units to the world, where the winner doesn't matter.
        for (int i = 0; i < 5; i++)
        {
            // Place them next to eachother, so they can battle in first round
            // These tiles should be valid for unit placement.
            tiles[i+5][5].setUnit(
                    new UnitModifiableStrengthDecorator(
                            unitFactory.spawnUnit(GameConstants.LEGION, Player.RED),
                            10,10)
            );
            tiles[i+5][6].setUnit(
                    new UnitModifiableStrengthDecorator(
                            unitFactory.spawnUnit(GameConstants.SETTLER,Player.BLUE),
                            10,10));
        }
        
        // Add Units where Im certain of the battls:
        // Blue should win 3 battles here:
        for (int i = 0; i< 3; i++)
        {
            tiles[i+5][8].setUnit(
                    new UnitModifiableStrengthDecorator(
                            unitFactory.spawnUnit(GameConstants.ARCHER, Player.BLUE),
                            40,
                            40));
            tiles[i+5][9].setUnit(
                    unitFactory.spawnUnit(GameConstants.SETTLER, Player.RED));
        }

    /*
     * A legion att4
     * is in a city with 2 friendly adjacent units.
     * The combined attack strength of the legion is 
     * (4+1+1)*3
     */
        tiles[15][15].setCity(cityFactory.spawnCity(Player.BLUE));
        tiles[15][15].setUnit(
                        unitFactory.spawnUnit(GameConstants.LEGION, Player.BLUE
                ));
        tiles[14][15].setUnit(unitFactory.spawnUnit(GameConstants.SETTLER, Player.BLUE));
        tiles[15][14].setUnit(unitFactory.spawnUnit(GameConstants.SETTLER, Player.BLUE));

        
        // Make super killer red unit to kill a blue unit:
        tiles[14][14].setUnit(
                new UnitModifiableStrengthDecorator(
                        unitFactory.spawnUnit(GameConstants.ARCHER, Player.RED),
                        40,
                        40));

        return tiles;
    }


}