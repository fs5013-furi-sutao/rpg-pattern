package jp.freestyles.rpg.field;

import jp.freestyles.rpg.injection.player.FighterInjector;
import jp.freestyles.rpg.injection.player.WizardInjector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.player.base.IPlayer;

public class BattleField {

    private int turnCount;
    private List<IPlayer> players;
    private List<IPlayer> playersForDisplaying;

    public BattleField() {
        this.turnCount = 1;
        initPlayers();
    }

    private void initPlayers() {
        this.players = new ArrayList<>();
        this.playersForDisplaying = new ArrayList<>();

        IPlayerServiceInjector wizardInjector = new WizardInjector();
        IPlayer wizard = wizardInjector.getPlayer("山本一郎");

        IPlayerServiceInjector fighterInjector = new FighterInjector();
        IPlayer fighter = fighterInjector.getPlayer("山田太郎");

        this.players.add(wizard);
        this.players.add(fighter);
        this.playersForDisplaying.add(wizard);
        this.playersForDisplaying.add(fighter);
    }

    public void play() {

        for (int i = 0; i < 10; i++) {
            showTurnCount();
            attackEachOther();
            // priest.attack(fighter);
            if (hasDeadPlayer(players))
                break;
            System.out.println();
            this.turnCount++;
        }
    }

    private void attackEachOther() {

        Collections.shuffle(this.players);
        showStatusEachOther();

        for (IPlayer hero : players) {

            IPlayer enemy = pickUpRandomEnemy(hero);
            hero.attack(enemy);
            if (hasDeadPlayer(players))
                break;
        }
    }

    private void showStatusEachOther() {

        for (IPlayer player : playersForDisplaying) {
            player.showStatus();
        }
        System.out.println();
    }

    private IPlayer pickUpRandomEnemy(IPlayer hero) {
        boolean isMatch = false;
        IPlayer enemy = null;
        while (!isMatch) {
            int count = this.players.size();
            int randomIndex = ThreadLocalRandom.current().nextInt(count);

            enemy = this.players.get(randomIndex);
            if (!enemy.equals(hero))
                break;
        }

        return enemy;
    }

    private boolean hasDeadPlayer(List<IPlayer> players) {
        for (IPlayer player : players) {
            if (player.isDead())
                return true;
        }
        return false;
    }

    private void showTurnCount() {
        System.out.format("%d ターン目: ========== %n", this.turnCount);
    }

}
