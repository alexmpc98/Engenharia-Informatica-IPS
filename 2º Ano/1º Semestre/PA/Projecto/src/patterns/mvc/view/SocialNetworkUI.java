package patterns.mvc.view;

import com.pa.proj2020.adts.graph.Edge;
import com.pa.proj2020.adts.graph.Vertex;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import patterns.mvc.controller.SocialNetworkController;
import patterns.mvc.model.*;
import patterns.observer.Observer;
import smartgraph.view.containers.ContentZoomPane;
import smartgraph.view.graphview.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents the view of the MVC Pattern
 */
public class SocialNetworkUI extends VBox implements Observer {

    private TextField textFieldIterative;
    private Button buttonIterative;
    private Button buttonGlobal;
    private Button buttonLoadSave;
    private Button buttonSave;
    private FileChooser fileChooser;
    private Button buttonUndo;
    private ComboBox<String> choices;
    private Button visualizer;
    private Button buttonInterests;
    private Button minCost;
    private Button showInterests;

    private SmartGraphPanel<User, Relation> graphView;
    private BorderPane borderPaneGraph;

    //modelo
    private SocialNetwork model;

    /**
     * Method to initialize the View
     * @param model model of the MVC Pattern
     */
    public SocialNetworkUI(SocialNetwork model) {
        this.model = model;
        initComponents();
        update(model);
    }

    /**
     * Method to update the object being observed
     * @param o object being observed
     */
    public void update(Object o) {
        if (o instanceof SocialNetwork) {
            SocialNetwork model = (SocialNetwork) o;
            this.model = model;
            updateLayout();
            System.out.println(this.model.getRelationships());
        }
    }

