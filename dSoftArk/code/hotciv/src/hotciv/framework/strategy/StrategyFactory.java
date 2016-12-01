package hotciv.framework.strategy;

public interface StrategyFactory
{
    public WinStrategy getWinStrategy();

    public AgeStrategy getAgeStrategy();

    public UnitFactory getUnitFactory();

    public WorldGenStrategy getWorldGenStrategy();

    public BattleStrategy getBattleStrategy();

    public CityFactory getCityFactory();

    public CityProductionAmountStrategy getCityProductionAmountStrategy();

    public ProductionUsageStrategy getProductionUsageStrategy();
}
