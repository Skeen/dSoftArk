package hotciv.variants.zetaciv;

import hotciv.framework.*;

import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;

import org.junit.*;

import static org.junit.Assert.*;

public class TestZetaCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new ZetaStrategyFactory();

        game = new GameImpl(strategyPackage);
    }

    // Test that red wins when red owns all cities
    @Test
    public void redShouldWinWhenOwningAllCities()
    {
        City cR = game.getCityAt(new Position(1,1));
        assertEquals("Default world city at 1,1 should be owned by Red.", Player.RED, cR.getOwner());
        
        City cB = game.getCityAt(new Position(4,1));
        assertEquals("Default world city at 4,1 should be owwned by Blue before conquest.", Player.BLUE, cB.getOwner());
        
        game.moveUnit(new Position(2,0), new Position(3,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(3,1), new Position(4,1));
        
        assertEquals("Default world city at 4,1 should be owned by Red after conquest by red archer", Player.RED, cB.getOwner());
        
        assertEquals("Red should win after taking blue city.", Player.RED, game.getWinner());
    }
    
    // Test that blue wins when Blue owns all cities
    @Test
    public void blueShouldWinWhenOwningAllCities()
    {
        City cR = game.getCityAt(new Position(1,1));
        assertEquals("Default world city at 1,1 should be owned by Red.", Player.RED, cR.getOwner());
        
        City cB = game.getCityAt(new Position(4,1));
        assertEquals("Default world city at 4,1 should be owwned by Blue before conquest.", Player.BLUE, cB.getOwner());
        
        game.endOfTurn();
        game.moveUnit(new Position(3,2), new Position(2,1));
        game.endOfTurn();
        game.endOfTurn();
        game.moveUnit(new Position(2,1), new Position(1,1));
        
        assertEquals("Default world city at 1,1 should be owned by Blue after conquest by blue legion", Player.BLUE, cR.getOwner());
        
        assertEquals("Blue should win after taking red city.", Player.BLUE, game.getWinner());
    }
    
    @Test
    public void redShouldWinAfterWinning3BattlesAnd20Rounds()
    {
        assertNull("There should not be a winner at the start of the game.", game.getWinner());
        CityModifiable cB = game.getCityAt(new Position(4,1));
        cB.setProduction(GameConstants.LEGION);
        
        for(int x = 0; x < 42; x++)
        {
            game.endOfTurn();
        }
        assertNotNull("There should now be a unit at 3,1", game.getUnitAt(new Position(3,1)));
        assertNotNull("There should now be a unit at 4,2", game.getUnitAt(new Position(4,2)));
        assertNotNull("There should now be a unit at 5,2", game.getUnitAt(new Position(5,2)));
        
        boolean moved = false;
        moved = game.moveUnit(new Position(2,0), new Position(3,1));
        assertEquals("Should have been able to move from 2,0 to 3,1", true, moved);
        moved = false;
        
        game.endOfTurn();
        game.endOfTurn();
        moved = game.moveUnit(new Position(3,1), new Position(4,1));
        assertEquals("Should have been able to move from 3,1 to 4,2", true, moved);
        moved = false;
        
        game.endOfTurn();
        game.endOfTurn();
        moved = game.moveUnit(new Position(4,1), new Position(4,2));
        assertEquals("Should have been able to move from 4,1 to 4,2", true, moved);
        moved = false;
        
        game.endOfTurn();
        game.endOfTurn();
        moved = game.moveUnit(new Position(4,2), new Position(5,2));
        assertEquals("Should have been able to move from 4,2 to 5,2", true, moved);
        
        
        assertEquals("Winner should be Red after 20 rounds + 3 battles won.", Player.RED, game.getWinner());
    }
}
