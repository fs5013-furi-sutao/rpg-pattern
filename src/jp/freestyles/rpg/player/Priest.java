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

        this.status = new Status.Builder(name)
            .breed(PRIEST.breed())
            .maxHp(PRIEST.maxHp())
            .hp(PRIEST.hp())
            .mp(PRIEST.mp())
            .build();

        this.magics = new MagicSet();
        this.magics.addMagic(new Paralyze());
        this.magics.addMagic(new Heal());
        this.magics.addMagic(new Poison());
    }

    private void showStatus(Status status) {
        status.show();
    }

    public void attack(IPlayer enemy) {

        showStatus(this.status);
        showStatus(enemy.outStatus());
        showDaclareAttack();

        // IMagic attackableMagic = this.magics.getRandomUsefulAttackableMagic(this.status);
        // if (attackableMagic != null) {
        //     System.out.println(attackableMagic.toString());
        //     attackableMagic.effect(this.status, enemy.outStatus());
        //     return;
        // }

        // IMagic heelableMagic = this.magics.getRandomUsefulHeelableMagic(this.status);
        // if (heelableMagic != null) {
        //     System.out.println(heelableMagic.toString());
        //     heelableMagic.effect(this.status, enemy.outStatus());
        //     return;
        // }

        //this.status.show();
        this.service.attack(this.status, enemy.outStatus());
    }

    private void showDaclareAttack() {
        System.out.format("%s の攻撃 %n", this.status.outName());
    }

    @Override
    public Status outStatus() {
        return this.status;
    }
}
