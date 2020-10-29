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
public class qBlock {
    private GridPane qBlock;
    
    public qBlock(){
        qBlock = new GridPane();
        for(int i=1;i<3;i++){
            for(int a=1;a<3;a++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                qBlock.add(unit.drawBlockUnit(),i,a); 
            }
        }
    } 
    public GridPane getQBlock(){
        return this.qBlock;
    }
    
}
