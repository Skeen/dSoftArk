package hotciv.variants.etaciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.DefaultProductionUsageStrategy;

public class EtaProductionUsageStrategy extends DefaultProductionUsageStrategy
{
    @Override
    public int useProduction(Position p, World w, UnitFactory u)
    {
        CityModifiable c = ((WorldModifiable) w).getCityAt(p);

        // Run the default for production focus.
        if (c.getWorkforceFocus() == GameConstants.productionFocus)
        {
            int unitCost = super.useProduction(p, w, u);

            if (unitCost != 0)
            {
                return unitCost;
            }
        }

        // Run the special one in case of food focus.
        else if (c.getWorkforceFocus() == GameConstants.foodFocus)
        {
            int citySize = c.getSize();

            if (citySize < 9)
            {
                int neededFood      = 5 + ((citySize) * 3);
                int foodAccumulated = c.getProductionAmount();

                if (neededFood < foodAccumulated)
                {
                    c.setSize(citySize + 1);
                    c.setProductionAmount(0);
                }
            }

            return 0;
        }

        // This should never happen!
        assert(true);

        return 0;
    }
}