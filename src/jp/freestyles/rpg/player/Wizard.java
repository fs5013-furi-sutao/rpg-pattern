package jp.freestyles.rpg.player;

import jp.freestyles.rpg.injection.base.IMagicServiceInjector;
import jp.freestyles.rpg.injection.magic.FireInjector;
import jp.freestyles.rpg.injection.magic.ThunderInjector;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.Poison;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.player.config.PlayerConfig.WIZARD;

public class Wizard implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Wizard(IPlayerService service, String name) {
        this.service = service;
        
        int randomizedHp = WIZARD.randomHp();
        int randomizedMp = WIZARD.randomMp();

        this.status = new Status.Builder(name)
            .breed(WIZARD.breed())
            .maxHp(randomizedHp)
            .hp(randomizedHp)
            .maxMp(randomizedMp)
            .mp(randomizedMp)
            .str(WIZARD.randomStr())
            .def(WIZARD.randomDef())
            .luck(WIZARD.randomLuck())
            .agi(WIZARD.randomAgi())
            .build();
        
        this.magics = new MagicSet();

        IMagicServiceInjector fireInjector = new FireInjector();
        IMagic fire = fireInjector.getMagic();
        this.magics.addMagic(fire);

        IMagicServiceInjector thunderInjector = new ThunderInjector();
        IMagic thunder = thunderInjector.getMagic();
        this.magics.addMagic(thunder);
    }

    @Override
    public void showStatus() {
        this.status.show();
    }

    public void attack(IPlayer enemy) {

        showDaclareAttack();
        if (this.status.isPoisoned()) {
            Poison.effectMe(this.status);
            return;
        }
        if (this.status.isParalyzed()) {
            Paralyze.effectMe(this.status);
            return;
        }

        IMagic heelableMagic = this.magics.getRandomUsefulHeelableMagic(this.status);
        if (heelableMagic != null) {
            heelableMagic.effect(this.status, enemy.outStatus());
            return;
        }

        IMagic attackableMagic = this.magics.getRandomUsefulAttackableMagic(this.status);
        if (attackableMagic != null) {
            attackableMagic.effect(this.status, enemy.outStatus());
            return;
        }

        this.service.attack(this.status, enemy.outStatus());

        if (enemy.isDead()) enemy.showDeadStatus();
        System.out.println();
    }

    private void showDaclareAttack() {
        this.status.showDaclareAttack();
    }

    @Override
    public Status outStatus() {
        return this.status;
    }

    @Override
    public boolean isDead() {
        return this.status.isHpEmpty();
    }

    @Override
    public void showDeadStatus() {
        this.status.showDeadStatus();
    }

    @Override
    public void showSurvivedStatus() {
        this.status.showSurvivedStatus();
    }
}
