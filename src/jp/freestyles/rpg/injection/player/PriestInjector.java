package jp.freestyles.rpg.injection.player;

import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Priest;
import jp.freestyles.rpg.service.StandardPlayerService;

public class PriestInjector implements IPlayerServiceInjector {

    public IPlayer getPlayer() {
        return new Priest(new StandardPlayerService());
    }
}
