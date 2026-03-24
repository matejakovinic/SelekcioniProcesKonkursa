import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kandidat {

    private String ime;
    private String prezime;
    private String email;
    private List<String> sposobnosti = new ArrayList<>();
    private List<IntervjuisanjeKandidata> intervjuisanje = new ArrayList<>();

    public Kandidat(String ime, String prezime, String email, List<String> sposobnosti) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.sposobnosti = sposobnosti;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Kandidat kandidat = (Kandidat) o;
        return Objects.equals(ime, kandidat.ime) && Objects.equals(prezime, kandidat.prezime) && Objects.equals(email, kandidat.email) && Objects.equals(sposobnosti, kandidat.sposobnosti) && Objects.equals(intervjuisanje, kandidat.intervjuisanje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, email, sposobnosti, intervjuisanje);
    }

    public boolean prijaviSeNaKonkurs(Konkurs konkurs){
        // 1. da li su prijave otvorene
        if (!konkurs.isPrijaveOtvorene()) {
            return false;
        }
        // 2. broj poklapanja sposobnosti
        int brojPoklapanja = 0;
        for (String s : this.sposobnosti) {
            if (konkurs.getZeljeneSposobnosti().contains(s)) {
                brojPoklapanja++;
            }
        }
        // 3. da li ima dovoljno sposobnosti
        if (brojPoklapanja < konkurs.getMinBrojSposobnosti()) {
            return false;
        }
        // 4. dodaj kandidata ako već nije dodat
        if (!konkurs.getKandidati().contains(this)) {
            konkurs.getKandidati().add(this);
        }

        return true;


    }

    public void odradiIntervju(Intervju intervju, LocalDate datum,String odgovori){
        IntervjuisanjeKandidata nadjen = null;
        // 1. da li je kandidat prijavljen na intervju
        for (IntervjuisanjeKandidata ik : intervjuisanje) {
            if (ik.getIntervju().equals(intervju)) {
                nadjen = ik;
                break;
            }
        }
        if (nadjen == null) {
            System.out.println("Kandidat nije prijavljen na ovaj intervju!");
            return;
        }
        // 2. da li je vec radio intervju
        if (nadjen.getOdradjeno() != null) {
            System.out.println("Intervju je vec odradjen!");
            return;
        }
        // 3. da li je istekao rok
        if (datum.isAfter(intervju.getAktivdanDo())) {
            System.out.println("Istekao je rok za ovaj intervju!");
            return;
        }
        // 4. sve ok → upisi rezultate
        nadjen.setOdradjeno(datum);

        int poeni = intervju.odrediBrojPoena(odgovori);
        nadjen.setPoeni(poeni);
    }

    public String getIme() {return ime;}
    public void setIme(String ime) {this.ime = ime;}

    public String getPrezime() {return prezime;}
    public void setPrezime(String prezime) {this.prezime = prezime;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public List<String> getSposobnosti() {return sposobnosti;}
    public void setSposobnosti(List<String> sposobnosti) {this.sposobnosti = sposobnosti;}

    public List<IntervjuisanjeKandidata> getIntervjuisanje() {return intervjuisanje;}
    public void setIntervjuisanje(List<IntervjuisanjeKandidata> intervjuisanje) {this.intervjuisanje = intervjuisanje;}
}
