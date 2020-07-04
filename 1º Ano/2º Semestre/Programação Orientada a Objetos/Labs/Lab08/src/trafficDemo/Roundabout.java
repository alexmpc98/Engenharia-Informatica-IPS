
package trafficDemo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import static trafficDemo.TrafficDemoFX.WINDOW_HEIGHT;
import static trafficDemo.TrafficDemoFX.WINDOW_WIDTH;

/**
 *
 * @author POO 2019/2020
 * @version maio/2020
 */ 
public class Roundabout implements Drawable {

    private static final int LANE_WIDTH = 40;
    private double x;
    private double y;
    private Color color;
    private Shape tarmac;
    private Shape continuousLine;
    private Circle dashedLine;


    public Roundabout(double centerX, double centerY, Color color) {
        x = centerX;
        y = centerY;
        this.color = color;
        build();
    }

    private void build() {
        
        // Tarmac roundabout
        Rectangle roadH = new Rectangle(0, y - LANE_WIDTH, WINDOW_WIDTH, LANE_WIDTH * 2);
        Rectangle roadV = new Rectangle(x - LANE_WIDTH, 50, 80, WINDOW_HEIGHT - 220);

        Circle roundaboutMiddle = new Circle();
        roundaboutMiddle.setCenterX(x);
        roundaboutMiddle.setCenterY(y);
        roundaboutMiddle.setRadius(60.0f);
        roundaboutMiddle.setStroke(Color.WHITE);
        
        Circle roundaboutTar = new Circle();
        roundaboutTar.setCenterX(x);
        roundaboutTar.setCenterY(y);
        roundaboutTar.setRadius(140.0f);

        tarmac = Shape.union(roadH, roadV);
        tarmac = Shape.union(tarmac, roundaboutTar);

        tarmac = Shape.subtract(tarmac, roundaboutMiddle);
        tarmac.setFill(color);

        // Paint roundabout continuous lines
        Line lineH = new Line();
        lineH.setStartX(0); 
        lineH.setStartY(y); 
        lineH.setEndX(WINDOW_WIDTH); 
        lineH.setEndY(y); 

        Line lineV = new Line();
        lineV.setStartX(x); 
        lineV.setStartY(50); 
        lineV.setEndX(x); 
        lineV.setEndY(WINDOW_HEIGHT - 150);
        
        continuousLine = Shape.union(lineH, lineV);
        continuousLine = Shape.subtract(continuousLine, roundaboutTar);
        continuousLine.setStroke(Color.WHITE);
        continuousLine.setStrokeType(StrokeType.CENTERED);
        continuousLine.setStrokeWidth(1);
        
        // Paint roundabout dashed lines
        dashedLine = new Circle();
        dashedLine.setCenterX(x);
        dashedLine.setCenterY(y);
        dashedLine.setRadius(100.0f);
        dashedLine.setFill(Color.TRANSPARENT);
        dashedLine.setStroke(Color.WHITE);
        dashedLine.setStrokeType(StrokeType.CENTERED);
        dashedLine.getStrokeDashArray().addAll(5d, 20d);
        dashedLine.setStrokeWidth(3);
        dashedLine.setStrokeDashOffset(10);
    }

    @Override
    public void addTo(Group group) {
        group.getChildren().addAll(tarmac, continuousLine, dashedLine);
    }
    
}
