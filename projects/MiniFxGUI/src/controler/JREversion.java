package controler;/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * Created on 10-2017.
 */

import helper.StageCreator;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JREversion implements Initializable {

    @FXML
    private TextArea ta_jre;

    @FXML
    void onOK(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ta_jre.appendText(System.getProperty("java.vendor") + "\n");
        ta_jre.appendText("Version: " + System.getProperty("java.version") + "\n");
        ta_jre.appendText(System.getProperty("java.vendor.url"));
    }

    public static void buildStage()
    {
        Platform.setImplicitExit(true);

        /* create and setup main stage */
        Stage me = StageCreator.getStageFromFXML("JRE Version", "JREversion.fxml");

        /* load hotkeys */
        loadHotkeysExit(me);

        // No resize
        me.setResizable(false);
    }

    public static void loadHotkeysExit(Stage stage) {
        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent k) {
                switch (k.getCode()) {
                    case ENTER:
                        System.exit(0);
                        break;
                    case ESCAPE:
                        System.exit(0);
                        break;
                    default:
                }
            }
        });
    }
}
