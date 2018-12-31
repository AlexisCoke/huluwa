package RecordSystem;

import MainDemo.Main;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Recorder implements Runnable{
    private Node view;
    private Robot robot;
    //private Recorder recorder = null;
    private String currentTime;
    private String currentDir;
    private long i = 0;

    public Recorder(Node node) {
        this.view = node;
        try {
            robot = new Robot();
        }catch (Exception e){

        }
        this.currentTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        this.currentDir = "GameRecords/" + currentTime;
        //创建目录
        new File(currentDir).mkdirs();
    }

    @Override
    public void run(){
        while(!Thread.interrupted()){
            try{
                //捕获屏幕指定的区域
                //System.out.println(""+Main.stage.getX() + ' ' + (int)Main.stage.getY());
                BufferedImage image = robot.createScreenCapture(new Rectangle((int)Main.stage.getX() + 1, (int)Main.stage.getY(),
                        1200,780));
                ImageIO.write(image,  "png", new File(currentDir + "/" + i + ".png"));
                System.out.println("截图成功");
                i++;
                //每1s截一次屏幕
                TimeUnit.MILLISECONDS.sleep(100);
            }catch(InterruptedException e1){
            }catch(IOException e3){
                e3.printStackTrace();
            }
        }
    }
}
