package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.Poison;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.status.Status;
import jp.freestyles.rpg.service.base.IPlayerService;

import static jp.freestyles.rpg.player.config.PlayerConfig.FIGHTER;

public class Fighter implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Fighter(IPlayerService service, String name) {
        this.service = service;
        
        int randomizedHp = FIGHTER.randomHp();
        int randomizedMp = FIGHTER.randomMp();
        
        this.status = new Status.Builder(name)
            .breed(FIGHTER.breed())
            .maxHp(randomizedHp)
            .hp(randomizedHp)
            .maxMp(randomizedMp)
            .mp(randomizedMp)
            .str(FIGHTER.randomStr())
            .def(FIGHTER.randomDef())
            .luck(FIGHTER.randomLuck())
            .agi(FIGHTER.randomAgi())
            .build();
        
        this.magics = new MagicSet();
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

        this.service.attack(this.status, enemy.outStatus());

        if (enemy.isDead()) enemy.showDeadStatus();
        System.out.println();
    }

    @Override
    public void showStatus() {
        this.status.show();
    }

    private void showDaclareAttack() {
        this.status.showDaclareAttack();
    }

    public Status outStatus() {
        return this.status;
    }

    public MagicSet outMagics() {
        return this.magics;
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
