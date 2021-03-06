package jp.freestyles.rpg.service;

import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class StandardPlayerService implements IPlayerService {

    public void attack(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        int damage = getDamageValue(heroStatus, enemyStatus);
        if (damage == 0) {
            showNoDamage(enemyStatus);
            return;
        }

        int hpBeforeAttack = enemyStatus.outHp();
        enemyStatus.minusHp(damage);
        int hpAfterAttack = enemyStatus.outHp();
        showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);      
    }

    private void showNoDamage(Status enemyStatus) {
        System.out.format(
            "%s にダメージを与えられなかった %n%n", 
            enemyStatus.outName());
    }

    private void showDamage(Status enemyStatus, int damage, int hpBeforeAttack,int hpAfterAttack) {
        System.out.format(
            "%s に %d のダメージを与えた (HP:%d -> %d) %n%n", 
            enemyStatus.outName(), damage, 
            hpBeforeAttack, hpAfterAttack);
    }

    public void chant(Status heroStatus) {
        System.out.format(
            "%s が通常攻撃した %n", 
            heroStatus.outName());
    }

    public int getDamageValue(Status heroStatus, Status enemyStatus) {
        return Status.calcNormalAttackDamege(heroStatus, enemyStatus);
    }
}
