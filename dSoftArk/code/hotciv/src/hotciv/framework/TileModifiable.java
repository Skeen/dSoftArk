package hotciv.framework;

public interface TileModifiable extends Tile
{
    @Override
    public CityModifiable getCity();

    @Override
    public UnitModifiable getUnit();

    /**
     * Set the city of the tile to 'city'
     * If a city is already present it is simply replaced.
     * @param city the city to be set instead of current one.
     */
    public void setCity(CityModifiable city);

    /**
     * Set the unit of the tile to 'unit'
     * If a unit is already present it is simply replaced.
     * @param unit the unit to be set instead of current one.
     */
    public void setUnit(UnitModifiable unit);
}