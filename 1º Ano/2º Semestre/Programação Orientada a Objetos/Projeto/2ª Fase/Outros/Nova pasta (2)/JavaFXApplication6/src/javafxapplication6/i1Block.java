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
public class i1Block{
    private GridPane gridI1Block;
    
    public i1Block(){
            gridI1Block = new GridPane();
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            gridI1Block.add(unit.drawBlockUnit(),1,1);
    }
    
    public GridPane getI1Block(){
        return this.gridI1Block;
    }  
}
