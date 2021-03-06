package jp.freestyles.rpg.magic.base;

import jp.freestyles.rpg.status.Status;

public interface IMagic {

    void effect(Status heroStatus, Status enemyStatus);
    void chant(Status heroStatus);
    void showDamage(Status enemyStatus, int damage, int hpBeforeAttack,int hpAfterAttack);
    int getDamageValue();
    int getDamageValueInRange(int min, int max);
	boolean isFullfillMp(Status status);
    boolean isAttackable();
    boolean isHealable();
	int teachHowMuchHealHp();
}
