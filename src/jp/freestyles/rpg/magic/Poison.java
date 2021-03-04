package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Heelable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

public class Poison implements IMagic, Attackable {
    
    private static final int CONSUMPTION_MP = 10;

    // 毒状態にする
    // 毒：毎ターン20のダメージを受ける
    private static final int EVERY_ROUND_DAMAGE = 20;

    private IMagicService service;

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
        System.out.println("Poison を唱えた");
    }

    public int getDamageValue() {
        return EVERY_ROUND_DAMAGE;
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
