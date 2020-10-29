/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Alexandre e Sérgio
 */
public class Block implements Serializable{

    private ArrayList<Square> squares;
    private GameMode blockGameMode;

    /**
     * Constructor que recebe um ArrayList de quadrados e o modo de jogo e 
     * inicializa o bloco.
     * 
     * @param squares ArrayList(Square)
     * @param gameMode GameMode
     */
    public Block(ArrayList<Square> squares, GameMode gameMode) {
        this.squares = new ArrayList<Square>(squares);
        this.blockGameMode = gameMode;
    }

    /**
     * Este método verifica quais as colunas possuem a linha recebida (Y) e 
     * retorna um ArrayList com estas colunas.
     * 
     * @param y int
     * @return Conjunto de colunas (ArrayList(Integer))
     */
    public ArrayList<Integer> getColumnsInteger(int y) {
        ArrayList<Integer> output = new ArrayList<>();
        for (Square square : this.squares) {
            //System.out.println(square.getY() + " | " + square.getX());
            if (square.getY() == y) {
                output.add(square.getX());
            }
        }
        return output;
    }

    /**
     * Este método retorna os quadrados do bloco.
     * 
     * @return Quadrados (ArrayList(Square))
     */
    public ArrayList<Square> getSquares() {
        return this.squares;
    }
    
    /**
     * Este método retorna o modo de jogo do bloco.
     * 
     * @return Modo de jogo do bloco (GameMode)
     */
    public GameMode getBlockGameMode() {
        return this.blockGameMode;
    }
    
    /**
     * Este método recebe um linha (Y) e compara na lista de quadrados e devolve
     * um ArrayList com os quadrados que cumprem os requesito de comparação.
     * 
     * @param y int
     * @return Lista de quadrados (ArrayList(Square))
     */
    public ArrayList<Square> getColumnsObject(int y) {
        ArrayList<Square> output = new ArrayList<>();
        for (Square square : this.squares) {
            if (square.getY() == y) {
                output.add(square);
            }
        }
        return output;
    }

    /**
     * Este método compara as colunas da lista recebida  com o valor recebido
     * e retorna verdadeiro ou false consoante o resultadao da comparação.
     * 
     * @param columnsList ArrayList(Integer)
     * @param value int
     * @return Resultado da comparação (boolean)
     */
    public boolean compareColumns(ArrayList<Integer> columnsList, int value) {
        for (Integer column : columnsList) {
            if (column == value) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Este método verifica qual é o quadrado mais a esquerda e retorna o valor
     * da linha (Y).
     * 
     * @param objectsList ArrayList(Square)
     * @return Linha mais a esquerda (int)
     */
    public int getLeftSquare(ArrayList<Square> objectsList) {
        int maxRow = 0;
        for (Square square : objectsList) {
            //System.out.println(square.getY() +" | "+square.getX() + "-> " + maxRow);
            if (square.getY() > maxRow && square.getX() == 1) {
                maxRow = square.getY();
            }
        }
        return maxRow;
    }
    
    /**
     * Este método recebe um lista de objetos e uma coluna e devolve o indice
     * desta coluna ou null se não existir.
     * 
     * @param objectsList ArrayList(Square)
     * @param value int
     * @return Indice da coluna (Integer)
     */
    public Integer getCurrentColumnIndex(ArrayList<Square> objectsList, int value) {
        int index = 0;
        for (Square square : objectsList) {
            if (square.getX() == value) {
                return index;
            }
            index++;
        }
        return null;
    }

    /**
     * Este método gera e devolve o bloco.
     * 
     * @return Bloco (String)
     */
    public String getBlockToDraw() {
        String output = "";
        ArrayList<Square> squaresSorted = new ArrayList<Square>(this.squares);
        Collections.sort(squaresSorted);
        List<Square> distinctSquares = squaresSorted.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> currentColumnValues = new ArrayList<>();
        for (int i = distinctSquares.size(); i > 0; i--) {
            //System.out.println("L: " + distinctSquares.get(i - 1).getY() + " - "+i);
            currentColumnValues = getColumnsInteger(distinctSquares.get(i - 1).getY());
            //currentSquareValues = getColumnsObject(distinctSquares.get(i - 1).getY());
            //System.out.println(currentColumnValues);
            for (int d = 0; d < squaresSorted.size(); d++) {
                //System.out.println("C: " + squaresSorted.get(d).getX() + "D: " + (d + 1));
                if (compareColumns(currentColumnValues, (d + 1))) {
                    //System.out.println("C: " + squaresSorted.get(d).getX() + " D: " + (d + 1));
                    output += "#";
                } else {
                    output += " ";
                }
            }
            output += "\n";
        }
        //System.out.println("\n\n\n");
        return output;
    }

    /**
     * Este método retorna as coordenadas X e Y de cada quadrado do bloco. 
     * 
     * @return X e Y de cada quadrado (String)
     */
    public String toString() {
        String output = "Block Coordinates: \n";
        for (int d = 0; d < this.squares.size(); d++) {
            output += "X: " + this.squares.get(d).getX() + "  |  Y: " + this.squares.get(d).getY() + "\n";
        }
        return output;
    }
}
