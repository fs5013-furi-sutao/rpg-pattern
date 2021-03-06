package jp.freestyles.rpg.status;

import jp.freestyles.rpg.magic.base.IMagic;

import static jp.freestyles.rpg.util.RandomGenerator.generateRandomInt;

public class Status {

    private String name;
    private String breed;
    private int maxHp;
    private int hp;
    private int maxMp;
    private int mp;
    private int str;
    private int def;
    private int luck;
    private int agi;

    public static class Builder {
        private String name;
        private String breed;
        private int maxHp;
        private int hp;
        private int maxMp;
        private int mp;
        private int str;
        private int def;
        private int luck;
        private int agi;

        public Builder(String name) {
            this.name = name;
        }

        public Builder breed(String breed) {
            this.breed = breed;
            return this;
        }

        public Builder maxHp(int maxHp) {
            this.maxHp = maxHp;
            return this;
        }

        public Builder hp(int hp) {
            this.hp = hp;
            return this;
        }

        public Builder maxMp(int maxMp) {
            this.maxMp = maxMp;
            return this;
        }

        public Builder mp(int mp) {
            this.mp = mp;
            return this;
        }

        public Builder str(int str) {
            this.str = str;
            return this;
        }

        public Builder def(int def) {
            this.def = def;
            return this;
        }

        public Builder luck(int luck) {
            this.luck = luck;
            return this;
        }

        public Builder agi(int agi) {
            this.agi = agi;
            return this;
        }

        public Status build() {
            if (this.name == null
                || this.breed == null
                || this.maxHp == 0 
                || this.hp == 0
                || this.str == 0
                || this.def == 0
                || this.luck == 0
                || this.agi == 0) {
                throw new NullPointerException();
            }
            return new Status(this);
        }
    }

    private Status(Builder builder) {
        this.name = builder.name;
        this.breed = builder.breed;
        this.maxHp = builder.maxHp;
        this.hp = builder.hp;
        this.maxMp = builder.maxMp;
        this.mp = builder.mp;
        this.str = builder.str;
        this.def = builder.def;
        this.luck = builder.luck;
        this.agi = builder.agi;
    }

	public void show() {
        System.out.println(getContents());
	}

	public boolean isFullFillMp(int consumptionMp) {
		return this.mp >= consumptionMp;
	}

	public String getContents() {
		return String.format(
            "%s [%s] MaxHP=%d, HP=%d, MaxMp=%d, MP=%d, "
            + "STR=%d, DEF=%d, LUCK=%d, AGI=%d", 
            this.name, this.breed, 
            this.maxHp, this.hp, 
            this.maxMp, this.mp,
            this.str, this.def, this.luck, this.agi);
	}

	public void minusHp(int damageValue) {
        this.hp -= damageValue;
        if (this.hp < 0) this.hp = 0;
	}

    public void minusMp(int consumptionMp) {
        this.mp -=consumptionMp;
        if (this.mp < 0) this.mp = 0;
	}

	public boolean isEnoughLossForHealing(IMagic magic, Status status) {
		return magic.teachHowMuchHealHp() < status.teachDecreasedHp();
	}

    private int teachDecreasedHp() {
        return this.maxHp - this.hp;
    }

	public String outName() {
		return this.name;
	}

    public int outHp() {
		return this.hp;
	}

    public int outMp() {
		return this.mp;
    }

    public int outStr() {
        return this.str;
    }

    public int outDef() {
        return this.def;
    }   

    public static int calcNormalAttackDamege(Status heroStatus, Status enemyStatus) {
        int damage = getRandomStr(heroStatus) - enemyStatus.outDef();
        damage = damage > 0 ? damage : 0;
        return damage;
    }

	private static int getRandomStr(Status heroStatus) {
        return generateRandomInt(heroStatus.outStr());
    }

    public boolean isHpEmpty() {
		return this.hp == 0;
	}

	public void plusHp(int hpForRecovery) {
        this.hp += hpForRecovery;
	}
}
