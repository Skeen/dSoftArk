package hotciv.framework.strategy;

import hotciv.framework.*;

public interface CityProductionAmountStrategy
{
    /**
     * Calculates the amount of producation a city does.
     * @param p the position of the city we're calculation for.
     * @param world the world object, such that we can read the nearby tiles, ect.
     * @return the amount of production the city is producing.
     */
    public int calculateProductionAmount(Position p, World w);
}
