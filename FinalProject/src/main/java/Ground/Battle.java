package Ground;

import Creature.*;

import GeneralTools.Move;
import GeneralTools.Observer;
import Formation.*;
import GeneralTools.SynRunLater;
import MainDemo.Main;
import RecordSystem.Recorder;
import RecordSystem.Replay;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;


import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Battle {
    private BattleGround battleGround;
    private Grandpa grandpa;
    private Snake snake;
    private ArrayList<Calabash> calabashes;
    private ArrayList<Monster> monsters;
    private ArrayList<ImageView> views;
    private ArrayList<ProgressBar> progressBars;
    private ArrayList<Thread> threads;
    private static int calabashFormatIndex;
    private static int monsterFormatIndex;
    private static int initCalabashFormatCount = 0;
    private static int initMonsterFormatCount = 0;
    private int battleResult;
    private boolean isFight;
    private Thread replayThread;
    private Recorder recorder;
    //private Replay replay;

    public boolean isFight() {
        return isFight;
    }

    public void setFight(boolean fight) {
        isFight = fight;
    }

    public BattleGround getBattleGround() {
        return battleGround;
    }

    public Battle() {
        calabashes = null;
        monsters = null;
        grandpa = null;
        snake = null;
        //threads = null;
        battleResult = -1;
        isFight = false;
        recorder = null;
        //replay = null;
        replayThread = null;
    }

    public void initBattle() {
        reset();
        grandpa = new Grandpa("爷爷", 220, this);
        grandpa.setX(50 / 35);
        grandpa.setY(300 / 35);
        grandpa.setXp(50);
        grandpa.setYp(300);
        calabashes.add(new Calabash("老大", 200, this));
        calabashes.add(new Calabash("老二", 180, this));
        calabashes.add(new Calabash("老三", 160, this));
        calabashes.add(new Calabash("老四", 140, this));
        calabashes.add(new Calabash("老五", 140, this));
        calabashes.add(new Calabash("老六", 140, this));
        calabashes.add(new Calabash("老七", 120, this));

        double labelX = 200;
        double r = 0.0;
        for (Calabash calabash : calabashes) {
            //calabash.setPower(30 - r * 50);
            //calabash.setCriticalStrikeRate(0.4 - r);
            calabash.setProgressBar(new ProgressBar(1));
            calabash.getProgressBar().setPrefSize(80 - r * 60, 15);
            r += 0.05;
            calabash.getProgressBar().setLayoutY(680);
            calabash.getProgressBar().setLayoutX(labelX);

            Label label = new Label(calabash.getCreatureName());
            label.setPrefSize(80, 15);
            label.setLayoutY(660);
            label.setLayoutX(labelX + 30 - r * 30);
            labelX += 120;

            //这里填入葫芦娃的图片位置

            Main.root.getChildren().add(label);
            Main.root.getChildren().add(calabash.getProgressBar());

        }

        snake = new Snake("蛇精", 220, this);
        snake.setX(1100 / 35);
        snake.setY(300 / 35);
        snake.setXp(1100);
        snake.setYp(300);
        //monsterLeader.setPower(20);
        //monsterLeader.setCriticalStrikeRate(0.2);
        monsters.add(new Scorpion("蝎子精", 220, this));
        monsters.add(new Flunky("喽啰", 100, this));
        monsters.add(new Flunky("喽啰", 100, this));
        monsters.add(new Flunky("喽啰", 100, this));
        monsters.add(new Flunky("喽啰", 100, this));
        monsters.add(new Flunky("喽啰", 100, this));
        monsters.add(new Flunky("喽啰", 100, this));

        labelX = 200;
        int count = 0;
        r = 0.0;
        for (Monster monster : monsters) {
            //monster.setPower(25 - r * 40);
            //monster.setCriticalStrikeRate(0.35 - r);
            monster.setProgressBar(new ProgressBar(1));
            if (count < 2) {
                monster.getProgressBar().setPrefSize(80, 15);
                count++;
            } else
                monster.getProgressBar().setPrefSize(60, 15);
            monster.getProgressBar().setLayoutX(labelX);
            //labelY += 30;
            monster.getProgressBar().setLayoutY(720);

            Label label = new Label(monster.getCreatureName());
            label.setPrefSize(80, 15);
            r += 0.05;
            label.setLayoutX(labelX + 30 - r * 30);
            labelX += 120;
            label.setLayoutY(700);

            //这里填入妖怪的图片位置

            Main.root.getChildren().add(label);
            Main.root.getChildren().add(monster.getProgressBar());
        }

        grandpa.setThreadID(0);
        threads.add(new Thread(grandpa));
        views.add(grandpa.getImageView());
        progressBars.add(grandpa.getProgressBar());
        snake.setThreadID(1);
        threads.add(new Thread(snake));
        views.add(snake.getImageView());
        progressBars.add(snake.getProgressBar());
        for (int i = 0; i < calabashes.size(); i++) {
            calabashes.get(i).setThreadID(i + 2);
            threads.add(new Thread(calabashes.get(i)));
            views.add(calabashes.get(i).getImageView());
            progressBars.add(calabashes.get(i).getProgressBar());
        }
        for (int i = 0; i < monsters.size(); i++) {
            monsters.get(i).setThreadID(i + 9);
            threads.add(new Thread(monsters.get(i)));
            views.add(monsters.get(i).getImageView());
            progressBars.add(monsters.get(i).getProgressBar());
        }
        System.out.println(threads.size());
    }

    public void setBattleResult(int battleResult) {
        this.battleResult = battleResult;
    }

    public int getBattleResult() {
        return battleResult;
    }

    public Snake getSnake() {
        return snake;
    }

    public Grandpa getGrandpa() {
        return grandpa;
    }

    public void stopThread(int id) {
        threads.get(id).interrupt();
    }

    public void stopBattle() {
        /*for (Thread r : threads)
            r.interrupt();*/
        //System.out.println(battleResult);
        if(battleResult == 1)
            System.out.println("葫芦娃胜利");
        else{
            System.out.println("妖怪胜利");
        }
        //System.exit(0);
    }

    public ArrayList<ImageView> getViews() {
        return views;
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public ArrayList<Calabash> getCalabashes() {
        return calabashes;
    }

    public void reset() {
        battleGround = new BattleGround(this);
        battleResult = -1;
        calabashes = new ArrayList<Calabash>();
        monsters = new ArrayList<Monster>();
        threads = new ArrayList<Thread>();
        views = new ArrayList<ImageView>();
        progressBars = new ArrayList<ProgressBar>();
        isFight = false;
        //grandpa = new Grandpa();
        //snake = new Snake();
    }

    public void battleStart() {
        isFight = true;
        //synchronizeTools = new SynchronizeTools(this);
        for (Thread r : threads) {
            r.start();
        }
    }

    public void battleStartAndSave() {
        recorder = new Recorder(Main.root);
        threads.add(new Thread(recorder));
        isFight = true;
        //synchronizeTools = new SynchronizeTools(this);
        for (Thread r : threads)
            r.start();
    }

    public void setCalabashFormat(int index) {
        calabashFormatIndex = index;
        if (calabashFormatIndex >= 0 && calabashFormatIndex <= 7) {
            System.err.println("true");
            SynRunLater.runLater(() -> {
                if (initCalabashFormatCount == 0) {
                    Formation.selectCalabashFormat(calabashes, calabashFormatIndex);
                    Main.root.getChildren().add(grandpa.getImageView());
                    for (int i = 0; i < calabashes.size(); i++)
                        Main.root.getChildren().add(calabashes.get(i).getImageView());
                    initCalabashFormatCount = 1;
                } else {
                    for (int i = 0; i < calabashes.size(); i++)
                        Main.root.getChildren().remove(calabashes.get(i).getImageView());

                    Formation.selectCalabashFormat(calabashes, calabashFormatIndex);
                    for (int i = 0; i < calabashes.size(); i++)
                        Main.root.getChildren().add(calabashes.get(i).getImageView());
                }
            });
        }
    }

    public void setMonsterFormat(int index) {
        monsterFormatIndex = index;
        if (monsterFormatIndex >= 0 && monsterFormatIndex <= 7) {
            System.err.println("true");
            SynRunLater.runLater(() -> {
                if (initMonsterFormatCount == 0) {
                    Formation.selectMonsterFormat(monsters, monsterFormatIndex);
                    Main.root.getChildren().add(snake.getImageView());
                    for (int i = 0; i < monsters.size(); i++)
                        Main.root.getChildren().add(monsters.get(i).getImageView());
                    initMonsterFormatCount = 1;
                } else {
                    for (int i = 0; i < monsters.size(); i++)
                        Main.root.getChildren().remove(monsters.get(i).getImageView());
                    Formation.selectMonsterFormat(monsters, monsterFormatIndex);
                    for (int i = 0; i < monsters.size(); i++)
                        Main.root.getChildren().add(monsters.get(i).getImageView());
                }
            });
        }
    }
}