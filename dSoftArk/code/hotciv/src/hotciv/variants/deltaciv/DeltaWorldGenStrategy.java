package hotciv.variants.deltaciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import hotciv.common.*;

import java.util.*;

public class DeltaWorldGenStrategy implements WorldGenStrategy
{
    private String[] map;
    private Map<Position, Player> cities;

    public DeltaWorldGenStrategy()
    {
        map = new String[] {
          "...ooMooooo.....",
          "..ohhoooofffoo..",
          ".oooooMooo...oo.",
          ".ooMMMoooo..oooo",
          "...ofooohhoooo..",
          ".ofoofooooohhoo.",
          "...ooo..........",
          ".ooooo.ooohooM..",
          ".ooooo.oohooof..",
          "offfoooo.offoooo",
          "oooooooo...ooooo",
          ".ooMMMoooo......",
          "..ooooooffoooo..",
          "....ooooooooo...",
          "..ooohhoo.......",
          ".....ooooooooo..",
         };

        cities = new HashMap<Position, Player>();
        cities.put(new Position(8,12), Player.RED);
        cities.put(new Position(4,5), Player.BLUE);
    }

    @Override
    public TileModifiable[][] createMap(UnitFactory unitFactory, CityFactory cityFactory)
    {
        TileModifiable[][] tiles = new TileModifiable[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        String line;

        for(int x = 0; x < tiles.length; x++ )
        {
            line = map[x];
            for(int y = 0; y < tiles[x].length; y++)
            {
                char tileChar = line.charAt(y);
                String type = getType(tileChar);
                tiles[x][y] = new TileImpl(new Position(x,y), type);
            }
        }
        
        for (Map.Entry<Position, Player> entry : cities.entrySet()) 
        {
            Position position = entry.getKey();
            Player owner = entry.getValue();
            int row = position.getRow();
            int column = position.getColumn();

            tiles[row][column].setCity(cityFactory.spawnCity(owner));
        }
       
        return tiles;
    }

    private String getType(char character)
    {
        String type = null;

        if ( character == '.' )
        {
            type = GameConstants.OCEANS; 
        }
        if ( character == 'o' ) 
        {
            type = GameConstants.PLAINS; 
        }
        if ( character == 'M' ) 
        {
            type = GameConstants.MOUNTAINS; 
        }
        if ( character == 'f' ) 
        {
            type = GameConstants.FOREST; 
        }
        if ( character == 'h' ) 
        {
            type = GameConstants.HILLS; 
        }

        return type;
    }
}
