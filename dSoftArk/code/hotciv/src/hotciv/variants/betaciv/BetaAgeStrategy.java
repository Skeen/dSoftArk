package hotciv.variants.betaciv;

import hotciv.framework.strategy.*;

public class BetaAgeStrategy implements AgeStrategy
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
        // Age is before christ
        if (age < -100)
        {
            age = age + 100;
        }

        // theBirthOfCristSequence
        else if ((age >= -100) && (age < 50))
        {
            switch (age)
            {
                // -100 --> -1
                case -100:
                    age = -1;
                    break;

                    // -1 --> 1
                case -1:
                    age = 1;
                    break;

                    // 1 --> 50
                case 1:
                    age = 50;
                    break;
            }
        }
        else if ((age >= 50) && (age < 1750))
        {
            age = age + 50;
        }
        else if ((age >= 1750) && (age < 1900))
        {
            age = age + 25;
        }
        else if ((age >= 1900) && (age < 1970))
        {
            age = age + 5;
        }
        else
        {
            age = age + 1;
        }
    }
}
