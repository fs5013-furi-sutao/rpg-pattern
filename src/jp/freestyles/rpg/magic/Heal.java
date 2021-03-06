package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Healable;
import jp.freestyles.rpg.service.base.IMagicService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.magic.config.MagicConfig.HEAL;

public class Heal implements IMagic, Healable {
    
    private static final String MAGIC_NAME = HEAL.outName();
    private static final int CONSUMPTION_MP = 20;

    // HP を50 回復
    private static final int HP_FOR_RECOVERY = 50;

    private IMagicService service;

    public Heal(IMagicService service) {
        this.service = service;
    }

    public int teachHowMuchHealHp() {
        return HP_FOR_RECOVERY;
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
    public void effect(Status heroStatus, Status enemyStatus) {
        chant(heroStatus);
        showRecoveriedValue(heroStatus, HP_FOR_RECOVERY);
        heroStatus.plusHp(HP_FOR_RECOVERY);
        heroStatus.minusMp(CONSUMPTION_MP);
    }

    private void showRecoveriedValue(Status heroStatus, int hpForRecovery) {
        int currentHeroHp = heroStatus.outHp();

        System.out.format(
            "%s の HP が %d から %d に回復した %n%n", 
            heroStatus.outName(), 
            currentHeroHp,
            currentHeroHp + hpForRecovery);
    }

    public void chant(Status heroStatus) {
        System.out.format(
            "%s が %s を唱えた %n", 
            heroStatus.outName(), MAGIC_NAME);
    }

    @Override
    public int getDamageValue() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void showDamage(Status enemyStatus, int damage, int hpBeforeAttack, int hpAfterAttack) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getDamageValueInRange(int min, int max) {
        // TODO Auto-generated method stub
        return 0;
    }
}
