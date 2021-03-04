package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Healable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

public class Heal implements IMagic, Healable {
    
    private static final int CONSUMPTION_MP = 20;

    // HP を50 回復
    private static final int HP_FOR_RECOVERY = 50;

    private IMagicService service;

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
        return this instanceof Healable;
    }

    @Override
    public void effect(Status heroStatus, Status enemyStatus) {
        // TODO Auto-generated method stub

    }
}
