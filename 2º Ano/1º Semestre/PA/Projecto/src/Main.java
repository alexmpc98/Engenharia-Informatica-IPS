import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import patterns.mvc.FactoryMVCSocialNetwork;
import patterns.mvc.view.SocialNetworkUI;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SocialNetworkUI view = FactoryMVCSocialNetwork.create();
        Scene scene = new Scene(view, 1250, 768);
        Image image = new Image("/image/iconSN.png");
        primaryStage.getIcons().add(image);
        primaryStage.setTitle("Advanced Programming Social Network");
        primaryStage.setScene(scene);
        primaryStage.show();
        view.initGraphView();
        view.setWidthAndHeight(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
