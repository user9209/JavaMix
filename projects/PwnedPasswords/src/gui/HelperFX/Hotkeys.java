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
 
package gui.HelperFX;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Hotkeys {

    // are true, if key is pressed
    private static boolean isControlPressed = false;
    private static boolean isAltPressed = false;
    private static boolean isShiftPressed = false;

    /**
     * Registers the hotkeys
     */
    public static void loadHotkeys(Stage stage)
    {
        stage.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent k) {
                switch (k.getCode())
                {
                    case M:
                        if(isControlPressed) {
                            System.out.println("M");
                        }
                        break;
                    case S:
                        if(isControlPressed)
                            System.out.println("S");
                        break;
                    case O:
                        if(isControlPressed)
                            System.out.println("O");
                        break;
                    case VOLUME_UP:
                    case UP:
                    case PAGE_UP:
                        if(isControlPressed)
                            // TODO: something to up?
                            System.out.println("Up");
                            break;
                    case VOLUME_DOWN:
                    case DOWN:
                    case PAGE_DOWN:
                        if(isControlPressed)
                            // TODO: something to down?
                            System.out.println("Down");
                            break;
                    case CONTROL:
                        isControlPressed = true;
                        break;
                    case ALT:
                        isAltPressed = true;
                        break;
                    case SHIFT:
                        isShiftPressed = true;
                        break;
                    default:
                }
            }
        });

        stage.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent k) {
                switch (k.getCode())
                {
                    case CONTROL:
                        isControlPressed = false;
                        break;
                    case ALT:
                        isAltPressed = false;
                        break;
                    case SHIFT:
                        isShiftPressed = false;
                        break;
                    default:
                }
            }
        });
    }
}
