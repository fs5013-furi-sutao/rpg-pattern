package jp.freestyles.rpg.service;

import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.util.RandomGenerator.generateRandomIntInRange;

public class StandardMagicService implements IMagicService {

    @Override
    public void chant(Status heroStatus, String magicName) {
        System.out.format(
            "%s が %s を唱えた %n", 
            heroStatus.outName(), magicName);
    }

    @Override
    public void showDamage(Status enemyStatus, int damage, int hpBeforeAttack,int hpAfterAttack) {
        System.out.format(
            "%s に %d のダメージを与えた (HP:%d -> %d) %n%n", 
            enemyStatus.outName(), damage, 
            hpBeforeAttack, hpAfterAttack);
    }

    @Override
    public int getDamageValueInRange(int min, int max) {
        return generateRandomIntInRange(min, max);
    }
}
