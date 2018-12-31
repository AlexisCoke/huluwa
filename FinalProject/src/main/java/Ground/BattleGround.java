package Ground;

import Creature.*;
import Formation.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BattleGround {
    private Point[][] battleGround;
    private Battle battle;

    public BattleGround(Battle battle) {
        battleGround = new Point[36][18];
        for (int i = 0; i < 36; i++) {
            for (int j = 0; j < 18; j++)
                battleGround[i][j] = new Point();
        }
        this.battle = battle;
    }

    public boolean isEmpty(int x, int y) {
        /*if ((x / 35) >= 0 && (y / 35) >= 0 && (x / 35) < 36 && (y / 35) < 18) {
            return battleGround[x / 35][y / 35].isEmpty();
        } else
            return false;*/

        if(x >= 0 && x <= 1100 && y >= 0 && y <= 600){
            if(battle.getGrandpa().getXp() >= (x - 10) && battle.getGrandpa().getXp() <= x + 10
               && battle.getGrandpa().getYp() >= (y - 10) && battle.getGrandpa().getYp() <= y + 10){
                return false;
            }
            if(battle.getSnake().getXp() >= x - 10 && battle.getSnake().getXp() <= x + 10
               && battle.getSnake().getYp() >= y - 10 && battle.getSnake().getYp() <= y + 10){
                return false;
            }
            for(int i = 0; i < battle.getCalabashes().size(); i++){
                if(battle.getCalabashes().get(i).getXp() >= (x - 10)  && battle.getCalabashes().get(i).getXp() <= (x + 10)
                   && battle.getCalabashes().get(i).getYp() >= (y - 10) && battle.getCalabashes().get(i).getYp() <= (y + 10)){
                    return false;
                }
            }
            for(int i = 0; i < battle.getMonsters().size(); i++){
                if(battle.getMonsters().get(i).getXp() >= (x - 10)  && battle.getMonsters().get(i).getXp() <= (x + 10)
                        && battle.getMonsters().get(i).getYp() >= (y - 10) && battle.getMonsters().get(i).getYp() <= (y + 10)){
                    return false;
                }
            }
            return true;
        }
        else
            return false;
    }

    public Point[][] getBattleGround() {
        return battleGround;
    }

    public void setPosition(int x, int y, Creature creature) {
        battleGround[x][y].setPoint(creature);
    }

    public void clearPosition(int x, int y) {
        battleGround[x][y].clearPoint();
    }
}