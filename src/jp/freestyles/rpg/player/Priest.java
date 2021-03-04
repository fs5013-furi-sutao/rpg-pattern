package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.Heel;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.Poison;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class Priest implements IPlayer {

    private String name;
    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Priest(IPlayerService service, String name) {
        this.name = name;
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(20).build();
        this.magics = new MagicSet();
        this.magics.addMagic(new Paralyze());
        this.magics.addMagic(new Heel());
        this.magics.addMagic(new Poison());
    }

    private void showStatus() {
        String statusContents = this.status.getContents();
        System.out.format("[%s] { %s } %n", this.name, statusContents);
    }

    public void attack(IPlayer enemy) {

        showStatus();

        // IMagic attackableMagic = this.magics.getRandomUsefulAttackableMagic(this.status);
        // if (attackableMagic != null) {
        //     System.out.println(attackableMagic.toString());
        //     attackableMagic.effect(this.status);
        //     return;
        // }

        IMagic heelableMagic = this.magics.getRandomUsefulHeelableMagic(this.status);
        if (heelableMagic != null) {
            System.out.println(heelableMagic.toString());
            heelableMagic.effect(this.status);
            return;
        }

        this.status.show();
        this.service.execute();
    }
}
