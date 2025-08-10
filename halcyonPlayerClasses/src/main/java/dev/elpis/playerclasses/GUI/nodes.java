package dev.elpis.playerclasses.GUI;

import dev.elpis.playerclasses.GUI.damage.*;
import dev.elpis.playerclasses.GUI.healer.*;
import dev.elpis.playerclasses.GUI.tank.*;


public class nodes {

    // widely used node info

    final public static int[] REQUIREMENTS = {0, 1, 10, 25, 40, 70}; // 0 so it starts at index 1

    // t1 attack perks

    final public static nodeClass ATTACK_BUFF = new attackBuff();
    final public static nodeClass ADRENALINE = new adrenaline();
    final public static nodeClass APPRENTICE = new apprentice();
    final public static nodeClass OPPRESSOR = new oppressor();
    final public static nodeClass PUNISHING_BLOW = new punishingBlow();



    // t1 tank perks

    final public static nodeClass THICK_PLATING = new thickPlating();
    final public static nodeClass VETERAN_SAILOR = new veteranSailor();
    final public static nodeClass HOLY_KNIGHT = new holyKnight();
    final public static nodeClass DARK_KNIGHT = new darkKnight();
    final public static nodeClass HORDE_RESISTANT = new hordeResistant();



    //t1 healer perks

    final public static nodeClass RESTED_BOOST = new restedBoost();
    final public static nodeClass FIELD_MEDIC = new fieldMedic();
    final public static nodeClass GENEROUS = new generous();

    //t2 healer perks

    final public static nodeClass ALCHEMIST = new alchemist();
    final public static nodeClass ELEMENTAL_SYNERGY = new elementalSynergy();
    final public static nodeClass SELFLESS = new selfless();

    //t3 healer perks

    final public static nodeClass CLERIC = new cleric(); // req alchemist
    final public static nodeClass ELEMENTAL_CATALYST = new elementalCatalyst(); // req syn
    final public static nodeClass ROBIN_HOOD = new robinHood();

    //t4

    final public static nodeClass MORALE_BOOSTER = new moraleBooster();  // paired with blood magic (t5)
    final public static nodeClass PROTECTOR = new protector(); // paired with Guardian angel (t5)
    final public static nodeClass RELEASED_POTENTIAL = new releasedPotential();

    // t5
    final public static nodeClass BLOOD_MAGIC = new bloodMagic(); // paired with morale booster (t4)
    final public static nodeClass GUARDIAN_ANGEL = new guardianAngel(); // paired with Protector (t4)
    final public static nodeClass DOUBLE_DOWN = new doubleDown();
}
