package jp.freestyles.rpg.magic;

import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Heelable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

public class Paralyze implements IMagic, Attackable {
    
    private static final int CONSUMPTION_MP = 10;

    private IMagicService service;

    public void effect(Status status) {

    }

    public int getDamageValue() {
        return 0;
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

    @Override
    public int teachHowMuchHealHp() {
        return 0;
    }
}
