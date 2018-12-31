package Creature;

import GeneralTools.*;
import GeneralTools.Party;
import Ground.Battle;
import Ground.BattleGround;
import MainDemo.Main;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Calabash extends Creature {
    public Calabash(){
        this.initblood = 200;
    }

    public Calabash(String calabashName, double blood, Battle battle){
        super(calabashName, blood, battle);
        this.party = Party.Good;
    }

    @Override
    public Monster nearEnemy(){
        int dis = 10000;
        Monster enemy = null;
        synchronized (battle) {
            for (int i = 0; i < battle.getMonsters().size(); i++) {
                Monster temp = battle.getMonsters().get(i);
                if (!temp.isAlive())
                    continue;
                int tempdis = (int) (Math.abs(this.xp - temp.xp) + Math.abs(this.yp - temp.yp));
                if (tempdis < dis) {
                    dis = tempdis;
                    enemy = temp;
                }
            }
            return enemy;
        }
    }

    @Override
    public void attack(Creature enemy){
        synchronized (battle){
            if(this.isAlive() && enemy.isAlive()){
                int test = new Random().nextInt(2);
                if(test == 0) {
                    if(enemy.nowBlood / enemy.initblood < 0.5)
                        enemy.killed();
                    else{
                        enemy.progressBar.setProgress(enemy.nowBlood / enemy.initblood);
                    }
                    this.nowBlood = this.nowBlood - 30;
                    progressBar.setProgress(this.nowBlood / this.initblood);
                    turnNow++;
                    if(turnNow == turnAll)
                        this.killed();
                }
                else{
                    if(this.nowBlood / this.initblood < 0.5)
                        this.killed();
                    else{
                        this.progressBar.setProgress(this.nowBlood / this.initblood);
                    }
                    enemy.nowBlood = enemy.nowBlood - 30;
                    enemy.progressBar.setProgress(enemy.nowBlood / enemy.initblood);
                    enemy.turnNow++;
                    if(enemy.turnNow == enemy.turnAll)
                        enemy.killed();
                }
            }
        }
    }

    @Override
    public void killed(){
        this.nowBlood = 0;
        this.isAlive = false;
        Image image = new Image("/葫芦娃死亡.jpg", 35,35,false,false);
        this.imageView.setImage(image);
        this.progressBar.setProgress(0.0);
        battle.stopThread(threadID);
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            if(!isAlive())
                return;
            else {
                randomMove();
                Monster enemy = nearEnemy();
                if (enemy == null) {
                    battle.setBattleResult(1);
                    //battle.stopBattle();
                    //System.out.println("葫芦娃赢");
                }
                else {
                    int distance = (int) (Math.abs(this.xp - enemy.xp) + Math.abs(this.yp - enemy.yp));
                    if (distance <= 200) {
                        this.attack(enemy);
                    } else {
                        this.moveOn(enemy);
                    }
                }

                SynRunLater.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Main.root.getChildren().remove(battle.getViews().get(threadID));
                        battle.getViews().get(threadID).setLayoutX(nx);
                        battle.getViews().get(threadID).setLayoutY(ny);
                        Main.root.getChildren().add(battle.getViews().get(threadID));
                        if(battle.getBattleResult() != -1)
                            battle.stopBattle();
                        //System.out.println("" + creatureName + "动起来了");
                    }
                });

                try {
                    if (battle.getBattleResult() == -1) {
                        Thread.sleep(new Random().nextInt(100) + 1000);
                    } else {
                        battle.stopThread(threadID);
                        //battle.stopBattle();
                        battle.setFight(false);
                        //break;
                        //battle.stopBattle();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //System.out.println("结束战斗");
                }

            }
        }
    }
}
