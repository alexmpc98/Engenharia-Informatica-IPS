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
public class iBlock{
    private GridPane iBlock;
    
    public iBlock(){
        iBlock = new GridPane();
        for(int a=1;a<5;a++){
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            iBlock.add(unit.drawBlockUnit(),1,a);
        }  
    }
    
    public GridPane getIBlock(){
        return this.iBlock;
    }
    
}
