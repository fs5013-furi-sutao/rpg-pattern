package jp.freestyles.rpg.field;

import jp.freestyles.rpg.injection.player.FighterInjector;
import jp.freestyles.rpg.injection.player.PriestInjector;
import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;

public class BattleField {
    
    private int turnCount;

    public BattleField() {
        this.turnCount = 1;
    }

    public void play() {

        IPlayerServiceInjector priestInjector = new PriestInjector();
        IPlayer priest = priestInjector.getPlayer("John Doe");

        IPlayerServiceInjector fighterInjector = new FighterInjector();
        IPlayer fighter = fighterInjector.getPlayer("Mick Jack");

        for (int i = 0; i < 10; i++) {
            showTurnCount();
            priest.attack(fighter);
            System.out.println();
            this.turnCount++;
        }
    }

    private void showTurnCount() {
        System.out.format(
            "%d ターン目: ========== %n", 
            this.turnCount);
    }

}
