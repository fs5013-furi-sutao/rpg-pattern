package jp.freestyles.rpg.player.config;

public enum PlayerConfig {

    FIGHTER("戦士", 100, 100, 0), 
    PRIEST("僧侶", 80, 80, 100), 
    WIZARD("魔法使い", 70, 70, 200),;

    private final String breed;
    private final int maxHp;
    private final int hp;
    private final int mp;

    private PlayerConfig(
        String breed, int maxHp, int hp, int mp) {

        this.breed = breed;
        this.maxHp = maxHp;
        this.hp = hp;
        this.mp = mp;
    }

    public String breed() {
        return this.breed;
    }

    public int maxHp() {
        return this.maxHp;
    }

    public int hp() {
        return this.hp;
    }

    public int mp() {
        return this.mp;
    }
}
