package hotciv.variants.defaultciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;
import hotciv.common.*;


public class DefaultUnitProductionStrategy implements ProductionUsageStrategy
{
    public int useProduction(Position p, World w, UnitFactory unitFactory)
    {
        City c = w.getCityAt(p);
        // Check if a city has enough productions to build a unit, and if
        // that's the case, simply build that unit.
        int     unitCost          = getUnitCost(c);

        // Produce the unit, if requested to do so.
        if (unitCost != 0)
        {
            // Find the next free position clockwise, defaulting north.
            Position freeTile = findNextFreeTile(p, w);

            // Place the unit at the tile, at the newly found position
            UnitModifiable unit = unitFactory.spawnUnit(c.getProduction(), c.getOwner());
            ((WorldModifiable) w).setUnitAt(freeTile, unit);
        }   
        return unitCost;
    }
    
    /**
     * Finds the next free position.
     * (next position that is not populated with a unit, and is moveable).
     *  @param cityPosition the city we're working with's position.
     *  @return a tile, that is not holding a unit, and that is the next one, to
     *  the city. otherwise null if no such one exists.
     */
    private Position findNextFreeTile(Position cityPosition, World w)
    {
        // This algorithm is segment based, that is, it divides the coordinate
        // sets into different segments, and work it's way though each segment,
        // before moving to the next one.
        //
        // Using our data, here's an example of the segments.
        // ....6
        // .3326
        // .4016
        // .4555
        // What is noticable here is that after two segments of the same size,
        // the segment grows 1 in length, and each new segment, is turned 90*
        // degrees compared to the previous one. Note that segment 0 is an
        // exception to this rule, but it is not an issue.
        // (dX, dY) is a vector
        // (direction in which we move right now)
        // As we want to initially go north, we'll set dX = 0 (no x movement),
        // and dY = -1 (y movement = up = north)
        int dX = 0;
        int dY = -1;

        // length of current segment = 1
        int segmentLength = 1;

        // current position (x, y)
        // and a variable to hold how much of the current segment we passed so far
        int x             = 0;
        int y             = 0;
        int segmentPassed = 0;
        
        // Loop forever (until return is called internally)
        // Worst case = O(tiles), in which case we'll return null
        // TODO: Implement a test that does returns null, when no tile is valid
        // at all.
        while (true)
        {
            // Calculate the position we're at, and get it's tile
            Position p = new Position(cityPosition.getRow() + x,
                    cityPosition.getColumn() + y);

            // Validate position
            if((p.getRow() > -1) && (p.getRow() < GameConstants.WORLDSIZE) && (p.getColumn() > -1) && (p.getColumn() < GameConstants.WORLDSIZE))
            {
                Tile t = w.getTileAt(p);
                // If the Tile is non-null and walkable
                if ((t != null) && w.isWalkable(t))
                {
                    // And if there's no unit on the tile
                    Unit u = w.getUnitAt(p);

                    if (u == null)
                    {
                        // Then return the tile to be used.
                        return p;
                    }
                }
            }

            // Update our working coordinates, according to the 'direction' vector(dX, dY).
            x += dX;
            y += dY;

            // And reflect that we just moved one segment
            segmentPassed++;

            // If we passed the entire segment, it is time to turn and get ready for the next segment.
            if (segmentPassed == segmentLength)
            {
                // Obviously we havn't passed anything in the new segment yet.
                segmentPassed = 0;

                // Translate the vector, such that we're now turning 90* clockwise.
                // NOTE: These can be easily modified for counterclockwise if wanted.
                int temp = dY;

                dY = dX;
                dX = -temp;

                // Increase segment length if necessary
                // (if we have passed two segments, which we'll just check using dX)
                if (dX == 0)
                {
                    segmentLength++;
                }
            }
        }
    }

    private int getUnitCost(City c)
    {
        int     unitCost          = 0;
        // If we want to build a settler, and we got the 'cash', then set
        // produce to true, and set the cost. (as well as with legion, and archer).
        if ((c.getProduction() == GameConstants.SETTLER)
                && (c.getProductionAmount() >= GameConstants.SETTLER_COST))
        {
            unitCost          = GameConstants.SETTLER_COST;
        }
        else if ((c.getProduction() == GameConstants.LEGION)
                && (c.getProductionAmount() >= GameConstants.LEGION_COST))
        {
            unitCost          = GameConstants.LEGION_COST;
        }
        else if ((c.getProduction() == GameConstants.ARCHER)
                && (c.getProductionAmount() >= GameConstants.ARCHER_COST))
        {
            unitCost          = GameConstants.ARCHER_COST;
        }
        return unitCost;
    }
}


