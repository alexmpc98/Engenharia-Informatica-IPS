/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import javafx.scene.layout.GridPane;

/**
 *
 * @author Alexandre
 */
public class i3Block {
    private GridPane gridI3Block;
    
    public i3Block(){
            this.gridI3Block = new GridPane();
            for(int i=1;i<4;i++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridI3Block.add(unit.drawBlockUnit(),1,i);
            }
    }
    
    public GridPane getI3Block(){
        return this.gridI3Block;
    }
    
}
