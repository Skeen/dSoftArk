package hotciv.variants.alphaciv;

import hotciv.framework.strategy.StrategyFactory;
import hotciv.framework.*;
import hotciv.framework.strategy.UnitFactory; 
import hotciv.common.*;

import hotciv.variants.defaultciv.DefaultCityProductionFocusStrategy;
import hotciv.variants.defaultciv.DefaultCitySizeStrategy;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Skeleton class for AlphaCiv test cases
 *
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com
 */
public class TestAlphaCiv
{
    private GameModifiable game;

    /** Fixture for alphaciv testing. */

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new AlphaStrategyFactory();

        game = new GameImpl(strategyPackage);
    }

    // test
    @Test
    public void positionShouldhaveColAndRowFromConstructor()
    {
        Position p = new Position(4, 1);

        assertEquals("Position (4,1) should have row == 4", p.getRow(), 4);
        assertEquals("Position (4,1) should have column == 1", p.getColumn(), 1);
    }

    // tests that the city at position (1,1) is not null
    @Test
    public void shouldHaveCityAt1_1()
    {
        City c = game.getCityAt(new Position(1, 1));

        assertNotNull("There should be a city at (1,1)", c);
    }

    // tests that the city at position (1,1) is a valid city, which is owned by the red player.
    @Test
    public void shouldHaveRedCityAt1_1()
    {
        City c = game.getCityAt(new Position(1, 1));

        assertNotNull("There should be a city at (1,1)", c);

        Player p = c.getOwner();

        assertEquals("City at (1,1) should be owned by red", Player.RED, p);
    }

    // Test that there is a blue city at 4,1
    @Test
    public void shouldHaveBlueCityAt4_1()
    {
        City c = game.getCityAt(new Position(4, 1));

        assertNotNull("There should be a city at (4,1)", c);

        Player p = c.getOwner();

        assertEquals("City at (4,1) should be owned by blue", Player.BLUE, p);
    }

    // Tests that the first player is the red player.

    /** First turn is red */
    @Test
    public void firstPlayerIsTheREDPlayer()
    {
        // first turn because setUp() is run before each test.
        Player p = game.getPlayerInTurn();

        assertEquals("First player should be the RED player", p, Player.RED);
    }

    // Tests that the tile at (0,0) returns a valid tile
    @Test
    public void shouldReturnValidTileOnGetTileAt0_0()
    {
        assertNotNull("Should return a valid tile on getTileAt", game.getTileAt(new Position(0, 0)));
    }

    // Tests that the tile at (1,0) is oceans.
    @Test
    public void shouldHaveOceanAtTile1_0()
    {
        Tile   t    = game.getTileAt(new Position(1, 0));
        String type = t.getTypeString();

        assertEquals("Tile should be Ocean at (1,0)", type, GameConstants.OCEANS);
    }

    // Tests that new cities has a population of 1
    @Test
    public void shouldHavePopulation1fACity()
    {
        City c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);
        int size = c.getSize();

        assertEquals("New City should have population of 1 M", size, 1);

        c    = new CityImpl(Player.RED,
                new DefaultCityProductionFocusStrategy(), 
                new DefaultCitySizeStrategy()
                );
        assertEquals("New constructed City should have population of 1 M", c.getSize(), 1);
    }

    // Tests that the turn after Red is Blue.
    @Test
    public void shouldBeBlueTurnAfterRed()
    {
        game.endOfTurn();

        Player p = game.getPlayerInTurn();

        assertEquals("Should be blue turn after red", p, Player.BLUE);
    }

    // Tests that Red gets the turn after Blue
    @Test
    public void shouldBeRedAfterBlue()
    {
        game.endOfTurn();
        game.endOfTurn();

        Player p = game.getPlayerInTurn();

        assertEquals("Should be Red after Blue", p, Player.RED);
    }

    // Tests that game starts at age 4000 BC
    @Test
    public void shouldStartAt4000BC()
    {
        int i = game.getAge();

        assertEquals("Should start at age -4000", i, -4000);
    }

    // Tests that the age advances by 100 each round
    @Test
    public void shouldAdvance100EachRound()
    {
        game.endOfTurn();
        game.endOfTurn();

        int i = game.getAge();

        assertEquals("Should be age -3900 at second round", i, -3900);
    }

    // Tests that there is no winner at start of game
    @Test
    public void noWinnerAtStart()
    {
        Player p = game.getWinner();

        assertNull("Should be no winner at start of game", p);
    }

    // Tests that Red wins at age 3000 BC, 20 turns meaning 10 rounds, 1000 years.
    @Test
    public void redShouldWinAt3000BC()
    {
        for (int i = 0; i < 20; i++) {
            game.endOfTurn();
        }

        Player p = game.getWinner();

        assertEquals("Red Should win at 3000BC", p, Player.RED);
    }

    // Tests that the tile at (2,2) is a mountain.
    @Test
    public void shouldHaveMountainAtTile2_2()
    {
        Tile   t    = game.getTileAt(new Position(2, 2));
        String type = t.getTypeString();

        assertEquals("Tile should be Mountain at (2,2)", type, GameConstants.MOUNTAINS);
    }

    // Tests that a request for the tile at (3,3) does indeed yield a tile
    // at 3.3.
    @Test
    public void tileAt3_3shouldBeAt3_3()
    {
        Tile     t = game.getTileAt(new Position(3, 3));
        Position p = t.getPosition();

        assertEquals("Tile row should be 3", p.getRow(), 3);
        assertEquals("Tile column should be 3", p.getColumn(), 3);
    }

    // Tests that a request for the tile at (3,2) does indeed yield a tile
    // at 3.2.
    @Test
    public void tileAt3_2shouldBeAt3_2()
    {
        Tile     t = game.getTileAt(new Position(3, 2));
        Position p = t.getPosition();

        assertEquals("Tile row should be 3", p.getRow(), 3);
        assertEquals("Tile column should be 2", p.getColumn(), 2);
    }

    // There should always be returned a valid tile.
    @Test
    public void checkThatAValidTileIsAlwaysReturned()
    {
        for (int x = 0; x < GameConstants.WORLDSIZE; x++) {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++) {
                Tile t = game.getTileAt(new Position(x, y));

                assertNotNull("A valid tile, should be return by getTileAt(" + x + ", " + y + ")", t);
            }
        }
    }

    // Every tile, should always return the correct position
    @Test
    public void checkThatEveryTileHasItsCorrectPosition()
    {
        for (int x = 0; x < GameConstants.WORLDSIZE; x++) {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++) {
                Tile     t = game.getTileAt(new Position(x, y));
                Position p = t.getPosition();

                assertEquals("Tile row should be " + x, p.getRow(), x);
                assertEquals("Tile column should be " + y, p.getColumn(), y);
            }
        }
    }

    // Tests that the tile at (0,1) is a hill.
    @Test
    public void shouldHaveHillsAtTile0_1()
    {
        Tile   t    = game.getTileAt(new Position(0, 1));
        String type = t.getTypeString();

        assertEquals("Tile should be Hills at (0,1)", type, GameConstants.HILLS);
    }

    // Tests that the tile at (1,1) is a plain.
    @Test
    public void shouldHavePlainsAtTile1_1()
    {
        Tile   t    = game.getTileAt(new Position(1, 1));
        String type = t.getTypeString();

        assertEquals("Tile should be Plains at (1,1)", type, GameConstants.PLAINS);
    }

    // Every tile, should always return the correct position
    @Test
    public void checkThatEveryTileIsAPlaneExcept1_0z0_1z2_2()
    {
        for (int x = 0; x < GameConstants.WORLDSIZE; x++) {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++) {
                if (((x == 1) && (y == 0)) || ((x == 0) && (y == 1)) || ((x == 2) && (y == 2))) {
                    continue;
                }

                Tile   t    = game.getTileAt(new Position(x, y));
                String type = t.getTypeString();

                assertEquals("Tile should be Plains at (" + x + ", " + y + ")", type, GameConstants.PLAINS);
            }
        }
    }

    // Tests that there is a unit at (3,2)
    @Test
    public void shouldHaveUnitAt3_2()
    {
        Unit u = game.getUnitAt(new Position(3, 2));

        assertNotNull("There should be a unit at (3,2)", u);
    }

    // Tests that unit at 3,2 is a Blue unit
    @Test
    public void shouldbeBlueUnitAt3_2()
    {
        Unit u = game.getUnitAt(new Position(3, 2));

        assertNotNull("There should be a unit at (3,2)", u);
        assertEquals("Unit at 3,2 should be a Blue Unit", u.getOwner(), Player.BLUE);
    }

    // Tests that unit 3,2 is Legion
    @Test
    public void shouldBeLegionAt3_2()
    {
        Unit u = game.getUnitAt(new Position(3, 2));

        assertNotNull("There should be a unit at (3,2)", u);
        assertEquals("Unit at 3,2 should be a Legion", u.getTypeString(), GameConstants.LEGION);
    }

    // Test that unit at 2,0 is an Archer
    @Test
    public void shouldBeArcherAt2_0()
    {
        Unit u = game.getUnitAt(new Position(2, 0));

        assertNotNull("There should be a unit at 2,0", u);
        assertEquals("Red unit at 2,0 should be an Archer", u.getTypeString(), GameConstants.ARCHER);
    }

    // Test that unit at 2,0 is Red
    @Test
    public void shouldBeRedUnit2_0()
    {
        Unit u = game.getUnitAt(new Position(2, 0));

        assertNotNull("There should be a unit at 2,0", u);
        assertEquals("Unit at 2,0 should be Red", u.getOwner(), Player.RED);
    }

    // Tests that unit at 4,3 is red Settler
    @Test
    public void shouldBeRedSettler4_3()
    {
        Unit u = game.getUnitAt(new Position(4, 3));

        assertNotNull("There should be a unit at 4,3", u);
        assertEquals("Unit at 4,3 should be a settler", u.getTypeString(), GameConstants.SETTLER);
        assertEquals("Unit at 4,3 should be Red", u.getOwner(), Player.RED);
    }

    // Tests that moveUnit changes unit position
    @Test
    public void moveUnitAt4_3ShouldChangePosition()
    {
        Unit u = game.getUnitAt(new Position(4, 3));

        assertNotNull("There should be unitT at 4,3", u);

        boolean b = game.moveUnit(new Position(4, 3), new Position(5, 3));

        assertEquals("Unit should have moved", b, true);
    }

    // Tests that moveUnit returns false when moving to a mountain
    @Test
    public void moveUnitToMountainGivesFalse()
    {
        boolean b = game.moveUnit(new Position(3, 2), new Position(2, 2));

        assertEquals("Unit at 3,2 should not be able to move to mountains at 3,2", b, false);
    }

    // Test that unit at 2,0 cant move to tile 1,0 (ocean)
    @Test
    public void moveUnitShouldGiveFalse()
    {
        boolean b = game.moveUnit(new Position(2, 0), new Position(1, 0));

        assertEquals("Unit at 2,0 should not be able to move to oceans at 1,0", b, false);
    }

    // Test that unit at 4,3 can move to unit 5,3 and that after move, unit exists at 5,3.
    @Test
    public void shouldMove4_3UnitToNewPosition5_3()
    {
        assertTrue(game.moveUnit(new Position(4, 3), new Position(5, 3)));

        Unit u = game.getUnitAt(new Position(5, 3));

        assertNotNull("There should be a unit at (5,3) caused by move", u);
    }

    // assert no unit is at 5,3
    @Test
    public void shouldNotBeUnitAt5_3()
    {
        Unit u = game.getUnitAt(new Position(5, 3));

        assertNull("This should not be a valid unit", u);
    }


    // assert no unit anywhere, except (3,2), (4,3), (2,0)
    @Test
    public void shouldOnlyBeUnitsAt3_2z4_3z2_0()
    {
        for (int x = 0; x < GameConstants.WORLDSIZE; x++) {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++) {

                // Other tests, check that these are actually units.
                if (((x == 3) && (y == 2)) || ((x == 4) && (y == 3)) || ((x == 2) && (y == 0))) {
                    continue;
                }

                // Check no unites at (x,y)
                Unit u = game.getUnitAt(new Position(x, y));

                assertNull("There shouldn't be a valid unit at Position(" + x + ", " + y + ")", u);
            }
        }
    }

    // assert that red is not able to move blue units
    @Test
    public void redShouldNotBeAbleToMoveBlueUnits()
    {
        // Try to move a blue unit
        boolean could_move = game.moveUnit(new Position(3, 2), new Position(5, 3));

        // Ensure that we couldn't
        assertFalse("Shouldn't be able to move blue units as red", could_move);
    }

    // assert that blue is not able to move red units
    @Test
    public void blueShouldNotBeAbleToMoveRedUnits()
    {
        // Switch to blue
        game.endOfTurn();

        // Try to move red unit
        boolean could_move = game.moveUnit(new Position(4, 3), new Position(5, 3));

        // Ensure that we couldn't
        assertFalse("Shouldn't be able to move blue units as red", could_move);
    }

    // assert that red units are able to move ontop of blue units
    @Test
    public void redUnitsShouldBeAbleToMoveOntopOfBlueUnits()
    {
        boolean could_move;

        // Try to move red unit ontop of the blue one
        could_move = game.moveUnit(new Position(4, 3), new Position(3, 2));

        // Ensure we were allowed to do so!
        assertTrue("Should be able to move ontop of blue unit!", could_move);
    }

    // assert that one a unit is moved, the tile it leaves is empty.
    @Test
    public void tileShouldBeNullAfterMove()
    {
        // Try to move red unit ontop of the blue one
        boolean could_move = game.moveUnit(new Position(4, 3),
                new Position(5, 3));

        // Ensure we were allowed to do so!
        assertTrue("Should be able to move!", could_move);

        // No unit should now be at 4.3
        Unit u = game.getUnitAt(new Position(4, 3));

        assertNull(
                "There shouldn't be a valid unit at Position(4, 3) due to a move",
                u);
    }

    // assert that red units are able to kill blue units.
    @Test
    public void redUnitsShouldBeAbleToKillBlueUnits()
    {
        boolean could_move;

        // Try to move red unit ontop of the blue one
        could_move = game.moveUnit(new Position(4, 3), new Position(3, 2));

        // Ensure we were allowed to do so!
        assertTrue("Should be able to move ontop of blue unit killing them",
                could_move);
        game.endOfTurn();
        game.endOfTurn();

        // Now let's move back, and ensure that we are allowed to do so
        could_move = game.moveUnit(new Position(3, 2), new Position(4, 3));

        assertTrue("Should be able to move back", could_move);

        // And check that the unit on 3.2 is gone.
        Unit u = game.getUnitAt(new Position(3, 2));

        assertNull("There shouldn't be a valid unit at Position(3, 2)", u);
    }

    // assert that blue units are able to kill red units.
    @Test
    public void blueUnitsShouldBeAbleToKillRedUnits()
    {
        // Switch to blue
        game.endOfTurn();

        boolean could_move;

        // Try to move blue unit ontop of the red one
        could_move = game.moveUnit(new Position(3, 2), new Position(4, 3));

        // Ensure we were allowed to do so!
        assertTrue("Should be able to move ontop of blue unit killing them",
                could_move);
        game.endOfTurn();
        game.endOfTurn();

        // Now let's move back, and ensure that we are allowed to do so
        could_move = game.moveUnit(new Position(4, 3), new Position(3, 2));

        assertTrue("Should be able to move back", could_move);

        // And check that the unit on 4.3 is gone.
        Unit u = game.getUnitAt(new Position(4, 3));

        assertNull("There shouldn't be a valid unit at Position(4, 3)", u);
    }

    // assert that red, isn't allowed to team kill
    @Test
    public void teamKillNotAllowed()
    {
        // Try to move red unit ontop of the red one, and see if we're allowed
        // to.
        boolean could_move = game.moveUnit(new Position(4, 3),
                new Position(2, 0));

        assertFalse("Red units, should not be allowed to kill other red units",
                could_move);
    }

    // assert that the tile movement rights are enforced.
    @Test
    public void implementTileMoveability()
    {
        for (int x = 0; x < GameConstants.WORLDSIZE; x++)
        {
            for (int y = 0; y < GameConstants.WORLDSIZE; y++)
            {
                // Reset game each iteration, such that moves don't affect each
                // other.
                setUp();

                // Move the red unit from (4,3) to (x,y)
                boolean could_move = game.moveUnit(new Position(4, 3),
                        new Position(x, y));

                // These expect that we cant move onto them
                if (((x == 2) && (y == 2))            // Mountain
                        || ((x == 1) && (y == 0))     // Ocean
                        || ((x == 4) && (y == 3))     // Self
                        || ((x == 2) && (y == 0)))    // Other red
                {
                    assertFalse("Shouldn't be able to move to (" + x + ", " + y
                            + ")", could_move);
                }
                else
                {  // These expect that we can move onto them
                    // (moveable = all - unmoveable)
                    assertTrue("Should be able to move to (" + x + ", " + y
                            + ")", could_move);
                }
            }
        }
    }

    // assert that a city can produce archers
    @Test
    public void canProduceArchers()
    {
        CityModifiable c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);

        c.setProduction(GameConstants.ARCHER);

        String s = c.getProduction();

        assertEquals("Should be able to build archers", s,
                GameConstants.ARCHER);
    }

    // assert that a city can produce legions
    @Test
    public void canProduceLegions()
    {
        CityModifiable c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);

        c.setProduction(GameConstants.LEGION);

        String s = c.getProduction();

        assertEquals("Should be able to build legions", s,
                GameConstants.LEGION);
    }

    // assert that a city can produce settlers
    @Test
    public void canProduceSettlers()
    {
        CityModifiable c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);

        c.setProduction(GameConstants.SETTLER);

        String s = c.getProduction();

        assertEquals("Should be able to build legions", s,
                GameConstants.SETTLER);
    }

    // assert that a city's production starts at 0.
    @Test
    public void citiesProductionAmountShouldStartAt0()
    {
        City c = game.getCityAt(new Position(1, 1));
        assertNotNull("There should be a city at (1,1)", c);

        int     amount = c.getProductionAmount();

        assertEquals("Initial production amount should be zero", amount, 0);
    }

    // tests that the is no city at position (2,2)
    @Test
    public void shouldNotHaveCityAt2_2()
    {
        City c = game.getCityAt(new Position(2, 2));

        assertNull("There shouldn't be a city at (2,2)", c);
    }

    // assert that a city's production increases at a fixed rate of 6 per round.
    @Test
    public void citiesProductionIncreaseAt6PerRound()
    {
        // Check the city exists.
        City c = game.getCityAt(new Position(1, 1));

        assertNotNull("There should be a city at (1,1)", c);

        // Check intial
        int amount = c.getProductionAmount();

        assertEquals("Initial production amount should be zero", amount, 0);

        // Skip a single round (two turns)
        game.endOfTurn();
        game.endOfTurn();

        // Recheck, to ensure it's increased by 6.
        amount = c.getProductionAmount();

        assertEquals("Production should be raised by 6 per round (first)",
                amount, 6);

        // Skip a single round (two turns)
        game.endOfTurn();
        game.endOfTurn();

        // Recheck, to ensure it's increased by 6.
        amount = c.getProductionAmount();

        assertEquals("Production should be raised by 6 per round (second)",
                amount, 12);
    }

    // assert that an Archer is produced and when the city has gathered the 10 productions needed,
    // checks that 10 is deducted from city treasury,
    // and assert that that unit is on the same tile.
    @Test
    public void shouldProduceArcher()
    {
        // We'll study the city at 1,1
        CityModifiable c = game.getCityAt(new Position(1, 1));
        c.setProduction(GameConstants.ARCHER);
        c.setWorkforceFocus(GameConstants.productionFocus);
        assertNotNull("There should be a city at (1,1)", c);

        // Skip 2 rounds.
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 4; x++)
        {
            game.endOfTurn();
        }

        int amount = c.getProductionAmount();

        assertEquals("Production should be 6 x 2 - 10, 2", 2, amount);

        // Check that the unit is build
        Unit u = game.getUnitAt(new Position(1, 1));

        assertNotNull(
                "There should be a unit at (1,1), build from the city at same location",
                u);
        assertEquals("Newly made unit should be Archer", GameConstants.ARCHER,
                u.getTypeString());
    }

    // assert that an LEGION is produced and when the city has gathered the 15 productions needed,
    // checks that 15 is deducted from city treasury,
    // and assert that that unit is on the same tile.
    @Test
    public void shouldProduceLegion()
    {
        // We'll study the city at 1,1
        CityModifiable c = game.getCityAt(new Position(1, 1));
        c.setWorkforceFocus(GameConstants.productionFocus);
        c.setProduction(GameConstants.LEGION);
        assertNotNull("There should be a city at (1,1)", c);

        // Skip Three rounds.
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 6; x++)
        {
            game.endOfTurn();
        }

        int amount = c.getProductionAmount();

        assertEquals("Production should be 6 x 3 - 15, 3", 3, amount);

        // Check that the unit is build
        Unit u = game.getUnitAt(new Position(1, 1));

        assertNotNull(
                "There should be a unit at (1,1), build from the city at same location",
                u);
        assertEquals("Newly made unit should be Legion", GameConstants.LEGION,
                u.getTypeString());
    }

    // assert that an SETTLER is produced and when the city has gathered the 30 productions needed,
    // checks that 30 is deducted from city treasury,
    // and assert that that unit is on the same tile.
    @Test
    public void shouldProduceSettler()
    {
        // We'll study the city at 1,1
        CityModifiable c = game.getCityAt(new Position(1, 1));
        c.setWorkforceFocus(GameConstants.productionFocus);
        c.setProduction(GameConstants.SETTLER);
        assertNotNull("There should be a city at (1,1)", c);

        // Skip 5 rounds.
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        int amount = c.getProductionAmount();

        assertEquals("Production should be 6 x 5 - 30, 3", 0, amount);

        // Check that the unit is build
        Unit u = game.getUnitAt(new Position(1, 1));

        assertNotNull(
                "There should be a unit at (1,1), build from the city at same location",
                u);
        assertEquals("Newly made unit should be Settler",
                GameConstants.SETTLER, u.getTypeString());
    }

    // assert that a unit being produced when a city already has a unit, gets placed clockwise
    @Test
    public void shouldPlaceUnitsClockwise()
    {
        // We'll study the city at 1,1
        CityModifiable c =  game.getCityAt(new Position(1, 1));

        c.setProduction(GameConstants.SETTLER);
        assertNotNull("There should be a city at (1,1)", c);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        Unit u;

        // Check that the unit is build
        u = game.getUnitAt(new Position(1, 1));

        assertNotNull(
                "There should be a unit at (1,1), build from the city at same location",
                u);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        // and check that the new unit is build just north
        u = game.getUnitAt(new Position(2, 1));

        assertNotNull(
                "There should be a unit at (2,1), build from the city at (1,1)", u);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        // and check that the new unit is build just north
        u = game.getUnitAt(new Position(1, 2));

        assertNotNull(
                "There should be a unit at (1,2), build from the city at (1,1)", u);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        // and check that the new unit is build just north
        u = game.getUnitAt(new Position(0, 2));

        assertNotNull(
                "There should be a unit at (0,2), build from the city at (1,1)", u);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        // and check that the new unit is build just north
        u = game.getUnitAt(new Position(0, 1));

        assertNotNull(
                "There should be a unit at (0,1), build from the city at (1,1)", u);

        // Skip a single round (two turns)
        // Resources should now ensure the unit should be built
        for (int x = 0; x < 10; x++)
        {
            game.endOfTurn();
        }

        // and check that the new unit is build just north
        u = game.getUnitAt(new Position(0, 0));

        assertNotNull(
                "There should be a unit at (0,0), build from the city at (1,1)", u);
    }

    // Test that getMoveCount() returns 1 for Archer, settler, Legion
    @Test
    public void shoulddreturn1AtgetMovementCount()
    {
        StrategyFactory strategyPackage = new AlphaStrategyFactory();
        UnitFactory unitFactory = strategyPackage.getUnitFactory();

        Unit settler= unitFactory.spawnUnit(GameConstants.SETTLER, Player.RED);
        Unit legion = unitFactory.spawnUnit(GameConstants.LEGION, Player.RED);
        Unit archer = unitFactory.spawnUnit(GameConstants.ARCHER, Player.RED);

        assertEquals("Movecount should be 1", 1, settler.getMoveCount());
        assertEquals("Movecount should be 1", 1, legion.getMoveCount());
        assertEquals("Movecount should be 1", 1, archer.getMoveCount());
    }

    // Test that units cannot move more than their getMoveCount()
    @Test
    public void shouldNotMoveMoreThangetMoveCount()
    {
        Unit    u     = game.getUnitAt(new Position(2, 0));
        boolean moved = game.moveUnit(new Position(2, 0), new Position(3, 0));

        assertEquals("First move should be valid", true, moved);

        moved = game.moveUnit(new Position(3, 0), new Position(4, 0));

        assertEquals("Second move should be rejected", false, moved);
    }
}
