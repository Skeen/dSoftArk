package hotciv.framework.strategy;

public interface AgeStrategy
{
    /**
     * Method to access the current age
     * @return age in the current round
     */
    public int getAge();

    /**
     * This function advances the age by an amount corresponding to 1 round.
     */
    public void advanceAge();
}
