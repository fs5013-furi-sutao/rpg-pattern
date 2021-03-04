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

            if (magic.isHeelable()) {
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

    private void generateUsefulHeelableMagics(Status status) {
        for (IMagic magic : this.magics) {

            if (magic.isHeelable() 
                && magic.isFullfillMp(status) 
                && status.isEnoughLossForHeeling(magic, status)) {
                
                    this.usefulHeelableMagics.add(magic);
            }
        }
    }

    public IMagic getRandomUsefulAttackableMagic(Status status) {
        generateUsefulAttackableMagics(status);

        int count = this.usefulAttackableMagics.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(count);

        int currentIndex = 0;
        IMagic result = null;
        for (IMagic magic : this.usefulAttackableMagics) {

            if (currentIndex == randomIndex) result = magic;
            currentIndex++;
        }

        return result;
    }

    public IMagic getRandomUsefulHeelableMagic(Status status) {
        generateUsefulHeelableMagics(status);

        int count = this.usefulHeelableMagics.size();
        int randomIndex = ThreadLocalRandom.current().nextInt(count);

        int currentIndex = 0;
        IMagic result = null;
        for (IMagic magic : this.usefulHeelableMagics) {

            if (currentIndex == randomIndex) result = magic;
            currentIndex++;
        }

        return result;
    }
}
