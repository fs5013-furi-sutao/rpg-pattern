package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.status.Status;
import jp.freestyles.rpg.service.base.IPlayerService;

public class Fighter implements IPlayer {

    private String name;
    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Fighter(IPlayerService service, String name) {
        this.name = name;
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(12).build();
        this.magics = new MagicSet();
    }

    private void showStatus() {
        String statusContents = this.status.getContents();
        System.out.format("[%s] { %s } %n", this.name, statusContents);
    }

    public void attack(IPlayer enemy) {

        showStatus();

        if (this.magics.hasHeelMagic()) {
            System.out.println("Heel が使えるよ");
        }

        this.status.show();
        this.service.execute();
    }
}
