package hotciv.variants.betaciv;

import hotciv.framework.*;

import hotciv.framework.strategy.*;

public class BetaWinStrategy implements WinStrategy
{
    @Override
    public Player getWinner(Game game)
    {
        Player winner = null;

        for (int x = 0; x < GameConstants.WORLDSIZE; x++)
        {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++)
            {
                Position   position = new Position(x, y);
                City c        = game.getCityAt(position);

                // No city at position, just continue ensureing O(cities)
                if (c == null)
                {
                    continue;
                }

                // First city, assume this player is the winner
                if (winner == null)
                {
                    winner = c.getOwner();
                }

                // Woops, this city wasn't owned by the 'winner' so he isn't
                // actually a winner yet :o
                else if (winner != c.getOwner())
                {
                    return null;
                }

                // If a city doesn't hit any of the if's it's just another city,
                // from the winning player! And we'll just keep searching.
            }
        }
        
        return winner;
    }
}
