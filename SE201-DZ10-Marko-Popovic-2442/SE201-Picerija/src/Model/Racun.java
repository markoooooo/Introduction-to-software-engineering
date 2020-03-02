
package Model;

public class Racun {
    private int id;
    private String username;
    private double bill;

    public Racun(int ID, String username, double bill) {
        this.id = ID;
        this.username = username;
        this.bill = bill;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }
    
    
}
