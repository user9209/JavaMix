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

        import javafx.beans.property.SimpleStringProperty;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.fxml.FXML;
        import javafx.fxml.Initializable;
        import javafx.scene.control.*;

        import java.net.URL;
        import java.util.Arrays;
        import java.util.ResourceBundle;

public class MainMenuControler implements Initializable {

    private PasswordEntry currentPasswordEntry = null;

    @FXML
    private TabPane tabMenu;

    @FXML
    private TableView<PasswordEntry> table;

    @FXML
    private TableColumn<PasswordEntry, String> colService;

    @FXML
    private TableColumn<PasswordEntry, String> colUsername;

    @FXML
    private TableColumn<PasswordEntry, String> colPassword;

    @FXML
    private TableColumn<PasswordEntry, String> colEmail;

    @FXML
    private TableColumn<PasswordEntry, String> colURL;

    @FXML
    private Tab tabPasswordEdit;

    @FXML
    private Tab tabPasswordList;

    @FXML
    private Tab tabNewPassword;

    @FXML
    private Tab tabSetting;

    @FXML
    private Tab tabAbout;

    @FXML
    private TextField tf_service;

    @FXML
    private TextField tf_username;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_url;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_customer_id;

    @FXML
    private TextField tf_more_information;

    @FXML
    private TextField tf_date;

    @FXML
    private Button b_notice;

    @FXML
    private Button b_reset;

    @FXML
    private Button b_save;


    @FXML
    private TextField tf_service1;

    @FXML
    private TextField tf_username1;

    @FXML
    private TextField tf_password1;

    @FXML
    private TextField tf_url1;

    @FXML
    private TextField tf_email1;

    @FXML
    private TextField tf_customer_id1;

    @FXML
    private TextField tf_more_information1;

    @FXML
    private TextField tf_date1;

    @FXML
    private Button b_notice1;

    @FXML
    private Button b_reset1;

    @FXML
    private Button b_save1;



    @FXML
    void onMenuAbout(ActionEvent event) {
        tabAbout.setDisable(false);
        tabMenu.getSelectionModel().select(tabAbout);
    }

    @FXML
    void onMenuDeletePassword(ActionEvent event) {
        PasswordEntry  entry = table.getSelectionModel().getSelectedItem();
        if(entry != null)
        {
            table.getItems().remove(entry);
        }
    }

    @FXML
    void onMenuExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onMenuNewPassword(ActionEvent event) {
        tabMenu.getSelectionModel().select(tabNewPassword);
    }

    @FXML
    void onMenueSettings(ActionEvent event) {
        tabMenu.getSelectionModel().select(tabSetting);
    }



    @FXML
    void onNotice(ActionEvent event) {

    }

    @FXML
    void onNoticeEdit(ActionEvent event) {

    }

    @FXML
    void onSave(ActionEvent event) {
        table.getItems().add(new PasswordEntry(getNewValues()));
        tabMenu.getSelectionModel().select(tabPasswordList);
    }

    @FXML
    void onSaveEdit(ActionEvent event) {
        currentPasswordEntry.updateValues(getEditValues());
        tabPasswordEdit.setDisable(true);
        currentPasswordEntry = null;
        tabMenu.getSelectionModel().select(tabPasswordList);
        table.refresh();
    }

    @FXML
    void onExit(ActionEvent event) {
        tabMenu.getSelectionModel().select(tabPasswordList);
    }

    @FXML
    void onExitEdit(ActionEvent event) {
        tabPasswordEdit.setDisable(true);
        currentPasswordEntry = null;
        tabMenu.getSelectionModel().select(tabPasswordList);
    }

    void loadTable() {
        ObservableList<PasswordEntry> data =
                FXCollections.observableArrayList(
                        new PasswordEntry("GMX", "paule", "45sdf4s5df4s5f","a@example.com", "https://gmx.de","none","none","none"),
                        new PasswordEntry("T-Online", "paule", "dsdf54s5f4sd","a@example.com", "https://t-online.de","none","none","none")
                );

        colService.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getServiceName()));
        colUsername.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getUsername()));
        colPassword.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getPassword()));
        colEmail.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getEmail()));
        colURL.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getUrl()));

        table.setItems(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTable();

        table.setRowFactory( tv -> {
            TableRow<PasswordEntry> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    currentPasswordEntry = row.getItem();

                    System.out.println(currentPasswordEntry);
                    setPasswordChange(currentPasswordEntry.values());

                    tabMenu.getSelectionModel().select(tabPasswordEdit);
                }
            });
            return row ;
        });

        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && table.getItems().isEmpty()) {
                tabMenu.getSelectionModel().select(tabNewPassword);
            }
        });
    }

    private void setPasswordChange(String ... values)
    {
        if(values.length != 9) {
            System.out.println("Invalid length: " + values.length + " " + Arrays.toString(values));
            return;
        }

        tabPasswordEdit.setDisable(false);
        tf_service1.setText(values[0]);
        tf_username1.setText(values[1]);
        tf_password1.setText(values[2]);
        tf_url1.setText(values[3]);
        tf_email1.setText(values[4]);
        tf_customer_id1.setText(values[5]);
        tf_more_information1.setText(values[6]);
        String notice = values[7];
        tf_date1.setText(values[8]);
    }

    public String[] getEditValues() {
        return new String[]{tf_service1.getText(), tf_username1.getText(), tf_password1.getText(),
                tf_url1.getText(), tf_email1.getText(), tf_customer_id1.getText(), tf_more_information1.getText(),
                "", tf_date1.getText()};
    }

    private void setPasswordNew(String ... values)
    {
        if(values.length != 9) {
            System.out.println("Invalid length: " + values.length + " " + Arrays.toString(values));
            return;
        }
        tf_service.setText(values[0]);
        tf_username.setText(values[1]);
        tf_password.setText(values[2]);
        tf_url.setText(values[3]);
        tf_email.setText(values[4]);
        tf_customer_id.setText(values[5]);
        tf_more_information.setText(values[6]);
        String notice = values[7];
        tf_date.setText(values[8]);
    }

    public String[] getNewValues() {
        return new String[]{tf_service.getText(), tf_username.getText(), tf_password.getText(),
                tf_url.getText(), tf_email.getText(), tf_customer_id.getText(), tf_more_information.getText(),
                "", "Now"};
    }
}
