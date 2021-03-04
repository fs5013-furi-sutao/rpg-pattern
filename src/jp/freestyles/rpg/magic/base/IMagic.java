package jp.freestyles.rpg.magic.base;

import jp.freestyles.rpg.status.Status;

public interface IMagic {

    void effect(Status heroStatus, Status enemyStatus);
    int getDamageValue();
	boolean isFullfillMp(Status status);
    boolean isAttackable();
    boolean isHeelable();
	int teachHowMuchHealHp();
}
