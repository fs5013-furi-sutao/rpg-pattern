package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.FIRE;
import static jp.freestyles.rpg.util.RandomGenerator.generateRandomIntInRange;

public class Fire implements IMagic, Attackable {

    private static final String MAGIC_NAME = FIRE.outName();
    private static final int CONSUMPTION_MP = 10;

    // 敵に 10 ～ 30 の防御無視ダメージ
    private static final int MIN_DAMAGE = 10;
    private static final int MAX_DAMAGE = 30;

    private IMagicService service;

    public Fire(IMagicService service) {
        this.service = service;
    }

    @Override
    public void effect(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        int damage = getDamageValueInRange(MIN_DAMAGE, MAX_DAMAGE);
        
        int hpBeforeAttack = enemyStatus.outHp();
        enemyStatus.minusHp(damage);
        int hpAfterAttack = enemyStatus.outHp();
        showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);

        heroStatus.minusMp(CONSUMPTION_MP);
        System.out.println();
    }

    public void showDamage(Status enemyStatus, int damage, int hpBeforeAttack,int hpAfterAttack) {
        this.service.showDamage(enemyStatus, damage, hpBeforeAttack, hpAfterAttack);
    }

    public void chant(Status heroStatus) {
        this.service.chant(heroStatus, MAGIC_NAME);
    }

    @Override
    public int getDamageValue() {
        return generateRandomIntInRange(MIN_DAMAGE, MAX_DAMAGE);
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
    public int getDamageValueInRange(int min, int max) {
        return this.service.getDamageValueInRange(min, max);
    }
}
