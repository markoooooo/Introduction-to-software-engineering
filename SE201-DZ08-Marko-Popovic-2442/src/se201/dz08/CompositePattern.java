package se201.dz08;

public class CompositePattern {

    public static void main(String[] args) {

        Lekcija lekcija = new Lekcija("CompositeDemo", "Ovo je composite demo", "14/08/2018");
        Lekcija lekcija2 = new Lekcija("CompositeDemo2 podlekcija", "asdasd sasd", "15/08/2018");
        Lekcija lekcija3 = new Lekcija("CompositeDemo3 podlekcija", "asdasd zxc", "16/08/2018");
        Lekcija lekcija4 = new Lekcija("CompositeDemo4 podlekcija", "ertert", "17/08/2018");
        Lekcija lekcija5 = new Lekcija("CompositeDemo5 podlekcija", "nvbbndgh", "18/08/2018");

        lekcija.addLekcija(lekcija2);
        lekcija.addLekcija(lekcija3);
        lekcija.addLekcija(lekcija4);
        lekcija.addLekcija(lekcija5);

        lekcija.removeLekcija(lekcija5);

        System.out.println(lekcija.toString());

    }
}
