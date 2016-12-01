package hotciv.framework.strategy;

import hotciv.framework.*;

/**
 * This strategy has as purpose to decide the outcome of every battle-encounter
 * in the game.
 * A battle encounter happens when a unit moves to a tile with another player's
 * unit.
 */
public interface BattleStrategy
{
    /**
     * Handles a battle, that is 2 units fights over a single tile.
     * This method does the computations behind deciding the winner of a battle.
     * @param attacker The unitExt invading from some other tile.
     * @param defender The unitExt being attacked
     * @return the unit who won the battle.
     */
    public UnitModifiable resolveBattleWinner(UnitModifiable attacker,
            UnitModifiable defender);
}