package Creature;

import Ground.Battle;
import Ground.BattleGround;
import javafx.application.Platform;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Flunky extends Monster {
    public Flunky(String monsterName, double blood, Battle battle){
        super(monsterName,blood, battle);
    }
}
