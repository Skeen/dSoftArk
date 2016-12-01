package hotciv.variants.deltaciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;

import hotciv.variants.defaultciv.DefaultStrategyFactory;
import java.util.*;

class DeltaStrategyFactory extends DefaultStrategyFactory
{
    @Override
    public WorldGenStrategy getWorldGenStrategy()
    {
        return new DeltaWorldGenStrategy();
    }
}
