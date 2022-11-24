

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



public class LoginControl{
    @FXML
    private TextField UserNameTextField;
    @FXML
    private Button LoginBtn;
    @FXML
    private Button CancelBtn;
    @FXML
    private Label LoginMessageLabel;

    private DataOutputStream out;
    private DataInputStream in;
    private boolean connection;

//    @FXML
//    public void Initialize(){
//
//    }

    public void LoginBtnOnAction(ActionEvent event){
        if(UserNameTextField.getText().isEmpty() == false){
            validateLogin();
        }else{
            LoginMessageLabel.setText("Please enter your unsername");
        }
    }

    public void CancelBtnOnAction(ActionEvent event){
        Stage stage = (Stage) CancelBtn.getScene().getWindow();
        stage.close();
    }

    public void validateLogin(){
        if(UserNameTextField.getText().isEmpty() == false){
            Stage stage = (Stage) LoginBtn.getScene().getWindow();
            stage.close();
            turnToChat();
        }else {
            LoginMessageLabel.setText("Invalid, try again");
        }
    }

    public void turnToChat(){
        try {
            FXMLLoader Loader = new FXMLLoader(getClass().getResource("Chat.fxml"));
            Parent root = Loader.load();
            ChatControl control = (ChatControl) Loader.getController();
            control.model.setText(UserNameTextField.getText());
            Stage chatStage = new Stage();
            chatStage.setScene(new Scene(root));
            chatStage.setTitle("Chat");
            chatStage.setMaxWidth(600);
            chatStage.setMinHeight(400);
            chatStage.show();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
