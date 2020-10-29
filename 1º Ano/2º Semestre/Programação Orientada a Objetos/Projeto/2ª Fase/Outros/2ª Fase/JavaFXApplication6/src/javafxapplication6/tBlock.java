/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alexandre
 */
public class tBlock {
    private GridPane tBlock;
    
    public tBlock(){
        tBlock = new GridPane();
        for(int a=1;a<4;a++){
            BlockButtonUnit unit = new BlockButtonUnit(50,30);
            tBlock.add(unit.drawBlockUnit(),a,1);
        }
        BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
        tBlock.add(unit2.drawBlockUnit(),2,2);
    }
    
    public GridPane getTBlock(){
        return this.tBlock;
    }
}
