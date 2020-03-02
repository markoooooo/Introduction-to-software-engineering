package View;

import Baza.Database;
import Baza.StaticVariables;
import Model.Proizvod;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class KorisnikGUI {

    private final Label label = new Label(StaticVariables.imePicerije);

    private ComboBox<Proizvod.Tip> comboBoxProizvoda;

    private TableView<Proizvod> table;
    private ObservableList<Proizvod> tabelaPodaci;

    private ListView<String> listView;
    private ObservableList<String> listViewItems;

    private Label trenutniRacun;
    private Button btn_dodaj, btn_brisi, dugme_racun;

    private ArrayList<Double> racun;

    private void inicijalizacija() {

        racun = new ArrayList<>();
        
        listView = new ListView<>();
        listViewItems = FXCollections.observableArrayList();

        tabelaPodaci = FXCollections.observableArrayList();
        table = new TableView(tabelaPodaci);

        comboBoxProizvoda = new ComboBox<>();

        btn_dodaj = new Button(StaticVariables.dugmeDodaj);
        btn_brisi = new Button(StaticVariables.dugmeBrisi);
        dugme_racun = new Button(StaticVariables.dugmeRacun);

        trenutniRacun = new Label();

    }

    private void ulepsavanjeIPodaci() {

        label.setFont(new Font("Verdana", 20));
        label.setTextFill(Color.web("#F0FFFF"));

        trenutniRacun.setText("Racun: 0");
        trenutniRacun.setFont(new Font("Verdana", 20));

        btn_dodaj.setPrefSize(100, 20);
        btn_brisi.setPrefSize(100, 20);
        dugme_racun.setPrefSize(100, 20);

        listView.setPrefWidth(150);
        listView.setPrefHeight(100);

        comboBoxProizvoda.setPromptText("Proizvodi");
        comboBoxProizvoda.setPrefSize(100, 20);

        for (Proizvod.Tip tipProizvoda : Proizvod.Tip.values()) {
            comboBoxProizvoda.getItems().add(tipProizvoda);
        }

        listView.setItems(listViewItems);

    }

    private void podesavanjeTabele() {
        TableColumn<Proizvod, Integer> tableCode = new TableColumn<>("kod");
        tableCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Proizvod, String> tableName = new TableColumn<>("ime");
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Proizvod, Double> tablePrice = new TableColumn<>("cena");
        tablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        table.getColumns().addAll(tableCode, tableName, tablePrice);
    }

    private void akcije() {
        comboBoxProizvoda.setOnAction(e -> {
            try {
                Database.getInstance().tableProizvodiUpdate(table, comboBoxProizvoda.getValue());
            } catch (SQLException ex) {
                Logger.getLogger(KorisnikGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        table.setRowFactory(tv -> {
            TableRow<Proizvod> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Proizvod rowData = row.getItem();

                    listViewItems.add(rowData.toString());
                    racun.add(rowData.getPrice());
                    trenutniRacun.setText(String.format("%.2f", zaUplatu(racun)));

                }
            });
            return row;
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    int brisi = listView.getSelectionModel()
                            .getSelectedIndex();
                    
                    racun.remove(brisi);
                    listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
                    trenutniRacun.setText(String.format("%.2f", zaUplatu(racun)));
                    //use this to do whatever you want to. Open Link etc.
                }
            }
        });

        dugme_racun.setOnAction(e -> {

            if (listView.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nema proizvoda na racunu");
            } else {
                String bill = "";
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

                bill += label.getText() + "\n";
                bill += "_____________________________" + "\n";
                bill += "Kupac: " + user + "\n";
                bill += "_____________________________" + "\n";
                bill += "Vreme: " + sdf.format(cal.getTime()) + "\n";
                for (String s : listView.getItems()) {
                    bill += s + "\n" + "_____________________________" + "\n";

                }
                bill += "Ukupan racun: " + zaUplatu(racun) + "\nPotvrdite narudzbinu";
                int odgovor = JOptionPane.showConfirmDialog(null, bill);
                if (odgovor == 0) {
                    Database.getInstance().upisiRacun(user, zaUplatu(racun));
                    JOptionPane.showMessageDialog(null, "Hvala Vam na kupovini. Dodjite nam ponovo.");
                    System.exit(0);
                }

            }
        });
    }

    private String user;

    public void start(Stage primaryStage, String user) {
        this.user = user;

        inicijalizacija();
        podesavanjeTabele();
        ulepsavanjeIPodaci();
        akcije();

        HBox labelPanel = new HBox(6);
        labelPanel.setPadding(new Insets(10));
        labelPanel.setStyle("-fx-background-color: #ff00ff;");
        labelPanel.setAlignment(Pos.CENTER);
        labelPanel.getChildren().add(label);

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        VBox center = new VBox(10);
        center.setPadding(new Insets(10));
        center.setAlignment(Pos.CENTER);
        center.setStyle("-fx-background-color: #00ff00;");
        center.getChildren().addAll(comboBoxProizvoda, table);
        root.setCenter(center);
        root.setTop(labelPanel);

        VBox leftEmpty = new VBox(2);
        Label empty = new Label("    ");
        leftEmpty.getChildren().add(empty);
        leftEmpty.setStyle("-fx-background-color: #00ff00;");
        root.setLeft(leftEmpty);

        HBox emptyBottom = new HBox();
        Label empryLabelBottom = new Label("   ");
        root.setBottom(emptyBottom);
        emptyBottom.getChildren().add(empryLabelBottom);
        emptyBottom.setStyle("-fx-background-color: #00ff00;");

        BorderPane rightPanel = new BorderPane();

        rightPanel.setPadding(new Insets(10));
        rightPanel.setStyle("-fx-background-color: #00ff00;");
        rightPanel.setCenter(listView);

        VBox rightBottomPanel = new VBox();
        rightBottomPanel.setAlignment(Pos.CENTER);
        rightBottomPanel.getChildren().addAll(trenutniRacun, dugme_racun);
        rightPanel.setBottom(rightBottomPanel);

        root.setRight(rightPanel);

        Scene scene = new Scene(root, 570, 300);

        primaryStage.setTitle(StaticVariables.imePicerije);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private double zaUplatu(ArrayList<Double> racun) {
        double ukupno = 0;
        for (Double d : racun) {
            ukupno += d;
        }
        return ukupno;
    }

}
