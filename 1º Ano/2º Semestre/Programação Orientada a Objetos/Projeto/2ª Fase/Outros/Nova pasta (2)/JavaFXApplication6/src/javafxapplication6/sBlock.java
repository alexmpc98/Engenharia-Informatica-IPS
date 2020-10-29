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
public class sBlock {
    private GridPane sBlock;
    
    public sBlock(){
        sBlock = new GridPane();
        
        for(int a=1;a<3;a++){
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            sBlock.add(unit.drawBlockUnit(),a,1);    
        }
        for(int i=0;i<2;i++){
            BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
            sBlock.add(unit2.drawBlockUnit(),i,2);
        }
    }
    
    public GridPane getSBlock(){
        return this.sBlock;
    }
    
}
