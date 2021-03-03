package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.MagicSet;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.status.Status;
import jp.freestyles.rpg.service.base.IService;

public class Fighter implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IService service;

    public Fighter(IService service) {
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(12).build();
        this.magics = new MagicSet();
    }

    public void attack() {

        if (this.magics.hasHeelMagic()) {
            System.out.println("Heel が使えるよ");
        }

        this.status.show();
        this.service.execute();
    }
}
  