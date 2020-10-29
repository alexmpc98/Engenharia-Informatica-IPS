/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import javafx.scene.layout.GridPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre
 */
public class lMaxBlock {
    private GridPane gridlMaxBlock;
    
    public lMaxBlock(){
            this.gridlMaxBlock = new GridPane();
            for(int a=1;a<4;a++){
                BlockButtonUnit unit = new BlockButtonUnit(50,30);
                gridlMaxBlock.add(unit.drawBlockUnit(),1,a);    
            }
            for(int i=2;i<4;i++){
                BlockButtonUnit unit2 = new BlockButtonUnit(50,30);
                gridlMaxBlock.add(unit2.drawBlockUnit(),i,3);
            }
    }
    
    public GridPane getLMaxBlock(){
        return this.gridlMaxBlock;
    }
    
}
