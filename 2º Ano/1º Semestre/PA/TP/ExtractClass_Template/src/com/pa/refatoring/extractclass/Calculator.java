package com.pa.refatoring.extractclass;

public class Calculator {
    private int total;

    public Calculator(){
        total = 1;
    }

    public int getTotal(){
        return total;
    }

    public void setTotal(int total){
        this.total = total;
    }

    public void multiplyBy(Integer operand) {
        total = total * operand;
    }

    public void reset(){
        setTotal(1);
    }
}
