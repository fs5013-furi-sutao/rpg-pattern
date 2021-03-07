package jp.freestyles.rpg.field;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import jp.freestyles.rpg.injection.base.IPlayerServiceInjector;
import jp.freestyles.rpg.injection.player.FighterInjector;
import jp.freestyles.rpg.injection.player.PriestInjector;
import jp.freestyles.rpg.injection.player.WizardInjector;
import jp.freestyles.rpg.player.base.IPlayer;

import static jp.freestyles.rpg.util.RandomGenerator.generateRandomInt;

public class BattleField {

    private int turnCount;
    private List<IPlayer> players;
    private List<IPlayer> playersForDisplaying;

    private static final int TURN_OF_STARTING = 1;

    public BattleField() {
        this.turnCount = TURN_OF_STARTING;
        initPlayers();
    }

    private void initPlayers() {
        this.players = new ArrayList<>();
        this.playersForDisplaying = new ArrayList<>();

        IPlayerServiceInjector wizardInjector = new WizardInjector();
        IPlayer wizard = wizardInjector.getPlayer("ウィザード");

        IPlayerServiceInjector priestInjector = new PriestInjector();
        IPlayer priest = priestInjector.getPlayer("プリースト");

        IPlayerServiceInjector fighterInjector = new FighterInjector();
        IPlayer fighter = fighterInjector.getPlayer("ファイター");

        this.players.add(wizard);
        this.players.add(priest);
        this.players.add(fighter);
        this.playersForDisplaying.add(wizard);
        this.playersForDisplaying.add(priest);
        this.playersForDisplaying.add(fighter);
    }

    public void play() {
        showStatusEachOther();
        pressAnyKeyToContinue();

        sortByAgiDesc();
        while (!isExistsOnlyOneLivePlayer()) {
            showTurnCount();
            attackEachOther();

            pressAnyKeyToContinue();
            this.turnCount++;
        }

        finishBattle();
    }

    private void pressAnyKeyToContinue() { 
        System.out.println("続けるには Enter キーを押してください ...");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            input.readLine();
        }  
        catch(Exception e) {
            e.printStackTrace();
        }  
    }

    private void finishBattle() {
        showEndResult(); 
        findTheLastSurvivor().showSurvivedStatus();
    }

    private IPlayer findTheLastSurvivor() {
        for (IPlayer player : players) {
            if (!player.isDead()) return player;
        }
        return null;
    }

    private void attackEachOther() {
        // Collections.shuffle(this.players);

        for (IPlayer hero : players) {

            if (hero.isDead()) continue;

            IPlayer enemy = pickUpRandomEnemy(hero);
            hero.attack(enemy);

            // if (enemy.isDead()) enemy.showDeadStatus();

            if (isExistsOnlyOneLivePlayer()) break;
        }

        showStatusEachOther();
    }

    private void sortByAgiDesc() {
        Collections.sort( this.players, new Comparator<IPlayer>(){
            @Override
            public int compare(IPlayer a, IPlayer b){
              return b.outStatus().outAgi() - a.outStatus().outAgi();
            }
        });
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
            int randomIndex = 0;

            do {
                randomIndex = generateRandomInt(count);
                enemy = this.players.get(randomIndex);
            } while (this.players.get(randomIndex).isDead());

            if (!enemy.equals(hero)) break;
        }

        return enemy;
    }

    private boolean isExistsOnlyOneLivePlayer() {
        int liveOfplayersCount = 0;
        for (IPlayer player : players) {
            if (!player.isDead()) liveOfplayersCount++;
        } 
        
        if (liveOfplayersCount == 1) return true;
        return false;
    }

    // private boolean hasDeadPlayer() {
    //     for (IPlayer player : this.players) {
    //         if (player.isDead())
    //             return true;
    //     }
    //     return false;
    // }

    private void showTurnCount() {
        System.out.format("%d ターン目: ========== %n", this.turnCount);
    }

    private void showEndResult() {
        System.out.println("最終結果: ========== ");
    }
}
