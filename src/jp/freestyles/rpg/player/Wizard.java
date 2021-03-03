package jp.freestyles.rpg.player;

import jp.freestyles.rpg.injection.base.IMagicServiceInjector;
import jp.freestyles.rpg.injection.magic.FireInjector;
import jp.freestyles.rpg.injection.magic.ThunderInjector;
import jp.freestyles.rpg.magic.Fire;
import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.magic.Thunder;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class Wizard implements IPlayer {

    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Wizard(IPlayerService service) {
        this.service = service;
        this.status = new Status.Builder().maxHp(100).hp(10).mp(12).build();
        this.magics = new MagicSet();

        IMagicServiceInjector fireInjector = new FireInjector();
        IMagic fire = fireInjector.getMagic();
        this.magics.addMagic(fire);

        IMagicServiceInjector thunderInjector = new ThunderInjector();
        IMagic thunder = thunderInjector.getMagic();
        this.magics.addMagic(thunder);
    }

    public void attack(IPlayer enemy) {

        if (this.magics.hasHeelMagic()) {
            System.out.println("Heel が使えるよ");
        }

        this.status.show();
        this.service.execute();
    }
}
