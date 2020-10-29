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
public class lMinBlock {
    private GridPane gridlMinBlock;
    
    public lMinBlock(){
            this.gridlMinBlock = new GridPane();
            for(int a=1;a<3;a++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridlMinBlock.add(unit.drawBlockUnit(),1,a);    
            }
            BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
            gridlMinBlock.add(unit2.drawBlockUnit(),2,2);
    }
    
    public GridPane getLMinBlock(){
        return this.gridlMinBlock;
    }
}
