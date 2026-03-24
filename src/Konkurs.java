import java.util.ArrayList;
import java.util.List;

public class Konkurs {

    private String pozicija;
    private boolean prijaveOtvorene;
    private List<String> zeljeneSposobnosti = new ArrayList<>();
    private int minBrojSposobnosti;
    private int trenutniBrojKruga;
    private List<Kandidat> kandidati = new ArrayList<>();
    private List<Intervju> intervjui = new ArrayList<>();

    public Konkurs(String pozicija, List<String> zeljeneSposobnosti, int trenutniBrojKruga) {
        this.pozicija = pozicija;
        this.zeljeneSposobnosti = zeljeneSposobnosti;
        this.trenutniBrojKruga = trenutniBrojKruga;
    }

    public boolean dodajIntervju(Intervju intervju){
        // 1. da li vec postoji
        if (intervjui.contains(intervju)) {
            return false;
        }
        // 2. odredi max krug
        int maxKrug = 0;
        for (Intervju i : intervjui) {
            if (i.getBrojKruga() > maxKrug) {
                maxKrug = i.getBrojKruga();
            }
        }
        // 3. mora biti sledeci krug
        if (intervju.getBrojKruga() != maxKrug + 1) {
            return false;
        }
        // 4. dodavanje
        intervjui.add(intervju);
        intervju.setKonkurs(this);

        return true;
    }
    public boolean otpocniSelekcioniProces(){
        // 1. da li postoje intervjui
        if (intervjui == null || intervjui.isEmpty()) {
            System.out.println("Ne postoji nijedan intervju!");
            return false;
        }
        // 2. zatvori prijave
        this.prijaveOtvorene = false;
        // 3. nadji intervju prvog kruga
        Intervju prvi = null;
        for (Intervju i : intervjui) {
            if (i.getBrojKruga() == 1) {
                prvi = i;
                break;
            }
        }
        if (prvi == null) {
            System.out.println("Ne postoji intervju prvog kruga!");
            return false;
        }
        // 4. dodaj svim kandidatima intervjuisanje
        for (Kandidat k : kandidati) {
            IntervjuisanjeKandidata ik = new IntervjuisanjeKandidata(prvi, k);
            // dodaj kod kandidata
            k.getIntervjuisanje().add(ik);
            // dodaj kod intervjua
            prvi.getIntervjuisanjeKandidata().add(ik);
        }
        return true;
    }
    public boolean posaljiUNaredniKrug(int ogranicenje) {
        // 1. nadji trenutni intervju
        Intervju trenutni = null;
        for (Intervju i : intervjui) {
            if (i.getBrojKruga() == this.trenutniBrojKruga) {
                trenutni = i;
                break;
            }
        }
        // 2. nadji sledeci intervju
        Intervju sledeci = null;
        for (Intervju i : intervjui) {
            if (i.getBrojKruga() == this.trenutniBrojKruga + 1) {
                sledeci = i;
                break;
            }
        }
        if (sledeci == null) {
            System.out.println("Ne postoji sledeci krug!");
            return false;
        }
        // 3. selekcija kandidata
        List<Kandidat> prosli = trenutni.odaberiKandidataZaProlaz(ogranicenje);
        // 4. dodaj ih u sledeci krug
        for (Kandidat k : prosli) {
            IntervjuisanjeKandidata ik = new IntervjuisanjeKandidata(sledeci, k);
            k.getIntervjuisanje().add(ik);
            sledeci.getIntervjuisanjeKandidata().add(ik);
        }
        // 5. azuriraj trenutni krug
        this.trenutniBrojKruga++;
        return true;
    }

    public void ispisiSve() {

        // 1. sortiraj intervjue po krugu
        intervjui.sort((a, b) -> Integer.compare(a.getBrojKruga(), b.getBrojKruga()));

        for (Intervju i : intervjui) {
            System.out.println("Intervju - krug: " + i.getBrojKruga());
            List<IntervjuisanjeKandidata> lista = new ArrayList<>(i.getIntervjuisanjeKandidata());
            // 2. sortiraj kandidate
            lista.sort((a, b) -> {
                // poeni opadajuce
                if (b.getPoeni() != a.getPoeni()) {
                    return Integer.compare(b.getPoeni(), a.getPoeni());
                }
                // datum rastuce
                if (a.getOdradjeno() == null && b.getOdradjeno() == null) return 0;
                if (a.getOdradjeno() == null) return 1;
                if (b.getOdradjeno() == null) return -1;
                return a.getOdradjeno().compareTo(b.getOdradjeno());
            });
            // 3. ispis kandidata
            for (IntervjuisanjeKandidata ik : lista) {
                Kandidat k = ik.getKandidat();
                System.out.println(k.getIme() + " " + k.getPrezime() + " | Poeni: " + ik.getPoeni() + " | Datum: " + ik.getOdradjeno());
            }
            System.out.println("----------------------");
        }
    }

    public String getPozicija() {return pozicija;}
    public void setPozicija(String pozicija) {this.pozicija = pozicija;}
  
    public List<String> getZeljeneSposobnosti() {return zeljeneSposobnosti;}
    public void setZeljeneSposobnosti(List<String> zeljeneSposobnosti) {this.zeljeneSposobnosti = zeljeneSposobnosti;}

    public int getMinBrojSposobnosti() {return minBrojSposobnosti;}
    public void setMinBrojSposobnosti(int minBrojSposobnosti) {this.minBrojSposobnosti = minBrojSposobnosti;}

    public int getTrenutniBrojKruga() {return trenutniBrojKruga;}
    public void setTrenutniBrojKruga(int trenutniBrojKruga) {this.trenutniBrojKruga = trenutniBrojKruga;}

    public List<Kandidat> getKandidati() {return kandidati;}
    public void setKandidati(List<Kandidat> kandidati) {this.kandidati = kandidati;}

    public void setPrijaveOtvorene(boolean prijaveOtvorene) {
        this.prijaveOtvorene=prijaveOtvorene;
    }

    public boolean isPrijaveOtvorene() {
        return prijaveOtvorene;
    }
}
