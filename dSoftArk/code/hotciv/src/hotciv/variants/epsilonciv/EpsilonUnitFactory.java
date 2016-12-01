package hotciv.variants.epsilonciv;

import hotciv.common.*;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.*;
import hotciv.variants.gammaciv.GammaArcherUnitActionStrategy;
import hotciv.variants.gammaciv.GammaSettlerUnitActionStrategy;

/**
 * At creation the only difference to GammaCivUnitFactory is 
 * the unitStrengthStrategy
 */
public class EpsilonUnitFactory implements UnitFactory
{
    @Override
    public UnitModifiable spawnUnit(String unitString, Player owner)
    {
        assert(unitString != GameConstants.ARCHER);
        assert(unitString != GameConstants.LEGION);
        assert(unitString != GameConstants.SETTLER);

        UnitStrengthStrategy unitStrengthStrategy =
            new EpsilonUnitStrengthStrategy();
        UnitMoveStrategy   unitMoveStrategy   = new DefaultUnitMoveStrategy();
        UnitActionStrategy unitActionStrategy = new DefaultUnitActionStrategy();

        if (unitString == GameConstants.SETTLER)
        {
            unitActionStrategy = new GammaSettlerUnitActionStrategy();
        }
        else if (unitString == GameConstants.ARCHER)
        {
            // Let's decorate it with FLOWERS!
            GammaArcherUnitActionStrategy strategy =
                new GammaArcherUnitActionStrategy(unitStrengthStrategy,
                    unitMoveStrategy);

            unitStrengthStrategy = strategy;
            unitMoveStrategy     = strategy;
            unitActionStrategy   = strategy;
        }

        return new UnitImpl(owner, unitString, unitStrengthStrategy,
                            unitMoveStrategy, unitActionStrategy);
    }
}