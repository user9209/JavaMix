/*
 * Copyright (c) 2017 Georg Schmidt <gs-develop@gs-sys.de>
 * All rights reserved
 * Licensing with individual contract possible, written contract necessary.
 *
 * Created on 10-2017.
 */
package controler;

import helper.StageCreator;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslatePassword implements Initializable {

    private static Button b_translateStatic;

    @FXML
    private TextArea ta_in;

    @FXML
    private TextArea ta_out;

    @FXML
    private ComboBox<String> cb_direction;

    @FXML
    private Button b_translate;
    private int direction = 0;

    @FXML
    void onExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onTranslate(ActionEvent event) {

        char[] in = ta_in.getText().toCharArray();

        if(in.length == 0)
            return;

        for (int i = 0; i < in.length; i++) {
            if(direction == 0)
                in[i] = translateToEnglish(in[i]);
            else if(direction == 1)
                in[i] = translateToGerman(in[i]);
        }

        ta_out.setText(new String(in));
    }

    private char translateToEnglish(char in) {
        switch (in)
        {
            case '"':
                return '@';
            case '§':
                return '#';
            case '&':
                return '^';
            case '/':
                return '&';
            case '(':
                return '*';
            case ')':
                return '(';
            case '=':
                return ')';
            case '?':
                return '_';
            case '+':
                return ']';
            case '*':
                return '}';
            case '#':
                return '\\';
            case '\'':
                return '|';
            case ';':
                return '<';
            case ':':
                return '>';
            case '-':
                return '/';
            case '_':
                return '?';
            default:
                return in;

        }
    }

    private char translateToGerman(char in) {
        switch (in)
        {
            case '@':
                return '"';
            case '#':
                return '§';
            case '^':
                return '&';
            case '&':
                return '/';
            case '*':
                return '(';
            case '(':
                return ')';
            case ')':
                return '=';
            case '_':
                return '?';
            case ']':
                return '+';
            case '}':
                return '*';
            case '\\':
                return '#';
            case '|':
                return '\'';
            case '<':
                return ';';
            case '>':
                return ':';
            case '/':
                return '-';
            case '?':
                return '_';
            default:
                return in;

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        b_translateStatic = b_translate;
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("German password to english password");
        list.add("English password to german password");
        cb_direction.setItems(list);
        cb_direction.getSelectionModel().select(0);
        ta_in.textProperty().addListener((obs, oldText, newText) -> {
            onTranslate(null);
        });

        ta_out.setEditable(false);

        cb_direction.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                onTranslate(null);
                System.out.println(newValue);
                direction = getDirection(newValue);
            }
        });
    }

    private int getDirection(String newText) {
        switch (newText)
        {
            case "German password to english password":
                return 0;
            case "English password to german password":
                return 1;
            default:
                return -1;
        }
    }

    public static void buildStage()
    {
        Platform.setImplicitExit(true);

        /* create and setup main stage */
        Stage me = StageCreator.getStageFromFXML("Translate Password", "TranslatePassword.fxml");

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
                        b_translateStatic.fire();
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
