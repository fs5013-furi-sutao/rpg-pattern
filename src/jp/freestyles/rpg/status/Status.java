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

    private boolean isParalyzed;
    private boolean isPoisoned;

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
        this.isParalyzed = false;
        this.isPoisoned = false;
    }

	public void show() {
        System.out.println(getContents());
	}

	public boolean isFullFillMp(int consumptionMp) {
		return this.mp >= consumptionMp;
    }

    public void showDaclareAttack() {
        System.out.format("%s の攻撃 %n", this.name);
    }
    
    public void showSurvivedStatus() {
        System.out.format(
            "%s %s が最後に生き残った %n", getStatusOfLiveMark(), this.name);
    }

    public void showDeadStatus() {
        System.out.format("%s は力尽きた %n", this.name);
    }

	public String getContents() {
        String paralyzeStatus = isParalyzed ? "麻痺🆘" : "麻痺🆗";
        String poisonedStatus = isPoisoned ? "毒🆘" : "毒🆗";

		return String.format(
            "%s %s [%s: HP=%3d, MP=%2d, %s, %s] MaxHP=%3d, MaxMp=%2d, "
            + "STR=%2d, DEF=%2d, LUCK=%2d, AGI=%2d", 
            getStatusOfLiveMark(),
            this.name, this.breed, 
            this.hp, this.mp,
            paralyzeStatus, poisonedStatus,
            this.maxHp, this.maxMp,
            this.str, this.def, this.luck, this.agi);
	}

	private String getStatusOfLiveMark() {
        if (isHpEmpty()) return "💀";
        return "😀";
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

    public int outAgi() {
        return this.agi;
    }

    public double outLuckRate() {
        double rate = (double) this.luck / 100;
        return rate;
    }

    public static int calcNormalAttackDamage(Status heroStatus, Status enemyStatus) {
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
    
    public void beParalyzed() {
        this.isParalyzed = true;
    }

    public void bePoisoned() {
        this.isPoisoned = true;
    }

    public boolean isParalyzed() {
        return this.isParalyzed;
    }

    public boolean isPoisoned() {
        return this.isPoisoned;
    }

    public void extractParalyzed() {
        this.isParalyzed = false;
    }

    public void extractPoisoned() {
        this.isPoisoned = false;
    }
}
