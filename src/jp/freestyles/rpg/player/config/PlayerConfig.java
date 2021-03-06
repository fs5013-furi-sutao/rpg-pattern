package jp.freestyles.rpg.player.config;

import static jp.freestyles.rpg.util.RandomGenerator.generateRandomIntInRange;

public enum PlayerConfig {

    FIGHTER("戦士    ", 
        130, 210,  // HP
        0, 0,      // MP
        20, 70,   // STR
        20, 70,   // DEF
        1, 100,    // LUCK
        10, 50),    // AGI

    WIZARD("魔法使い", 
        110, 190,   // HP
        80, 140,    // MP
        20, 60,     // STR
        10, 70,     // DEF
        1, 100,    // LUCK
        20, 60),   // AGI

    PRIEST("僧侶    ", 
        120, 200,   // HP
        90, 140,    // MP
        20, 60,    // STR
        30, 60,    // DEF
        1, 100,    // LUCK
        20, 60),;  // AGI

    private final String breed;

    private final int maxHpMin;
    private final int maxHpMax;

    private final int mpMin;
    private final int mpMax;

    private final int strMin;
    private final int strMax;

    private final int defMin;
    private final int defMax;

    private final int luckMin;
    private final int luckMax;

    private final int agiMin;
    private final int agiMax;

    private PlayerConfig(
        String breed, 
        int maxHpMin, int maxHpMax, 
        int mpMin, int mpMax,
        int strMin, int strMax,
        int defMin, int defMax,
        int luckMin, int luckMax,
        int agiMin, int agiMax) {

        this.breed = breed;

        this.maxHpMin = maxHpMin;
        this.maxHpMax = maxHpMax;

        this.mpMin = mpMin;
        this.mpMax = mpMax;

        this.strMin = strMin;
        this.strMax = strMax;
    
        this.defMin = defMin;
        this.defMax = defMax;
    
        this.luckMin = luckMin;
        this.luckMax = luckMax;
    
        this.agiMin = agiMin;
        this.agiMax = agiMax;
    }

    public int randomHp() {
        return generateRandomIntInRange(this.maxHpMin, this.maxHpMax);
    }

    public int randomMp() {
        return generateRandomIntInRange(this.mpMin, this.mpMax);
    }

    public int randomStr() {
        return generateRandomIntInRange(this.strMin, this.strMax);
    }

    public int randomDef() {
        return generateRandomIntInRange(this.defMin, this.defMax);
    }

    public int randomLuck() {
        return generateRandomIntInRange(this.luckMin, this.luckMax);
    }

    public int randomAgi() {
        return generateRandomIntInRange(this.agiMin, this.agiMax);
    }

    public String breed() {
        return this.breed;
    }

    public int maxHpMin() {
        return this.maxHpMin;
    }

    public int maxHpMax() {
        return this.maxHpMax;
    }

    public int mpMin() {
        return this.mpMin;
    }

    public int mpMax() {
        return this.mpMax;
    }

    public int strMin() {
        return this.strMin;
    }

    public int strMax() {
        return this.strMax;
    }

    public int defMin() {
        return this.defMin;
    }

    public int defMax() {
        return this.defMax;
    }

    public int luckMin() {
        return this.luckMin;
    }

    public int luckMax() {
        return this.luckMax;
    }

    public int agiMin() {
        return this.agiMin;
    }

    public int agiMax() {
        return this.agiMax;
    }
}
