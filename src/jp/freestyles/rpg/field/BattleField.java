package jp.freestyles.rpg.field;

import jp.freestyles.rpg.injection.player.FighterInjector;
import jp.freestyles.rpg.injection.player.PriestInjector;
import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;

public class BattleField {

        public void play() {

                IPlayerServiceInjector priestInjector = new PriestInjector();
                IPlayer priest = priestInjector.getPlayer("良い僧侶");

                IPlayerServiceInjector fighterInjector = new FighterInjector();
                IPlayer fighter = fighterInjector.getPlayer("良い戦士");
                priest.attack(fighter);
        }

}
