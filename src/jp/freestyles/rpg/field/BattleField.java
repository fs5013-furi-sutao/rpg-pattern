package jp.freestyles.rpg.field;

import jp.freestyles.rpg.injection.FighterInjector;
import jp.freestyles.rpg.injection.PriestInjector;
import jp.freestyles.rpg.injection.base.IServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;

public class BattleField {

        public void play() {

                IServiceInjector priestInjector = new PriestInjector();
                IPlayer priest = priestInjector.getPlayer();
                priest.attack();

                IServiceInjector fighterInjector = new FighterInjector();
                IPlayer fighter = fighterInjector.getPlayer();
                fighter.attack();
        }

}
