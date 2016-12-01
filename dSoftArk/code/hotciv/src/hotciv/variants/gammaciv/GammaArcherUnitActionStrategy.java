package hotciv.variants.gammaciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class GammaArcherUnitActionStrategy
        implements UnitActionStrategy, UnitMoveStrategy, UnitStrengthStrategy
{
    private boolean              fortifyActive = false;
    private UnitMoveStrategy     unitMoveStrategy;
    private UnitStrengthStrategy unitStrengthStrategy;

    public GammaArcherUnitActionStrategy(
            UnitStrengthStrategy unitStrengthStrategy,
            UnitMoveStrategy unitMoveStrategy)
    {
        this.unitStrengthStrategy = unitStrengthStrategy;
        this.unitMoveStrategy     = unitMoveStrategy;
    }

    @Override
    public int getAttackingStrength(Tile tile, Game game)
    {
        return unitStrengthStrategy.getAttackingStrength(tile, game);
    }

    @Override
    public int getDefensiveStrength(Tile tile, Game game)
    {
        int defensiveStrength = unitStrengthStrategy.getDefensiveStrength(tile,
                                    game);

        if (fortifyActive)
        {
            return defensiveStrength * 2;
        }
        else
        {
            return defensiveStrength;
        }
    }

    @Override
    public int getMoveCount()
    {
        if (fortifyActive)
        {
            return 0;
        }
        else
        {
            return unitMoveStrategy.getMoveCount();
        }
    }

    @Override
    public void modMoveCount(int i)
    {
        unitMoveStrategy.modMoveCount(i);
    }

    @Override
    public void resetMoves()
    {
        unitMoveStrategy.resetMoves();
    }

    @Override
    public void unitAction(World world, Position position)
    {
        fortifyActive = !fortifyActive;
    }
}