package hotciv.variants.gammaciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;
import hotciv.variants.defaultciv.*;

public class GammaUnitFactory implements UnitFactory
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
        else if(unitString == GameConstants.ARCHER)
        {
            // Let's decorate it with FLOWERS!
            GammaArcherUnitActionStrategy strategy = new GammaArcherUnitActionStrategy(unitStrengthStrategy, unitMoveStrategy);
            unitStrengthStrategy = strategy;
            unitMoveStrategy     = strategy;
            unitActionStrategy   = strategy;
        }

        return new UnitImpl(owner, unitString, 
                            unitStrengthStrategy, unitMoveStrategy, unitActionStrategy);
    }
}
