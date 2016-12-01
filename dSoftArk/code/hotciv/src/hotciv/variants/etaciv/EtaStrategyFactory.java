package hotciv.variants.etaciv;

import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.DefaultStrategyFactory;

class EtaStrategyFactory extends DefaultStrategyFactory
{
    @Override
    public CityFactory getCityFactory()
    {
        return new EtaCityFactory();
    }

    @Override
    public CityProductionAmountStrategy getCityProductionAmountStrategy()
    {
        return new EtaCityProductionAmountStrategy();
    }

    @Override
    public ProductionUsageStrategy getProductionUsageStrategy()
    {
        return new EtaProductionUsageStrategy();
    }
}