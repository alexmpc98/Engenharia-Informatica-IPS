/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import BlockuDoku.Backend.Block;
import BlockuDoku.Backend.Game;
import BlockuDoku.Backend.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author Alexandre e Sérgio
 */
public class DrawBlocksToPlay {
    private VBox clicked;
    private Block selectedBlock;
    private String selectedBlockLetter;
    private Game game;
    private Background blockBackground = new Background(new BackgroundFill(Color.CORNSILK, CornerRadii.EMPTY, Insets.EMPTY));
    private Background blockClickedBackground = new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY));
    private HBox blockBox = new HBox();
    private ArrayList<VBox> blocksBoxes;
    private String blockBorderLayout = "-fx-border-color: black;\n"
                                     + "-fx-border-insets: 3;\n"
                                     + "-fx-border-width: 3;\n"
                                     + "-fx-border-style: dashed solid;\n";

    /**
     * Contrutor da class DrawBlocksToPlay, que inicializa uma instância dos blocos 
     * disponiveis para jogar.
     * 
     * @param game Game
     */
    public DrawBlocksToPlay(Game game) {
        this.game = game;
        this.clicked = null;
        this.selectedBlock = null;
        this.blocksBoxes = new ArrayList<>();
        this.selectedBlockLetter= "";
    }

    /**
     * Este método recebe o número de blocos e criação o seu espaço no ecrã e 
     * desenha-os.
     * 
     * @param numberOfBlocks int
     */
    public void createBlockSpace(int numberOfBlocks) {
        HashMap<String, Block> roundBlocks = this.game.getRoundBlocks();
        for (Map.Entry<String, Block> entry : roundBlocks.entrySet()) {
            GridPane randomBlock = getBlockToDraw(entry.getValue());;
            VBox blockVBox = new VBox();
            blockVBox.setStyle(blockBorderLayout);
            blockVBox.setMinWidth(150);
            blockVBox.setAlignment(Pos.CENTER);
            blockVBox.setBackground(blockBackground);
            blockVBox.setOnMouseClicked((e)
                    -> {
                if (clicked != null) {
                    clicked.setBackground(blockBackground);
                }
                if (clicked != blockVBox) {
                    selectedBlock = entry.getValue();
                    selectedBlockLetter = entry.getKey();
                    this.blocksBoxes.remove(blockVBox);
                }
                clicked = blockVBox;
                blockVBox.setBackground(blockClickedBackground);
            });
            blockVBox.setMargin(randomBlock, new Insets(10, 10, 10, 35));
            blockVBox.getChildren().add(randomBlock);
            getBlocksBoxes().add(blockVBox);
        }
        addBlocksToGroup();
    }

    /**
     * Este método adiona os blocos criados ao grupo de blocos.
     * 
     */
    public void addBlocksToGroup() {
        getBlockGroup().setPadding(new Insets(-140, 15, 10, 180));
        getBlockGroup().setSpacing(15);
        for (int i = 0; i < getBlocksBoxes().size(); i++) {
            getBlockGroup().getChildren().add(getBlocksBoxes().get(i));
        }
    }

    /**
     * Este método recebe um bloco e desenha-o.
     *
     * @param block Block
     * @return Bloco (GridPane)
     */
    public GridPane getBlockToDraw(Block block) {
        GridPane drawnBlock = new GridPane();
        ArrayList<Square> squaresSorted = new ArrayList<Square>(block.getSquares());
        Collections.sort(squaresSorted, Collections.reverseOrder());
        List<Square> distinctSquares = squaresSorted.stream().distinct().collect(Collectors.toList());
        ArrayList<Square> currentSquareValues = new ArrayList<>();
        //System.out.println("Rows: " + distinctSquares);
        for (int row = 0; row < distinctSquares.size(); row++) {
            currentSquareValues = block.getColumnsObject(distinctSquares.get(row).getY());
            //System.out.println("Columns: " + currentSquareValues + " | Row: " + distinctSquares.get(row).getY());
            for (int col = 0; col < currentSquareValues.size(); col++) {
                //System.out.println("Col: " + currentSquareValues.get(col).getX());
                BlockButtonUnit unit = new BlockButtonUnit(50, 30);
                drawnBlock.add(unit.drawBlockUnit(), currentSquareValues.get(col).getX() - 1, distinctSquares.size() - (distinctSquares.get(row).getY() - 1));
            }
        }
        return drawnBlock;
    }

    /**
     * Este método retorna a lista de blocos desenhados (Vbox's).
     * 
     * @return Lista de blocos desenhados (ArrayList(VBox))
     */
    public ArrayList<VBox> getBlocksBoxes() {
        return this.blocksBoxes;
    }

    /**
     * Este método retorna o grupo de blocos desenhados. 
     * 
     * @return Grupo de blocos desenhados (Hbox)
     */
    public HBox getBlockGroup() {
        return this.blockBox;
    }

    /**
     * Este método retorna a o bloco desenhado clicado.
     * 
     * @return Bloco desenhado (Vbox)
     */
    public VBox getClicked() {
        return clicked;
    }
    
    /**
     * Este método retorna o bloco selecionado.
     * 
     * @return Bloco (Block)
     */
    public Block getSelectedBlock() {
        return selectedBlock;
    }

    /**
     * Este método retorna a letra do bloco.
     * 
     * @return Letra do bloco (String)
     */
    public String getSelectedBlockLetter() {
        return selectedBlockLetter;
    }
    
    /**
     * Este método limpa o desenho do bloco selecionado.
     * 
     */
    public void resetClicked() {
        this.clicked.setBackground(blockBackground);
        this.clicked = null;
        this.selectedBlock = null;
        this.selectedBlockLetter= "";
    }
       
}
