package hotciv.variants.defaultciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;
import hotciv.common.*;


public class DefaultProductionUsageStrategy implements ProductionUsageStrategy
{
    private DefaultUnitProductionStrategy defaultUnitProductionStrategy;

    public DefaultProductionUsageStrategy()
    {
        defaultUnitProductionStrategy = new DefaultUnitProductionStrategy();
    }

    public int useProduction(Position p, World w, UnitFactory u)
    {
        // Just implement the unit behavier
        return defaultUnitProductionStrategy.useProduction(p,w,u);
    }
}

