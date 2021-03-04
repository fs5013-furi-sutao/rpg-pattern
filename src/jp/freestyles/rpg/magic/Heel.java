package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Heelable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

public class Heel implements IMagic, Heelable {
    
    private static final int CONSUMPTION_MP = 20;

    // HP を50 回復
    private static final int HP_FOR_RECOVERY = 50;

    private IMagicService service;

    public void effect(Status status) {

    }

    public int getDamageValue() {
        return 0;
    }

    public int teachHowMuchHealHp() {
        return this.HP_FOR_RECOVERY;
    }

    @Override
    public boolean isFullfillMp(Status status) {
        return status.isFullFillMp(CONSUMPTION_MP);
    }

    @Override
    public boolean isAttackable() {
        return this instanceof Attackable;
    }

    @Override
    public boolean isHeelable() {
        return this instanceof Heelable;
    }
}
