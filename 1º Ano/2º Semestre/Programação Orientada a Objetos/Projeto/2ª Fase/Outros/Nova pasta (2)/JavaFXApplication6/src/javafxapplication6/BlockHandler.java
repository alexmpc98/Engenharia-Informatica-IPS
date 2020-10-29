/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Alexandre
 */
public class BlockHandler{
    private ArrayList<GridPane> blocks = new ArrayList<>();
    
    public BlockHandler(String mode){
        if(mode.equals("basic")){
            iBlock BlockI = new iBlock();
            qBlock BlockQ = new qBlock();
            tBlock BlockT = new tBlock();
            lBlock BlockL = new lBlock();
            jBlock BlockJ = new jBlock();
            sBlock BlockS = new sBlock();
            zBlock BlockZ = new zBlock();
            
            this.blocks.add(BlockI.getIBlock());
            this.blocks.add(BlockQ.getQBlock());
            this.blocks.add(BlockT.getTBlock());
            this.blocks.add(BlockL.getLBlock());
            this.blocks.add(BlockJ.getJBlock());
            this.blocks.add(BlockS.getSBlock());
            this.blocks.add(BlockZ.getZBlock());      
        }
        
        else if(mode.equals("advanced")){
            i1Block Blocki1 = new i1Block();
            i2Block Blocki2 = new i2Block();
            i3Block Blocki3 = new i3Block();
            lMinBlock BlocklMin = new lMinBlock();
            lMaxBlock BlocklMax = new lMaxBlock();
            tEstBlock BlockTEst = new tEstBlock();
            qEstBlock BlockQEst = new qEstBlock();
            
            this.blocks.add(Blocki1.getI1Block()); 
            this.blocks.add(Blocki2.getI2Block()); 
            this.blocks.add(Blocki3.getI3Block()); 
            this.blocks.add(BlocklMin.getLMinBlock()); 
            this.blocks.add(BlocklMax.getLMaxBlock()); 
            this.blocks.add(BlockTEst.getTEstBlock()); 
            this.blocks.add(BlockQEst.getQEstBlock()); 
        }
        //Else com exception
    }
    
    public GridPane getRandomBlock(){
        Random rand = new Random();
        return this.blocks.get(rand.nextInt(6));
    }
    
    public ArrayList<GridPane> getArrayOfBlocks(){
        return this.blocks;
    }
    
}
