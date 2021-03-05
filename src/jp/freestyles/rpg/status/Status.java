package jp.freestyles.rpg.status;

import jp.freestyles.rpg.magic.base.IMagic;

public class Status {

    private String name;
    private String breed;
    private int maxHp;
    private int hp;
    private int mp;

    public static class Builder {
        private String name;
        private String breed;
        private int maxHp;
        private int hp;
        private int mp;

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

        public Builder mp(int mp) {
            this.mp = mp;
            return this;
        }

        public Status build() {
            if (this.name == null
                || this.breed == null
                || this.maxHp == 0 
                || this.hp == 0) {

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
        this.mp = builder.mp;
    }

	public void show() {
        System.out.println(getContents());
	}

	public boolean isFullFillMp(int consumptionMp) {
		return this.mp >= consumptionMp;
	}

	public String getContents() {
		return String.format(
            "%s [%s] MaxHP=%d, HP=%d, MP=%d", 
            this.name, this.breed, this.maxHp, this.hp, this.mp);
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

	public boolean isHpEmpty() {
		return this.hp == 0;
	}

	public void plusHp(int hpForRecovery) {
        this.hp += hpForRecovery;
	}
}
