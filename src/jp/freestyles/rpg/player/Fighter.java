package jp.freestyles.rpg.player;

import jp.freestyles.rpg.magic.set.MagicSet;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.status.Status;
import jp.freestyles.rpg.service.base.IPlayerService;

import static jp.freestyles.rpg.player.config.PlayerConfig.FIGHTER;

public class Fighter implements IPlayer {

    private String name;
    private MagicSet magics;
    private Status status;

    private IPlayerService service;

    public Fighter(IPlayerService service, String name) {
        this.name = name;
        this.service = service;
        
        this.status = new Status.Builder(name)
            .breed(FIGHTER.breed())
            .maxHp(FIGHTER.maxHpMin())
            .hp(FIGHTER.maxHpMax())
            .mp(FIGHTER.mp())
            .build();
        
        this.magics = new MagicSet();
    }

    public void attack(IPlayer enemy) {

        showDaclareAttack();
        this.service.attack(this.status, enemy.outStatus());
    }

    @Override
    public void showStatus() {
        this.status.show();
    }

    private void showDaclareAttack() {
        System.out.format("%s の攻撃 %n", this.status.outName());
    }

    public Status outStatus() {
        return this.status;
    }

    public String outName() {
        return this.name;
    }

    public MagicSet outMagics() {
        return this.magics;
    }

    @Override
    public boolean isDead() {
        return this.status.isHpEmpty();
    }
}
