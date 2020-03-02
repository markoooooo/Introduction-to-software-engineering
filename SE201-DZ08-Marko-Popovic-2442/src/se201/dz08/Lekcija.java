
package se201.dz08;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lekcija implements LekcijaInterface{
    
    private String nazivLekcije;
    private List<Lekcija> podlekcije=new ArrayList<>();
    private String opisLekcije;
    private String datumKreiranja;

    public Lekcija(String nazivLekcije, String opisLekcije, String datumKreiranja) {
        this.nazivLekcije = nazivLekcije;
        this.opisLekcije = opisLekcije;
        this.datumKreiranja = datumKreiranja;
    }

    @Override
    public String getNazivLekcije() {
        return nazivLekcije;
    }

    @Override
    public void setNazivLekcije(String nazivLekcije) {
        this.nazivLekcije = nazivLekcije;
    }

    @Override
    public List<Lekcija> getPodlekcije() {
        return podlekcije;
    }

    @Override
    public void setPodlekcije(List<Lekcija> podlekcije) {
        this.podlekcije = podlekcije;
    }

    @Override
    public String getOpisLekcije() {
        return opisLekcije;
    }

    @Override
    public void setOpisLekcije(String opisLekcije) {
        this.opisLekcije = opisLekcije;
    }

    @Override
    public String getDatumKreiranja() {
        return datumKreiranja;
    }

    @Override
    public void setDatumKreiranja(String datumKreiranja) {
        this.datumKreiranja = datumKreiranja;
    }

    @Override
    public void removeLekcija(Lekcija lekcija) {
       podlekcije.remove(lekcija);
    }

    @Override
    public void addLekcija(Lekcija lekcija) {
        podlekcije.add(lekcija);
    }

    @Override
    public String toString() {
        return "Lekcija{" + "nazivLekcije=" + nazivLekcije + ", podlekcije=" + podlekcije + ", opisLekcije=" + opisLekcije + ", datumKreiranja=" + datumKreiranja + '}';
    }
}
