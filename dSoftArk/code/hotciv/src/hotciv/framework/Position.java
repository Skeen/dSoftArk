package hotciv.framework;

/**
 * Position on the world map.
 *
 * Responsibilities:
 * 1) Know a specific location (row,column).
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
public class Position
{
    protected int c;
    protected int r;

    /**
     * create a position.
     * @param r the row
     * @param c the column
     */
    public Position(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

    /**
     * get the column represented by this position.
     * @return the column.
     */
    public int getColumn()
    {
        return c;
    }

    /**
     * get the row represented by this position.
     * @return the row.
     */
    public int getRow()
    {
        return r;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }

        if (o.getClass() != Position.class)
        {
            return false;
        }

        Position other = (Position) o;

        return (r == other.r) && (c == other.c);
    }

    @Override
    public int hashCode()
    {
        // works ok for positions up to columns == 479
        return 479 * r + c;
    }

    @Override
    public String toString()
    {
        return "[" + r + "," + c + "]";
    }
}