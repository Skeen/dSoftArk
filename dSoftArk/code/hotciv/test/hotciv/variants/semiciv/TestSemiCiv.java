package hotciv.variants.semiciv;

import hotciv.framework.*;

import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;

import org.junit.*;

import static org.junit.Assert.*;

public class TestSemiCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new SemiStrategyFactory();

        game = new GameImpl(strategyPackage);
    }
    
    
}
