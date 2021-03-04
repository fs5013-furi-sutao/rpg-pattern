package jp.freestyles.rpg.status;

import jp.freestyles.rpg.magic.base.IMagic;

public class Status {

    private int maxHp;
    private int hp;
    private int mp;

    public static class Builder {
        private int maxHp;
        private int hp;
        private int mp;

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
            if (this.maxHp == 0 || this.hp == 0 || this.mp == 0) {
                throw new NullPointerException();
            }
            return new Status(this);
        }
    }

    private Status(Builder builder) {
        this.maxHp = builder.maxHp;
        this.hp = builder.hp;
        this.mp = builder.mp;
    }

	public void show() {
        System.out.format(
            "MaxHP=%d, HP=%d, MP=%d %n", this.maxHp, this.hp, this.mp);
	}

	public boolean isFullFillMp(int consumptionMp) {
		return this.mp >= consumptionMp;
	}

	public String getContents() {
		return String.format(
            "MaxHP=%d, HP=%d, MP=%d", this.maxHp, this.hp, this.mp);
	}

	public void minusHp(int damageValue) {
        this.hp -= damageValue;
        if (this.hp < 0) this.hp = 0;
	}

	public boolean isEnoughLossForHeeling(IMagic magic, Status status) {
		return magic.teachHowMuchHealHp() < status.teachDecreasedHp();
	}

    private int teachDecreasedHp() {
        return this.maxHp - this.hp;
    }
}
