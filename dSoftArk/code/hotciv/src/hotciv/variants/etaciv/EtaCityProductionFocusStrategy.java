package hotciv.variants.etaciv;

import hotciv.framework.strategy.*;

public class EtaCityProductionFocusStrategy
        implements CityProductionFocusStrategy
{
    String productionFocus;

    @Override
    public String getWorkforceFocus()
    {
        return productionFocus;
    }

    @Override
    public void setWorkforceFocus(String s)
    {
        productionFocus = s;
    }
}