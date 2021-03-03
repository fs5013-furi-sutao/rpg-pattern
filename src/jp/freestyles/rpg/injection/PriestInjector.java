package jp.freestyles.rpg.injection;

import jp.freestyles.rpg.injection.base.IServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Priest;
import jp.freestyles.rpg.service.StandardService;

public class PriestInjector implements IServiceInjector {

    public IPlayer getPlayer() {
        return new Priest(new StandardService());
    }
}
