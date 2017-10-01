package helper;/*
 * Copyright (c) 2017
 * All rights reserved
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Creates JavaFX sage from a fxml file
 */
public class StageCreator {

    /**
     * Opens a .fxml file and returns a stage using this file and the given title
     *
     * @param title Title of the window
     * @param file  .fxml file with the window configuration
     * @return stage out of the fxml file
     */
    @SuppressWarnings("ConstantConditions")
    public static Stage getStageFromFXML(String title, String file) {
        if (file == null || file.isEmpty())
            return null;

        // create new stage
        Stage newStage = new Stage(StageStyle.DECORATED); // normal window

        // set title if exist
        if (title != null && !title.isEmpty())
            newStage.setTitle(title);

        try {
            // create new scene
            BorderPane root = FXMLLoader.load(StageCreator.class.getClassLoader().getResource(file));
            Scene scene = new Scene(root);

            /* final stage setup */
            newStage.setScene(scene);
            newStage.show();
            return newStage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
