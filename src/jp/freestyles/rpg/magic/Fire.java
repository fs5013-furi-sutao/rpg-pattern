package jp.freestyles.rpg.magic;

import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Heelable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

public class Fire implements IMagic, Attackable {

    private static final int CONSUMPTION_MP = 10;

    // 敵に 10 ～ 30 の防御無視ダメージ
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 30;

    private IMagicService service;

    public Fire(IMagicService service) {
        this.service = service;
    }

    public void effect(Status status) {
        chant();
        int damage = getDamageValue();
        status.minusHp(damage);
        showDamage(damage);
    }

    private void showDamage(int damage) {
        System.out.format("%d のダメージを受けた", damage);
    }

    public void chant() {
        System.out.println("Fire を唱えた");
    }

    public int getDamageValue() {
        return ThreadLocalRandom.current().nextInt(MIN_DAMAGE, MAX_DAMAGE + 1);
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
