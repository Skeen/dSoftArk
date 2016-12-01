package hotciv.variants.etaciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

import java.util.*;

public class EtaCityProductionAmountStrategy
        implements CityProductionAmountStrategy
{
    @Override
    public int calculateProductionAmount(Position p, World w)
    {
        // Get the city we're working on.
        City c = w.getCityAt(p);

        // Get a lists of adjacentTiles to the city
        List<Tile> adjacentTiles = getAdjacentTiles(p, w);

        // If we're focusing on production maximize for that.
        if (c.getWorkforceFocus() == GameConstants.productionFocus)
        {
            // Calculate the amount of production produced on the tiles outside
            // of the city; Note: we're passing c.getSize()-1 intentionally,
            // because we're forced to always hame one worker on the city tile.
            int productionMaximum = calculateMaximum(adjacentTiles,
                                        c.getSize() - 1,
                                        new ProductionMaximizationStrategy());

            // The city always produces 1
            productionMaximum = productionMaximum + 1;

            return productionMaximum;
        }

        // If we're focusing on food maximize for that.
        else if (c.getWorkforceFocus() == GameConstants.foodFocus)
        {
            // Check comments above.
            int foodMaximum = calculateMaximum(adjacentTiles, c.getSize() - 1,
                                               new FoodMaximizationStrategy());

            foodMaximum = foodMaximum + 1;

            return foodMaximum;
        }

        // This happens, if no workforce is set, or an invalid is set.
        assert(true);

        return 0;
    }

    // Responsible for getting a list of adjacent tiles to the city.
    private List<Tile> getAdjacentTiles(Position p, World w)
    {
        // A list of the adjacentTiles to the city.
        List<Tile> adjacentTiles = new ArrayList<Tile>();

        // Build the list, by running a square around the city.
        // x x x  ||  (-1, 1) ( 0, 1) ( 1, 1)
        // x c x  ||  (-1, 0) ( 0, 0) ( 1, 0)
        // x x x  ||  (-1,-1) ( 0,-1) ( 1,-1)
        for (int x = -1; x <= 1; x++)
        {
            for (int y = -1; y <= 1; y++)
            {
                if ((x == 0) && (y == 0))
                {
                    continue;
                }

                Position position = new Position(p.getRow() + x,
                                                 p.getColumn() + y);
                Tile t = w.getTileAt(position);

                adjacentTiles.add(t);
            }
        }

        return adjacentTiles;
    }

    // Responsable for maximizing the amount of production done, based upon a
    // strategy.
    private int calculateMaximum(List<Tile> adjacentTiles, int people,
                                 AmountMaximizationStrategy pams)
    {
        // The summation of production by people
        int productionAmountMaximum = 0;

        // Loop though all the persons in the city.
        for (int x = 0; x < people; x++)
        {
            // The tile we choose to pick.
            Tile takenTile = null;

            // Loop though all tiles to find the best one.
            for (Tile t : adjacentTiles)
            {
                // Everything is better than nothing!
                if (takenTile == null)
                {
                    takenTile = t;
                }

                // Optimize tile pick according to some maximization strategy.
                takenTile = pams.getBestTile(takenTile, t);
            }

            // We found the best tile at this point!
            adjacentTiles.remove(takenTile);

            // Add it's value to productionMaximum.
            productionAmountMaximum = productionAmountMaximum
                                      + pams.getTileValue(takenTile);
        }

        return productionAmountMaximum;
    }
}