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
public class zBlock {
    private GridPane zBlock;
    
    public zBlock(){
        zBlock = new GridPane();  
         for(int a=0;a<2;a++){
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            zBlock.add(unit.drawBlockUnit(),a,1);    
        }
        for(int i=1;i<3;i++){
            BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
            zBlock.add(unit2.drawBlockUnit(),i,2);
        }
    }
    
    public GridPane getZBlock(){
        return this.zBlock;
    }
}
