package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.magic.type.Healable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.PARALYZE;

public class Paralyze implements IMagic, Attackable {
    
    private static final String MAGIC_NAME = PARALYZE.outName();
    private static final int CONSUMPTION_MP = 10;

    // 毒状態にする
    // 毒：毎ターン20のダメージを受ける
    private static final int EVERY_ROUND_DAMAGE = 20;

    private IMagicService service;

    @Override
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
    public boolean isHealable() {
        return this instanceof Healable;
    }

    @Override
    public int teachHowMuchHealHp() {
        return 0;
    }

    @Override
    public void showDamage(Status enemyStatus, int damage, int hpBeforeAttack, int hpAfterAttack) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getDamageValueInRange(int min, int max) {
        // TODO Auto-generated method stub
        return 0;
    }
}
