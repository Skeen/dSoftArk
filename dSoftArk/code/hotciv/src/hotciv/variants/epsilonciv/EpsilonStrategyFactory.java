package hotciv.variants.epsilonciv;

import hotciv.framework.World;
import hotciv.framework.strategy.BattleStrategy;
import hotciv.framework.strategy.UnitFactory;
import hotciv.framework.strategy.WinStrategy;

import hotciv.variants.defaultciv.DefaultStrategyFactory;

public class EpsilonStrategyFactory extends DefaultStrategyFactory
{
    private EpsilonBestOf5BattlesWinStrategy e;

    public EpsilonStrategyFactory()
    {
        e = new EpsilonBestOf5BattlesWinStrategy();
    }
    
    @Override
    public BattleStrategy getBattleStrategy()
    {
        return e;
    }

    @Override
    public WinStrategy getWinStrategy()
    {
        return e;
    }

    @Override
    public UnitFactory getUnitFactory()
    {
        return new EpsilonUnitFactory();
    }

}