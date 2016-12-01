package hotciv.common;

import hotciv.framework.*;
import hotciv.framework.strategy.*;
import java.util.*;

public class WorldImpl implements WorldModifiable
{
    private TileModifiable[][] tiles;
    private List<Position> cityPos;
    private List<Position> unitTiles;

    private CityFactory cityFactory;

    public WorldImpl(WorldGenStrategy strat, UnitFactory unitFactory, CityFactory cityFactory)
    {
        this.cityFactory = cityFactory;
            
        // Get strategy to create the world.
        tiles = strat.createMap(unitFactory, cityFactory);
        
        // Fill the list of cities.
        cityPos = new ArrayList<Position>();
        for (int x = 0; x < tiles.length; x++)
        {
            for (int y = 0; y < tiles[x].length; y++)
            {
                Position p = new Position(x,y);
                TileModifiable t = getTileAt(p);
                City c = t.getCity();
                if(c != null)
                {
                    cityPos.add(p);
                }
            }
        }
        
        // Fill the list of units.
        
        unitTiles = new ArrayList<Position>();
        for (int x = 0; x < tiles.length; x++)
        {
            for (int y = 0; y < tiles[x].length; y++)
            {
                Position p = new Position(x,y);

                TileModifiable t = getTileAt(new Position(x,y));
                Unit u = t.getUnit();
                if(u != null)
                {
                    unitTiles.add(p);
                }
            }
        }
    }

    @Override
    public TileModifiable getTileAt(Position p)
    {
        return tiles[p.getRow()][p.getColumn()];
    }

    @Override
    public UnitModifiable getUnitAt(Position p)
    {
        TileModifiable t = getTileAt(p);
        UnitModifiable u = t.getUnit();
        return u;
    }

    @Override
    public CityModifiable getCityAt(Position p)
    {
        TileModifiable t = getTileAt(p);
        CityModifiable c = t.getCity();
        return c;
    }

    @Override
    public List<Position> getAllCities()
    {
        return cityPos;
    }

    @Override
    public List<Position> getAllUnits()
    {
        return unitTiles;
    }
    
    @Override
    public boolean unitAction(Position p)
    {
        UnitModifiable u = getUnitAt(p);
        u.unitAction(this, p);
        return true;
    }
    
    @Override
    public void setUnitAt(Position p, UnitModifiable unit)
    {
        TileModifiable t = getTileAt(p);
        Unit u = t.getUnit();
        
        unitTiles.remove(p);
        
        if(unit != null)
            unitTiles.add(p);
        
        t.setUnit(unit);
    }

    @Override
    public void setCityAt(Position p, Player owner)
    {
        TileModifiable t = getTileAt(p);
        
        if(owner == null)
        {
            cityPos.remove(p);
            return;
        }
        else if( !cityPos.contains(p) )
        {
            cityPos.add(p);
        }
        
        t.setCity(cityFactory.spawnCity(owner));
    }
    
    @Override
    public void removeUnitAt(Position p)
    {
        setUnitAt(p,null);
    }

    @Override
    public boolean isWalkable(Tile t)
    {
        // Comparison uses == because of comparison of GameConstants
        if ((t.getTypeString() == GameConstants.MOUNTAINS)
                || (t.getTypeString() == GameConstants.OCEANS))
        {
            return false;
        }
        return true;
    }

    @Override
    public boolean moveUnit(Position from, Position to)
    {
        UnitModifiable u = getUnitAt(from);
        // Do the actual unit movement
        setUnitAt(to, u);
        removeUnitAt(from);

        return true;
    }
}
