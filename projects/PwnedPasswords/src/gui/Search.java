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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import util.FindLine;
import util.TestPassword;

import java.net.URL;
import java.util.ResourceBundle;


public class Search implements Initializable {

    @FXML
    private Label l_version;

    @FXML
    private Label l_status;

    @FXML
    private TextArea ta_search;

    @FXML
    private Button b_search;

    @FXML
    void onSearch(ActionEvent event) {
        String[] in = ta_search.getText().split("\n");

        for(String x : in)
        {

            if(FindLine.isHashContained(x,"passwords/pwned-passwords-update-2.txt")) {
                l_status.setText("Status: Found! Please change our password!");
                return;
            }
        }

        l_status.setText("Status: Not found. All ok!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        l_version.setText("Version " + TestPassword.version);
    }
}
