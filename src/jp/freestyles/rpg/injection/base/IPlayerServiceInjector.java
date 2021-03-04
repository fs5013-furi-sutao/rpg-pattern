package jp.freestyles.rpg.injection.base;

import jp.freestyles.rpg.player.base.IPlayer;

public interface IPlayerServiceInjector {
    IPlayer getPlayer(String name);
}
