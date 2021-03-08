package com.pa.refatoring.extractclass;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CalculatorUI extends GridPane{
    private Slider slider;
    private TextField totalTf;
    private Button multiplyBtn;
    private Button clearBtn;
    private Calculator calc;

    public CalculatorUI(){
        calc = new Calculator();
        initComponents();
    }

    private void initComponents() {
        //... Initialize components
        slider = new Slider(0, 100, 1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMinorTickCount(20);
        slider.setMajorTickUnit(20);
        totalTf = new TextField();
        multiplyBtn = new Button("Multiply");
        clearBtn = new Button("Reset");
        setTriggers();
        totalTf.setText(calc.getTotal()+"");
        totalTf.setEditable(false);
        //... Layout the components.
        this.setPadding(new Insets(15, 15, 15, 15));
        this.setVgap(10);
        this.setHgap(10);
        this.add(new Label("INPUT"), 0, 0);
        this.add(slider, 1, 0);
        this.add(multiplyBtn, 2, 0);
        this.add(new Label("TOTAL"), 0, 1);
        this.add(totalTf, 1, 1);
        this.add(clearBtn, 2, 1);
    }

    private void setTriggers() {
        multiplyBtn.setOnAction(
                event -> {
                    calc.multiplyBy(getUserInput());
                    update();
                });
        clearBtn.setOnAction(
                event -> {
                    calc.reset();
                    resetInput();
                    update();
                });
    }

    public void update() {
        totalTf.setText(calc.getTotal()+"");
    }

    public void resetInput() {
        slider.setValue(1.0);
    }


    public int getUserInput() {
        int value = (int) slider.getValue();
        return value;
    }

    public void showError(String errMessage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error Message");
        alert.setContentText(errMessage);
        alert.showAndWait();
    }
}
