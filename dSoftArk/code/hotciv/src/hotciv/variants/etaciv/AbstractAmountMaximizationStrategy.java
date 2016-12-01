package hotciv.variants.etaciv;

import hotciv.framework.*;

import java.util.*;

public abstract class AbstractAmountMaximizationStrategy implements AmountMaximizationStrategy
{
    @Override
    public Tile getBestTile(Tile t1, Tile t2)
    {
        // Get the tiles production value
        int valueT1 = getTileValue(t1);
        int valueT2 = getTileValue(t2);

        // Find the one with the hightest and return that one.
        if(valueT1 > valueT2)
        {
            return t1;
        }
        else
        {
            return t2;
        }
        
    }

    public abstract Map<String, Integer> getValueMap();

    @Override
    public int getTileValue(Tile t)
    {
        // Get value map
        Map<String, Integer> valueMap = getValueMap();

        // Lookup the specific value based upon the type.
        String tileType = t.getTypeString();
        Integer value = valueMap.get(tileType);
        if(value != null)
        {
            // Autobox return
            return value;
        }

        // This should never be reached!
        assert(true);
        return 0;
    }
}