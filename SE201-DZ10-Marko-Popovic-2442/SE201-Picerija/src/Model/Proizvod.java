
package Model;
//Ovo je primer enkapsulacije podataka gde sve globalne promenljive imaju modifikator pristupa private
//a pristapunje i menjanje podataka vrsimo pomocu getera i setera.
public class Proizvod {
    
    public enum Tip {
        Pizza, Pasta, Sok, Vino,
        Pivo, Sladoled 
    }
    
    private int id;
    private int code;
    private String name;
    private double price;
    private Enum tipProizvoda;

    public Proizvod(int code, String name, double price, Tip tipProizvoda) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.tipProizvoda = tipProizvoda;
    }

    public Enum getTipProizvoda() {
        return tipProizvoda;
    }

    public void setTipProizvoda(Enum tipProizvoda) {
        this.tipProizvoda = tipProizvoda;
    }

    
    
    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " " + price ;
    }
    
}
