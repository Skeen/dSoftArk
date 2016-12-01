package hotciv.variants.betaciv;

import hotciv.framework.*;
import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;
import org.junit.*;
import static org.junit.Assert.*;

import hotciv.variants.defaultciv.TestHelpers;

public class TestBetaCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new BetaStrategyFactory();
        game = new GameImpl(strategyPackage);
        TestHelpers.seedGame(game);
    }

    @Test
    public void blueCitiesShouldBeCaptureable()
    {
        // Move Red unit ontop of Blue City
        // And Check that we could do so.
        // And Check that the city was indeed taken.
        Position redArcherPosition = new Position(2, 0);
        Position blueCityPosition  = new Position(4, 1);

        TestHelpers.assertCityTakenByMove(redArcherPosition, blueCityPosition, Player.RED);
    }

    @Test
    public void redCitiesShouldBeCaptureable()
    {
        // End Red Turn, such that Blue can move
        game.endOfTurn();

        // Move Blue unit ontop of Red City
        // And Check that we could do so.
        // And Check that the city was indeed taken.
        Position blueLegionPosition = new Position(3, 2);
        Position redCityPosition    = new Position(1, 1);

        TestHelpers.assertCityTakenByMove(blueLegionPosition, redCityPosition, Player.BLUE);
    }

    @Test
    public void RedPlayerShouldWinWhenConqouringAllCities()
    {
        // Move Red unit ontop of Blue City
        // And Check that we could do so.
        // And Check that the city was indeed taken.
        Position redArcherPosition = new Position(2, 0);
        Position blueCityPosition  = new Position(4, 1);

        TestHelpers.assertCityTakenByMove(redArcherPosition, blueCityPosition, Player.RED);

        // Check that Red has won
        TestHelpers.assertWinner(Player.RED);
    }

    @Test
    public void BluePlayerShouldWinWhenConqouringAllCities()
    {
        // End Red Turn, such that Blue can move
        game.endOfTurn();

        // Move Blue unit ontop of Red City
        // And Check that we could do so.
        Position blueLegionPosition = new Position(3, 2);
        Position redCityPosition    = new Position(1, 1);

        TestHelpers.assertCityTakenByMove(blueLegionPosition, redCityPosition, Player.BLUE);

        // Check that Red has won
        TestHelpers.assertWinner(Player.BLUE);
    }

    @Test
    public void theFirst3900YearsShouldBeAt100YearsPerRound()
    {
        // Run 39 loops
        // (4000(startyear)-100(endyear))/100(rounds per year)
        for (int x = 0; x < 39; x++)
        {
            assertEquals(-4000 + 100 * x, game.getAge());
            TestHelpers.runOneRound();
        }
    }

    @Test
    public void theBirthOfCristSequence()
    {
        // Run rounds till we hit year 100BC
        TestHelpers.runTillAge(-100);
        assertEquals(-100, game.getAge());
        TestHelpers.runOneRound();
        assertEquals(-1, game.getAge());
        TestHelpers.runOneRound();
        assertEquals(1, game.getAge());
        TestHelpers.runOneRound();
        assertEquals(50, game.getAge());
    }

    @Test
    public void the50To1750ShouldIncrementWith50YearsPerRound()
    {
        // Run rounds till we hit year 50AD
        TestHelpers.runTillAge(50);

        // Run 34 loops
        // (50(startyear)-1750(endyear))/50(rounds per year)
        for (int x = 0; x < 34; x++)
        {
            assertEquals(50 + 50 * x, game.getAge());
            TestHelpers.runOneRound();
        }
    }

    @Test
    public void the1750To1900ShouldIncrementWith25YearsPerRound()
    {
        // Run rounds till we hit year 1750AD
        TestHelpers.runTillAge(1750);
            
        // Run 6 loops
        // (1750(startyear)-1750(endyear))/25(rounds per year)
        for (int x = 0; x < 6; x++)
        {
            assertEquals(1750 + 25 * x, game.getAge());
            TestHelpers.runOneRound();
        }
    }

    @Test
    public void the1900To1970ShouldIncrementWith5YearsPerRound()
    {
        // Run rounds till we hit year 1900AD
        TestHelpers.runTillAge(1900);

        // Run 14 loops
        // (1900(startyear)-1970(endyear))/5(rounds per year)
        for (int x = 0; x < 14; x++)
        {
            assertEquals(1900 + 5 * x, game.getAge());
            TestHelpers.runOneRound();
        }
    }

    @Test
    public void the1970AndAboveShouldIncrementWith1YearsPerRound()
    {
        // Run rounds till we hit year 1970AD
        TestHelpers.runTillAge(1970);

        // Run 100 loops to check.
        for (int x = 0; x < 100; x++)
        {
            assertEquals(1970 + 1 * x, game.getAge());
            TestHelpers.runOneRound();
        }
    }
}
