/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlockuDoku.Visuals;

import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Alexandre e Sérgio
 */
public class DrawAlert {

    private String title;
    private String message;
    private AlertType alertType;

    /**
     * Constructor que inicializa o alerta (DrawAlerta). 
     * 
     * @param title String
     * @param message String
     * @param alertType AlertType
     */
    public DrawAlert(String title, String message, AlertType alertType) {
        this.title = title;
        this.message = message;
        this.alertType = alertType;

    }

    /**
     * Este método cria e mostra o alert.
     * 
     */
    public void setAlert() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(this.alertType);
        alert.setHeaderText(this.title);
        alert.setContentText(this.message);
        alert.show();
    }

}
