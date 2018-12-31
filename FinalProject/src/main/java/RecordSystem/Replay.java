package RecordSystem;


import Ground.BattleGround;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javax.tools.Tool;
import java.awt.*;
import java.io.File;

public class Replay {
    private String getDir;
    private ImageView imageView;
    private Image image;
    private Screen screen;
    public boolean isEnd;

    public Replay(String dir, Pane pane){

        this.getDir = dir;
        image = new Image("file:" + getDir + '/' + "1.png");
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        isEnd = false;
        screen = new Screen();
        new Thread(screen).start();
        pane.getChildren().add(imageView);
    }


    class Screen implements Runnable{
        private long i = 0;

        public void run(){
            while(true){
                try{
                    File file = new File(getDir + '/' + i + ".png");
                    if(file.exists()){
                        image = new Image("file:" + getDir + '/' + i +".png");
                        i++;
                        imageView.setImage(image);
                        Thread.sleep(100);
                    }
                    else {
                        isEnd = true;
                        Thread.interrupted();
                    }
                }catch(Exception e){
                    System.out.println("回放结束");
                }
            }
        }
    }

    public boolean playEnd(){
        return isEnd;
    }
}
