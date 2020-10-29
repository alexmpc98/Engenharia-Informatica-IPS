/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.CornerRadii;

/**
 *
 * @author Alexandre
 */
public class Screen extends Application {
    private String name;
    private Scene scene;
    private Scene scene2;
    private int points;
    private String filename;
    private String fileType;
    private ArrayList<GridPane> blocks = new ArrayList<>();
    private boolean clicked;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Enter Name");
        btn.setDefaultButton(true);
        btn.setFont(new Font("Arial", 24));
        Label presentationLbl = new Label("Welcome to BlockuDoku");
        presentationLbl.setFont(new Font("Arial", 24));
        Label insertName = new Label("Insert your name:");
        insertName.setFont(new Font("Arial", 15));
        TextField txtField = new TextField();
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!txtField.getText().trim().isEmpty()){
                    name = txtField.getText();
                    scene2 = new Scene(returnScreen2(name,primaryStage), 650, 450); 
                    primaryStage.setScene(scene2);
                }
                else{
                    Alert alert = new Alert(AlertType.ERROR); 
                    alert.setHeaderText("Campo de Nome vazio!");
                    alert.setContentText("Tem de inserir um nome antes de prosseguir!");
                    alert.show();
                    System.out.println("Empty Name");
                }
            }
        });
        btn.setStyle("-fx-background-color: \n" +
"        #000000,\n" +
"        linear-gradient(#7ebcea, #2f4b8f),\n" +
"        linear-gradient(#426ab7, #263e75),\n" +
"        linear-gradient(#395cab, #223768);\n" +
"    -fx-background-insets: 0,1,2,3;\n" +
"    -fx-background-radius: 3,2,2,2;\n" +
"    -fx-padding: 12 30 12 30;\n" +
"    -fx-text-fill: white;\n" +
"    -fx-font-size: 12px;");
        
        GridPane root = new GridPane(); 
        HBox hbox1 = new HBox();
        hbox1.getChildren().add(presentationLbl);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(insertName,txtField);   
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(btn);
        
        hbox1.setMinWidth(350);
        hbox1.setAlignment(Pos.CENTER);
        
        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(10);
        hbox2.setMinWidth(350); 
        hbox2.setAlignment(Pos.CENTER);
        
        hbox3.setPadding(new Insets(10));
        hbox3.setSpacing(40);
        hbox3.setMinWidth(350);
        hbox3.setAlignment(Pos.CENTER);

        
        root.setVgap(20);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.add(hbox1,1,1);
        root.add(hbox2,1,3);
        root.add(hbox3,1,4);   
        root.setAlignment(Pos.CENTER);
        
        this.scene = new Scene(root, 650, 450);
        
        primaryStage.getIcons().add(new Image("file:assets/blockuDoku.jpg"));
        primaryStage.setTitle("BlockuDoku!");
        primaryStage.setScene(scene); 
        primaryStage.show();
        //primaryStage.setResizable(false);     
    }
    
    public GridPane returnScreen2(String name,Stage primaryStage){
        GridPane root2 = new GridPane();
        root2.setVgap(20);
        root2.setPadding(new Insets(10, 10, 10, 10));
        
         
        Text nameTxt = new Text(name);
        Text welcomeTxt = new Text("Welcome ");
        Text gladTxt = new Text(" ,glad to see you again!");
         
        welcomeTxt.setFill(Color.CORNFLOWERBLUE);
        welcomeTxt.setFont(Font.font("Verdana",FontWeight.BOLD,18));
        gladTxt.setFill(Color.CORNFLOWERBLUE);
        gladTxt.setFont(Font.font("Verdana",FontWeight.BOLD,18));
        nameTxt.setFill(Color.DARKBLUE);
        nameTxt.setFont(Font.font("Verdana",18));
         
         
        TextFlow textFlowPane = new TextFlow();
        textFlowPane.getChildren().addAll(welcomeTxt,nameTxt,gladTxt);
 
        ImageView img = new ImageView();
        ImageView img2 = new ImageView();
        ImageView img3 = new ImageView();
        ImageView img4 = new ImageView();
        ImageView img5 = new ImageView();
         
        Image playGame = new Image("file:assets/playgame.png");
        Image playSavesGame = new Image("file:assets/playsavedgame.png");
        Image pointsImg = new Image("file:assets/points.png");
        Image ranking = new Image("file:assets/ranking.png");
        Image exitIMG = new Image("file:assets/exit.png");
         
        img.setImage(playGame);
        img2.setImage(playSavesGame);
        img3.setImage(pointsImg);
        img4.setImage(ranking);
        img5.setImage(exitIMG);
         
        Button startNewGame = new Button("Start new game",img);
        Button openGame = new Button("Open a saved game",img2);
        Button showPersonalPoints = new Button("My personal points",img3);
        Button topTen = new Button("Ranking (Top 10)",img4);
        Button exit = new Button("Exit game",img5);
         
        startNewGame.setFont(Font.font("Verdana",14));
        openGame.setFont(Font.font("Verdana",14));
        showPersonalPoints.setFont(Font.font("Verdana",14));
        topTen.setFont(Font.font("Verdana",14));
        exit.setFont(Font.font("Verdana",14));
         
         
        startNewGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                scene2 = new Scene(drawBoard(getName(),primaryStage), 850, 600); 
                primaryStage.setScene(scene2);
            }
        });
        
        
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setName("");
                Platform.exit();
            }
        });
         
        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(startNewGame,openGame);
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(showPersonalPoints,topTen,exit); 
        HBox hbox3 = new HBox();
        hbox3.getChildren().add(textFlowPane);
         
        hbox1.setMinWidth(350);
        hbox1.setAlignment(Pos.CENTER);

        hbox2.setPadding(new Insets(10));
        hbox2.setSpacing(10);
        hbox2.setMinWidth(350); 
        hbox2.setAlignment(Pos.CENTER);
         
        hbox3.setMinWidth(350);
        hbox3.setAlignment(Pos.CENTER);
         
        root2.add(hbox3,1,1);
        root2.add(hbox1,1,3);
        root2.add(hbox2,1,4);
        root2.setAlignment(Pos.CENTER);
         
        return root2;
    }
    
    // Código para desenhar o cenário de tabuleiro
    public BorderPane drawBoard(String name, Stage primaryStage){
        BorderPane bp = new BorderPane();
        GridPane grid = new GridPane();
        setPoints(0);
        /*DrawBlocksToPlay blockuDokuBlocksToPlay = new DrawBlocksToPlay("basic");
        blockuDokuBlocksToPlay.createBlockSpace(3);*/
        
        
        // Criação da barra de Menu
        MenuBar menuBar = new MenuBar();
        Menu options = new Menu("Options");
        MenuItem save = new MenuItem("Save");
        MenuItem exit = new MenuItem("Exit");
        
        save.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
                FileChooser.ExtensionFilter binFilter = new FileChooser.ExtensionFilter("Bin Files (*.bin)", "*.bin");
                fileChooser.getExtensionFilters().addAll(txtFilter,binFilter);
                fileChooser.setTitle("Save game");
                fileChooser.setInitialDirectory(new File("C:\\"));
                File file = fileChooser.showSaveDialog(primaryStage);
                // Tem de ser modificado
                String texto = "123";
                if(file != null){
                    saveTextToFile(texto,file);
                }
            }
        });
        
        exit.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                scene2 = new Scene(returnScreen2(getName(),primaryStage), 850, 600); 
                primaryStage.setScene(scene2);        
            }
        });
        Image saveGame = new Image("file:assets/playsavedgame.png");
        ImageView saveGameView = new ImageView(saveGame);
        saveGameView.setFitWidth(15);
        saveGameView.setFitHeight(15);
        save.setGraphic(saveGameView);
        
        Image exitToMenu = new Image("file:assets/exit.png");
        ImageView exitImgView = new ImageView(exitToMenu);
        exitImgView.setFitWidth(15);
        exitImgView.setFitHeight(15);
        exit.setGraphic(exitImgView);
        
        options.getItems().addAll(save,exit);
        menuBar.getMenus().addAll(options);
        bp.setTop(menuBar);  
        
        // Criação do tabuleiro do BlockuDoku
        /*Board BlockuDokuBoard = new Board(9,9);
        BlockuDokuBoard.addHeaders(9);
        BlockuDokuBoard.addNumberColumn(9);
        
        bp.setCenter(BlockuDokuBoard.getBoard());
        */
        // Pontos 
        HBox hbox1 = new HBox();
        String actualPoints = "Pontos: " + String.valueOf(getPoints());
        Text pointsTxt = new Text(actualPoints);
        pointsTxt.setFont(Font.font("Verdana",18));
        pointsTxt.setFill(Color.DARKBLUE);
        hbox1.getChildren().add(pointsTxt);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setPadding(new Insets(-100, 0, 0, 40));
        grid.setAlignment(Pos.CENTER);
        
        bp.setLeft(hbox1);
        
        bp.prefHeightProperty().bind(scene.heightProperty());
        bp.prefWidthProperty().bind(scene.widthProperty());
        
        //bp.setBottom(blockuDokuBlocksToPlay.getBlockGroup());
        return bp;
    }
    
    private void saveTextToFile(String content, File file) {
        try {
            PrintWriter writer;
            writer = new PrintWriter(file);
            writer.println(content);
            writer.close();
        } catch (IOException ex) {
            //Classe tem de mudar
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    
    public String getFilename(){
        return this.filename;
    }
    
    public void setFilename(String filename){
        this.filename = filename;
    }
    
    public String getFileType(){
        return this.fileType;
    }
    
    public void setFileType(String fileType){
        this.fileType = fileType;
    }
    
    public int getPoints(){
        return this.points;
    }
    
    public void setPoints(int points){
        this.points = points;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
     
}
