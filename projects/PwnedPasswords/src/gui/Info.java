/*
 *     Copyright (C) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
 package gui;

import gui.HelperFX.StageCreator;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Info extends Application implements Initializable {

    public static String TEXT = "";

    @FXML
    private TextArea ta_text;

    @FXML
    private Button b_ok;

    @FXML
    void onOK(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ta_text.setText(TEXT);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage mainStage = StageCreator.getStageFromFXML("Info","gui/Info.fxml");
        mainStage.setTitle("Info");

        // Close App on X (Needs to be AFTER addStage() to overwrite default close)
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        // No resize
        mainStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
