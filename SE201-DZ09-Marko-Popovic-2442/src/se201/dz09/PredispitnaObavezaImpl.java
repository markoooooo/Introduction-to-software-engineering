
package se201.dz09;


public class PredispitnaObavezaImpl extends IspitnaObavezaApstrakt{
    
     public PredispitnaObavezaImpl(PredispitnaObavezaInterface obaveze) {
        super(obaveze);
    }

    @Override
    public boolean daLiPredispitnaObavezaImaOd0DoMax(){
        return obaveze.daLiPredispitnaObavezaImaOd0DoMax();
    }

    @Override
    public boolean daLiImaViseOd15PoenaUZadacimaIliTestovima(){
        return obaveze.daLiImaViseOd15PoenaUZadacimaIliTestovima();
    }

    @Override
    public Double ukupanBrojPoena() {
        return obaveze.ukupanBrojPoena();
    }
}
