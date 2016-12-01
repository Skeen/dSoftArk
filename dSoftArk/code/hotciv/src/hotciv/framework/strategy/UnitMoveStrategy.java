package hotciv.framework.strategy;

public interface UnitMoveStrategy
{
    public int getMoveCount();
    public void resetMoves();
    public void modMoveCount(int i);
}

