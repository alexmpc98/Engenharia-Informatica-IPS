
package trafficDemo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author POO 2019/2020
 * @version maio/2020
 */
public class Blinker extends Circle {
    
    private boolean switchedOn;

    public Blinker(double centerX, double centerY) {
        super(centerX, centerY, 3.0f, Color.TRANSPARENT);
        switchedOn = false;
    }
    
    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public void switchOn() {
        if (!switchedOn) {
            this.switchedOn = true;
            setFill(Color.GOLD);
        }
    }
    
    public void switchOff() {
        if (switchedOn) {
            this.switchedOn = false;
            setFill(Color.TRANSPARENT);
        }
    }
    
}
