package hotciv.variants.etaciv;

import hotciv.common.*;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class EtaCityFactory implements CityFactory
{
    @Override
    public CityModifiable spawnCity(Player owner)
    {
        return new CityImpl(owner, new EtaCityProductionFocusStrategy(),
                            new EtaCitySizeStrategy());
    }
}