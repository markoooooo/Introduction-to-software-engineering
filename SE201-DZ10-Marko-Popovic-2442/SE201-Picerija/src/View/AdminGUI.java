package View;

import Baza.Database;
import Baza.StaticVariables;
import Model.Korisnik;
import Model.Proizvod;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class AdminGUI extends Application {

    private final Label label = new Label(StaticVariables.imePicerije);

    private ComboBox<Proizvod.Tip> comboBox_tipProizvoda;

    private TableView<Korisnik> tabelaKorisnika;
    private ObservableList<Korisnik> podaci_korisnici;

    private TableView<Proizvod> tabekaProizvoda;
    private ObservableList<Proizvod> podaci_proizvodi;

    private Button btnProducts, btnUsers;
    private Button btn_add, btn_delete, btn_update;
    private TextField username, password, repass;
    private TextField code, name, price;

    private BorderPane root;
    private VBox rightSidePanel;
    private HBox hbox;
    private HBox horizontalForButtons;

    private int editId = 0;

    private void inicijalizacija() {

        root = new BorderPane();

        comboBox_tipProizvoda = new ComboBox<>();

        btn_add = new Button(StaticVariables.dugmeDodaj);
        btn_delete = new Button(StaticVariables.dugmeBrisi);
        btn_update = new Button(StaticVariables.dugmeIzmeni);

        btnUsers = new Button(StaticVariables.dugmeKorisnici);
        btnProducts = new Button(StaticVariables.dugmeProdukti);

        username = new TextField();
        password = new TextField();
        repass = new TextField();

        code = new TextField();
        name = new TextField();
        price = new TextField();

        podaci_proizvodi = FXCollections.observableArrayList();
        podaci_korisnici = FXCollections.observableArrayList();

        rightSidePanel = new VBox(5);
        hbox = new HBox();
        horizontalForButtons = new HBox();

        tabelaKorisnika = new TableView(podaci_korisnici);
        tabekaProizvoda = new TableView(podaci_proizvodi);

    }

    private void ulepsavanjeIDodavanjePodataka() {

        root.setPadding(new Insets(10));
        hbox.setPadding(new Insets(10));
        hbox.setSpacing(10);
        hbox.setStyle("-fx-background-color: #ff00ff;");

        username.setPromptText("username");
        password.setPromptText("Password");
        repass.setPromptText("repassword");

        code.setPromptText("code");
        name.setPromptText("name");
        price.setPromptText("price");

        btnUsers.setPrefSize(150, 30);
        btnUsers.setDisable(true);

        btnProducts.setPrefSize(150, 30);
        btnProducts.setDisable(false);

        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.web("#000000"));

        comboBox_tipProizvoda.setPrefSize(100, 20);

        rightSidePanel.setPadding(new Insets(10));

        comboBox_tipProizvoda.setPromptText("Proizvodi");
        for (Proizvod.Tip tipProizvoda : Proizvod.Tip.values()) {
            comboBox_tipProizvoda.getItems().add(tipProizvoda);
        }

        try {
            Database.getInstance().tableKorisnikUpdate(tabelaKorisnika);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        setTabelaKorisnika();
        setTabelaProizvoda();
    }

    private void setTabelaKorisnika() {
        
        TableColumn<Korisnik, String> tableUsername = new TableColumn<>("username");
        tableUsername.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Korisnik, String> tablePassword = new TableColumn<>("password");
        tablePassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Korisnik, String> tableRepassword = new TableColumn<>("repassword");
        tableRepassword.setCellValueFactory(new PropertyValueFactory<>("repassword"));

        tabelaKorisnika.getColumns().addAll(tableUsername, tablePassword, tableRepassword);
    }

    private void setTabelaProizvoda() {

        TableColumn<Proizvod, Integer> tableCode = new TableColumn<>("kod");
        tableCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Proizvod, String> tableName = new TableColumn<>("ime");
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Proizvod, Double> tablePrice = new TableColumn<>("cena");
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tabekaProizvoda.getColumns().addAll(tableCode, tableName, tablePrice);
    }

    private void akcije(Stage primaryStage) {
        btnUsers.setOnAction(e -> {
            primaryStage.setTitle("Korisnici");
            btnProducts.setDisable(false);
            btnUsers.setDisable(true);
            hbox.setStyle("-fx-background-color: #00ff00;");
            rightSidePanel.getChildren().clear();
            rightSidePanel.getChildren().addAll(label, new Label(), username, password, repass, new Label(), horizontalForButtons);
            root.setCenter(tabelaKorisnika);

        });
        btnProducts.setOnAction(e -> {
            primaryStage.setTitle("Produckti");
            btnProducts.setDisable(true);
            btnUsers.setDisable(false);
            hbox.setStyle("-fx-background-color: #ff00ff;");
            rightSidePanel.getChildren().clear();
            rightSidePanel.getChildren().addAll(comboBox_tipProizvoda, new Label(), code, name, price, new Label(),  horizontalForButtons);
            root.setCenter(tabekaProizvoda);

        });

        tabelaKorisnika.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                if (newValue != null) {
                    Korisnik k = (Korisnik) tabelaKorisnika.getSelectionModel().getSelectedItem();
                    username.setText(k.getUsername());
                    password.setText(k.getPassword());
                    repass.setText(k.getRepassword());

                }
            }
        });

        tabekaProizvoda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue ov, Object oldValue, Object newValue) {
                if (newValue != null) {
                    Proizvod p = (Proizvod) tabekaProizvoda.getSelectionModel().getSelectedItem();
                    code.setText(p.getCode() + "");
                    name.setText(p.getName());
                    price.setText(p.getPrice() + "");

                }
            }
        });

        comboBox_tipProizvoda.setOnAction(e -> {
            try {
                Database.getInstance().tableProizvodiUpdate(tabekaProizvoda, comboBox_tipProizvoda.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btn_add.setOnAction(e -> {

            if(comboBox_tipProizvoda.getValue() == null){
                
                JOptionPane.showMessageDialog(null, "Niste selektovali proizvod!");
                return;
            }
            
            if (btnUsers.isDisabled()) {

                if (username.getText().isEmpty() || password.getText().isEmpty() || repass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prazna polja!");
                } else if (password.getText().equals(repass.getText())) {

                    try {
                        if (!Database.getInstance().daLiPostojiKorisnik(username.getText())) {
                            try {
                                Database.getInstance().kreirajKorisnika(new Korisnik(username.getText(), password.getText(),
                                        repass.getText()));
                                JOptionPane.showMessageDialog(null, "Uspesno ste dodali korisnika");

                                Database.getInstance().tableKorisnikUpdate(tabelaKorisnika);
                            } catch (SQLException ex) {
                                Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Vez zauzeto korisnicko ime");
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Nije isti password");
                }

            } else {

                if (code.getText().isEmpty() || name.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prazna polja!");
                } else {
                    try {
                        int kod = Integer.parseInt(code.getText());
                        double cena = Double.parseDouble(price.getText());
                        if (!Database.getInstance().daLiPostojiProizvod(kod)) {
                            Database.getInstance().dodajProizvod(new Proizvod(kod, name.getText(), cena, comboBox_tipProizvoda.getValue()));
                            JOptionPane.showMessageDialog(null, "Uspesno ste dodali proizvod");
                            code.setText("");
                            name.setText("");
                            price.setText("");

                        } else {
                            JOptionPane.showMessageDialog(null, "Proizvod vec postoji");
                            code.setText("");
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Greska pri unosu podataka!");
                    }
                }
                try {
                    Database.getInstance().tableProizvodiUpdate(tabekaProizvoda, comboBox_tipProizvoda.getValue());
                } catch (SQLException ex) {
                }

            }

        });

        btn_delete.setOnAction(e -> {

             if(comboBox_tipProizvoda.getValue() == null){
                
                JOptionPane.showMessageDialog(null, "Niste selektovali proizvod!");
                return;
            }
            
            if (btnUsers.isDisabled()) {
                if (username.getText().isEmpty() || password.getText().isEmpty() || repass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prazna polja!");
                } else {
                    editId = ((Korisnik) tabelaKorisnika.getSelectionModel().getSelectedItem()).getId();
                    try {
                        Database.getInstance().izbrisiKorisnika(editId);
                        Database.getInstance().tableKorisnikUpdate(tabelaKorisnika);
                        JOptionPane.showMessageDialog(null, "Uspesno ste obrisali korisnika");
                        username.setText("");
                        password.setText("");
                        repass.setText("");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                if (code.getText().isEmpty() || name.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Greska prilikom brisanja!");
                } else {
                    editId = ((Proizvod) tabekaProizvoda.getSelectionModel().getSelectedItem()).getID();
                    try {
                        Database.getInstance().izbrisiProizvod(editId);
                        Database.getInstance().tableProizvodiUpdate(tabekaProizvoda, comboBox_tipProizvoda.getValue());
                        JOptionPane.showMessageDialog(null, "Uspesno ste izbrisali proizvod");
                        code.setText("");
                        name.setText("");
                        price.setText("");
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btn_update.setOnAction(e -> {

             if(comboBox_tipProizvoda.getValue() == null){
                
                JOptionPane.showMessageDialog(null, "Niste selektovali proizvod!");
                return;
            }
            
            if (btnUsers.isDisabled()) {
                if (username.getText().isEmpty() || password.getText().isEmpty() || repass.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Prazna polja!");
                } else {
                    try {
                        if (!Database.getInstance().daLiPostojiKorisnik(username.getText())) {
                            JOptionPane.showMessageDialog(null, "Takav korisnik ne postoji!");
                        } else {
                            editId = ((Korisnik) tabelaKorisnika.getSelectionModel().getSelectedItem()).getId();

                            Korisnik k = new Korisnik(username.getText(), password.getText(), repass.getText());
                            Database.getInstance().updateKorisnik(k, editId);
                            Database.getInstance().tableKorisnikUpdate(tabelaKorisnika);
                            JOptionPane.showMessageDialog(null, "Uspesno ste izmenili podatke korisnika");
                            username.setText("");
                            password.setText("");
                            repass.setText("");

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                if (code.getText().isEmpty() || name.getText().isEmpty() || price.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Greska!!");
                } else {
                    try {
                        int kod = Integer.parseInt(code.getText());
                        if (!Database.getInstance().daLiPostojiProizvod(kod)) {
                            JOptionPane.showMessageDialog(null, "Proizvod sa tim code-om ne postoji");
                            code.setText("");
                            name.setText("");
                            price.setText("");
                        } else {
                            editId = ((Proizvod) tabekaProizvoda.getSelectionModel().getSelectedItem()).getID();
                            try {

                                double cena = Double.parseDouble(price.getText());
                                Proizvod p = new Proizvod(kod, name.getText(), cena, comboBox_tipProizvoda.getValue());
                                Database.getInstance().updateProizvod(p, editId);
                                Database.getInstance().tableProizvodiUpdate(tabekaProizvoda, comboBox_tipProizvoda.getValue());
                                JOptionPane.showMessageDialog(null, "Uspesno ste izmenili proizvod");
                                code.setText("");
                                name.setText("");
                                price.setText("");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Greska pri izmeni podataka");
                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AdminGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

    }

    @Override
    public void start(Stage primaryStage) {

        inicijalizacija();
        ulepsavanjeIDodavanjePodataka();
        akcije(primaryStage);

        hbox.getChildren().addAll(btnUsers, btnProducts);
        horizontalForButtons.getChildren().addAll(btn_add, btn_delete, btn_update);
        rightSidePanel.getChildren().addAll(label, new Label(), username, password, repass,new Label(),  horizontalForButtons);

        root.setCenter(tabelaKorisnika);
        root.setRight(rightSidePanel);
        root.setTop(hbox);
        

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
