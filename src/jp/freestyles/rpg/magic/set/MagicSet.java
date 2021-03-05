package jp.freestyles.rpg.magic.set;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.status.Status;

public class MagicSet {

    private Set<IMagic> magics;
    private Set<IMagic> usefulAttackableMagics;
    private Set<IMagic> usefulHeelableMagics;

    public MagicSet() {
        this.magics = new HashSet<>();
        this.usefulAttackableMagics = new HashSet<>();
        this.usefulHeelableMagics = new HashSet<>();
    }

    public void addMagic(IMagic magic) {
        magics.add(magic);
    }

    public boolean hasAttackableMagic() {
        for (IMagic magic : this.magics) {

            if (magic instanceof Attackable) {
                return true;
            }
        }
        return false;
    }

    public boolean hasHeelMagic() {
        for (IMagic magic : this.magics) {

            if (magic.isHealable()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullfillMp(Status status) {

        for (IMagic magic : this.magics) {

            if (magic.isFullfillMp(status))
                return true;
        }
        return false;
    }

    public boolean canUseAttackableMagic(Status status) {

        for (IMagic magic : this.magics) {

            if (magic.isAttackable() && magic.isFullfillMp(status))
                return true;
        }
        return false;
    }

    private void generateUsefulAttackableMagics(Status status) {
        for (IMagic magic : this.magics) {

            if (magic.isAttackable() && magic.isFullfillMp(status))
                this.usefulAttackableMagics.add(magic);
        }
    }

    private void generateUsefulHealableMagics(Status status) {
        for (IMagic magic : this.magics) {

            if (magic.isHealable() && magic.isFullfillMp(status) && status.isEnoughLossForHealing(magic, status)) {

                this.usefulHeelableMagics.add(magic);
            }
        }
    }

    public IMagic getRandomUsefulAttackableMagic(Status status) {
        generateUsefulAttackableMagics(status);

        IMagic result = null;
        int count = this.usefulAttackableMagics.size();
        if (count == 0) return result;

        int randomIndex = ThreadLocalRandom.current().nextInt(count);

        int currentIndex = 0;
        for (IMagic magic : this.usefulAttackableMagics) {

            if (currentIndex == randomIndex)
                result = magic;
            currentIndex++;
        }

        this.usefulAttackableMagics.clear();
        return result;
    }

    public IMagic getRandomUsefulHeelableMagic(Status status) {
        generateUsefulHealableMagics(status);

        IMagic result = null;
        int count = this.usefulHeelableMagics.size();
        if (count == 0) return result;

        int randomIndex = ThreadLocalRandom.current().nextInt(count);

        int currentIndex = 0;
        for (IMagic magic : this.usefulHeelableMagics) {

            if (currentIndex == randomIndex)
                result = magic;
            currentIndex++;
        }

        this.usefulHeelableMagics.clear();
        return result;
    }
}
