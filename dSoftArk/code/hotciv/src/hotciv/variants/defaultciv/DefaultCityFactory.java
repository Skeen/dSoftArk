package hotciv.variants.defaultciv;

import hotciv.common.*;
import hotciv.framework.*;
import hotciv.framework.strategy.*;

import hotciv.framework.GameConstants;

public class DefaultCityFactory implements CityFactory
{
    @Override
    public CityModifiable spawnCity(Player owner)
    {
        return new CityImpl(owner, new DefaultCityProductionFocusStrategy(), new DefaultCitySizeStrategy());

    }
}
