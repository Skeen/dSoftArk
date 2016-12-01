package hotciv.common;

import hotciv.framework.*;

public class TileImpl implements TileModifiable
{
    private CityModifiable city;
    private Position       position;
    private String         type;
    private UnitModifiable unit;

    // Private default constructor,
    // such that one cannot create a Tile like that.
    private TileImpl()
    {
    }

    public TileImpl(Position position, String type)
    {
        this.position = position;
        this.type     = type;

        // No units or cities by default.
        unit = null;
        city = null;
    }

    @Override
    public CityModifiable getCity()
    {
        return city;
    }

    @Override
    public Position getPosition()
    {
        return position;
    }

    @Override
    public String getTypeString()
    {
        return type;
    }

    @Override
    public UnitModifiable getUnit()
    {
        return unit;
    }

    @Override
    public void setCity(CityModifiable c)
    {
        city = c;
    }

    @Override
    public void setUnit(UnitModifiable u)
    {
        unit = u;
    }
}