package jp.freestyles.rpg.field;

import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.injection.player.FighterInjector;
import jp.freestyles.rpg.injection.player.PriestInjector;
import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;

public class BattleField {

        public void play() {

                IPlayerServiceInjector priestInjector = new PriestInjector();
                IPlayer priest = priestInjector.getPlayer();

                IPlayerServiceInjector fighterInjector = new FighterInjector();
                IPlayer fighter = fighterInjector.getPlayer();
                priest.attack(fighter);
        }

}
