package hotciv.framework;

import java.util.*;

public interface World
{
    /**
     * Return all cities in the map.
     * @return all cities in the map.
     */
    public List<Position> getAllCities();

    /**
     * Return all units in the map.
     * @return all units in the map.
     */
    public List<Position> getAllUnits();

    /**
     * Return the city at position, if any.
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world.
     * @return the city at position p, if any.
     */
    public City getCityAt(Position p);

    /**
     * Return a specific tile at position
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world that must be returned.
     * @return the tile at position p.
     */
    public Tile getTileAt(Position p);  

    /**
     * Return the unit at position, if any.
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world.
     * @return the unit at position p, if any.
     */
    public Unit getUnitAt(Position p);


    /**
     * Determins whether a tile is walkable or not
     *  @param t the tile to be checked for walkability
     *  @return a boolean stating whether the tile is walkable or not.
     */
    public boolean isWalkable(Tile t);
}