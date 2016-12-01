package hotciv.framework;

/**
 * Collection of constants used in HotCiv Game. Note that strings are
 * used instead of enumeration types to keep the set of valid
 * constants open to extensions by future HotCiv variants.  Enums can
 * only be changed by compile time modification.
 *
 * This source code is from the book
 * "Flexible, Reliable Software:
 * Using Patterns and Agile Development"
 * published 2010 by CRC Press.
 * Author:
 * Henrik B Christensen
 * Computer Science Department
 * Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either
 * expressed or implied. You may study, use, modify, and
 * distribute it for non-commercial purposes. For any
 * commercial use, see http://www.baerbak.com
 */
public interface GameConstants
{
    // Valid unit types
    public static final String ARCHER      = "archer";
    public static final int    ARCHER_COST = 10;
    public static final int    ARCHER_DEFENSE = 3;
    public static final int    ARCHER_ATTACK = 2;
    public static final int    ARCHER_DISTANCE = 1;
    
    public static final String LEGION      = "legion";
    public static final int    LEGION_COST = 15;
    public static final int    LEGION_DEFENSE = 2;
    public static final int    LEGION_ATTACK = 4;
    public static final int    LEGION_DISTANCE = 1;
    
    public static final String SETTLER      = "settler";
    public static final int    SETTLER_COST = 30;
    public static final int    SETTLER_DEFENSE = 3;
    public static final int    SETTLER_ATTACK = 0;
    public static final int    SETTLER_DISTANCE = 1;

    // Valid terrain types
    public static final String PLAINS      = "plains";
    public static final int    PLAINS_FOOD = 3;
    public static final int    PLAINS_PRODUCTION = 0;
    public static final boolean PLAINS_MOVABLE = true;
    public static final String OCEANS      = "ocean";
    public static final int    OCEANS_FOOD = 1;
    public static final int    OCEANS_PRODUCTION = 0;
    public static final boolean OCEANS_MOVABLE = false;
    public static final String FOREST      = "forest";
    public static final int    FOREST_FOOD = 0;
    public static final int    FOREST_PRODUCTION = 3;
    public static final boolean FOREST_MOVABLE = true;
    public static final String MOUNTAINS   = "mountain";
    public static final int    MOUNTAINS_FOOD = 0;
    public static final int    MOUNTAINS_PRODUCTION = 1;
    public static final boolean MOUNTAINS_MOVABLE = false;
    public static final String HILLS       = "hills";
    public static final int    HILLS_FOOD = 0;
    public static final int    HILLS_PRODUCTION = 2;
    public static final boolean HILLS_MOVABLE = true;
    
    // City
    public static final int    CITY_FOOD = 1;
    public static final int    CITY_PRODUCTION = 1;

    // The size of the world is set permanently to a 16x16 grid
    public static final int    WORLDSIZE = 16;

    // Valid production balance types
    public static final String productionFocus = "hammer";
    public static final String foodFocus = "apple";
}
