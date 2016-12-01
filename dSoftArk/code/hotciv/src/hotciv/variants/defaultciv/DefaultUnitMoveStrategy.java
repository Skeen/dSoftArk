package hotciv.variants.defaultciv;
import hotciv.framework.strategy.*;

public class DefaultUnitMoveStrategy implements UnitMoveStrategy
{
    final private int maxMoves  = 1;
    private int       moveCount = 1;

    @Override
    public int getMoveCount()
    {
        return moveCount;
    }

    @Override
    public void resetMoves()
    {
        moveCount = maxMoves;
    }

    @Override
    public void modMoveCount(int i)
    {
        moveCount = moveCount + i;
    }
}


