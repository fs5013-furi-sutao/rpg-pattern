package jp.freestyles.rpg.injection.player;

import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Fighter;
import jp.freestyles.rpg.service.StandardPlayerService;

public class FighterInjector implements IPlayerServiceInjector {

    public IPlayer getPlayer(String name) {
        return new Fighter(new StandardPlayerService(), name);
    }
}
