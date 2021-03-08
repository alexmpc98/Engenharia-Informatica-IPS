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
public abstract class A {

    protected char value;

    public A(char value) {
        this.value = value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public abstract String replaceCharInMsg(String msg);

    public abstract String encloseMsg(String msg);

    public String formatMessage(String msg) {
        msg = replaceCharInMsg(msg);
        //capitalize
        msg = msg.toUpperCase();
        //enclose
        msg = encloseMsg(msg);
        return msg;
    }

}
