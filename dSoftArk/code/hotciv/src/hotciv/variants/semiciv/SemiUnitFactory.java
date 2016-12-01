package hotciv.variants.semiciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;
import hotciv.variants.defaultciv.*;
import hotciv.variants.gammaciv.*;

public class SemiUnitFactory implements UnitFactory
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

        if(unitString == GameConstants.SETTLER)
        {
            unitActionStrategy = new GammaSettlerUnitActionStrategy();
        }

        return new UnitImpl(owner, unitString, 
                            unitStrengthStrategy, unitMoveStrategy, unitActionStrategy);
    }
}
