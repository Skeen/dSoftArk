package hotciv.variants.zetaciv;

import hotciv.framework.strategy.*;
import hotciv.variants.defaultciv.DefaultStrategyFactory;

class ZetaStrategyFactory extends DefaultStrategyFactory
{
    private ZetaWinStrategy winStrat;
    
    public ZetaStrategyFactory()
    {
        winStrat = new ZetaWinStrategy();
    }
    
    @Override
    public WinStrategy getWinStrategy()
    {
        return winStrat;
    }
    
    @Override
    public AgeStrategy getAgeStrategy()
    {
        return winStrat;
    }
    
    @Override
    public BattleStrategy getBattleStrategy()
    {
        return winStrat;
    }
}
