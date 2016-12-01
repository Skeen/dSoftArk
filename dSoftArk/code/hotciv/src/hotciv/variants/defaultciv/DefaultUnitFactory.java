package hotciv.variants.defaultciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;
import hotciv.common.*;

public class DefaultUnitFactory implements UnitFactory
{
    @Override
    public UnitModifiable spawnUnit(String unitString, Player owner)
    {
        assert(unitString != GameConstants.ARCHER);
        assert(unitString != GameConstants.LEGION);
        assert(unitString != GameConstants.SETTLER);

        UnitStrengthStrategy unitStrengthStrategy   = new DefaultUnitStrengthStrategy();
        UnitMoveStrategy     unitMoveStrategy       = new DefaultUnitMoveStrategy();
        UnitActionStrategy   unitActionStrategy     = new DefaultUnitActionStrategy();

        return new UnitImpl(owner, unitString, 
                            unitStrengthStrategy, unitMoveStrategy, unitActionStrategy);
    }
}
