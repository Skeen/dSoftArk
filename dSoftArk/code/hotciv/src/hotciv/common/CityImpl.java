package hotciv.common;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class CityImpl implements CityModifiable
{
    // The player who's owning the city.
    private Player owner;

    // The unit being produced by the city.
    private String produces;

    // The current workForceFocus of the city.
    private String workForceFocus;

    // The amount of accumulated production.
    private int productionAmount;

    // The production focus strategy for the city.
    private CityProductionFocusStrategy cityProductionFocusStrategy;

    // The number of people who live in the city.
    private CitySizeStrategy citySizeStrategy;

    // Private default constructor,
    // such that one cannot create a city like that.
    private CityImpl()
    {
    }

    public CityImpl(Player owner, 
            CityProductionFocusStrategy cityProductionFocusStrategy, 
            CitySizeStrategy citySizeStrategy)
    {
        this.owner = owner;
        this.cityProductionFocusStrategy = cityProductionFocusStrategy;
        this.citySizeStrategy = citySizeStrategy;

        // The production amount defaults to zero.
        productionAmount = 0;
    }

    @Override
    public Player getOwner()
    {
        return owner;
    }

    @Override
    public int getSize()
    {
        return citySizeStrategy.getSize();
    }

    @Override
    public void setSize(int i)
    {
        citySizeStrategy.setSize(i);
    }

    @Override
    public String getProduction()
    {
        return produces;
    }

    @Override
    public int getProductionAmount()
    {
        return productionAmount;
    }

    @Override
    public String getWorkforceFocus()
    {
        return cityProductionFocusStrategy.getWorkforceFocus();
    }

    @Override
    public void setWorkforceFocus(String s)
    {
        cityProductionFocusStrategy.setWorkforceFocus(s);
    }

    @Override
    public void setOwner(Player owner)
    {
        this.owner = owner;
    }

    @Override
    public void setProduction(String s)
    {
        // Assert that a correct unit is begin passed to us.
        // as stated in the precondition.
        assert(s.equals(GameConstants.LEGION) || s.equals(GameConstants.ARCHER)
               || s.equals(GameConstants.SETTLER));

        this.produces = s;
    }

    @Override
    public void setProductionAmount(int amount)
    {
        productionAmount = amount;
    }
}
