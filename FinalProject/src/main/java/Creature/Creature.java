package Creature;


import Ground.Battle;
import Ground.BattleGround;
import MainDemo.Main;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import GeneralTools.*;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public abstract class Creature implements Runnable, Move, Observer, Attack{
    protected int xp, yp;
    protected int nx, ny;
    protected int x, y;
    protected int turnAll;
    protected int turnNow;
    protected ImageView imageView;
    protected int threadID;
    protected double nowBlood;
    protected double initblood;
    protected String creatureName;
    protected Party party;
    protected boolean isAlive;
    protected boolean isFight;
    protected ProgressBar progressBar;
    protected Battle battle;

    public Creature(){
        this.initblood = 200;
        nowBlood = initblood;
        this.turnAll = new Random().nextInt(4) + 1;
        this.turnNow = 0;
    }

    public Creature(String creatureName, double b, Battle battle){
        Image image = new Image("/" + creatureName + ".jpg", 35,35,false,false);
        this.imageView = new ImageView(image);
        this.creatureName = creatureName;
        this.initblood = b;
        nowBlood = initblood;
        this.isFight = false;
        this.battle = battle;
        this.turnAll = new Random().nextInt(4) + 1;
        this.turnNow = 0;
    }

    public void setThreadID(int id){
        this.threadID = id;
    }

    public int getThreadID() {
        return threadID;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public String getCreatureName() {
        return creatureName;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public boolean isAlive(){
        if(nowBlood > 0)
            return true;
        else
            return false;
    }

    public void killed(){
        this.nowBlood = 0;
    }

    @Override
    public boolean move(BattleGround ground, int x, int y) {
        if(x < 1100 && y < 600 && x > 100 && y > 60) {
            if (ground.isEmpty(x, y)) {
                //ground.clearPosition(this.x, this.y);
                //this.x = x;
                //this.y = y;
                this.nx = x;
                this.ny = y;
                this.xp = x;
                this.yp = y;
                System.out.println(creatureName + "从" + xp + ',' + yp + "移动到" + nx + ',' + ny);
                //ground.setPosition(x, y, this);
                //this.xp = this.ny;
                //this.yp = this.nx;
                //this.imageView.relocate(this.nx, this.ny);
                return true;
            } else {
                return false;
            }
        }
        else
            return false;

    }

    @Override
    public void randomMove(){

    }

    public boolean moveOn(Creature enemy) {
        synchronized (battle) {
            int dx = enemy.getXp() - this.xp;
            int dy = enemy.getYp() - this.yp;
            int stepx = 0;
            int stepy = 0;
            if (dx != 0)
                stepx = (dx > 0) ? 1 : -1;
            if (dy != 0)
                stepy = (dy > 0) ? 1 : -1;
            if (dx != 0 && dy != 0) {
                int nnx = this.xp + stepx * 35;
                int nny = this.yp + stepy * 35;
                return move(battle.getBattleGround(), nnx, nny);
            }
            else if (dx == 0 && dy != 0) {
                int nny = this.yp + stepy * 35;
                return move(battle.getBattleGround(), nx, nny);
            } else if (dx != 0 && dy == 0) {
                int nnx = this.xp + stepx * 35;
                return move(battle.getBattleGround(), nnx, ny);
            } else
                return false;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Party getParty() {
        return party;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
        this.nx = xp;
        imageView.setLayoutX(xp);
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
        this.ny = yp;
        imageView.setLayoutY(yp);
    }

    public int getNx() {
        return nx;
    }

    public void setNx(int nx) {
        this.nx = nx;
    }

    public int getNy() {
        return ny;
    }

    public void setNy(int ny) {
        this.ny = ny;
    }
}
