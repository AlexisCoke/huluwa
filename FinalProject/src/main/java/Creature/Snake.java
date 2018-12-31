package Creature;

import Ground.Battle;
import Ground.BattleGround;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Snake extends Monster {
    public Snake(String monsterName, double blood, Battle battle){
        super(monsterName, blood, battle);
    }

    @Override
    public void killed(){
        this.nowBlood = 0;
        this.isAlive = false;
        Image image = new Image("/小怪死亡.jpg", 35,35,false,false);
        this.imageView = new ImageView(image);
        this.progressBar.setProgress(0.0);
    }

}
