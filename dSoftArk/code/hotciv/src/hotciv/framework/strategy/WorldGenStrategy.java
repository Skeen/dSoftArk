package hotciv.framework.strategy;

import hotciv.framework.*;

public interface WorldGenStrategy
{
    /**
     * Return a List of tiles, created map
     * Responsible for tile, unit and city placements.
     * @return a list of tiles, to be modified.
     */
    public TileModifiable[][] createMap(UnitFactory unitFactory, CityFactory cityFactory);
}
