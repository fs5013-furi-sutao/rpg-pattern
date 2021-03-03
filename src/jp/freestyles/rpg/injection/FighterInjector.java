package jp.freestyles.rpg.injection;

import jp.freestyles.rpg.injection.base.IServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Fighter;
import jp.freestyles.rpg.service.StandardService;

public class FighterInjector implements IServiceInjector {

    public IPlayer getPlayer() {
        return new Fighter(new StandardService());
    }
}
