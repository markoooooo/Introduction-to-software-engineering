package View;

import Baza.Database;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class Login extends Application {
    
    private Stage primaryStage;
    private VBox root;

    private Label lbl_user;
    private Label lbl_password;

    private TextField tf_username;
    private PasswordField pf_password;

    private Button btn_login;
    // ova metoda sluzi za inicijalizovanje (obezbedjivanje memorijskog prostora)
    private void inicijalizacija() {

        root = new VBox(10);

        lbl_user = new Label("Username");
        lbl_password = new Label("Password");

        tf_username = new TextField();
        pf_password = new PasswordField();

        btn_login = new Button("Login");

    }

    private void dodatnaPodesavanja() {
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #546543;");

        lbl_user.setFont(new Font(24));
        lbl_user.setStyle("-fx-text-fill: white;");

        lbl_password.setFont(new Font(24));
        lbl_password.setStyle("-fx-text-fill: white;");

        btn_login.setFont(new Font(16));
    }

    private void akcije() {
        btn_login.setOnAction(e -> {
            try {
                if (Database.getInstance().daLiKorisnikPostojiSaSifrom(tf_username.getText(), pf_password.getText())) {
                    
                    if (tf_username.getText().equals("admin")) 
                        new AdminGUI().start(primaryStage);

                    else 
                        new KorisnikGUI().start(primaryStage, tf_username.getText());
                    
                }else 
                    JOptionPane.showMessageDialog(null, "Korisnik ne postoji!!");
                

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        inicijalizacija();
        dodatnaPodesavanja();
        akcije();

        root.getChildren().addAll(lbl_user, tf_username, lbl_password, pf_password, btn_login);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Login scene");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
