package hotciv.framework;

public interface WorldModifiable extends World
{
    @Override
    public CityModifiable getCityAt(Position p);

    @Override
    public TileModifiable getTileAt(Position p);

    @Override
    public UnitModifiable getUnitAt(Position p);

    /**
     * removes the unit at position p
     * Precondition: Position p is a valid position in the world.
     */
    public void setUnitAt(Position p, UnitModifiable unit);

    public void setCityAt(Position p, Player owner);

    /**
     * Move unit from one tile to another.
     * World is only responsible for the movement, not checks if moving is valid.
     */
    public boolean moveUnit(Position from, Position to);

    /**
     * Removes the unit at position p.
     * Precondition: Position p is a valid position in the world.
     * Postcondition: getUnitAt(p) should return null after this function.
     * @param p The position to swipe for units.
     */
    public void removeUnitAt(Position p);

    /**
     * Perform actions for unit at p.
     * @return a boolean to tell whether action succeeded.
     */
    public boolean unitAction(Position p);
}
