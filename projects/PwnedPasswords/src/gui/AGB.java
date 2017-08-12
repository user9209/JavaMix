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
import gui.HelperFX.StageStore;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import util.TestPassword;

import java.net.URL;
import java.util.ResourceBundle;

public class AGB extends Application implements Initializable {

    private static String[] args = null;

    final String agb_text = "Terms of Use: <b>You only allow to continue if you accept the following things:</b>\n\n" +
            " 1) This software includes a copy of <b>7-Zip</b> (http://www.7-zip.org/).\n" +
            " 7-Zip licence must be accept by you and is found under http://www.7-zip.org/license.txt\n" +
            " Licenses for files are:\n" +
            "    a) 7z.dll: GNU LGPL + unRAR restriction\n" +
            "    b) All other files:  GNU LGPL\n\n" +
            " 2) The password database based on <b>\"https://haveibeenpwned.com/Passwords\"</b>:\n" +
            " Be sure you are allowed to download that files before continuing!\n\n" +
            " 3) This software is <b>checking for updates</b>.\n" +
            " This means it will connect the authors server and requires network connection.\n" +
            " To avoid the home call you can use the --force command line switch to start without version check.\n" +
            " Do never enter you current active passwords to a application you do not trust.\n" +
            " Here use only signed versions from the original source or build from the source code.\n" +
            " This tool is WITHOUT ANY WARRANTY; without even the implied warranty of\n" +
            " MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. GNU General Public License 3\n\n" +
            " <b>If you disagree to any of these points of Terms of Use, press exit and delete this jar-File!</b>";

    @FXML
    private TextArea ta_agbs;

    @FXML
    private Button b_exit;

    @FXML
    private Button b_accept;

    @FXML
    void onAccept(ActionEvent event) throws Exception {
        TestPassword.main(args);
    }

    @FXML
    void onExit(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ta_agbs.setContentType("text/html");
        ta_agbs.setText(agb_text);
        b_exit.setDefaultButton(true);
    }

    public static void main(String[] args) {
        AGB.args = args;
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage mainStage = StageCreator.getStageFromFXML("Terms of Use","gui/AGB.fxml");
        StageStore.addStage("agb", mainStage);

        // No resize
        mainStage.setResizable(false);
    }
}
