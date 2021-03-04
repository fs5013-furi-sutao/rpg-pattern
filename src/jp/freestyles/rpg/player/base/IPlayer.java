package jp.freestyles.rpg.player.base;

import jp.freestyles.rpg.status.Status;

public interface IPlayer {
    
    void attack(IPlayer enemy);
    Status outStatus();
}
