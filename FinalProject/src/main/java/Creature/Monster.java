package Creature;

import GeneralTools.*;
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

public class Monster extends Creature {
    public Monster(){}

    public Monster(String monsterName, double blood, Battle battle){
        super(monsterName, blood, battle);
        this.party = Party.Bad;
    }

    @Override
    public Calabash nearEnemy(){
        int dis = 10000;
        Calabash enemy = null;
        synchronized (battle) {
            for (int i = 0; i < battle.getCalabashes().size(); i++) {
                Calabash temp = battle.getCalabashes().get(i);
                if (!temp.isAlive())
                    continue;
                int tempdis = (Math.abs(this.x - temp.x) + Math.abs(this.y - temp.y));
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
                if(test == 0){
                    if(enemy.nowBlood / enemy.initblood < 0.5)
                        enemy.killed();
                    else{
                        enemy.progressBar.setProgress(enemy.nowBlood / enemy.initblood);
                    }
                    this.nowBlood = this.nowBlood - 30;
                    progressBar.setProgress(this.nowBlood / this.initblood);
                    this.turnNow++;
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
        Image image = new Image("/小怪死亡.jpg", 35,35,false,false);
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
                Calabash enemy = nearEnemy();
                if (enemy == null) {
                    battle.setBattleResult(0);
                    //battle.stopBattle();
                    //System.out.println("妖怪赢");
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
                        //System.out.println("" + creatureName + "动起来了");
                        if(battle.getBattleResult() != -1)
                            battle.stopBattle();
                    }
                });

                try {
                    //System.out.println(battle.getBattleResult());
                    if (battle.getBattleResult() == -1) {
                        Thread.sleep(new Random().nextInt(100) + 1000);
                    } else {
                        battle.stopThread(threadID);
                        battle.setFight(false);
                        notify();
                        //break;
                        //battle.stopBattle();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(creatureName + "被意外唤醒了");
                    //System.out.println("结束战斗");
                }
            }

        }
    }
}
