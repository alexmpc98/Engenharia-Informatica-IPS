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
public class i2Block {
    private GridPane gridi2Block;
    
    public i2Block(){
            gridi2Block = new GridPane();
            for(int i=1;i<3;i++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridi2Block.add(unit.drawBlockUnit(),1,i);
            }
    }
    
    public GridPane getI2Block(){
        return this.gridi2Block;
    }
}
