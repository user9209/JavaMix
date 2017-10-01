/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * Created on 10-2017.
 */

public class MainLauncher {
    public static void main(String... args) {
        try {
            MainLauncher.class.getClassLoader().loadClass("javafx.application.Application");
        } catch (Throwable  e) { // ClassNotFoundException and NoClassDefFoundError
            NoFXMessage.showMessageAndExit("No JavaFX found","Your computer does not provide JavaFX 8.");
            return;
        }
        StartJavaFxApp.startFX();
    }
}
