package jp.freestyles.rpg.player.config;

public enum PlayerConfig {

    FIGHTER("戦士", 100, 300, 0), 
    PRIEST("僧侶", 50, 150, 100), 
    WIZARD("魔法使い", 80, 200, 200),;

    private final String breed;
    private final int maxHpMin;
    private final int maxHpMax;
    private final int mp;

    private PlayerConfig(
        String breed, int maxHpMin, int maxHpMax, int mp) {

        this.breed = breed;
        this.maxHpMin = maxHpMin;
        this.maxHpMax = maxHpMax;
        this.mp = mp;
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

    public int mp() {
        return this.mp;
    }
}
