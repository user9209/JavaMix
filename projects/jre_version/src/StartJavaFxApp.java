/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class StartJavaFxApp extends Application {

    public static void startFX(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        JREversion.buildStage();
    }
}
