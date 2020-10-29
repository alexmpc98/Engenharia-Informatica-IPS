/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import javafx.scene.control.Button;

/**
 *
 * @author Alexandre e Sérgio
 */
public class BlockButtonUnit {
    private Button blockUnit;
    
    public BlockButtonUnit(int width, int height){
            blockUnit = new Button();
            blockUnit.setStyle("-fx-background-color: blue;-fx-border-color:black;");
            blockUnit.setMinWidth(width);
            blockUnit.setMinHeight(height);
    }
    
    public Button drawBlockUnit(){
        return this.blockUnit;
    }
}
