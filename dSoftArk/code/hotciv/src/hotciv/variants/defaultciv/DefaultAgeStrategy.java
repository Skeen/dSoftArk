package hotciv.variants.defaultciv;

import hotciv.framework.strategy.*;

public class DefaultAgeStrategy implements AgeStrategy
{
    private int age = -4000;

    @Override
    public int getAge()
    {
        return age;
    }

    @Override
    public void advanceAge()
    {
        age = age + 100;
    }
}
