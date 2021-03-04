package jp.freestyles.rpg.service;

import jp.freestyles.rpg.service.base.IPlayerService;
import jp.freestyles.rpg.status.Status;

public class StandardPlayerService implements IPlayerService {

    public void attack(Status heroStatus, Status enemyStatus) {
        showAction(heroStatus, enemyStatus);
    }

    private void showAction(Status heroStatus, Status enemyStatus) {
        System.out.format(
            "%s が %s を通常攻撃した %n", 
            heroStatus.outName(), enemyStatus.outName());
    }
}
