package Baza;

import Model.Korisnik;
import Model.Proizvod;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TableView;

public class Database {
    
    private static Database instance;
//Singleton pattern
    public static Database getInstance() {
        if(instance == null)

            try {
                instance = new Database();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instance;
    }

    private Connection con = null;
    private final String url = "jdbc:mysql://localhost/";
    private final String user = "root";
    private final String password = "";
    private final String db_name = "PicerijaMarko";
    private final String tabelaKorisnika = "korisnik";
    private final String tabelaProizvoda = "proizvod";
    private final String tabelaRacuna = "racun";

    private Database() throws SQLException {
        con = DriverManager.getConnection(url, user, password);

        String query = "CREATE DATABASE IF NOT EXISTS " + db_name;

        Statement statement = con.createStatement();

        statement.execute(query);
        con.close();

        kreiranjeTabela();
        

    }
    
    private void kreiranjeTabela() throws SQLException{
        
        korisnikTabela();
        racuniTabela();
        proizvodTabela();
    }

    private void korisnikTabela() throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        Statement statement = con.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS " + tabelaKorisnika
                + "(ID INTEGER NOT NULL AUTO_INCREMENT,"
                + "username VARCHAR(15) NOT NULL, "
                + "password VARCHAR(10) NOT NULL, "
                + "repassword VARCHAR(25) NOT NULL, "
                + "PRIMARY KEY (ID)"
                + ")";

        statement.execute(query);

        if (!daLiPostojiKorisnik("admin")) {
            kreirajKorisnika(new Korisnik("admin", "admin", "admin"));
        }
        
        con.close();

    }

