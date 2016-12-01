package hotciv.variants.epsilonciv;

import hotciv.framework.strategy.*;
import hotciv.framework.*;
import java.util.EnumMap;

public class EpsilonBestOf5BattlesWinStrategy implements WinStrategy,BattleStrategy
{
    private EnumMap<Player,Integer> wonMap; 

    public EpsilonBestOf5BattlesWinStrategy()
    {
        wonMap = new EnumMap(Player.class);
        for (Player p : Player.values())
            wonMap.put(p,0);
    }

    @Override
    public UnitModifiable resolveBattleWinner(
            UnitModifiable attacker, 
            UnitModifiable defender)
    {
        // This is expected to evolve , ...
        UnitModifiable res = attacker;
        registerBattleWon(res.getOwner());
        return res;
    }

    @Override
    public Player getWinner(Game game) 
    {
        Player res = null;
        for (Player player : Player.values()) 
        {
            if (getNumberOfBattlesWon(player) >= 3)
               res = player;
        }
        return res;
    }

    public int getNumberOfBattlesWon(Player player) 
    {
        return wonMap.get(player);
    }

    public void registerBattleWon(Player player)
    {
        wonMap.put(player, getNumberOfBattlesWon(player) +1);
    }

}

