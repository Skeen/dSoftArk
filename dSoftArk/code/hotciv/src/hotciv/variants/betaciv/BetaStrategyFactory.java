package hotciv.variants.betaciv;

import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.DefaultStrategyFactory;

class BetaStrategyFactory extends DefaultStrategyFactory
{
    @Override
    public WinStrategy getWinStrategy()
    {
        return new BetaWinStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy()
    {
        return new BetaAgeStrategy();
    }
}
