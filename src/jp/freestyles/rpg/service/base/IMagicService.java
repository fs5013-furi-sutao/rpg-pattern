package jp.freestyles.rpg.service.base;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.status.Status;

public interface IMagicService {
    void chant(Status heroStatus, String magicName);
    void showDamage(Status enemyStatus, int damage, int hpBeforeAttack,int hpAfterAttack);
    int getDamageValueInRange(int min, int max);
    boolean isHealable(IMagic magic);
    boolean isAttackable(IMagic magic);
}
