package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(getClass().getResource("ProgramListView.fxml"));
        Parent mainWindow = mainLoader.load();

        ProgramListViewController controller = mainLoader.getController();
        controller.setProgramsListView();

        stage.setTitle("Select Program");
        stage.setScene(new Scene(mainWindow));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