    /**
     * Method to initiate the components of the view
     */
    private void initComponents() {
        //controlos
        HBox hboxMenuBar = new HBox(10);
        this.choices = new ComboBox<>();
        this.visualizer = new Button("Visualize");
        this.choices.setPromptText("Statistics");
        this.choices.getItems().addAll(
                "Added Users",
                "Included Users by an Added User",
                "User with most Direct Relations",
                "Average number of Interests Per User",
                "Top 5 users with most Relations",
                "Top 5 users with most Included Users"
        );

        /* BACKGROUND */
        Background background = new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(1), null));
        hboxMenuBar.setBackground(background);
        hboxMenuBar.setPadding(new Insets(2, 2, 2, 2));

        /* ITERATIVE */
        HBox hboxIterative = new HBox(4);
        Label labelIterative = new Label("User:");
        this.textFieldIterative = new TextField();
        this.textFieldIterative.setPrefWidth(40);
        this.buttonIterative = new Button("Iterative Import");
        hboxIterative.getChildren().addAll(labelIterative, textFieldIterative, buttonIterative);
        hboxIterative.setAlignment(Pos.CENTER);

        /* GLOBAL */
        this.buttonGlobal = new Button("Global import");

        /* OPEN SAVED */
        this.buttonLoadSave = new Button("Load save");

        /* SAVE STATE */
        this.buttonSave = new Button("Save");

        /* UNDO */
        this.buttonUndo = new Button("Undo");

        /* INTERESTS */
        this.buttonInterests = new Button("Load interests");

        this.minCost = new Button("Calculate shortest path");

        /* SHOW INTERESTS */
        this.showInterests = new Button("Show User Interests");

        hboxMenuBar.getChildren().addAll(
                hboxIterative,
                this.buttonGlobal,
                this.buttonUndo,
                this.buttonLoadSave,
                this.buttonSave,
                this.buttonInterests,
                this.choices,
                this.visualizer, this.minCost, showInterests);
        hboxMenuBar.setAlignment(Pos.CENTER);

        /* FILE CHOOSER */
        this.fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filterBin = new FileChooser.ExtensionFilter("Bin Files (*.bin)", "*.bin");
        FileChooser.ExtensionFilter filterJson = new FileChooser.ExtensionFilter("Json Files(*.json)", "*.json");
        fileChooser.getExtensionFilters().addAll(filterBin, filterJson);
        fileChooser.setInitialDirectory(new File("C:\\"));

        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        this.graphView = new SmartGraphPanel<>(this.model.getRelationships(), strategy);

        this.borderPaneGraph = new BorderPane();
        this.borderPaneGraph.setCenter(new ContentZoomPane(this.graphView));

        this.borderPaneGraph.setTop(hboxMenuBar);

        /* AUTOMATIC LAYOUT */
        CheckBox automatic = new CheckBox("Automatic layout");
        automatic.selectedProperty().bindBidirectional(graphView.automaticLayoutProperty());

        HBox bottom = new HBox(10);
        bottom.setBackground(background);
        bottom.getChildren().addAll(automatic);
        bottom.setAlignment(Pos.CENTER);
        bottom.setPadding(new Insets(2, 2, 2, 2));
        this.borderPaneGraph.setBottom(bottom);
        this.getChildren().addAll(this.borderPaneGraph);
    }

    /**
     * Method to set the triggers of the buttons, checkboxes, and all elements of the view
     * @param controller Controller of the MVC pattern
     */
    public void setTriggers(SocialNetworkController controller) {
        this.buttonIterative.setOnAction(e -> controller.loadIterative());
        this.buttonGlobal.setOnAction(e -> controller.loadGlobal());
        this.buttonUndo.setOnAction(e -> controller.undo());
        this.buttonInterests.setOnAction(e -> controller.loadInterests());
        this.showInterests.setOnAction(e -> controller.showInterests());
        this.minCost.setOnAction(e -> {
            resetSelect();
            TextInputDialog td = createDialog(
                    "1-2",
                    "Enter both IDs of the user using a space between them (start-end)"
            );
            td.show();
            td.setOnCloseRequest(e1 -> displayMinPath(controller, td.getResult()));
        });
        this.visualizer.setOnAction(e -> {
            switch (choices.getSelectionModel().getSelectedIndex()) {
                case 0:
                    controller.setStrategyStatistics("AddedUsers");
                    allAddedUsersStatistics(controller);
                    break;
                case 1:
                    controller.setStrategyStatistics("IncludedUsers");
                    includedUsersByAddedStatistics(controller);
                    break;
                case 2:
                    controller.setStrategyStatistics("UserWithMostRelations");
                    userWithMostDirectRelationsStatistics(controller);
                    break;
                case 3:
                    controller.setStrategyStatistics("AverageNumberOfRelations");
                    averageNumberOfInterestsPerUserStatistics(controller);
                    break;
                case 4:
                    controller.setStrategyStatistics("Top5UsersWithMostRelations");
                    top5UsersWithMostRelationsStatistics(controller);
                    break;
                case 5:
                    controller.setStrategyStatistics("Top5UsersWithMostIncludedUsers");
                    top5UsersWithMostIncludedUsersStatistics(controller);
                    break;
            }
        });
        this.buttonLoadSave.setOnAction(e -> controller.load(setFileChooserLoad("Load a social network")));
        this.buttonSave.setOnAction(e -> controller.save(setFileChooserSave("Save a social network")));
        this.graphView.setVertexDoubleClickAction(graphVertex -> controller.setSelectedUser(graphVertex));
        this.graphView.setEdgeDoubleClickAction(graphEdge -> controller.showInterestsRelationInfo(graphEdge));
    }

    /**
     * Method to display the minimum path between two users
     * @param controller Controller of the MVC pattern
     * @param ids String input with the two user id's for the minimum cost path algorithm
     */
    private void displayMinPath(SocialNetworkController controller, String ids) {
        ArrayList<Integer> usersId = splitIds(ids);
        if (usersId == null) return;
        int userStart = usersId.get(0);
        int userEnd = usersId.get(1);
        int minCost = controller.minCost(userStart, userEnd);
        if (minCost == -1) {
            createAlert(
                    "Minimum Cost Path",
                    "The minimum Cost Path between " + userStart + " and " + userEnd + " could not be calculated",
                    Alert.AlertType.ERROR
            ).show();
        } else {
            String info = "IDs of the path: ";
            int count = 0;
            Alert a = createAlert(
                    "Minimum Cost Path",
                    "Calculating the minimum cost path",
                    Alert.AlertType.INFORMATION
            );
            for (User u : controller.getUsers()) {
                info += u + (count > 0 ? " " : "-");
                count++;
            }
            TextArea area = createTextArea(
                    "The minimum cost path between " + userStart
                            + " and " + userEnd + " is " + minCost + "\n" + info,
                    true,
                    false
            );
            a.getDialogPane().setContent(area);
            a.setResizable(true);
            a.show();
            decoratePath(controller);
        }
    }

    /**
     * Method to split the input with the two ids for the minimum cost path algorithm
     * @param idString string with the two id's
     * @return Array with the string's id, or null if catches exception
     */
    private ArrayList<Integer> splitIds(String idString) {
        ArrayList<Integer> splitIds = new ArrayList<>();
        try {
            String[] inputMinCost = idString.split("-");
            splitIds.add(Integer.parseInt(inputMinCost[0]));
            splitIds.add(Integer.parseInt(inputMinCost[1]));
            return splitIds;
        } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException except) {
            createAlert(
                    "Minimum Cost Path",
                    "Try to insert a valid format.\nExample: 1-38",
                    Alert.AlertType.ERROR
            ).show();
        }
        return null;
    }

    /**
     * Method to get statistics from all the added users in the social network
     * @param controller controller of the MVC pattern
     */
    private void allAddedUsersStatistics(SocialNetworkController controller) {
        Alert a = createAlert(
                "Users Added",
                "Statistics from all the added users",
                Alert.AlertType.INFORMATION
        );

        TextArea area = new TextArea(controller.applyStrategy().get(0));
        area.setWrapText(true);
        area.setEditable(false);

        a.getDialogPane().setContent(area);
        a.setResizable(true);
        a.show();
    }

    /**
     * Method to get the statistics from the included users in the social network
     * @param controller controller of the MVC pattern
     */
    private void includedUsersByAddedStatistics(SocialNetworkController controller) {
        TextInputDialog td = new TextInputDialog("1");
        td.setHeaderText("Enter the ID of the user");
        td.show();
        td.setOnCloseRequest(e1 -> displayIncludedUsers(controller));
    }

    /**
     * Method to get the statistics from the user with the most direct relations in the social network
     * @param controller controller of the MVC pattern
     */
    private void userWithMostDirectRelationsStatistics(SocialNetworkController controller) {
        Alert a = createAlert(
                "Most Relations",
                "User with most relations",
                Alert.AlertType.INFORMATION
        );

        TextArea area = createTextArea(controller.applyStrategy().get(0), true, false);

        a.getDialogPane().setContent(area);
        a.setResizable(true);
        a.show();
    }

    /**
     * Method to get the statistics from the average number of interests per user in the social network
     * @param controller controller of the MVC pattern
     */
    private void averageNumberOfInterestsPerUserStatistics(SocialNetworkController controller) {
        Alert a = createAlert(
                "Average number of Interests Per User",
                "Average number of Interests Per User",
                Alert.AlertType.INFORMATION
        );

        TextArea area = createTextArea(controller.applyStrategy().get(0), true, false);

        a.getDialogPane().setContent(area);
        a.setResizable(true);
        a.show();
    }

    /**
     * Method to get the statistics from the top 5 users with the most relations in the social network
     * @param controller controller of the MVC pattern
     */
    private void top5UsersWithMostRelationsStatistics(SocialNetworkController controller) {
        List<String> info = controller.applyStrategy();
        Alert a = createAlert("Top 5", "Top 5 users with the most relationships", Alert.AlertType.INFORMATION);
        if (info.size() < 10) {
            a.setContentText("There aren't at least 5 users entered!");
        } else {
            final BarChart<String, Number> bc = createBarChart(
                    "Top 5 users with the most relationships",
                    "User",
                    "Nº of Relations",
                    "Number of Relationships",
                    info
            );
            a.getDialogPane().setContent(bc);
            a.setResizable(true);
        }
        a.show();
    }

    /**
     * MEthod to get the statistics from the top 5 users with the most included users by them in the social network
     * @param controller controller of the MVC pattern
     */
    private void top5UsersWithMostIncludedUsersStatistics(SocialNetworkController controller) {
        List<String> info = controller.applyStrategy();

        Alert a = createAlert(
                "Top 5 Users With Most Included Users",
                "Top 5 Users With Most Included Users",
                Alert.AlertType.INFORMATION
        );

        if (info.size() < 10 || info.get(0).equals("0")
                || info.get(1).equals("0") || info.get(2).equals("0")
                || info.get(3).equals("0") || info.get(4).equals("0")) {
            a.setContentText("There are not at least 5 Added Users having included users");
        } else {
            final BarChart<String, Number> bc = createBarChart(
                    "Top 5 users with most included users",
                    "User",
                    "Nº Included Users",
                    "Number of Included Users",
                    info
            );
            a.getDialogPane().setContent(bc);
            a.setResizable(true);
        }
        a.show();
    }

    /**
     * Method to display the included users in the UI
     * @param controller controller of the MVC Pattern
     */
    private void displayIncludedUsers(SocialNetworkController controller) {
        int aux;
        try {
            aux = getUserId() == null ? -1 : getUserId();
        } catch (NumberFormatException except) {
            aux = -2; // the input could not be converted to Integer - Try to insert a number next time.
        }

        Alert a = createAlert(
                "Included users by an Added user",
                "Included users",
                Alert.AlertType.INFORMATION
        );

        String text;
        if (aux == -1 || aux == -2) {
            text = "Try to insert a valid number ID.";
        } else {
            try {
                text = controller.applyStrategy(aux).get(0);
            } catch (NullPointerException except) {
                text = "The user does not currently exist";
                a.setAlertType(Alert.AlertType.ERROR);
            }
        }

        TextArea area = createTextArea(text, true, false);

        a.getDialogPane().setContent(area);
        a.setResizable(true);
        a.show();
    }

    /**
     * Method to set the text of the iterative field
     * @param text text who is going to be set
     */
    public void setTextFieldIterative(String text) {
        this.textFieldIterative.setText(text);
    }

    /**
     * Method to set the file the file chooser to load files
     * @param title title of the file to be chosen
     * @return file chosen
     */
    private File setFileChooserLoad(String title) {
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(this.getScene().getWindow());
    }

    /**
     * Method to set the file the file chooser to save a file
     * @param title title of the file to be chosen
     * @return file chosen
     */
    private File setFileChooserSave(String title) {
        fileChooser.setTitle(title);
        return fileChooser.showSaveDialog(this.getScene().getWindow());
    }

    /**
     * Method to get the user id of the selected user in the UI
     * @return Integer number of the user selected
     */
    public Integer getUserId() {
        return !this.textFieldIterative.getText().equals("") ?
                Integer.parseInt(this.textFieldIterative.getText()) :
                null;
    }


    /**
     * Method to initialize the graph view
     */
    public void initGraphView() {
        this.graphView.init();
    }

    /**
     * Method to reset the selected user to none
     */
    public void resetSelect() {
        this.textFieldIterative.setText("");
        this.graphView.resetStyles();
    }

    /**
     * Method to update the layout of the UI
     */
    public void updateLayout() {
        this.graphView.updateLayout();
    }

    /**
     * Method to disable the global import button
     */
    public void disableGlobalButton() {
        this.buttonGlobal.setDisable(true);
    }

    /**
     * Method to reset the text of the textField
     */
    public void resetText() {
        this.textFieldIterative.setText("");
    }

    /**
     * Method to enable the global import button
     */
    public void enableGlobalButton() {
        this.buttonGlobal.setDisable(false);
    }

    /**
     * Method to set the dimensions of the scene in the UI
     * @param scene scene whose parameters are going to be set
     */
    public void setWidthAndHeight(Scene scene) {
        this.borderPaneGraph.prefHeightProperty().bind(scene.heightProperty());
        this.borderPaneGraph.prefWidthProperty().bind(scene.widthProperty());
    }

    /**
     * Method to set the style of a vertex
     * @param vertex vertex getting his style changed
     * @param className attributes of the style changed
     */
    public void setVertexStyleClass(SmartGraphVertex<User> vertex, String className) {
        if (!vertex.removeStyleClass(className)) {
            vertex.addStyleClass(className);
        }
    }

    /**
     * Method to set the style of an edge
     * @param edge edge getting his style changed
     * @param className attributes of the style changed
     */
    public void setEdgeStyleClass(SmartGraphEdge<Relation, User> edge, String className) {
        if (!edge.removeStyleClass(className)) {
            edge.addStyleClass(className);
        }
    }

    /**
     * Method that will restart the graph view
     *
     * @param model the social network to be restarted
     */
    public void restartGraphView(SocialNetwork model) {
        this.model = model;
        this.graphView.setTheGraph(this.model.getRelationships());
    }

    /**
     * Method that will return the type of the file
     *
     * @param file file to check the type to return
     * @return the type of the file
     */
    public String getFileType(File file) {
        if (file != null) {
            String extension = "";
            String fileName = file.getName();
            int i = fileName.lastIndexOf('.');
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }
            return extension;
        }
        return "";
    }

    /**
     * Method that will create the bar chart
     *
     * @param title the bar chart title
     * @param labelX the bar chart X label
     * @param labelY the bar chart Y label
     * @param seriesName the series name of the bar chart
     * @param data the data of the bar chart
     * @return the bar chart with all the attributes
     */
    public BarChart<String, Number> createBarChart(String title, String labelX, String labelY, String seriesName, List<String> data) {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final int columnsSize = 5;

        final BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
        bc.setTitle(title);
        xAxis.setLabel(labelX);
        yAxis.setLabel(labelY);

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(seriesName);

        for (int i = 0; i < columnsSize; i++) {
            series1.getData().add(new XYChart.Data(data.get(i + columnsSize), Integer.parseInt(data.get(i))));
        }

        bc.getData().addAll(series1);
        return bc;
    }

    /**
     * Method that will create the alert
     *
     * @param title the title of the alert
     * @param headerText the header text of the alert
     * @param alertType the alert type of the alert
     * @return the alert with the type, header and title
     */
    public Alert createAlert(String title, String headerText, Alert.AlertType alertType) {
        Alert a = new Alert(alertType);
        a.setTitle(title);
        a.setHeaderText(headerText);
        return a;
    }

    /**
     * Method that will display the content of an alert
     *
     * @param title the title of the alert
     * @param headerText the header text of the alert
     * @param alertType the alert type of the alert
     * @param content the content of the alert
     */
    public void displayContentAlert(String title, String headerText, Alert.AlertType alertType, String content) {
        Alert alert = createAlert(
                title,
                headerText,
                alertType
        );
        alert.getDialogPane().setContent(createTextArea(content, true, false));
        alert.setResizable(true);
        alert.show();
    }

    /**
     * Method to create text input dialog
     *
     * @param defaultValue the default value of the dialog
     * @param headerText the header text of the dialog
     * @return the dialog with the default value and the header text
     */
    public TextInputDialog createDialog(String defaultValue, String headerText) {
        TextInputDialog td = new TextInputDialog(defaultValue);
        td.setHeaderText(headerText);
        return td;
    }

    /**
     * Method that will create the text area
     *
     * @param text message on the text area
     * @param wrapText a boolean to set the wrap text
     * @param editable a boolean to set the editable to text
     * @return a text area with the text inserted
     */
    public TextArea createTextArea(String text, boolean wrapText, boolean editable) {
        TextArea area = new TextArea(text);
        area.setWrapText(wrapText);
        area.setEditable(editable);
        return area;
    }

    /**
     * Method that will decorate the patch with colors
     *
     * @param controller the controller to decorate
     */
    private void decoratePath(SocialNetworkController controller) {
        for (Edge<Relation, User> relation : controller.getEdgesPath()) {
            graphView.getStylableEdge(relation).addStyleClass("edge-cost");
            graphView.getSmartGraphEdge(relation).getAttachedArrow().addStyleClass("arrow-cost");
        }
        for (User u : controller.getUsers()) {
            Vertex<User> vertUser = controller.getUserNode(u);
            graphView.getStylableVertex(vertUser).removeStyleClass("vertex-blue");
            graphView.getStylableVertex(vertUser).removeStyleClass("vertex-green");
            graphView.getStylableVertex(vertUser).addStyleClass("vertex-cost");
        }
    }
}