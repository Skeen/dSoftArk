package hotciv.variants.defaultciv;

import hotciv.common.*;
import hotciv.framework.*;
import hotciv.framework.strategy.*;


public class DefaultCityProductionAmountStrategy implements CityProductionAmountStrategy
{
    public int calculateProductionAmount(Position p, World w)
    {
        return 6;
    }
}
