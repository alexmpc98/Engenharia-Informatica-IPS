/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pa.refatoring.templatemethod;

/**
 *
 * @author patricia.macedo
 */
public class Main {

    public static void main(String[] args) {
        B b = new B('e');
        System.out.println(b.formatMessage("Adorei a viagem ao Mexico"));
        C c = new C('o');
        System.out.println(c.formatMessage("Adorei a viagem ao Mexico"));
    }

}
