/*
 * Copyright (c) 2017
 * All rights reserved
 */

import controler.JREversion;
import controler.TranslatePassword;
import javafx.application.Application;
import javafx.stage.Stage;

public class StartJavaFxApp extends Application {

    public static void startFX(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //JREversion.buildStage();
        TranslatePassword.buildStage();
    }
}
