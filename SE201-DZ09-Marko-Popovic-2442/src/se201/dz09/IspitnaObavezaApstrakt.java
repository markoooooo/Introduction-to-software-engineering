
package se201.dz09;


public abstract class IspitnaObavezaApstrakt implements PredispitnaObavezaInterface {

    protected PredispitnaObavezaInterface obaveze;

    public IspitnaObavezaApstrakt(PredispitnaObavezaInterface obaveze) {
        this.obaveze = obaveze;
    }

    public boolean daLiPredispitnaObavezaImaOd0DoMax() {
        return obaveze.daLiPredispitnaObavezaImaOd0DoMax();
    }

    public boolean daLiImaViseOd15PoenaUZadacimaIliTestovima() {
        return obaveze.daLiImaViseOd15PoenaUZadacimaIliTestovima();
    }

    public Double ukupanBrojPoena() {
        return obaveze.ukupanBrojPoena();
    }

}

