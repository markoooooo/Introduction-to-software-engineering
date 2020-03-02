package filling;

import Baza.Database;
import Model.Korisnik;
import Model.Proizvod;
import java.sql.SQLException;

public class FillingDB {

    public static void main(String[] args) throws SQLException {

        Database db = Database.getInstance();

        db.kreirajKorisnika(new Korisnik("marko", "marko", "marko"));

        db.dodajProizvod(new Proizvod(111, "Pizza Marko", 1100, Proizvod.Tip.Pizza));
        db.dodajProizvod(new Proizvod(444, "Lav pivo", 120, Proizvod.Tip.Pivo));
        db.dodajProizvod(new Proizvod(666, "Fungi", 180, Proizvod.Tip.Pasta));
        db.dodajProizvod(new Proizvod(001, "Jabuka", 55, Proizvod.Tip.Sok));
        db.dodajProizvod(new Proizvod(002, "Ananas", 65, Proizvod.Tip.Sok));

    }

}
