package hotciv.variants.epsilonciv;

import hotciv.framework.*;
import hotciv.framework.strategy.UnitStrengthStrategy;

/**
 *
 */
class EpsilonUnitStrengthStrategy implements UnitStrengthStrategy
{
    @Override
    public int getAttackingStrength(Tile tile, Game game)
    {
        return modifiers(tile, game, GameConstants.LEGION_ATTACK);
    }

    @Override
    public int getDefensiveStrength(Tile tile, Game game)
    {
        int res = modifiers(tile, game, GameConstants.ARCHER_DEFENSE);
        return res;
    }

    /*
     * Handle the like calculation in both getDefensiveStrength and 
     * getAttackingStrength.
     */
    private int modifiers(Tile tile, Game game, int initialValue)
    {
        int res = initialValue;
        res += getNumberOfAdjacentFriends(tile.getPosition(), tile.getUnit().getOwner(), game);
        res *= getTileModifier(tile);

        return res;
    }

    // Responsible for counting the adjacent units of
    // the given ownership to the position.
    private int getNumberOfAdjacentFriends(Position p, Player owner, Game game)
    {
        int res = 0;

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

                int newX = p.getRow() + x,
                    newY = p.getColumn() + y;

                // only within map:
                if (((newX >= 0) && (newY >= 0))
                        && ((newX < 16) && (newY < 16)))    
                {
                    Unit u = game.getUnitAt(new Position(newX, newY));

                    if ((u != null) && (u.getOwner() == owner))
                    {
                        res++;
                    }
                }
            }
        }

        return res;
    }

    private int getTileModifier(Tile tile)
    {
        // if ( world.getCityAt(p) != null) return 3;
        if (tile.getCity() != null)
        {
            return 3;
        }

        return 0;
    }
}