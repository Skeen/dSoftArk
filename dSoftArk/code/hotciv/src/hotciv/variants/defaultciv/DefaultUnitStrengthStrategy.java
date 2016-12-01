package hotciv.variants.defaultciv;
import hotciv.framework.Game;
import hotciv.framework.Tile;
import hotciv.framework.strategy.*;

public class DefaultUnitStrengthStrategy implements UnitStrengthStrategy
{
    @Override
    public int getDefensiveStrength(Tile tile, Game game)
    {
        return -1;
    }

    @Override
    public int getAttackingStrength(Tile tile, Game game)
    {
        return -1;
    }
}

