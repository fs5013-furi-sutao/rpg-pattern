package jp.freestyles.rpg.magic;

import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Healable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.FIRE;

public class Fire implements IMagic, Attackable {

    private static final String MAGIC_NAME = FIRE.outName();
    private static final int CONSUMPTION_MP = 10;

    // 敵に 10 ～ 30 の防御無視ダメージ
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 30;

    private IMagicService service;

    public Fire(IMagicService service) {
        this.service = service;
    }

    public void effect(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        int damage = getDamageValue();
        enemyStatus.minusHp(damage);
        showDamage(enemyStatus, damage);
        heroStatus.minusMp(CONSUMPTION_MP);
        System.out.println();
    }

    private void showDamage(Status enemyStatus, int damage) {
        System.out.format(
            "%s に %d のダメージを与えた %n", 
            enemyStatus.outName(), damage);
    }

    public void chant(Status heroStatus) {
        System.out.format(
            "%s が %s を唱えた %n", 
            heroStatus.outName(), MAGIC_NAME);
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
    public boolean isHealable() {
        return this instanceof Healable;
    }

    @Override
    public int teachHowMuchHealHp() {
        return 0;
    }
}
