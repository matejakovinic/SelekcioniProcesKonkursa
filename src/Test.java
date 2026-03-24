import java.time.LocalDate;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        // 1. Konkurs
        Konkurs konkurs = new Konkurs("Java Developer", Arrays.asList("Java", "SQL", "OOP"), 1);
        konkurs.setPrijaveOtvorene(true);
        konkurs.setMinBrojSposobnosti(2);
        // 2. Kandidati
        Kandidat k1 = new Kandidat("Mateja", "A", "m1@mail.com", Arrays.asList("Java", "SQL", "OOP"));
        Kandidat k2 = new Kandidat("Marko", "B", "m2@mail.com", Arrays.asList("Java"));
        Kandidat k3 = new Kandidat("Jovan", "C", "m3@mail.com", Arrays.asList("Java", "SQL"));

        Kandidat k4 = new Kandidat("Ana", "D", "m4@mail.com", Arrays.asList("HTML", "CSS"));
        // prijave (3 prolaze, 1 ne)
        k1.prijaviSeNaKonkurs(konkurs);
        k2.prijaviSeNaKonkurs(konkurs);
        k3.prijaviSeNaKonkurs(konkurs);
        k4.prijaviSeNaKonkurs(konkurs); // nece proci
        // 3. Intervjui
        TehnickiIntervju t1 = new TehnickiIntervju("Petar", 3, 5, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5), 1);
        t1.definisiPoene(Arrays.asList(2, 2, 2));
        HRIntervju hr = new HRIntervju("Ivana", 3, 2, LocalDate.now().minusDays(1), LocalDate.now().plusDays(5), 2);
        hr.setOcekivaneOsobine(Arrays.asList("Ambiciozan", "Uporan", "Timski"));
        // dodavanje intervjua
        konkurs.dodajIntervju(t1);
        konkurs.dodajIntervju(hr);
        // 4. pokretanje procesa
        konkurs.otpocniSelekcioniProces();
        // 5. kandidati rade prvi intervju
        k1.odradiIntervju(t1, LocalDate.now(), "1-TACNO,2-TACNO,3-TACNO");
        k2.odradiIntervju(t1, LocalDate.now(), "1-TACNO,2-NETACNO,3-TACNO");
        k3.odradiIntervju(t1, LocalDate.now(), "1-NETACNO,2-TACNO,3-TACNO");
        // 6. selekcija u drugi krug (npr top 2)
        konkurs.posaljiUNaredniKrug(2);
        // 7. ispis
        konkurs.ispisiSve();
    }
}