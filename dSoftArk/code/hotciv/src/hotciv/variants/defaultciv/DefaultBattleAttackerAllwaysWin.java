package hotciv.variants.defaultciv;

import hotciv.framework.strategy.BattleStrategy;
import hotciv.framework.*;

public class DefaultBattleAttackerAllwaysWin implements BattleStrategy {
    @Override
    public UnitModifiable resolveBattleWinner(
            UnitModifiable attacker,
            UnitModifiable defender)
    {
        return attacker;
    }
}
