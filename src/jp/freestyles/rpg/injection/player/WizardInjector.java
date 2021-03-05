package jp.freestyles.rpg.injection.player;

import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;
import jp.freestyles.rpg.player.Wizard;
import jp.freestyles.rpg.service.StandardPlayerService;

public class WizardInjector implements IPlayerServiceInjector {

    public IPlayer getPlayer(String name) {
        return new Wizard(new StandardPlayerService(), name);
    }
}
