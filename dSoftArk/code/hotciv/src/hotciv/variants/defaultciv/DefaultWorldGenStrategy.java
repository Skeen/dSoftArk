package hotciv.variants.defaultciv;

import hotciv.common.*;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class DefaultWorldGenStrategy implements WorldGenStrategy
{
    @Override
    public TileModifiable[][] createMap(UnitFactory unitFactory,
            CityFactory cityFactory)
    {
        TileModifiable[][] tiles =
            new TileModifiable[GameConstants.WORLDSIZE][GameConstants.WORLDSIZE];
        TileModifiable t;

        tiles[2][2] = new TileImpl(new Position(2, 2), GameConstants.MOUNTAINS);
        tiles[0][1] = new TileImpl(new Position(0, 1), GameConstants.HILLS);
        tiles[1][0] = new TileImpl(new Position(1, 0), GameConstants.OCEANS);

        for (int x = 0; x < tiles.length; x++)
        {
            for (int y = 0; y < tiles[x].length; y++)
            {
                if (tiles[x][y] == null)
                {
                    tiles[x][y] = new TileImpl(new Position(x, y),
                                               GameConstants.PLAINS);
                }
            }
        }

        tiles[3][2].setUnit(unitFactory.spawnUnit(GameConstants.LEGION,
                Player.BLUE));
        tiles[4][3].setUnit(unitFactory.spawnUnit(GameConstants.SETTLER,
                Player.RED));
        tiles[2][0].setUnit(unitFactory.spawnUnit(GameConstants.ARCHER,
                Player.RED));
        tiles[1][1].setCity(cityFactory.spawnCity(Player.RED));
        tiles[4][1].setCity(cityFactory.spawnCity(Player.BLUE));

        return tiles;
    }
}