package hotciv.variants.etaciv;

import hotciv.framework.*;

import java.util.*;

public class FoodMaximizationStrategy extends AbstractAmountMaximizationStrategy
{
    Map<String, Integer> tileValues;
    public FoodMaximizationStrategy()
    {
        tileValues = new HashMap();
        // Add the values
        tileValues.put(GameConstants.PLAINS, 3);
        tileValues.put(GameConstants.OCEANS, 1);
        tileValues.put(GameConstants.FOREST, 0);
        tileValues.put(GameConstants.HILLS, 0);
        tileValues.put(GameConstants.MOUNTAINS, 0);
    }

    @Override
    public Map<String, Integer> getValueMap()
    {
        return tileValues;
    }
}


