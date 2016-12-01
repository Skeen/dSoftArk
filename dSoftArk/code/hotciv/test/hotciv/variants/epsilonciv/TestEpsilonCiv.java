package hotciv.variants.epsilonciv;

import hotciv.framework.*;
import hotciv.common.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestEpsilonCiv
{
    private GameModifiable game;
    private TestStrategyFactory e;
    
    // This is run before each test.
    @Before
    public void setUp()
    {
        // Setup special strategy that traces all information about calls to
        // resolveBattleWinner.
        // This allows for tests on correct game.getWinner.
        e = new TestStrategyFactory();
        game = new GameImpl(e);
    }
    
    /**
     * This method only uses accessors in its assertion. Call from another
     * method to utilise properly.
     * Maybe remove the Test annotation, that is, if run as a test, then
     * functions as ShouldNotHaveWinnerAtGameStart.
     */
    @Test
    public void ShouldNotDecideWinnerBefore3BattlesHasBeenResolved()
    {
        if (e.getHasBattleBeenResolved() == false)
        {
            assertNull("No Battle Has taken place, so getWinner should give Null", game.getWinner());
        }
        
        if ((e.getTimesResolved() >= 3) && (e.getWinnee() == Player.RED))
        {
            // compute the value and then assert either Player.Red or Null:
            Player gameWinner = game.getWinner();
            try
            {
                assertEquals(Player.RED, gameWinner);
            }
            catch (AssertionError a)
            {
                assertNull("The only possible winner after" +
                        " RED winning battle is either Null or RED",
                        gameWinner);
            }
        }
        // test the same, just with blue winner:
        else if ((e.getTimesResolved() >= 3) && (e.getWinnee() == Player.BLUE))
        {
            Player gameWinner = game.getWinner();
            try
            {
                assertEquals(Player.BLUE, gameWinner);
            }
            catch (AssertionError a)
            {
                assertNull("The only possible winner after" +
                        " BLUE winning battle is either Null or BLUE",
                        gameWinner);
            }
        }
        
        // execute these tests also, for good measure.
        // And for a complete assertion of the rules.
        ShouldBeRedWinnerIfMT3Wins();
        ShouldBeBlueWinnerIfMT3Wins();
    }
    
    @Test
    public void ShouldBeRedWinnerIfMT3Wins()
    {
        if (e.getNoRedWins() >= 3)
        {
            assertEquals("Red has 3 or more wins, ergo winner",
                    Player.RED,game.getWinner());
        }
    }
    
    @Test
    public void ShouldBeBlueWinnerIfMT3Wins()
    {
        if (e.getNoBlueWins() >= 3)
        {
            assertEquals("Blue has 3 or more wins, ergo winner",
                    Player.BLUE,game.getWinner());
        }
    }
    
    @Test
    public void unitsCorrectlyPlaced()
    {
        assertNotNull("There should be a unit at (5,5)",
                game.getUnitAt(new Position(5,5)));
        assertEquals("unit at 5,5 should be red",
                Player.RED, game.getUnitAt(new Position(5,5)).getOwner());
        
        assertNotNull("There should be a unit at (5,6)",
                game.getUnitAt(new Position(5,6)));
        assertEquals("unit at 5,6 should be BLUE",
                Player.BLUE, game.getUnitAt(new Position(5,6)).getOwner());
    }
    
    @Test
    public void shouldHaveUnitsWith10AttackDefenseAt5_5And5_6()
    {
        Position p55 = new Position(5, 5),
                p56 = new Position(5, 6);
        
        Unit u = game.getUnitAt(p55);
        assertEquals("Unit at 5,5 should have right attack strength ",
                10, u.getAttackingStrength(game.getTileAt(p55), game));
        assertEquals("Unit at 5,5 should have right defensive strength ",
                10, u.getDefensiveStrength(game.getTileAt(p55), game));
        
        u = game.getUnitAt(p56);
        assertEquals("Unit at 5,6 should have right attack strength ",
                10, u.getAttackingStrength(game.getTileAt(p56), game));
        assertEquals("Unit at 5,6 should have right defensive strength ",
                10, u.getDefensiveStrength(game.getTileAt(p56), game));
    }
    
    @Test
    public void shouldResolve2BattlesWithoutResolvingGameWinner()
    {
        Position p1 = new Position(5,5),
                p2 = new Position(5,6),
                p3 = new Position(6,5),
                p4 = new Position(6,6);
        
        assertNotNull(game.getUnitAt(p1));
        assertNotNull(game.getUnitAt(p2));
        assertNotNull(game.getUnitAt(p3));
        assertNotNull(game.getUnitAt(p4));
        
        //Commense battle 1
        game.moveUnit(p1,p2);
        
        ShouldNotDecideWinnerBefore3BattlesHasBeenResolved();
        
        assertNotNull(game.getUnitAt(p3));
        assertNotNull(game.getUnitAt(p4));
        
        //Commense battle 2
        game.moveUnit(p3,p4);
        
        ShouldNotDecideWinnerBefore3BattlesHasBeenResolved();
        
        assertNull("A winner should not have been found before 3 battles",
                game.getWinner());
    }
    
    @Test
    public void shouldResolveAtMost5BattlesForResolvingGameWinner()
    {
        boolean battleCommenced;
        
        Position p55 = new Position(5,5),p56 = new Position(5,6),
                p65 = new Position(6,5),p66 = new Position(6,6),
                p75 = new Position(7,5),p76 = new Position(7,6),
                p85 = new Position(8,5),p86 = new Position(8,6),
                p95 = new Position(9,5),p96 = new Position(9,6);
        
        assertNotNull(game.getUnitAt(p55)); assertNotNull(game.getUnitAt(p56));
        assertNotNull(game.getUnitAt(p65)); assertNotNull(game.getUnitAt(p66));
        assertNotNull(game.getUnitAt(p75)); assertNotNull(game.getUnitAt(p76));
        assertNotNull(game.getUnitAt(p85)); assertNotNull(game.getUnitAt(p86));
        assertNotNull(game.getUnitAt(p95)); assertNotNull(game.getUnitAt(p96));
        
        //Commense battle 1
        battleCommenced = game.moveUnit(p55,p56);
        
        //Commense battle 2
        battleCommenced &= game.moveUnit(p65,p66);
        
        //Commense battle 3
        battleCommenced &= game.moveUnit(p75,p76);
        
        //Commense battle 4
        battleCommenced &= game.moveUnit(p85,p86);
        
        //Commense battle 1
        battleCommenced &= game.moveUnit(p95,p96);
        
        assertTrue("All Battles should be commenced succesfully", battleCommenced);
        
        assertNotNull("A winner should at least have been found after 5 battles", game.getWinner());
    }
    
    @Test
    public void ShouldBluePlayerWin3BattlesInARow()
    {
        boolean battleCommenced;
        
        Position p58 = new Position(5,8),p59 = new Position(5,9),
                p68 = new Position(6,8),p69 = new Position(6,9),
                p78 = new Position(7,8),p79 = new Position(7,9);
        
        assertNotNull(game.getUnitAt(p58)); assertNotNull(game.getUnitAt(p59));
        assertNotNull(game.getUnitAt(p68)); assertNotNull(game.getUnitAt(p69));
        assertNotNull(game.getUnitAt(p78)); assertNotNull(game.getUnitAt(p79));
        
        //change turn so blue player starts;
        game.endOfTurn();
        
        assertNull("Should Not Find winner with no battles", game.getWinner());
        battleCommenced = game.moveUnit(p58,p59);
        assertNull("Should Not Find winner with 1 battles", game.getWinner());
        battleCommenced &= game.moveUnit(p68,p69);
        assertNull("Should Not Find winner with 2 battles", game.getWinner());
        battleCommenced &= game.moveUnit(p78,p79);
        
        assertEquals("Expects Blue to Win",
                Player.BLUE, game.getUnitAt(p59).getOwner());
        assertEquals("Expects Blue to Win",
                Player.BLUE, game.getUnitAt(p69).getOwner());
        assertEquals("Expects Blue to Win",
                Player.BLUE, game.getUnitAt(p79).getOwner());
        
        assertTrue("Battle in second turn expects to be valid", battleCommenced);
        
        
        // Should this really run
        //ShouldNotDecideWinnerBefore3BattlesHasBeenResolved();
        
        assertEquals(
                "A Blue winner should have been found after 3 certain battles",
                Player.BLUE, game.getWinner());
    }
    
    @Test
    public void shouldHaveRightAttStrLegionHere()
    {
        Position p1515 = new Position(15,15),
                p1514 = new Position(15,14),
                p1415 = new Position(14,15),
                p1414 = new Position(14,14);
        Tile tile = game.getTileAt(p1515);
        
        // I have removed some extra assertions that just made sure
        // testworld was setup right:
        UnitModifiable u = game.getUnitAt(p1515);
 
        assertEquals(
                "should have correct attacking strength with 2 adjacent friends",
                (4+1+1)*3, u.getAttackingStrength(tile, game));
        
        assertTrue("Attack should succeed",game.moveUnit(p1414, p1514));
        
        assertEquals("After attack this should be a red unit",
                Player.RED, game.getUnitAt(p1514).getOwner());
        
        assertEquals( "Legion should have correct " 
                + "attacking strength with 1 adjacent friend", 
                (4+1)*3, u.getAttackingStrength(tile, game));
    }
    
    /*
     * An archer def3
     * on a hill with a single adjacent unit
     * The combined defensive strength is
     * (3 + 1)* 2 = 8
     */
}
