package hotciv.variants.etaciv;

import hotciv.framework.*;
import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;
import hotciv.variants.alphaciv.*;
import org.junit.*;
import static org.junit.Assert.*;

import hotciv.variants.defaultciv.TestHelpers;

public class TestEtaCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new EtaStrategyFactory();
        game = new GameImpl(strategyPackage);
        TestHelpers.seedGame(game);
    }

    @Test
    public void playersShouldBeAbleToSetProductionFocus()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
       
        // Assert that the workforce is changed as expected.
        TestHelpers.assertCorrectWorkforce(c, GameConstants.productionFocus);
        TestHelpers.assertCorrectWorkforce(c, GameConstants.foodFocus);
    }

    @Test
    public void citiesShouldProduceAtTheEndOfARound()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        c.setWorkforceFocus(GameConstants.productionFocus);

        int beforeProductionAmount = c.getProductionAmount();
        TestHelpers.runOneRound();
        int afterProductionAmount = c.getProductionAmount();
        TestHelpers.assertDifferent("ProductionAmount should have changed!", beforeProductionAmount, afterProductionAmount);
    }

    @Test
    public void citiesShouldAlwaysMaximizeProduction()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        setCitySizeAndWorkforceFocus(c, 4, GameConstants.productionFocus);
        // Adjacent to the city is;
        // 5 plains, 1 ocean, 1 hill and 1 mountain.
        // With 4 persons that focus on production that yields:
        // city + hill + mountain + random || for maximum production.
        // 1    + 2    + 1        + 0      == 4
        assertProductionAmount(c, 4);
    }

    @Test
    public void citiesShouldAlwaysMaximizeFood()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        setCitySizeAndWorkforceFocus(c, 4, GameConstants.foodFocus);
        // === || === Same as above === || ===
        // With 4 persons that focus on food that yields:
        // city + plains * 3               || for maximum food.
        // 1    + 3      * 3               == 10
        assertProductionAmount(c, 10);
    }

    @Test
    public void citiesShouldIncrease1InSizeWhenEnoughFoodIsAccumulated()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        setCitySizeAndWorkforceFocus(c, 4, GameConstants.foodFocus);
        // Previous tests assert that we're producing 10food per round!
        // In this world setup! - This means we need 5+(4*3) food expand city
        // size by 1.
        // 5+(4*3) = 17 = 2 rounds
        assertCityGrowth(c, 5, 2);
        assertCityGrowth(c, 6, 2);
    }

    @Test
    public void citiesShouldResetFoodAfterIncreaseInSize()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        setCitySizeAndWorkforceFocus(c, 4, GameConstants.foodFocus);
        assertCityGrowth(c, 5, 2);
        int productionLeft = c.getProductionAmount();
        assertEquals("Food should be reset.", 0, productionLeft);
    }

    @Test
    public void citiesShouldNeverExceedASizeOf9()
    {
        Position cityPosition = new Position(1,1); 
        CityModifiable c = TestHelpers.assertCityAtPosition(cityPosition);
        setCitySizeAndWorkforceFocus(c, 9, GameConstants.foodFocus);
        // 5+(9*3) = 32 @ 10/round = 4 rounds
        // Test that the city is still size 9 after 4 rounds.
        for(int x=0; x<4/*rounds*/; x++)
        {
             TestHelpers.runOneRound();
        }
        int citySize = c.getSize();
        assertEquals("The city should still be size 9!", 9, citySize);
    }

    private void assertCityGrowth(City c, int size, int rounds)
    {
        for(int x=0; x<rounds; x++)
        {
             TestHelpers.runOneRound();
        }
        int citySize = c.getSize();
        assertEquals("The city should have grown in size!", size, citySize);
    }

    private void assertProductionAmount(City c, int amount)
    {
        TestHelpers.runOneRound();
        int productionAmount = c.getProductionAmount();
        assertEquals("The city at 1,1 should produce:", amount, productionAmount);
    }

    private void setCitySizeAndWorkforceFocus(CityModifiable c, int size, String focus)
    {
        c.setWorkforceFocus(focus);
        c.setSize(size);
    }
}
