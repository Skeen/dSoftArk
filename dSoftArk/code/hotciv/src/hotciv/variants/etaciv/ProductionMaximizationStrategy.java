package hotciv.variants.etaciv;

import hotciv.framework.*;

import java.util.*;

public class ProductionMaximizationStrategy
        extends AbstractAmountMaximizationStrategy
{
    Map<String, Integer> tileValues;

    public ProductionMaximizationStrategy()
    {
        tileValues = new HashMap();

        // Add the values
        tileValues.put(GameConstants.PLAINS, 0);
        tileValues.put(GameConstants.OCEANS, 0);
        tileValues.put(GameConstants.FOREST, 3);
        tileValues.put(GameConstants.HILLS, 2);
        tileValues.put(GameConstants.MOUNTAINS, 1);
    }

    @Override
    public Map<String, Integer> getValueMap()
    {
        return tileValues;
    }
}