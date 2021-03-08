/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.refatoringexamples.extractmethod;

/**
 *
 * @author patricia.macedo
 */
public class ExtractMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo1 = "Duplicate Code";
        String titulo2 = "Extract Method";
        spaces(4);
        symbols(titulo1,"*");
        spaces(3);
        symbols(titulo2,"-");

        // O que foi "refactured"
//        String titulo1 = "Duplicate Code";
//        String titulo2 = "Extract Method";
//
//        for (int i = 0; i < 4; i++) {
//            System.out.println();
//        }
//        for (int i = 0; i < titulo1.length(); i++) {
//            System.out.print("*");
//        }
//        System.out.println("\n" + titulo1);
//
//        for (int i = 0; i < 3; i++) {
//            System.out.println();
//        }
//        for (int i = 0; i < titulo2.length(); i++) {
//            System.out.print("-");
//        }
//        System.out.println("\n" + titulo2);

    }

    public static void spaces(int n){
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    public static void symbols(String phrase, String symbol){
        for (int i = 0; i < phrase.length(); i++) {
            System.out.print(symbol);
        }
        System.out.println("\n" + phrase);
    }
}
