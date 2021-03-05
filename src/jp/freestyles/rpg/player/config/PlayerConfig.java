package jp.freestyles.rpg.player.config;

import java.util.concurrent.ThreadLocalRandom;

public enum PlayerConfig {

    FIGHTER("戦士", 
        100, 300,  // HP
        0, 0,      // MP
        30, 100,   // STR
        30, 100,   // DEF
        1, 100,    // LUCK
        1, 50),    // AGI

    WIZARD("魔法使い", 
        80, 200,   // HP
        30, 80,    // MP
        1, 50,     // STR
        1, 50,     // DEF
        1, 100,    // LUCK
        20, 60),   // AGI

    PRIEST("僧侶", 
        50, 150,   // HP
        20, 50,    // MP
        10, 70,    // STR
        10, 70,    // DEF
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
        return ThreadLocalRandom.current()
            .nextInt(this.maxHpMin, this.maxHpMax + 1);
    }

    public int randomMp() {
        return ThreadLocalRandom.current()
            .nextInt(this.mpMin, this.mpMax + 1);
    }

    public int randomStr() {
        return ThreadLocalRandom.current()
            .nextInt(this.strMin, this.strMax + 1);
    }

    public int randomDef() {
        return ThreadLocalRandom.current()
            .nextInt(this.defMin, this.defMax + 1);
    }

    public int randomLuck() {
        return ThreadLocalRandom.current()
            .nextInt(this.luckMin, this.luckMax + 1);
    }

    public int randomAgi() {
        return ThreadLocalRandom.current()
            .nextInt(this.agiMin, this.agiMax + 1);
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
