package jp.freestyles.rpg.magic;

import java.util.HashSet;
import java.util.Set;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Heelable;

public class MagicSet {

    private Set<IMagic> magics;

    public MagicSet() {
        this.magics = new HashSet<>();
    }

    public void addMagic(IMagic magic) {
        magics.add(magic);
    }

    public boolean hasHeelMagic() {
        for (IMagic magic : this.magics) {

            if (magic instanceof Heelable) {
                return true;
            }
        }
        return false;
    }
}
