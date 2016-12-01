package hotciv.framework;

public interface CityModifiable extends City
{
    /**
     * Sets the cities owner, to 'owner'.
     * @param owner the player to control the city.
     */
    void setOwner(Player owner);

    /**
     * Set the type of unit this city is going to produce.
     * Precondition: String s should be a valid unitname.
     * see GameConstants for valid values.
     * @param s the string for the unit to be produced.
     */
    void setProduction(String s);

    /**
     * Sets the amount of production a city has accumulated.
     * @param amount the amount to be set.
     */
    void setProductionAmount(int amount);

    public void setWorkforceFocus(String s);
}
