package hotciv.variants.defaultciv;

import hotciv.framework.strategy.*;

public class DefaultStrategyFactory implements StrategyFactory
{
    @Override
    public WinStrategy getWinStrategy()
    {
        return new DefaultWinStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy()
    {
        return new DefaultAgeStrategy();
    }

    @Override
    public UnitFactory getUnitFactory()
    {
        return new DefaultUnitFactory();
    }

    @Override
    public WorldGenStrategy getWorldGenStrategy()
    {
        return new DefaultWorldGenStrategy();
    }

    @Override
    public CityFactory getCityFactory()
    {
        return new DefaultCityFactory();
    }

    @Override
    public CityProductionAmountStrategy getCityProductionAmountStrategy()
    {
        return new DefaultCityProductionAmountStrategy();
    }

    @Override
    public ProductionUsageStrategy getProductionUsageStrategy()
    {
        return new DefaultProductionUsageStrategy();
    }

    @Override
    public BattleStrategy getBattleStrategy() {
        return new DefaultBattleAttackerAllwaysWin();
    }
}

