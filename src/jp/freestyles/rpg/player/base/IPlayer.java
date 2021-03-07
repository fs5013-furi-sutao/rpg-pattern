package jp.freestyles.rpg.player.base;

import jp.freestyles.rpg.status.Status;

public interface IPlayer {
    
    void attack(IPlayer enemy);
    Status outStatus();
	boolean isDead();
    void showStatus();
    void showDeadStatus();
    void showSurvivedStatus();
}
