package hotciv.common;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class UnitImpl implements UnitModifiable
{
    private Player owner;
    private String unitString;
    private UnitStrengthStrategy    unitStrengthStrategy;
    private UnitMoveStrategy        unitMoveStrategy;
    private UnitActionStrategy      unitActionStrategy;

    public UnitImpl(Player owner, String unitString, UnitStrengthStrategy unitStrengthStrategy,
                    UnitMoveStrategy unitMoveStrategy, UnitActionStrategy unitActionStrategy)
    {
        this.owner                = owner;
        this.unitString           = unitString;
        this.unitStrengthStrategy = unitStrengthStrategy;
        this.unitMoveStrategy     = unitMoveStrategy;
        this.unitActionStrategy   = unitActionStrategy;
    }

    @Override
    public String getTypeString()
    {
        return unitString;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }
    
    @Override
    public int getDefensiveStrength(Tile tile, Game game)
    {
        return unitStrengthStrategy.getDefensiveStrength(tile, game);
    }

    @Override
    public int getAttackingStrength(Tile tile, Game game)
    {
        return unitStrengthStrategy.getAttackingStrength(tile, game);
    }

    @Override
    public int getMoveCount()
    {
        return unitMoveStrategy.getMoveCount();
    }

    @Override
    public void resetMoves()
    {
        unitMoveStrategy.resetMoves();
    }

    @Override
    public void modMoveCount(int i)
    {
        unitMoveStrategy.modMoveCount(i);
    }

    @Override
    public void unitAction(World world, Position position)
    {
        unitActionStrategy.unitAction(world, position);
    }
}
