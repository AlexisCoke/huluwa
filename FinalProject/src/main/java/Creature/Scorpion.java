package Creature;

import Ground.Battle;
import Ground.BattleGround;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class Scorpion extends Monster {
    public Scorpion(String monsterName, double blood, Battle battle){
        super(monsterName, blood, battle);
    }
}
