package hotciv.variants.gammaciv;

import hotciv.common.*;
import hotciv.framework.*;
import hotciv.framework.strategy.*;

public class GammaSettlerUnitActionStrategy implements UnitActionStrategy
{
    @Override
    public void unitAction(World world, Position position)
    {
        Unit u = world.getUnitAt(position);
        ((WorldModifiable) world).setCityAt(position, u.getOwner());
        ((WorldModifiable) world).removeUnitAt(position);
    }
}
