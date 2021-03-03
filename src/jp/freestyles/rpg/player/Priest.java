package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.Heel;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.Poison;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class Priest implements IPlayer {

    private MagicSet magics;
    private Status status;
    private boolean isCompletedToAttack;

    private IPlayerService service;

    public Priest(IPlayerService service) {
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(12).build();
        this.magics = new MagicSet();
        this.magics.addMagic(new Paralyze());
        this.magics.addMagic(new Heel());
        this.magics.addMagic(new Poison());
    }

    public void attack(IPlayer enemy) {

        if (this.magics.hasAttackableMagic()) {

            // if () {

            // }
            System.out.println("AttackMagic が使えるよ");
        }

        if (this.magics.hasHeelMagic()) {
            System.out.println("HeelMagic が使えるよ");
        }

        this.status.show();
        this.service.execute();
    }
}
  