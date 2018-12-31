package MainDemo;

import Creature.Calabash;
import Creature.Grandpa;
import Creature.Monster;
import Creature.Snake;
import GeneralTools.State;
import RecordSystem.Replay;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;

import Ground.*;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static Pane root;
    public static Stage stage;
    public static Scene scene;
    private static ChoiceBox calabashFormat;
    private static ChoiceBox monsterFormat;
    private static Label tips;
    private static Label label1;
    private static Label label2;
    //private static Media media;
    //private static MediaPlayer mediaPlayer;
    private Battle battle;
    private Replay replay;
    private State state;

    @Override
    public void start(Stage primaryStage) throws Exception{

        root = new Pane();
        stage = primaryStage;
        stage.setTitle("葫芦娃大战盘丝洞");
        scene = new Scene(root, 1200,780);

        tips = new Label("TIPS:  SPACE键开始游戏  L键读取游戏存档  S键开始游戏并保存录像");
        tips.setPrefWidth(1200);
        tips.setPrefHeight(30);
        tips.setLayoutX(0);
        tips.setLayoutY(750);
        root.getChildren().add(tips);

        /*media = new Media("BGM.mp3");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();*/

        label1 = new Label("葫芦娃阵型");
        label1.setPrefWidth(70);
        label1.setPrefHeight(30);
        label1.setLayoutX(30);
        label1.setLayoutY(660);
        root.getChildren().add(label1);
        calabashFormat = new ChoiceBox();
        calabashFormat.setItems(FXCollections.observableArrayList("鹤翼","雁行","衡轭","长蛇","鱼鳞","方门","偃月","锋矢"));
        calabashFormat.setPrefHeight(30);
        calabashFormat.setPrefWidth(80);
        calabashFormat.setLayoutX(20);
        calabashFormat.setLayoutY(690);
        calabashFormat.setFocusTraversable(false);
        root.getChildren().add(calabashFormat);

        label2 = new Label("妖怪阵型");
        label2.setPrefWidth(85);
        label2.setPrefHeight(30);
        label2.setLayoutX(1115);
        label2.setLayoutY(660);
        root.getChildren().add(label2);
        monsterFormat = new ChoiceBox();
        monsterFormat.setItems(FXCollections.observableArrayList("鹤翼","雁行","衡轭","长蛇","鱼鳞","方门","偃月","锋矢"));
        monsterFormat.setPrefHeight(30);
        monsterFormat.setPrefWidth(80);
        monsterFormat.setLayoutX(1100);
        monsterFormat.setLayoutY(690);
        monsterFormat.setFocusTraversable(false);
        root.getChildren().add(monsterFormat);

        Image image = new Image("/battleground.jpg", 1200,660,false,false);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        root.getChildren().add(imageView);

        primaryStage.setMinHeight(780);
        primaryStage.setMinWidth(1200);
        primaryStage.setScene(scene);
        primaryStage.show();

        state = State.init;
        battle = new Battle();
        battle.initBattle();

        calabashFormat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index1 = calabashFormat.getSelectionModel().getSelectedIndex();
                battle.setCalabashFormat(index1);
            }
        });

        monsterFormat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //if(battle.getBattleResult() != -1){
                int index2 = monsterFormat.getSelectionModel().getSelectedIndex();
                battle.setMonsterFormat(index2);
            }
        });

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE){
                    if(!battle.isFight()){
                        battle.battleStart();
                    }
                    else{
                        battle.initBattle();
                        battle.battleStart();
                    }

                }
                else if(event.getCode() == KeyCode.L){
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    File file = directoryChooser.showDialog(stage);
                    replay = new Replay(file.toString(), root);
                    //battle.initBattle();
                }
                else if(event.getCode() == KeyCode.S){
                    if(!battle.isFight()){
                        battle.battleStartAndSave();
                    }
                    else{
                        battle.initBattle();
                        battle.battleStartAndSave();
                    }
                }
            }
        });
    }

    public static ChoiceBox getCalabashFormat() {
        return calabashFormat;
    }

    public static ChoiceBox getMonsterFormat() {
        return monsterFormat;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
