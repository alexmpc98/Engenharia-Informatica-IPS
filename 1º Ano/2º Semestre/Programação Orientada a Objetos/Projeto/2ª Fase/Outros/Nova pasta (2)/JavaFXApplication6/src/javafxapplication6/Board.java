/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication6;

import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Alexandre
 */
public class Board {
    private GridPane board;
    private ArrayList<Character> boardHeaders = new ArrayList<>();
    
    public Board(int numberOfColumns, int numberOfRows){
        board = new GridPane();
        
        //Adicionar todos os headers possiveis
        for(char chr = 'a'; chr <= 'z'; chr++){
            boardHeaders.addAll(Collections.nCopies(1, Character.toUpperCase(chr)));
        }
        
        // Criar o tabuleiro com botões em branco
        for(int row=2;row<numberOfColumns + 2;row++){
            for(int col=2;col<numberOfRows + 2;col++){
                Button newBt = new Button();
                newBt.setMinWidth(50);
                newBt.setMinHeight(30);
                
                // OnClick no tabuleiro
                /*newBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    Button btn = new Button();
                    btn.setMinWidth(50);
                    btn.setMinHeight(30);
                    btn.setStyle("-fx-background-color: blue;-fx-border-color:black;");
                    System.out.println("Coordenates " + board.getColumnIndex(newBt) + " " + board.getRowIndex(newBt));
                    board.add(btn,board.getColumnIndex(newBt),board.getRowIndex(newBt));
                        
                }
                }); */
                newBt.setStyle("-fx-background-color: white;-fx-border-color:black;");
                board.add(newBt,row,col);
            }
        }
        board.setPadding(new Insets(50, 0, 0, 50));   
    }
    
    public GridPane getBoard(){
        return this.board;
    }
    
    public void addHeaders(int numberOfHeaders){
        for(int i=1;i<=numberOfHeaders;i++){
            Text txt = new Text();
            txt.setText(String.valueOf(this.boardHeaders.get(i-1)));
            txt.setFont(Font.font("Verdana",20));
            getBoard().add(txt,i+1,1);
            getBoard().setHalignment(txt, HPos.CENTER);
        }
    }
    
    public void addNumberColumn(int numbersOfCols){
        // Aplicar os números ao tabuleiro dinamicamente
        for(int i=1;i<=numbersOfCols;i++){
            Text numberTxt = new Text();
            String s = String.valueOf(i);
            numberTxt.setText(s);
            numberTxt.setFont(Font.font("Verdana",20));
            getBoard().add(numberTxt,1,i+1);
            getBoard().setHalignment(numberTxt, HPos.CENTER);
            getBoard().setMargin(numberTxt, new Insets(3, 7, 3, 7));
        }
    }
    
    public ArrayList<Character> getBoardHeaders(){
        return this.boardHeaders;
    }
    
    public void addToHeader(String header){
        if(header.length() == 1){
            this.boardHeaders.add(header.charAt(0));
        }
    }
    
}
