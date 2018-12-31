package Formation;

import Creature.Calabash;
import Creature.Monster;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class Formation {
    public static void selectCalabashFormat(ArrayList<Calabash> array, int index){
        if(index == 0)
            craneCalabash(array);
        else if(index == 1)
            wildGooseCalabash(array);
        else if(index == 2)
            crossCalabash(array);
        else if(index == 3)
            snakeCalabash(array);
        else if(index == 4)
            fishCalabash(array);
        else if(index == 5)
            doorCalabash(array);
        else if(index == 6)
            moonCalabash(array);
        else if(index == 7)
            arrowCalabash(array);
    }

    public static void selectMonsterFormat(ArrayList<Monster> array, int index){
        if(index == 0)
            craneMonster(array);
        else if(index == 1)
            wildGooseMonster(array);
        else if(index == 2)
            crossMonster(array);
        else if(index == 3)
            snakeMonster(array);
        else if(index == 4)
            fishMonster(array);
        else if(index == 5)
            doorMonster(array);
        else if(index == 6)
            moonMonster(array);
        else if(index == 7)
            arrowMonster(array);
    }

    public static void craneCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);
        array.get(0).setYp(310);
        for(int i = 1; i < (array.size() / 2) + 1; i++){
            array.get(i) .setXp(500 - i * 50);
            array.get(i).setYp(310 - i * 50);
            array.get(i + 3).setXp(500 - i * 50);
            array.get(i + 3).setYp(310 + i * 50);
        }

        array.get(0).setX(500/35);
        array.get(0).setY(310/35);
        for(int i = 1; i < (array.size() / 2) + 1; i++){
            array.get(i) .setX((8 - i)/35);
            array.get(i).setY((28 - i * 50)/35);
            array.get(i + 3).setX((500 - i * 50)/35);
            array.get(i + 3).setY((310 + i * 50)/35);
        }
    }

    public static void craneMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);
        array.get(0).setYp(310);
        for(int i = 1; i < (array.size() / 2) + 1; i++){
            array.get(i) .setXp(700 + i * 50);
            array.get(i).setYp(310 - i * 50);
            array.get(i + 3).setXp(700 + i * 50);
            array.get(i + 3).setYp(310 + i * 50);
        }

        array.get(0).setX(700/35);
        array.get(0).setY(310/35);
        for(int i = 1; i < (array.size() / 2) + 1; i++){
            array.get(i) .setX((700 + i * 50)/35);
            array.get(i).setY((310 - i * 50)/35);
            array.get(i + 3).setX((700 + i * 50)/35);
            array.get(i + 3).setY((310 + i * 50)/35);
        }
    }

    public static void wildGooseCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);
        array.get(0).setYp(150);
        for(int i = 1; i < array.size(); i++){
            array.get(i).setXp(500 - i * 50);
            array.get(i).setYp(150 + i * 50);
        }

        array.get(0).setX(500/35);
        array.get(0).setY(150/35);
        for(int i = 1; i < array.size(); i++){
            array.get(i).setX((500 - i * 50)/35);
            array.get(i).setY((150 + i * 50)/35);
        }
    }

    public static void wildGooseMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);
        array.get(0).setYp(150);
        for(int i = 1; i < array.size(); i++){
            array.get(i).setXp(700 + i * 50);
            array.get(i).setYp(150 + i * 50);
        }

        array.get(0).setX(700/35);
        array.get(0).setY(150/35);
        for(int i = 1; i < array.size(); i++){
            array.get(i).setX((700 + i * 50)/35);
            array.get(i).setY((150 + i * 50)/35);
        }
    }

    public static void crossCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);array.get(0).setYp(200);
        array.get(1).setXp(500);array.get(1).setYp(300);
        array.get(2).setXp(500);array.get(2).setYp(400);
        array.get(3).setXp(400);array.get(3).setYp(150);
        array.get(4).setXp(400);array.get(4).setYp(250);
        array.get(5).setXp(400);array.get(5).setYp(350);
        array.get(6).setXp(400);array.get(6).setYp(450);

        array.get(0).setX(500/35);array.get(0).setY(200/35);
        array.get(1).setX(500/35);array.get(1).setY(300/35);
        array.get(2).setX(500/35);array.get(2).setY(400/35);
        array.get(3).setX(400/35);array.get(3).setY(150/35);
        array.get(4).setX(400/35);array.get(4).setY(250/35);
        array.get(5).setX(400/35);array.get(5).setY(350/35);
        array.get(6).setX(400/35);array.get(6).setY(450/35);
    }

    public static void crossMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);array.get(0).setYp(200);
        array.get(1).setXp(700);array.get(1).setYp(300);
        array.get(2).setXp(700);array.get(2).setYp(400);
        array.get(3).setXp(800);array.get(3).setYp(150);
        array.get(4).setXp(800);array.get(4).setYp(250);
        array.get(5).setXp(800);array.get(5).setYp(350);
        array.get(6).setXp(800);array.get(6).setYp(450);

        array.get(0).setX(700/35);array.get(0).setY(200/35);
        array.get(1).setX(700/35);array.get(1).setY(300/35);
        array.get(2).setX(700/35);array.get(2).setY(400/35);
        array.get(3).setX(800/35);array.get(3).setY(150/35);
        array.get(4).setX(800/35);array.get(4).setY(250/35);
        array.get(5).setX(800/35);array.get(5).setY(350/35);
        array.get(6).setX(800/35);array.get(6).setY(450/35);
    }

    public static void snakeCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);
        array.get(0).setYp(150);
        for(int i = 0; i < array.size(); i++){
            array.get(i).setXp(500);
            array.get(i).setYp(150 + i *50);
        }

        array.get(0).setX(500/35);
        array.get(0).setY(150/35);
        for(int i = 0; i < array.size(); i++){
            array.get(i).setX(500/35);
            array.get(i).setY((150 + i *50)/35);
        }
    }

    public static void snakeMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);
        array.get(0).setYp(150);
        for(int i = 0; i < array.size(); i++){
            array.get(i).setXp(700);
            array.get(i).setYp(150 + i *50);
        }

        array.get(0).setX(700/35);
        array.get(0).setY(150/35);
        for(int i = 0; i < array.size(); i++){
            array.get(i).setX(700/35);
            array.get(i).setY((150 + i *50)/35);
        }
    }

    public static void fishCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(200);array.get(0).setYp(300);
        array.get(1).setXp(300);array.get(1).setYp(200);
        array.get(2).setXp(300);array.get(2).setYp(300);
        array.get(3).setXp(300);array.get(3).setYp(400);
        array.get(4).setXp(400);array.get(4).setYp(250);
        array.get(5).setXp(400);array.get(5).setYp(350);
        array.get(6).setXp(500);array.get(6).setYp(300);

        array.get(0).setX(200/35);array.get(0).setY(300/35);
        array.get(1).setX(300/35);array.get(1).setY(200/35);
        array.get(2).setX(300/35);array.get(2).setY(300/35);
        array.get(3).setX(300/35);array.get(3).setY(400/35);
        array.get(4).setX(400/35);array.get(4).setY(250/35);
        array.get(5).setX(400/35);array.get(5).setY(350/35);
        array.get(6).setX(500/35);array.get(6).setY(300/35);
    }

    public static void fishMonster(ArrayList<Monster> array){
        array.get(0).setXp(1000);array.get(0).setYp(300);
        array.get(1).setXp(900);array.get(1).setYp(200);
        array.get(2).setXp(900);array.get(2).setYp(300);
        array.get(3).setXp(900);array.get(3).setYp(400);
        array.get(4).setXp(800);array.get(4).setYp(250);
        array.get(5).setXp(800);array.get(5).setYp(350);
        array.get(6).setXp(700);array.get(6).setYp(300);

        array.get(0).setX(1000/35);array.get(0).setY(300/35);
        array.get(1).setX(900/35);array.get(1).setY(200/35);
        array.get(2).setX(900/35);array.get(2).setY(300/35);
        array.get(3).setX(900/35);array.get(3).setY(400/35);
        array.get(4).setX(800/35);array.get(4).setY(250/35);
        array.get(5).setX(800/35);array.get(5).setY(350/35);
        array.get(6).setX(700/35);array.get(6).setY(300/35);
    }

    public static void doorCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);array.get(0).setYp(300);
        array.get(1).setXp(400);array.get(1).setYp(250);
        array.get(2).setXp(400);array.get(2).setYp(350);
        array.get(3).setXp(300);array.get(3).setYp(200);
        array.get(4).setXp(300);array.get(4).setYp(400);
        array.get(5).setXp(200);array.get(5).setYp(250);
        array.get(6).setXp(200);array.get(6).setYp(350);

        array.get(0).setX(500/35);array.get(0).setY(300/35);
        array.get(1).setX(400/35);array.get(1).setY(250/35);
        array.get(2).setX(400/35);array.get(2).setY(350/35);
        array.get(3).setX(300/35);array.get(3).setY(200/35);
        array.get(4).setX(300/35);array.get(4).setY(400/35);
        array.get(5).setX(200/35);array.get(5).setY(250/35);
        array.get(6).setX(200/35);array.get(6).setY(350/35);
    }

    public static void doorMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);array.get(0).setYp(300);
        array.get(1).setXp(800);array.get(1).setYp(250);
        array.get(2).setXp(800);array.get(2).setYp(350);
        array.get(3).setXp(900);array.get(3).setYp(200);
        array.get(4).setXp(900);array.get(4).setYp(400);
        array.get(5).setXp(1000);array.get(5).setYp(250);
        array.get(6).setXp(1000);array.get(6).setYp(350);

        array.get(0).setX(700/35);array.get(0).setY(300/35);
        array.get(1).setX(800/35);array.get(1).setY(250/35);
        array.get(2).setX(800/35);array.get(2).setY(350/35);
        array.get(3).setX(900/35);array.get(3).setY(200/35);
        array.get(4).setX(900/35);array.get(4).setY(400/35);
        array.get(5).setX(1000/35);array.get(5).setY(250/35);
        array.get(6).setX(1000/35);array.get(6).setY(350/35);
    }

    public static void moonCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(400);array.get(0).setYp(300);
        array.get(1).setXp(400);array.get(1).setYp(250);
        array.get(2).setXp(400);array.get(2).setYp(350);
        array.get(3).setXp(500);array.get(3).setYp(200);
        array.get(4).setXp(500);array.get(4).setYp(400);
        array.get(5).setXp(300);array.get(5).setYp(300);
        array.get(6).setXp(200);array.get(6).setYp(300);

        array.get(0).setX(400/35);array.get(0).setY(300/35);
        array.get(1).setX(400/35);array.get(1).setY(250/35);
        array.get(2).setX(400/35);array.get(2).setY(350/35);
        array.get(3).setX(500/35);array.get(3).setY(200/35);
        array.get(4).setX(500/35);array.get(4).setY(400/35);
        array.get(5).setX(300/35);array.get(5).setY(300/35);
        array.get(6).setX(200/35);array.get(6).setY(300/35);
    }

    public static void moonMonster(ArrayList<Monster> array){
        array.get(0).setXp(800);array.get(0).setYp(300);
        array.get(1).setXp(800);array.get(1).setYp(250);
        array.get(2).setXp(800);array.get(2).setYp(350);
        array.get(3).setXp(700);array.get(3).setYp(200);
        array.get(4).setXp(700);array.get(4).setYp(400);
        array.get(5).setXp(900);array.get(5).setYp(300);
        array.get(6).setXp(1000);array.get(6).setYp(300);

        array.get(0).setX(800/35);array.get(0).setY(300/35);
        array.get(1).setX(800/35);array.get(1).setY(250/35);
        array.get(2).setX(800/35);array.get(2).setY(350/35);
        array.get(3).setX(700/35);array.get(3).setY(200/35);
        array.get(4).setX(700/35);array.get(4).setY(400/35);
        array.get(5).setX(900/35);array.get(5).setY(300/35);
        array.get(6).setX(1000/35);array.get(6).setY(300/35);
    }

    public static void arrowCalabash(ArrayList<Calabash> array){
        array.get(0).setXp(500);array.get(0).setYp(300);
        array.get(1).setXp(450);array.get(1).setYp(250);
        array.get(2).setXp(450);array.get(2).setYp(350);
        array.get(3).setXp(450);array.get(3).setYp(300);
        array.get(4).setXp(400);array.get(4).setYp(300);
        array.get(5).setXp(350);array.get(5).setYp(300);
        array.get(6).setXp(300);array.get(6).setYp(300);

        array.get(0).setX(500/35);array.get(0).setY(300/35);
        array.get(1).setX(450/35);array.get(1).setY(250/35);
        array.get(2).setX(450/35);array.get(2).setY(350/35);
        array.get(3).setX(450/35);array.get(3).setY(300/35);
        array.get(4).setX(400/35);array.get(4).setY(300/35);
        array.get(5).setX(350/35);array.get(5).setY(300/35);
        array.get(6).setX(300/35);array.get(6).setY(300/35);
    }

    public static void arrowMonster(ArrayList<Monster> array){
        array.get(0).setXp(700);array.get(0).setYp(300);
        array.get(1).setXp(750);array.get(1).setYp(250);
        array.get(2).setXp(750);array.get(2).setYp(350);
        array.get(3).setXp(750);array.get(3).setYp(300);
        array.get(4).setXp(800);array.get(4).setYp(300);
        array.get(5).setXp(850);array.get(5).setYp(300);
        array.get(6).setXp(900);array.get(6).setYp(300);

        array.get(0).setX(700/35);array.get(0).setY(300/35);
        array.get(1).setX(750/35);array.get(1).setY(250/35);
        array.get(2).setX(750/35);array.get(2).setY(350/35);
        array.get(3).setX(750/35);array.get(3).setY(300/35);
        array.get(4).setX(800/35);array.get(4).setY(300/35);
        array.get(5).setX(850/35);array.get(5).setY(300/35);
        array.get(6).setX(900/35);array.get(6).setY(300/35);
    }
}
