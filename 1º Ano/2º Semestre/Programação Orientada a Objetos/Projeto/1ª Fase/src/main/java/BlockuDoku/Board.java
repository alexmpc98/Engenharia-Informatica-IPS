/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Board implements Serializable {

    private ArrayList<String> header = new ArrayList<>();
    private String[][] boardPositions = new String[9][9];

    /**
     * Constructor que inicializa o tabuleiro (Board)
     *
     */
    public Board() {
        this.header.add("A");
        this.header.add("B");
        this.header.add("C");
        this.header.add("D");
        this.header.add("E");
        this.header.add("F");
        this.header.add("G");
        this.header.add("H");
        this.header.add("I");
        for (int i = 0; i < boardPositions.length; i++) {
            for (int j = 0; j < boardPositions[i].length; j++) {
                boardPositions[i][j] = ".";
            }
        }
    }

    /**
     * Este método retorna uma string com o tabuleiro de jogo.
     *
     * @return Tabuleiro de jogo (String)
     */
    public String getBoardDraw() {
        String output = "\n  | A | B | C | D | E | F | G | H | I \n";
        for (int i = 0; i < boardPositions.length; i++) {
            output += i + 1;
            for (int j = 0; j < boardPositions[i].length; j++) {
                output += " | " + boardPositions[i][j];
            }
            output += "\n";
        }
        return output;
    }

    /**
     * Este método percorre as linhas e verifica se estão completas, e se sim
     * adiciona-as a um ArrayList que será retornado.
     *
     * @return Lista de linhas preenchidas (ArrayList(Integer))
     */
    public ArrayList<Integer> checkRowPoints() {
        ArrayList<Integer> output = new ArrayList<>();
        int rowCount = this.boardPositions.length;
        int colCount = this.boardPositions[0].length;
        int blockCounter;
        for (int row = 0; row < rowCount; row++) {
            blockCounter = 0;
            for (int col = 0; col < colCount; col++) {
                if (this.boardPositions[row][col] == "#") {
                    blockCounter++;
                }
            }
            if (blockCounter == 9) {
                output.add(row);
            }
        }
        return output;
    }

    /**
     * Este método percorre as colunas e verifica se estão completas, e se sim
     * adiciona-as a um ArrayList que será retornado.
     *
     * @return Lista de colunas preenchidas (ArrayList(Integer))    
     */
    public ArrayList<Integer> checkColumnPoints() {
        ArrayList<Integer> output = new ArrayList<>();
        int rowCount = this.boardPositions.length;
        int colCount = this.boardPositions[0].length;
        int blockCounter;
        for (int col = 0; col < colCount; col++) {
            blockCounter = 0;
            for (int row = 0; row < rowCount; row++) {
                if (this.boardPositions[row][col] == "#") {
                    blockCounter++;
                }
            }
            if (blockCounter == 9) {
                output.add(col);
            }
        }
        return output;
    }

    /**
     * Este método percorre os quadrados grandes e verifica se estão completos, 
     * e se sim adiciona-os a um ArrayList que será retornado.
     *
     * @return Lista de quadrados grandes preenchidos (ArrayList(Square)) 
     */
    public ArrayList<Square> checkSquarePoints() {
        ArrayList<Square> output = new ArrayList<>();
        int blockCounter = 0;
        int rowStart = 0;
        int rowEnd = 3;
        int colStart = 0;
        int colEnd = 3;
        for (int squareRow = 0; squareRow < 3; squareRow++) {
            for (int squareCol = 0; squareCol < 3; squareCol++) {
                for (int row = rowStart; row < rowEnd; row++) {
                    for (int col = colStart; col < colEnd; col++) {
                        if (this.boardPositions[row][col] == "#") {
                            blockCounter++;
                        }
                    }
                }
                if (blockCounter == 9) {
                    output.add(new Square(squareCol, squareRow));
                }
                blockCounter = 0;
                colStart += 3;
                colEnd += 3;
            }
            colStart = 0;
            colEnd = 3;
            rowStart += 3;
            rowEnd += 3;
        }
        return output;
    }

    /**
     * Este método recebe uma linha e limpa essa linha do tabuleiro e retorna o
     * numero de quadrados limpos.
     *
     * @param row int
     * @return Numero de quadrados limpos (int)
     */
    public int clearRow(int row) {
        int colCount = this.boardPositions[0].length;
        for (int col = 0; col < colCount; col++) {
            this.boardPositions[row][col] = ".";
        }
        return colCount;
    }

    /**
     * Este método recebe uma coluna e limpa essa coluna do tabuleiro e retorna
     * o numero de quadrados limpos.
     *
     * @param col int
     * @return Numero de quadrados limpos (int)
     */
    public int clearColumn(int col) {
        int rowCount = this.boardPositions.length;
        for (int row = 0; row < rowCount; row++) {
            this.boardPositions[row][col] = ".";
        }
        return rowCount;
    }

    /**
     * Este método recebe uma quadrado grande e limpa esse quadrado do tabuleiro
     * e retorna o numero de quadrados pequenos limpos.
     *
     * @param square Square
     * @return Numero de quadrados limpos (int)
     */
    public int clearSquare(Square square) {
        int squareCount = 3 * 3;
        for (int row = square.getY() * 3; row < (square.getY() + 1) * 3; row++) {
            for (int col = square.getX() * 3; col < (square.getX() + 1) * 3; col++) {
                this.boardPositions[row][col] = ".";
            }
        }
        return squareCount;
    }

    /**
     * Este método recebe um bloco, a coluna e a linha em que o bloco deve ser 
     * inserido e adiciona este bloco ao tabuleiro.
     * 
     * @param block Block
     * @param insertedColumn String
     * @param insertedRow int
     * @throws ExceptionManager CANT_PLACE_BLOCK
     */
    public void addBlock(Block block, String insertedColumn, int insertedRow) throws ExceptionManager {
        int letterIndex = this.header.indexOf(insertedColumn);
        Square maxColumnSquare = Collections.max(block.getSquares(), Comparator.comparing(p -> p.getX()));
        Square maxRowSquare = Collections.max(block.getSquares(), Comparator.comparing(p -> p.getY()));       
        Integer currentColumnIndex;
        if ((8 - (letterIndex - 1)) >= maxColumnSquare.getX()) {
            ArrayList<Square> squaresSorted = new ArrayList<Square>(block.getSquares());
            List<Square> distinctSquares = squaresSorted.stream().distinct().collect(Collectors.toList());
            ArrayList<Integer> currentColumnValues = new ArrayList<>();
            ArrayList<Square> currentSquareValues = new ArrayList<>();
            if (block.getLeftSquare(block.getSquares()) <= 1) {
                Collections.sort(squaresSorted);
            } else {
                Collections.sort(squaresSorted, Collections.reverseOrder());
            }
            for (int row = 0; row < distinctSquares.size(); row++) {
                currentColumnValues = block.getColumnsInteger(distinctSquares.get(row).getY());
                currentSquareValues = block.getColumnsObject(distinctSquares.get(row).getY());
                for (int col = 0; col < squaresSorted.size(); col++) {
                    if (block.compareColumns(currentColumnValues, (col + 1))) {
                        currentColumnIndex = block.getCurrentColumnIndex(currentSquareValues, (col + 1));
                        if (currentColumnIndex != null) {
                            if (block.getLeftSquare(block.getSquares()) <= 1) {
                                this.boardPositions[((insertedRow - 1) - (currentSquareValues.get(currentColumnIndex).getY() - 1))][(letterIndex + currentSquareValues.get(currentColumnIndex).getX() - 1)] = "#";
                                /*System.out.println(
                                        "TRow(1):"
                                        + ((insertedRow - 1) - (currentSquareValues.get(currentColumnIndex).getY() - 1))
                                        + " | "
                                        + "TCol(1):"
                                        + (letterIndex + currentSquareValues.get(currentColumnIndex).getX() - 1)
                                );*/
                            } else {
                                this.boardPositions[((insertedRow - 1) - (currentSquareValues.get(currentColumnIndex).getY() - maxRowSquare.getY()))][(letterIndex + currentSquareValues.get(currentColumnIndex).getX() - 1)] = "#";
                                /*System.out.println(
                                        "TRow(2):"
                                        + ((insertedRow - 1) - (currentSquareValues.get(currentColumnIndex).getY() - maxRowSquare.getY()))
                                        + " | "
                                        + "TCol(2):"
                                        + (letterIndex + currentSquareValues.get(currentColumnIndex).getX() - 1)
                                );*/
                            }
                        } else {
                            throw new ExceptionManager(ErrorCode.CANT_PLACE_BLOCK);
                        }
                    }
                }
            }
        } else {
            throw new ExceptionManager(ErrorCode.CANT_PLACE_BLOCK);
        }
    }
}
