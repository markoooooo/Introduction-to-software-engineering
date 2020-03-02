
package Baza;

import Model.Korisnik;
import Model.Proizvod;
import java.sql.SQLException;
import javafx.scene.control.TableView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marko Popovic
 */
public class Testing {
    
    public Testing() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void testCreateKorisnik() throws Exception {
        System.out.println("createKorisnik");
        Korisnik kk = new Korisnik("markoni", "a", "a");
        boolean ocekivanaVrednost = true;     
        assertEquals(ocekivanaVrednost, Database.getInstance().kreirajKorisnika(kk));
       
    }

    @Test
    public void testRemoveKorisnik() throws Exception {
        System.out.println("removeKorisnik");
        int id = 2;
        assertEquals(true, Database.getInstance().izbrisiKorisnika(id));
    }

    @Test
    public void testDaLiPostojiKorisnik() throws Exception {
        System.out.println("daLiPostojiKorisnik");
        String username = "marko";
        boolean expResult = true;
        boolean result = Database.getInstance().daLiPostojiKorisnik(username);
        assertEquals(expResult, result);
       
    }

    @Test
    public void testIsUserNameAndPasswordInDatabase() throws Exception {
        System.out.println("isUserNameAndPasswordInDatabase");
        String username = "marko";
        String password = "marko";
        boolean expResult = true;
        boolean result = Database.getInstance().daLiKorisnikPostojiSaSifrom(username, password);
        assertEquals(expResult, result);
   
    }

    @Test
    public void testRemoveProizvod() throws Exception {
        System.out.println("removeProizvod");
        int id = 1;
        Database.getInstance().izbrisiProizvod(id);
   
    }

    @Test
    public void testDaLiPostojiProizvod() throws Exception {
        System.out.println("daLiPostojiProizvod");
        int code = 111;
        boolean expResult = true;
        boolean result = Database.getInstance().daLiPostojiProizvod(code);
        assertEquals(expResult, result);

    }

    @Test
    public void testUpisiRacun() throws SQLException {
        System.out.println("upisiRacun");
        String username = "a";
        double bill = 250.00;
        boolean expResult = true;      
        boolean result = Database.getInstance().upisiRacun(username, bill);
        assertEquals(expResult, result);

    }
    
}
