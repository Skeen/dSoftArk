package hotciv.framework;

public interface UnitModifiable extends Unit
{
    /**
     * Modify the move counter according to the value i.
     * Mostly to be used to reduce the movement left, on the movecounter.
     * @param i the value the move counter should be modified with.
     */
    void modMoveCount(int i);

    /**
     * Reset the move counter, to its maximum.
     * Should be called at the start/end of each turn,
     * such that the move counter is always reset.
     */
    void resetMoves();

    void unitAction(World world, Position position);
}