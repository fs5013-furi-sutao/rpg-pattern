package jp.freestyles.rpg.service;

import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.util.RandomGenerator.isInRate;

public class StandardPlayerService implements IPlayerService {

    public void attack(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);

        int damage = 0;
        if (isCriticalHit(heroStatus, enemyStatus)) {
            damage = getCriticalHitValue(heroStatus);
            showOccuredCriticalHit();
        } else {
            damage = getDamageValue(heroStatus, enemyStatus);
        }

        if (damage == 0) {
            showNoDamage(enemyStatus);
            return;
        }

        int hpBeforeAttack = enemyStatus.outHp();
        enemyStatus.minusHp(damage);
        int hpAfterAttack = enemyStatus.outHp();
        showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);
    }

    private void showOccuredCriticalHit() {
        System.out.println("会心の一撃");
    }

    private boolean isCriticalHit(Status heroStatus, Status enemyStatus) {
        double rate = heroStatus.outLuckRate() - enemyStatus.outLuckRate();
        rate = rate > 0 ? rate : 0.0;
        return isInRate(rate);
    }

    private void showNoDamage(Status enemyStatus) {
        System.out.format("%s にダメージを与えられなかった %n%n", enemyStatus.outName());
    }

    private void showDamage(Status enemyStatus, int damage, int hpBeforeAttack, int hpAfterAttack) {
        System.out.format("%s に %d のダメージを与えた (HP:%d -> %d) %n%n", enemyStatus.outName(), damage, hpBeforeAttack,
                hpAfterAttack);
    }

    public void chant(Status heroStatus) {
        System.out.format("%s が通常攻撃した %n", heroStatus.outName());
    }

    public int getDamageValue(Status heroStatus, Status enemyStatus) {
        return Status.calcNormalAttackDamage(heroStatus, enemyStatus);
    }

    public int getCriticalHitValue(Status heroStatus) {
        return heroStatus.outStr();
    }
}
