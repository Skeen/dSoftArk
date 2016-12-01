package hotciv.framework;

public interface Game
{
    /**
     * return the age of the world. Negative numbers represent a world
     * age BC (-4000 equals 4000 BC) while positive numbers are AD.
     *  @return world age.
     */
    public int getAge();

    /**
     * return the city at position 'p' in the world.
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world.
     * @return the city at this position or null if no city here.
     */
    public City getCityAt(Position p);

    /**
     * return the player that is 'in turn', that is, is able to
     * move units and manage cities.
     * @return the player that is in turn
     */
    public Player getPlayerInTurn();

    /**
     * return a specific tile.
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world that must be returned.
     * @return the tile at position p.
     */
    public Tile getTileAt(Position p);

    /**
     * return the uppermost unit in the stack of units at position 'p'
     * in the world.
     * Precondition: Position p is a valid position in the world.
     * @param p the position in the world.
     * @return the unit that is at the top of the unit stack at position
     * p, OR null if no unit is present at position p.
     */
    public Unit getUnitAt(Position p);

    /**
     * return the player that has won the game.
     * @return the player that has won. If the game is still
     * not finished then return null.
     */
    public Player getWinner();

    public World getWorld();
}
