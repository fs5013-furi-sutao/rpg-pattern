package jp.freestyles.rpg.service;

import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class StandardPlayerService implements IPlayerService {

    private static final int NORMAL_DAMAGE = 20;

    public void attack(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        int damage = getDamageValue();
        enemyStatus.minusHp(damage);
        showDamage(enemyStatus, damage);      
        System.out.println();
    }

    private void showDamage(Status enemyStatus, int damage) {
        System.out.format(
            "%s に %d のダメージを与えた %n", 
            enemyStatus.outName(), damage);
    }

    public void chant(Status heroStatus) {
        System.out.format(
            "%s が通常攻撃した %n", 
            heroStatus.outName());
    }

    public int getDamageValue() {
        return NORMAL_DAMAGE;
    }
}
