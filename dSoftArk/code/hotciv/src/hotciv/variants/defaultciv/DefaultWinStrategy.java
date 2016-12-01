package hotciv.variants.defaultciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;

public class DefaultWinStrategy implements WinStrategy
{
    @Override
    public Player getWinner(Game game)
    {
        if (game.getAge() < -3000)
        {
            return null;
        }
        else
        {
            return Player.RED;
        }
    }
}
