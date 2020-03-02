
package se201.dz09;


public class SE201DZ09 {

    
    public static void main(String[] args) {
        IspitnaObaveza obaveza = new IspitnaObaveza();
        obaveza.projekti.add(10.2);
        obaveza.testovi.add(11.3);
        obaveza.testovi.add(10.3);
        obaveza.zadaci.add(2.4);
        obaveza.zadaci.add(2.5);
        obaveza.zadaci.add(1.4);
        obaveza.zadaci.add(0.4);
        PredispitnaObavezaImpl obavezaImpl = new PredispitnaObavezaImpl(obaveza);
        System.out.println("Da li ima vise od 15 poena u zadacima ili testovima:"+obavezaImpl.daLiImaViseOd15PoenaUZadacimaIliTestovima());
        System.out.println("Da li sve predispitne obaveze imaju od 0 do 15 bodova:"+obavezaImpl.daLiPredispitnaObavezaImaOd0DoMax());
        System.out.println("Ukupan broj predispitnih obaveza je:"+obavezaImpl.ukupanBrojPoena());
    }
    
}
