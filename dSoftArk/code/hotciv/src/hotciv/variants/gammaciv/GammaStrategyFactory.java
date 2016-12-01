package hotciv.variants.gammaciv;

import hotciv.common.*;
import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.*;

class GammaStrategyFactory extends DefaultStrategyFactory
{
    @Override
    public UnitFactory getUnitFactory()
    {
        return new GammaUnitFactory();
    }
}
