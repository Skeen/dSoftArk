package hotciv.framework;

/**
 * Tile represents a single territory tile of a given type.
 *
 * Responsibilities:
 * 1) Know its type and position on the board.
 *
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com
 */
public interface Tile
{
    /**
     * Get the current unit at the city if any.
     * @return returns the city at the tile, or null if no such one exists.
     */
    public City getCity();

    /**
     * return position of this tile on the board.
     * @return position of tile.
     */
    public Position getPosition();

    /**
     * return the tile type as a string. The set of
     * valid strings are defined by the graphics
     * engine, as they correspond to named image files.
     * @return the type type as string
     */
    public String getTypeString();

    /**
     * Get the current unit at the tile if any.
     * @return returns the unit at the tile, or null if no such one exists.
     */
    public Unit getUnit();
}