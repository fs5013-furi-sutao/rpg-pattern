package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.PARALYZE;
import static jp.freestyles.rpg.util.RandomGenerator.isInRate;

public class Paralyze implements IMagic, Attackable {
    
    private static final String MAGIC_NAME = PARALYZE.outName();
    private static final int CONSUMPTION_MP = 10;

    // 毒状態にする
    // 毒：毎ターン20のダメージを受ける
    private static final int EVERY_ROUND_DAMAGE = 20;

    // 10% の確率で麻痺状態を抜けられる
    private static final int EXTRACT_PARALYZED_RATE = 10;

    private IMagicService service;

    public Paralyze(IMagicService service) {
        this.service = service;
    }

    @Override
    public void effect(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        enemyStatus.beParalyzed();
        showParalyzedStatus(enemyStatus);

        heroStatus.minusMp(CONSUMPTION_MP);

        whetherExtractParalyzedOrNot(enemyStatus);
        System.out.println();
    }

    public static void effectMe(Status heroStatus) {
        chantMe(heroStatus);

        whetherExtractParalyzedOrNot(heroStatus);
        System.out.println();
    }

    private static void chantMe(Status heroStatus) {
        System.out.format(
            "%s は麻痺して動けない %n", heroStatus.outName());
    }

    public static void showMeDamage(Status heroStatus, int damage, int hpBeforeAttack,int hpAfterAttack) {
        System.out.format(
            "%s は %d のダメージを受けた (HP:%d -> %d) %n%n", 
            heroStatus.outName(), damage, 
            hpBeforeAttack, hpAfterAttack);
    }

    private static void whetherExtractParalyzedOrNot(Status heroStatus) {
        boolean result = isInRate((double) EXTRACT_PARALYZED_RATE / 100);
        if (result) {
            heroStatus.extractParalyzed();
            showExtractParalyzedStatus(heroStatus);
        }
    }

    private static void showExtractParalyzedStatus(Status heroStatus) {
        System.out.format(
            "%s は麻痺状態から抜け出した %n", 
            heroStatus.outName());
    }

    private void showParalyzedStatus(Status enemyStatus) {
        System.out.format(
            "%s は麻痺させられた %n", 
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
}
