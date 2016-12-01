package hotciv.variants.deltaciv;

import hotciv.framework.*;
import hotciv.common.*;
import hotciv.framework.strategy.StrategyFactory;
import org.junit.*;
import static org.junit.Assert.*;

public class TestDeltaCiv
{
    private GameModifiable game;

    // This is run before each test.
    @Before
    public void setUp()
    {
        StrategyFactory strategyPackage = new DeltaStrategyFactory();

        game = new GameImpl(strategyPackage);
    }

    @Test
    public void shouldBeRedCityAt8_12()
    {
        City city = game.getCityAt(new Position(8, 12));

        assertNotNull("There is no city at 8,12", city);
        assertEquals("City at 8,12 not blue.", Player.RED, city.getOwner());
    }

    @Test
    public void shouldBeBlueCityAt4_5()
    {
        City city = game.getCityAt(new Position(4, 5));

        assertNotNull("There is no city at 4,5", city);
        assertEquals("City at 4,5 not blue.", Player.BLUE, city.getOwner());
    }
    
    @Test
    public void tile0_3ShouldBeOcean()
    {
        Tile t = game.getTileAt(new Position(0,3));
        
        assertEquals("Tile at 0,3 should be plains", GameConstants.PLAINS, t.getTypeString());
    }
    
    @Test
    public void tile0_0ShouldBeOcean()
    {
        Tile t = game.getTileAt(new Position(0,0));
        
        assertEquals("Tile at 0,0 should be ocean", GameConstants.OCEANS, t.getTypeString());
    }
    
    @Test
    public void tile0_6ShouldBeMountain()
    {
        Tile t = game.getTileAt(new Position(0,5));

        assertEquals("Tile at 0,6 should be Mountain", GameConstants.MOUNTAINS, t.getTypeString());
    }
    
    @Test
    public void tile5_3ShouldBeForest()
    {
        Tile t = game.getTileAt(new Position(5,2));
        
        assertEquals("Tile at 5,3 should be Forest", GameConstants.FOREST, t.getTypeString());
    }
}
