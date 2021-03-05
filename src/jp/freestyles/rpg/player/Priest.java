package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.Heal;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.Poison;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

import static jp.freestyles.rpg.player.config.PlayerConfig.PRIEST;

public class Priest implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Priest(IPlayerService service, String name) {
        this.service = service;
            
        int randomizedHp = PRIEST.randomHp();
        int randomizedMp = PRIEST.randomMp();

        this.status = new Status.Builder(name)
            .breed(PRIEST.breed())
            .maxHp(randomizedHp)
            .hp(randomizedHp)
            .maxMp(randomizedMp)
            .mp(randomizedMp)
            .str(PRIEST.randomStr())
            .def(PRIEST.randomDef())
            .luck(PRIEST.randomLuck())
            .agi(PRIEST.randomAgi())
            .build();

        this.magics = new MagicSet();
        this.magics.addMagic(new Paralyze());
        this.magics.addMagic(new Heal());
        this.magics.addMagic(new Poison());
    }

    @Override
    public void showStatus() {
        this.status.show();
    }

    public void attack(IPlayer enemy) {

        showDaclareAttack();

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

        // this.status.show();
        this.service.attack(this.status, enemy.outStatus());
    }

    private void showDaclareAttack() {
        System.out.format("%s の攻撃 %n", this.status.outName());
    }

    @Override
    public Status outStatus() {
        return this.status;
    }

    @Override
    public boolean isDead() {
        return this.status.isHpEmpty();
    }
}
