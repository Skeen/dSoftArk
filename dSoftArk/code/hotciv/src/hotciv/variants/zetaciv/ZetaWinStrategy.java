package hotciv.variants.zetaciv;

import hotciv.framework.*;
import hotciv.framework.strategy.*;

import hotciv.variants.defaultciv.*;
import hotciv.variants.betaciv.*;
import hotciv.variants.epsilonciv.*;

import java.util.*;

public class ZetaWinStrategy
        implements WinStrategy, AgeStrategy, BattleStrategy
{
    private int                     age;
    private Player                  winner;
    private WinStrategy             winStrategy;
    private AgeStrategy             ageStrategy;
    private EnumMap<Player, Integer> wonMap;

    public ZetaWinStrategy()
    {
        ageStrategy = new DefaultAgeStrategy();
        winStrategy = new BetaWinStrategy();
        
        // Battles won storer
        wonMap      = new EnumMap(Player.class);

        for (Player p : Player.values())
        {
            wonMap.put(p, 0);
        }
    }

    @Override
    public int getAge()
    {
        return ageStrategy.getAge();
    }

    public int getNumberOfBattlesWon(Player player)
    {
        return wonMap.get(player);
    }

    @Override
    public Player getWinner(Game game)
    {
        if (ageStrategy.getAge() <= -2000)
        {
            return winStrategy.getWinner(game);
        }

        Player res = null;

        for (Player player : Player.values())
        {
            if (getNumberOfBattlesWon(player) >= 3)
            {
                res = player;
            }
        }
        return res;
    }

    @Override
    public void advanceAge()
    {
        ageStrategy.advanceAge();
    }
    
    /* 
     * This determines who wins any given battle.
     * Same as default battle strategy,
     * except it will also record battles after round 20.
     */
    @Override
    public UnitModifiable resolveBattleWinner(UnitModifiable attacker,
            UnitModifiable defender)
    {
        if (ageStrategy.getAge() >= -2000)
        {
            //Register battle won, only after round 20.
            Player player = attacker.getOwner();
            wonMap.put(player, getNumberOfBattlesWon(player) + 1);
        }

        return attacker;
    }
}