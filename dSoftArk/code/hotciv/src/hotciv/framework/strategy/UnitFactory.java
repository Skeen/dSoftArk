package hotciv.framework.strategy;

import hotciv.framework.*;

public interface UnitFactory
{
    /**
     * precondition: the unit string should be valid!
     */
    UnitModifiable spawnUnit(String unitString, Player owner);
}
