package hotciv.variants.etaciv;

import hotciv.framework.strategy.*;

public class EtaCitySizeStrategy implements CitySizeStrategy
{
    private int cityInhabitants;

    public EtaCitySizeStrategy()
    {
        cityInhabitants = 1;
    }

    @Override
    public int getSize()
    {
        return cityInhabitants;
    }

    @Override
    public void setSize(int i)
    {
        cityInhabitants = i;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
