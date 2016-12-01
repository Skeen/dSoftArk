package hotciv.variants.gammaciv;

import hotciv.framework.*;
import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;

import org.junit.*;
import static org.junit.Assert.*;

import hotciv.variants.defaultciv.TestHelpers;

public class TestGammaCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new GammaStrategyFactory();

        game = new GameImpl(strategyPackage);
        TestHelpers.seedGame(game);
    }

    @Test
    public void settlersActionShouldRemoveTheSettler()
    {
        Position settlerPosition = new Position(4, 3);

        // Check that the settler exists
        TestHelpers.assertUnitAt(settlerPosition);

        // Perform it's action
        game.performUnitActionAt(settlerPosition);

        // Check that the unit is gone!
        TestHelpers.assertNoUnitAt(settlerPosition);
    }

    @Test
    public void settlersActionShouldAddACityBelowTheSettler()
    {
        Position settlerPosition = new Position(4, 3);

        // Check that the settler exists
        TestHelpers.assertUnitAt(settlerPosition);

        // Perform it's action
        game.performUnitActionAt(settlerPosition);

        // Check that c city was spawned!
        TestHelpers.assertCityBuild(settlerPosition);
    }

    @Test
    public void citySpawnedBySettlersActionShouldHaveSameOwnerAsSettler()
    {
        Position settlerPosition = new Position(4, 3);

        // Check that the settler exists
        TestHelpers.assertUnitAt(settlerPosition);

        // Perform it's action
        game.performUnitActionAt(settlerPosition);

        // Check that c city was spawned!
        TestHelpers.assertCityBuild(settlerPosition);

        // Check that we own the city
        TestHelpers.assertCityOwner(settlerPosition, Player.RED);
    }

    @Test
    public void onlyPlayerInTurnShouldBeAbleToExecuteUnitAction()
    {
        // Let it be blue
        game.endOfTurn();

        Position settlerPosition = new Position(4, 3);

        // Check that the settler exists
        TestHelpers.assertUnitAt(settlerPosition);

        // Perform it's action
        game.performUnitActionAt(settlerPosition);

        // Check that c city was spawned!
        TestHelpers.assertNoCityBuild(settlerPosition);
    }

    @Test
    public void archersActionShouldDoubleDefensiveStrength()
    {
        Position archerPosition = new Position(2, 0);

        // Check that the archer exists
        TestHelpers.assertUnitAt(archerPosition);

        // Assert that it has doubled it's strength
        TestHelpers.assertUnitStrengthAfterAction(archerPosition, 2);
    }

    @Test
    public void archersActionShouldBeToggleAbleAndSoShouldTheDefensiveStrengthBuff()
    {
        Position archerPosition = new Position(2, 0);

        // Check that the archer exists
        TestHelpers.assertUnitAt(archerPosition);

        // Assert that it has doubled it's strength
        TestHelpers.assertUnitStrengthAfterAction(archerPosition, 2);

        // And halfed it again, after another UnitAction
        TestHelpers.assertUnitStrengthAfterAction(archerPosition, 0.5f);
    }

    @Test
    public void archersActionShouldMakeItImmovable()
    {
        Position archerPosition = new Position(2, 0);

        // Check that the archer exists
        TestHelpers.assertUnitAt(archerPosition);

        // Perform it's action
        game.performUnitActionAt(archerPosition);

        // It should not be able to move now!
        boolean couldMove = game.moveUnit(archerPosition, new Position(2, 1));

        assertEquals(
            "Archer should not be able to move, with ablility active.", false,
            couldMove);
    }


    
// TESTS FOUND TO BE SOMEHOW LOST IN A MERGE, MANUALLY MOVED IN from the original
    private void assertUnitStrengthAfterAction(Position p, float factor)
    {
        // Save it's before defensive strength
        Unit uBefore                 = game.getUnitAt(p);
        int  beforeDefensiveStrength = uBefore.getDefensiveStrength(game.getTileAt(p), game);

        // Perform it's action
        game.performUnitActionAt(p);

        // Save it's after defensive strength
        Unit uAfter                 = game.getUnitAt(p);
        int  afterDefensiveStrength = uAfter.getDefensiveStrength(game.getTileAt(p), game);

        assertEquals("Expected the archer to another defensive strength",
                     beforeDefensiveStrength * factor, afterDefensiveStrength, 1);
}

    private void assertCityBuild(Position p)
    {
        City c = game.getCityAt(p);

        assertNotNull("There should be a city at (" + p.getRow() + ", "
                      + p.getColumn() + ")", c);
    }

    private void assertNoCityBuild(Position p)
    {
        City c = game.getCityAt(p);

        assertNull("There shouldn't be a city at (" + p.getRow() + ", "
                   + p.getColumn() + ")", c);
    }

    private void assertUnitAt(Position p)
    {
        Unit u = game.getUnitAt(p);

        assertNotNull("There should be a unit at (" + p.getRow() + ", "
                      + p.getColumn() + ")", u);
    }

    private void assertNoUnitAt(Position p)
    {
        Unit u = game.getUnitAt(p);

        assertNull("There shouldn't be a unit at (" + p.getRow() + ", "
                   + p.getColumn() + ")", u);
    }

    private void assertCityOwner(Position p, Player expectedOwner)
    {
        City   c     = game.getCityAt(p);
        Player owner = c.getOwner();

        assertEquals("The city at (" + p.getRow() + ", " + p.getColumn()
                     + ") should be " + expectedOwner, expectedOwner, owner);
    }
}
