package se201.dz08;

import java.util.Date;
import java.util.List;

public interface LekcijaInterface {

    public void removeLekcija(Lekcija lekcija);

    public void addLekcija(Lekcija lekcija);

    public String getNazivLekcije();

    public void setNazivLekcije(String nazivLekcije);

    public List<Lekcija> getPodlekcije();

    public void setPodlekcije(List<Lekcija> podlekcije);

    public String getOpisLekcije();

    public void setOpisLekcije(String opisLekcije);

    public String getDatumKreiranja();

    public void setDatumKreiranja(String datumKreiranja);
}
