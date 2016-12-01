package hotciv.variants.semiciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;

import hotciv.variants.defaultciv.*;
import hotciv.variants.betaciv.*;
import hotciv.variants.gammaciv.*;
import hotciv.variants.deltaciv.*;
import hotciv.variants.epsilonciv.*;
import hotciv.variants.etaciv.*;

import java.util.*;

public class SemiStrategyFactory implements StrategyFactory
{
    private EpsilonBestOf5BattlesWinStrategy e;
    public SemiStrategyFactory()
    {
        e = new EpsilonBestOf5BattlesWinStrategy();
    }

    @Override
    public AgeStrategy getAgeStrategy()
    {
        return new BetaAgeStrategy();
    }

    @Override
    public UnitFactory getUnitFactory()
    {
        return new SemiUnitFactory();
    }

    @Override
    public WorldGenStrategy getWorldGenStrategy()
    {
        return new DeltaWorldGenStrategy();
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

