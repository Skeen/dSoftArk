package hotciv.variants.epsilonciv;

import hotciv.framework.Player;
import hotciv.framework.strategy.BattleStrategy;
import hotciv.framework.strategy.WorldGenStrategy;
import hotciv.framework.UnitModifiable;

import hotciv.framework.strategy.WinStrategy;

public class TestStrategyFactory extends EpsilonStrategyFactory
{
    private defaultWithExtension bs = new defaultWithExtension(); 

    @Override
    public BattleStrategy getBattleStrategy()
    {
        return bs;
    }
    
    @Override
    public WinStrategy getWinStrategy()
    {
        return bs;
    }

    @Override
    public WorldGenStrategy getWorldGenStrategy()
    {
        return new TestWorld();
    }

    protected boolean getHasBattleBeenResolved()
    {
        return bs.hasBattleBeenResolved;
    }

    protected int getNoBlueWins()
    {
        return bs.blueWins;
    }

    protected int getNoRedWins()
    {
        return bs.redWins;
    }

    protected int getTimesResolved()
    {
        return bs.counter;
    }

    protected Player getWinnee()
    {
        return bs.winnee;
    }

    private class defaultWithExtension extends EpsilonBestOf5BattlesWinStrategy
    {
        protected int     blueWins              = 0;
        protected int     counter               = 0;
        protected boolean hasBattleBeenResolved = false;
        protected int     redWins               = 0;
        protected Player  winnee;

        @Override
        public UnitModifiable resolveBattleWinner(UnitModifiable unitFrom,
                UnitModifiable unitTo)
        {
            UnitModifiable res = super.resolveBattleWinner(unitFrom, unitTo);

            hasBattleBeenResolved = true;

            counter++;


            winnee = res.getOwner();

            if (winnee == Player.RED)
            {
                redWins++;
            }
            else
            {
                blueWins++;
            }

            return res;
        }
    }
}