    private void proizvodTabela() throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        Statement statement = con.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS " + tabelaProizvoda
                + "(ID INTEGER NOT NULL AUTO_INCREMENT,"
                + "code INTEGER(20) NOT NULL, "
                + "name VARCHAR(30) NOT NULL, "
                + "price DOUBLE NOT NULL,"
                + "tipProizvoda VARCHAR(30) NOT NULL, "
                + "PRIMARY KEY (ID)"
                + ")";
        statement.execute(query);
         con.close();
    }
    
    private void racuniTabela() throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        Statement statement = con.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS " + tabelaRacuna
                + "(ID INTEGER NOT NULL AUTO_INCREMENT,"
                + "username VARCHAR(20) NOT NULL, "
                + "bill DOUBLE NOT NULL, "
                + "PRIMARY KEY (ID)"
                + ")";
        statement.execute(query);
        con.close();
    }


    public boolean kreirajKorisnika(Korisnik korisnik) throws SQLException {

        Connection con = DriverManager.getConnection(url + db_name, user, password);

        PreparedStatement statement = con.prepareStatement("INSERT INTO " + tabelaKorisnika + " (username, password, repassword) VALUES(?, ?, ?)");
        statement.setString(1, korisnik.getUsername());
        statement.setString(2, korisnik.getPassword());
        statement.setString(3, korisnik.getRepassword());

        statement.execute();
        con.close();
        return true;

    }

    public void updateKorisnik(Korisnik korisnik, int id) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);

        PreparedStatement statement = con.prepareStatement("UPDATE " + tabelaKorisnika + " SET username = ?, password = ?, repassword = ? WHERE id=?");
        statement.setString(1, korisnik.getUsername());
        statement.setString(2, korisnik.getPassword());
        statement.setString(3, korisnik.getRepassword());

        statement.setInt(4, id);
        statement.execute();
        con.close();

    }

    public boolean izbrisiKorisnika(int id) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        PreparedStatement statement = con.prepareStatement("DELETE FROM " + tabelaKorisnika + " WHERE id = ?");
        statement.setInt(1, id);
        statement.execute();
        con.close();
        return true;
    }

    public boolean daLiPostojiKorisnik(String username) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        String query = "SELECT * FROM " + tabelaKorisnika + " WHERE username = '" + username + "'";
        Statement statement = (Statement) con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.absolute(1)) {
            con.close();
            return true;
        }

        con.close();
        return false;

    }

    public void tableKorisnikUpdate(TableView<Korisnik> tableView) throws SQLException {

        tableView.getItems().clear();

        Connection con = DriverManager.getConnection(url + db_name, user, password);
        String query = "SELECT * FROM " + tabelaKorisnika;

        Statement statement = (Statement) con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Korisnik korisnik = new Korisnik(resultSet.getString("username"), resultSet.getString("password"),
                    resultSet.getString("repassword")) ;

            korisnik.setId(resultSet.getInt("ID"));
            tableView.getItems().add(korisnik);
        }
        con.close();
    }

    public boolean daLiKorisnikPostojiSaSifrom(String username, String password) throws SQLException {
        
        Connection con = DriverManager.getConnection(url + db_name, user, this.password);
        String query = "SELECT * FROM korisnik WHERE username = '" + username + "' AND password = '" + password + "'";
        Statement statement = (Statement) con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.absolute(1)) {
            con.close();
            return true;
        }

        con.close();
        return false;

    }

    public void tableProizvodiUpdate(TableView<Proizvod> tableView, Proizvod.Tip tipProizvoda) throws SQLException {

        tableView.getItems().clear();

        Connection con = DriverManager.getConnection(url + db_name, user, password);
        String query = "SELECT * FROM " + tabelaProizvoda + " WHERE tipProizvoda = '" + tipProizvoda.toString() + "'";

        Statement statement = (Statement) con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Proizvod proizvod = new Proizvod(resultSet.getInt("code"), resultSet.getString("name"),
                    resultSet.getDouble("price"), tipProizvoda);

            proizvod.setID(resultSet.getInt("ID"));
            tableView.getItems().add(proizvod);
        }
        con.close();
    }

    public void izbrisiProizvod(int id) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        PreparedStatement statement = con.prepareStatement("DELETE FROM " + tabelaProizvoda + " WHERE id = ?");
        statement.setInt(1, id);
        statement.execute();
        con.close();

    }

    public void dodajProizvod(Proizvod proizvod) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        PreparedStatement statement = con.prepareStatement("INSERT INTO " + tabelaProizvoda + "(code,name,price,tipProizvoda) VALUES(?,?,?,?)");
        statement.setInt(1, proizvod.getCode());
        statement.setString(2, proizvod.getName());
        statement.setDouble(3, proizvod.getPrice());
        statement.setString(4, proizvod.getTipProizvoda().toString());
        statement.execute();
        con.close();

    }

    public boolean daLiPostojiProizvod(int code) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        String query = "SELECT * FROM " + tabelaProizvoda + " WHERE code = '" + code + "'";
        Statement statement = (Statement) con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (resultSet.absolute(1)) {
            con.close();
            return true;
        }

        con.close();
        return false;

    }

    public void updateProizvod(Proizvod p, int id) throws SQLException {
        Connection con = DriverManager.getConnection(url + db_name, user, password);
        PreparedStatement statement = con.prepareStatement("UPDATE " + tabelaProizvoda + " SET code = ?, name = ?, price = ?, tipProizvoda = ? WHERE id=?");
        statement.setInt(1, p.getCode());
        statement.setString(2, p.getName());
        statement.setDouble(3, p.getPrice());
        statement.setString(4, p.getTipProizvoda().toString());
        statement.setInt(5, id);
        statement.execute();
        con.close();

    }

    

    public boolean upisiRacun(String username, double bill) {

        Connection con = null;
        try {
            con = DriverManager.getConnection(url + db_name, user, password);
            PreparedStatement statement = con.prepareStatement("INSERT INTO " + tabelaRacuna + " (username,bill) VALUES(?,?)");
            statement.setString(1, username);
            statement.setDouble(2, bill);
            statement.execute();
            con.close();
        } catch (SQLException ex) {
            try {
                con.close();
            } catch (SQLException ex1) {
            }
            return false;
        }

        return true;
    }

}
