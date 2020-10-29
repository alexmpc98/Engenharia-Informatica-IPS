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
public class lBlock {
    private GridPane lBlock;
      
    public lBlock(){
        lBlock = new GridPane();
        
        for(int a=1;a<4;a++){
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            lBlock.add(unit.drawBlockUnit(),1,a);    
        }
        BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
        lBlock.add(unit2.drawBlockUnit(),2,3);
        
    }
    
    public GridPane getLBlock(){
        return this.lBlock;
    }
}
