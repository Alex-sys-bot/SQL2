
package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    Connection conn;
    public Controller() throws ClassNotFoundException {conn=ConnectionUtil.connDB();}



    //    Controlls;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtAge;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label txtResult;


    //    Обработчики;
    @FXML
    public void pressSelect(ActionEvent event) throws SQLException {
        String select = "SELECT * FROM user WHERE id_user=?";
        PreparedStatement statement = conn.prepareStatement(select);
        statement.setString(1, txtID.getText());
        ResultSet result = statement.executeQuery();
        while (result.next()){
            txtName.setText(result.getString("name"));
            txtLastName.setText(result.getString("lastname" ));
            txtAge.setText(result.getString("age"));
            txtEmail.setText(result.getString("email"));
            txtPassword.setText(result.getString("password"));
        }
        txtResult.setText("Данные выбраны");
    }

    @FXML
    public void pressInsert(ActionEvent event) throws SQLException {
        String insert = "INSERT INTO user(name,lastname,age,email,password) VALUES (?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(insert);
        statement.setString(1, txtName.getText());
        statement.setString(2, txtLastName.getText());
        statement.setString(3, txtAge.getText());
        statement.setString(4, txtEmail.getText());
        statement.setString(5, txtPassword.getText());
        int res = statement.executeUpdate();
        if (res == 1){
            txtResult.setText("Данные добавлены");
        }
    }

    @FXML
    public void pressUpdate(ActionEvent event) throws SQLException {
        String update = "UPDATE user SET name=?, lastname=?, age=?, email=?, password=? WHERE id_user=?";
        PreparedStatement statement = conn.prepareStatement(update);
        statement.setString(1, txtName.getText());
        statement.setString(2, txtLastName.getText());
        statement.setString(3, txtAge.getText());
        statement.setString(4, txtEmail.getText());
        statement.setString(5, txtPassword.getText());
        statement.setString(6, txtID.getText());
        int res = statement.executeUpdate();
        if (res == 1){
            txtResult.setText("Данные обновлены");
        }
    }

    @FXML
    public void pressDelete(ActionEvent event) throws SQLException {
        String delete = "DELETE FROM user WHERE id_user=?";
        PreparedStatement statement = conn.prepareStatement(delete);
        statement.setString(1, txtID.getText());
        int res = statement.executeUpdate();

        if (res == 1){
            txtResult.setText("Данные удалены");
        }
    }

    @FXML
    public void pressClear(ActionEvent event){
        txtID.setText("");
        txtName.setText("");
        txtLastName.setText("");
        txtAge.setText("");
        txtEmail.setText("");
        txtPassword.setText("");
        txtResult.setText("Поля очищены");
    }
}
