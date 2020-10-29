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
public class qEstBlock {
       private GridPane gridQEstBlock;
    
    public qEstBlock(){
            gridQEstBlock = new GridPane();  
            for(int a=1;a<4;a++){
                for(int i=1;i<4;i++){
                    BlockButtonUnit unit = new BlockButtonUnit(50,30);
                    gridQEstBlock.add(unit.drawBlockUnit(),i,a);
                }
            }
    }
    
    public GridPane getQEstBlock(){
        return this.gridQEstBlock;
    }
}
