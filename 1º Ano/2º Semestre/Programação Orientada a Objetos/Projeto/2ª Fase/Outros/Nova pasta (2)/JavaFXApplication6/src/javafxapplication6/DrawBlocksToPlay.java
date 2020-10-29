/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Alexandre
 */
public class DrawBlocksToPlay {
    private boolean clicked;
    private BlockHandler newBlocks;
    private Background blockBackground = new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY));   
    private Background blockClickedBackground = new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    private HBox blockBox = new HBox();
    private ArrayList<VBox> blocksBoxes = new ArrayList<>();
    private String blockBorderLayout = "-fx-border-color: black;\n" +
                   "-fx-border-insets: 3;\n" +
                   "-fx-border-width: 3;\n" +
                   "-fx-border-style: dashed solid;\n";
    
    
    public DrawBlocksToPlay(String mode){
        if(mode.length() > 0){
             newBlocks = new BlockHandler(mode);
        }
        // Else com excepção
    }
    
    public BlockHandler getBlockHandler(){
        return this.newBlocks;
    }
    
    public void changeBlockHandlerMode(String mode){
        this.newBlocks = new BlockHandler(mode);
    }
    
    public void createBlockSpace(int numberOfBlocks){
        for(int i=0;i<numberOfBlocks;i++){
            VBox blockVBox = new VBox();
            blockVBox.setStyle(blockBorderLayout);
            blockVBox.setMinWidth(150);
            blockVBox.setAlignment(Pos.CENTER);
            blockVBox.setBackground(blockBackground);
            blockVBox.setOnMouseClicked( ( e ) ->
            {   
                if(getClicked()){
                    blockVBox.setBackground(blockClickedBackground);
                    setClicked(false);
                }
                else{
                    setClicked(true);
                    blockVBox.setBackground(blockBackground);
                }
            }); 
            blockVBox.setMargin(getBlockHandler().getRandomBlock(), new Insets(10, 10, 10, 35));
            blockVBox.getChildren().add(getBlockHandler().getRandomBlock());
            getBlocksBoxes().add(blockVBox);    
        }   
        addBlocksToGroup();
    }
    
     public void addBlocksToGroup(){
        getBlockGroup().setPadding(new Insets(-140, 15, 10, 180));
        getBlockGroup().setSpacing(15);
        for(int i=0;i<getBlocksBoxes().size();i++){
            getBlockGroup().getChildren().add(getBlocksBoxes().get(i));
        }
    }
    
    public ArrayList<VBox> getBlocksBoxes(){
        return this.blocksBoxes;
    }
    
    public HBox getBlockGroup(){
        return this.blockBox;
    }
    
    public boolean getClicked(){
        return this.clicked;
    }
    
     public void setClicked(boolean clickedState){
        this.clicked = clickedState;
    } 
    
     
}
