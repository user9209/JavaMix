/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * Created on 10-2017.
 */

public class MiniLoader {
    public static void main(String... args) {
        try {
            MiniLoader.class.getClassLoader().loadClass("javafx.application.Application");
        } catch (Throwable  e) { // ClassNotFoundException and NoClassDefFoundError
            NoFXMessage.showMessageAndExit("No JavaFX found","Your computer does not provide JavaFX 8.");
            return;
        }
        BootFX.startFX();
    }
}
