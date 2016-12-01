package hotciv.framework.strategy;

import hotciv.framework.Game;
import hotciv.framework.Tile;

public interface UnitStrengthStrategy
{
    public int getDefensiveStrength(Tile tile, Game game);

    /**
     * Precondition: There is a unit on the tile.
     * @param tile The tile of a unit preparing to attack some other tile.
     * @param game State of the game, to find adjacant friends.
     * @return 
     */
    public int getAttackingStrength(Tile tile, Game game);
}
