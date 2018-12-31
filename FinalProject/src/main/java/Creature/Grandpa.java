package Creature;

import GeneralTools.Party;
import GeneralTools.SynRunLater;
import Ground.Battle;
import MainDemo.Main;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Grandpa extends Creature{
    public Grandpa(String creatureName, double b, Battle battle){
        super(creatureName, b, battle);
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
                int tempdis = (int) (Math.abs(this.x - temp.x) + Math.abs(this.y - temp.y));
                if (tempdis < dis) {
                    dis = tempdis;
                    enemy = temp;
                }
            }
        }
        return enemy;
    }

    @Override
    public void attack(Creature enemy){
        synchronized (battle){
            if(this.isAlive() && enemy.isAlive()){
                int sum = (int)(this.nowBlood + enemy.nowBlood);
                int test = new Random().nextInt(sum);
                if(test < this.nowBlood){
                    enemy.killed();
                    this.nowBlood = this.nowBlood / 2;
                }
                else{
                    this.killed();
                    enemy.nowBlood = enemy.nowBlood / 2;
                }
            }
        }
    }

    @Override
    public void killed(){
        this.nowBlood = 0;
        this.isAlive = false;
        Image image = new Image("/葫芦娃死亡.jpg", 35,35,false,false);
        this.imageView = new ImageView(image);
        this.progressBar.setProgress(0.0);
    }

    @Override
    public void randomMove(){
        int rx = new Random().nextInt(50);
        int direction = new Random().nextInt(4);
        if(direction == 0){
            if(this.nx + rx > 0 && this.nx + rx < 1200)
                this.nx += rx;
        }
        else if(direction == 1) {
            if(this.ny + rx > 0 && this.ny + rx < 660)
                this.ny += rx;
        }
        else if(direction == 3) {
            if(this.nx - rx > 0 && this.nx - rx < 1200)
            this.xp -= rx;
        }
        else {
            if(this.ny - rx > 0 && this.ny - rx < 660)
                this.yp -= rx;
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            randomMove();
            SynRunLater.runLater(new Runnable() {
                @Override
                public void run() {
                    if (battle.getBattleResult() != -1)
                        battle.stopBattle();
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
