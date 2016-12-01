package hotciv.variants.defaultciv;

import hotciv.framework.CityModifiable;
import hotciv.framework.Unit;
import hotciv.framework.City;
import hotciv.framework.Position;
import hotciv.framework.Player;
import hotciv.framework.GameModifiable;
import static org.junit.Assert.*;

public class TestHelpers
{
    private static GameModifiable game;

    public static void seedGame(GameModifiable initGame)
    {
        game = initGame;
    }

    /**
     * Asserts that a specific player has won the game.
     * @param exceptedWinner  The player, we want to assert, that has won!
     */
    public static void assertWinner(Player exceptedWinner)
    {
        Player winner = game.getWinner();

        assertNotNull("Someone should have won at this point", winner);
        assertEquals(exceptedWinner + " should have won at this point",
                exceptedWinner, winner);
    }

    /**
     * Asserts that a city has been captured, by a move from 'from' to 'to'.
     * @param cityPosition  The position, from which we load the city, there
     * @param from  The from position, which should contain a unit.
     * @param to    The to position, which should contain a city.
     * @param expectedOwner The player expected to own the city after the attack.
     */
    public static void assertCityTakenByMove(Position from, Position to,
            Player expectedOwner)
    {
        String city = "City(" + to + ")";

        assertMoveUnit("We should have been able to attack " + city, from, to);
        assertCityOwnership(city + " should have been captured!", to,
                expectedOwner);
    }
    
    /**
     * Run the game for 'x' turns (until a round has elapsed)
     * A round is defined as the time from the player currently having the turn,
     * till it has the turn again.
     * Note; if the player will not get a turn again, the function will never return.
     */
    public static void runOneRound()
    {
        // Get the currently player in turn.
        Player inTurn = game.getPlayerInTurn();
        // And obviously end the round, such that we can start checking. 
        game.endOfTurn();

        while (true)
        {
            // Check that it is our turn again!
            // - And if it is, then a round has elapsed.
            if (inTurn == game.getPlayerInTurn())
            {
                return;
            }
            // Not our turn again yet, so keep going.
            else
            {
                game.endOfTurn();
            }
        }
    }

    /**
     * Run the game until a specific age
     * @param age The age to run until.
     * Note; the function runs till that age, and the current turn within that
     * age.
     * Note; if the age has passed, or will never occur,
     * the function will never return!
     */
    public static void runTillAge(int age)
    {
        while (true)
        {
            // The wanted age is achieved, return
            if (age == game.getAge())
            {
                return;
            }

            // Havn't gotten the wanted age yet, keep going.
            else
            {
                runOneRound();
            }
        }
    }

    /**
     * Asserts that a unit was moved correctly from 'from' to 'to', otherwise
     * 'error' is printed within a JUnit assertion error.
     * @param error The error string to be send to JUnit.
     * @param from  The from position, which should contain a unit.
     * @param to    The to position.
     */
    public static void assertMoveUnit(String error, Position from, Position to)
    {
        boolean moveAccepted = game.moveUnit(from, to);

        assertTrue(error, moveAccepted);
    }

    /**
     * Asserts that a city has a specific owner, if that's not the case
     * 'error' is printed within a JUnit assertion error.
     * @param error The error string to be send to JUnit.
     * @param cityPosition  The position, from which we load the city, there
     * should be a city at this position!.
     * @param expectedOwner The player, expected to own this city.
     */
    public static void assertCityOwnership(String error, Position cityPosition,
            Player expectedOwner)
    {
        City c = game.getCityAt(cityPosition);

        assertEquals(error, expectedOwner, c.getOwner());
    }

    
    public static void assertUnitStrengthAfterAction(Position p, float factor)
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
                     beforeDefensiveStrength * factor, afterDefensiveStrength,
                     1);
    }

    public static void assertCityBuild(Position p)
    {
        City c = game.getCityAt(p);

        assertNotNull("There should be a city at (" + p.getRow() + ", "
                      + p.getColumn() + ")", c);
    }

    public static void assertNoCityBuild(Position p)
    {
        City c = game.getCityAt(p);

        assertNull("There shouldn't be a city at (" + p.getRow() + ", "
                   + p.getColumn() + ")", c);
    }

    public static void assertUnitAt(Position p)
    {
        Unit u = game.getUnitAt(p);

        assertNotNull("There should be a unit at (" + p.getRow() + ", "
                      + p.getColumn() + ")", u);
    }

    public static void assertNoUnitAt(Position p)
    {
        Unit u = game.getUnitAt(p);

        assertNull("There shouldn't be a unit at (" + p.getRow() + ", "
                   + p.getColumn() + ")", u);
    }

    public static void assertCityOwner(Position p, Player expectedOwner)
    {
        City   c     = game.getCityAt(p);
        Player owner = c.getOwner();

        assertEquals("The city at (" + p.getRow() + ", " + p.getColumn()
                     + ") should be " + expectedOwner, expectedOwner, owner);
    }

    public static CityModifiable assertCityAtPosition(Position p)
    {
        CityModifiable c = game.getCityAt(p);
        assertNotNull("There should be a city at " + p, c);
        return c;
    }

    /**
     * Assert that the correct workforce is returned after being set.
     * @param c a city, which is to be tested with.
     * @param workForce, the workforce focus to be set, and checked.
     */
    public static void assertCorrectWorkforce(CityModifiable c, String workForce)
    {
        String workFocus = null;

        c.setWorkforceFocus(workForce);
        workFocus = c.getWorkforceFocus();
        assertNotNull("There should be a valid string in workFocus", workFocus);
        assertEquals("There should be a correct string in workFocus", workForce, workFocus); 
    }

    public static void assertDifferent(String s, int before, int after)
    {
        int difference = before - after;
        boolean isDifferent = false;
        if(difference != 0)
        {
            isDifferent = true;
        }
        assertTrue(s, isDifferent);
    }
}
