package hotciv.variants.etaciv;

import hotciv.framework.*;

public interface AmountMaximizationStrategy
{
    /**
     * Return the best of the two tiles t1, and t2, in terms of maximizing the
     * specific amount of production.
     * @param t1 one of the tiles to be compared.
     * @param t2 the other tile to be compared.
     * @return the best of the two tiles in terms of production.
     */
    public Tile getBestTile(Tile t1, Tile t2);

    /**
     * Return the value of the tile, using an internal value scheme.
     * @param t the tile to have it's value calculated.
     * @return the amount of production that tile does.
     */
    public int getTileValue(Tile t);
}
