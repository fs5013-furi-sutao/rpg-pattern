package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.POISON;
import static jp.freestyles.rpg.util.RandomGenerator.isInRate;

public class Poison implements IMagic, Attackable {
    
    private static final String MAGIC_NAME = POISON.outName();
    private static final int CONSUMPTION_MP = 20;

    // 毒状態にする
    // 毒：毎ターン20のダメージを受ける
    private static final int EVERY_ROUND_DAMAGE = 20;

    // 40% の確率で毒状態を抜けられる
    private static final int EXTRACT_POISONED_RATE = 40;

    private IMagicService service;

    public Poison(IMagicService service) {
        this.service = service;
    }

    @Override
    public void effect(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        enemyStatus.bePoisoned();
        showPoisonedStatus(enemyStatus);

        int damage = getDamageValue();

        int hpBeforeAttack = enemyStatus.outHp();
        enemyStatus.minusHp(damage);
        int hpAfterAttack = enemyStatus.outHp();
        showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);

        heroStatus.minusMp(CONSUMPTION_MP);

        whetherExtractPoisonedOrNot(enemyStatus);
        System.out.println();
    }

    private void showPoisonedStatus(Status enemyStatus) {
        System.out.format(
            "%s は毒にかかった %n", 
            enemyStatus.outName());
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
        return this.service.isAttackable(this);
    }

    @Override
    public boolean isHealable() {
        return this.service.isHealable(this);
    }

    @Override
    public int teachHowMuchHealHp() {
        return 0;
    }

    @Override
    public void showDamage(Status enemyStatus, int damage, int hpBeforeAttack, int hpAfterAttack) {
        this.service.showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);
    }

    @Override
    public int getDamageValueInRange(int min, int max) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void effectMe(Status heroStatus) {
        chantMe(heroStatus);

        int damage = EVERY_ROUND_DAMAGE;

        int hpBeforeAttack = heroStatus.outHp();
        heroStatus.minusHp(damage);
        int hpAfterAttack = heroStatus.outHp();
        showMeDamage(heroStatus, damage, hpBeforeAttack, hpAfterAttack);

        whetherExtractPoisonedOrNot(heroStatus);
        System.out.println();
    }

    private static void whetherExtractPoisonedOrNot(Status heroStatus) {
        boolean result = isInRate((double) EXTRACT_POISONED_RATE / 100);
        if (result) {
            heroStatus.extractPoisoned();
            showExtractPoisonedStatus(heroStatus);
        }
    }

    private static void showExtractPoisonedStatus(Status heroStatus) {
        System.out.format(
            "%s は毒状態から抜け出した %n", 
            heroStatus.outName());
    }

    public static void showMeDamage(Status heroStatus, int damage, int hpBeforeAttack,int hpAfterAttack) {
        System.out.format(
            "%s は %d のダメージを受けた (HP:%d -> %d) %n", 
            heroStatus.outName(), damage, 
            hpBeforeAttack, hpAfterAttack);
    }

    private static void chantMe(Status heroStatus) {
        System.out.format(
            "%s は毒にかかっている %n", 
            heroStatus.outName());
    }
}
