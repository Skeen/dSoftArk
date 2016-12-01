package hotciv.framework.strategy;

import hotciv.framework.*;

public interface CityFactory
{
    CityModifiable spawnCity(Player owner);
}
