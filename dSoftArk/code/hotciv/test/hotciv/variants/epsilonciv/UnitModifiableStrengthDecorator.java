package hotciv.variants.epsilonciv;

import hotciv.framework.Game;
import hotciv.framework.Player;
import hotciv.framework.Position;
import hotciv.framework.Tile;
import hotciv.framework.UnitModifiable;
import hotciv.framework.World;

/**
 * Decorator for setting the strength of a given unit.
 * Only used as a helper in the tests.
 */
public class UnitModifiableStrengthDecorator implements UnitModifiable
{
    private final int            nAttackStrength;
    private final int            nDefenseStrength;
    private final UnitModifiable unit;

    /**
     * The constructer sets up the variables to return in case
     * of getAttackStrength or getDefenseStrength.
     * All other methods refers to the unit.
     * @param unit The unit to decorate with new Strength values
     * @param nAttackStrength The value wanted returned from future calls
     * to getAttackStrength().
     * @param nDefenseStrength The value wanted returned from future calls
     * to getDefenseStrength
     */
    public UnitModifiableStrengthDecorator(UnitModifiable unit, int nAttackStrength,
                                    int nDefenseStrength)
    {
        this.unit             = unit;
        this.nAttackStrength  = nAttackStrength;
        this.nDefenseStrength = nDefenseStrength;
    }

    @Override
    public int getAttackingStrength(Tile tile, Game game)
    {
        return nAttackStrength;
    }

    @Override
    public int getDefensiveStrength(Tile tile, Game game)
    {
        return nDefenseStrength;
    }

    @Override
    public int getMoveCount()
    {
        return unit.getMoveCount();
    }

    @Override
    public Player getOwner()
    {
        return unit.getOwner();
    }

    @Override
    public String getTypeString()
    {
        return unit.getTypeString();
    }

    @Override
    public void modMoveCount(int i)
    {
        unit.modMoveCount(i);
    }

    @Override
    public void resetMoves()
    {
        unit.resetMoves();
    }

    @Override
    public void unitAction(World world, Position position)
    {
        unit.unitAction(world, position);
    }
}