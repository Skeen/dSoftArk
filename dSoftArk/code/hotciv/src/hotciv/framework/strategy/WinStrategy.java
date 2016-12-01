package hotciv.framework.strategy;

import hotciv.framework.*;

public interface WinStrategy
{
    /**
     * Return the player that has won the game.
     * @param game the game to calculate the winner on.
     * @return the player that has won. If the game is 
     * still not finished then return null.
     */
    public Player getWinner(Game game);
}
