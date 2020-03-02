
package se201.dz09;

import java.util.*;

public class IspitnaObaveza implements PredispitnaObavezaInterface {

    List<Double> zadaci = new ArrayList<>();
    List<Double> testovi = new ArrayList<>();
    List<Double> projekti = new ArrayList<>();
    private double ukupno;

    public List<Double> getZadaci() {
        return zadaci;
    }

    public void setZadaci(List<Double> zadaci) {
        this.zadaci = zadaci;
    }

    public List<Double> getTestovi() {
        return testovi;
    }

    public void setTestovi(List<Double> testovi) {
        this.testovi = testovi;
    }

    public List<Double> getProjekti() {
        return projekti;
    }

    public void setProjekti(List<Double> projekti) {
        this.projekti = projekti;
    }

    public double getUkupno() {
        return ukupno;
    }

    public void setUkupno(double ukupno) {
        this.ukupno = ukupno;
    }

    @Override
    public boolean daLiPredispitnaObavezaImaOd0DoMax() {
        for (int i = 0; i < this.projekti.size(); i++) {
            if (this.projekti.get(i) < 0 || this.projekti.get(i) > 15) {
                return false;
            }
        }
        for (int i = 0; i < this.zadaci.size(); i++) {
            if (this.zadaci.get(i) < 0 || this.zadaci.get(i) > 15) {
                return false;
            }
        }
        for (int i = 0; i < this.testovi.size(); i++) {
            if (this.testovi.get(i) < 0 || this.testovi.get(i) > 15) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean daLiImaViseOd15PoenaUZadacimaIliTestovima() {
        for (int i = 0; i < this.zadaci.size(); i++) {
            if (this.zadaci.get(i) > 15) {
                return true;
            }
        }
        for (int i = 0; i < this.testovi.size(); i++) {
            if (this.testovi.get(i) > 15) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Double ukupanBrojPoena() {
        for (int i = 0; i < this.projekti.size(); i++) {
            this.ukupno = this.ukupno + this.projekti.get(i);
        }
        for (int i = 0; i < this.zadaci.size(); i++) {
            this.ukupno = this.ukupno + this.zadaci.get(i);
        }
        for (int i = 0; i < this.testovi.size(); i++) {
            this.ukupno = this.ukupno + this.testovi.get(i);
        }
        return ukupno;
    }

    @Override
    public String toString() {
        return "IspitnaObaveza{" + "zadaci=" + zadaci + ", testovi=" + testovi + ", projekti=" + projekti + ", ukupno=" + ukupno + '}';
    }

}
