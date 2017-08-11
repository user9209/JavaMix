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

package de.gs_sys.java.GuiFX;


import de.gs_sys.java.HelperFX.Hotkeys;
import de.gs_sys.java.HelperFX.StageCreator;
import de.gs_sys.java.HelperFX.StageStore;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Main class of our program
 * The main JavaFX application is started here.
 */
public class ControlerFX extends Application {

    /**
     * Main main
     * @param args
     */
    public static void main(String[] args) {
        switch (args.length)
        {
            case 0:
                launch(args);
                break;
            case 1:
                System.exit(0);
                break;
            default:
                System.out.println("To start JavaFX use no parameters.\n\n" +
                        "To use the command line use:\n" +
                        "<jar-file> <text or morse>\n\n" +
                        "Sound and light are not supported in the command line."
                );
                System.exit(0);
                break;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        // ignores the given stage !

        // Hidden windows will not closed for optimisation
        Platform.setImplicitExit(false);

        /* create and setup main stage */
        Stage mainStage = StageCreator.getStageFromFXML("Morse Converter","MainMenu.fxml");
        mainStage.setTitle("Passwort Manager");
        StageStore.addStage("main", mainStage);

        /* load hotkeys */
        Hotkeys.loadHotkeys(mainStage);

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
}
