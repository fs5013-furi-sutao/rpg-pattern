package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.Fire;
import jp.freestyles.rpg.magic.Heel;
import jp.freestyles.rpg.magic.MagicSet;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Priest;
import jp.freestyles.rpg.service.base.IService;
import jp.freestyles.rpg.status.Status;

public class Priest implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IService service;

    public Priest(IService service) {
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(12).build();
        this.magics = new MagicSet();
        this.magics.addMagic(new Fire());
        this.magics.addMagic(new Heel());
    }

    public void attack() {

        if (this.magics.hasHeelMagic()) {
            System.out.println("Heel が使えるよ");
        }

        this.status.show();
        this.service.execute();
    }
}
  