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
public class tEstBlock{
       private GridPane gridTEstBlock;
    
    public tEstBlock(){
            gridTEstBlock = new GridPane();  
            for(int a=1;a<4;a++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridTEstBlock.add(unit.drawBlockUnit(),a,1);
            }
            for(int i=1;i<4;i++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridTEstBlock.add(unit.drawBlockUnit(),2,i);
            } 
    }
    
    public GridPane getTEstBlock(){
        return this.gridTEstBlock;
    }
}
