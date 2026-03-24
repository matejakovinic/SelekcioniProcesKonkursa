import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Intervju {

    private String intervjuer;
    private int brojPitanja;
    private int minBrojPoenaZaProlaz;
    private LocalDate sprovestiOd;
    private LocalDate aktivdanDo;
    private int brojKruga;
    private Konkurs konkurs;
    private List<IntervjuisanjeKandidata> intervjuisanjeKandidata = new ArrayList<>();

    public Intervju(String intervjuer,int brojPitanja,int minBrojPoenaZaProlaz,LocalDate sprovestiOd,LocalDate aktivdanDo,int brojKruga) {
        this.brojKruga = brojKruga;
        this.aktivdanDo = aktivdanDo;
        this.sprovestiOd = sprovestiOd;
        this.minBrojPoenaZaProlaz = minBrojPoenaZaProlaz;
        this.brojPitanja = brojPitanja;
        this.intervjuer = intervjuer;
        this.konkurs = konkurs;
    }

    public abstract int odrediBrojPoena(String odgovori);

    public List<Kandidat> odaberiKandidataZaProlaz(int ogranicenje) {
        List<IntervjuisanjeKandidata> prosli = new ArrayList<>();
        // 1. filtriranje
        for (IntervjuisanjeKandidata ik : intervjuisanjeKandidata) {
            if (ik.getOdradjeno() != null &&
                    ik.getPoeni() >= this.getMinBrojPoenaZaProlaz()) {

                prosli.add(ik);
            }
        }
        // 2. sortiranje
        prosli.sort((a, b) -> {
            // prvo po poenima (opadajuce)
            if (b.getPoeni() != a.getPoeni()) {
                return Integer.compare(b.getPoeni(), a.getPoeni());
            }
            // ako su isti poeni → raniji datum ima prednost
            return a.getOdradjeno().compareTo(b.getOdradjeno());
        });

        // 3. uzmi prvih N
        List<Kandidat> rezultat = new ArrayList<>();
        for (int i = 0; i < prosli.size(); i++) {
            IntervjuisanjeKandidata ik = prosli.get(i);
            if (i < ogranicenje) {
                ik.setStatus(Status.PROSAO);
                rezultat.add(ik.getKandidat());
            } else {
                ik.setStatus(Status.NIJE_PROSAO);
            }
        }

        return rezultat;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Intervju intervju = (Intervju) o;
        return brojKruga == intervju.brojKruga && Objects.equals(konkurs, intervju.konkurs); }

    @Override
    public int hashCode() {
        return Objects.hash(brojKruga,konkurs);
    }

    public String getIntervjuer() {return intervjuer;};
    public void setIntervjuer(String intervjuer) {this.intervjuer = intervjuer;}

    public int getBrojPitanja() {return brojPitanja;}
    public void setBrojPitanja(int brojPitanja) {this.brojPitanja = brojPitanja;}

    public int getMinBrojPoenaZaProlaz() {return minBrojPoenaZaProlaz;}
    public void setMinBrojPoenaZaProlaz(int minBrojPoenaZaProlaz) {this.minBrojPoenaZaProlaz = minBrojPoenaZaProlaz;}

    public LocalDate getSprovestiOd() {return sprovestiOd;}
    public void setSprovestiOd(LocalDate sprovestiOd) {this.sprovestiOd = sprovestiOd;}

    public LocalDate getAktivdanDo() {return aktivdanDo;}
    public void setAktivdanDo(LocalDate aktivdanDo) {this.aktivdanDo = aktivdanDo;}

    public int getBrojKruga() {return brojKruga;}
    public void setBrojKruga(int brojKruga) {this.brojKruga = brojKruga;}

    public Konkurs getKonkurs() {return konkurs;}
    public void setKonkurs(Konkurs konkurs) {this.konkurs = konkurs;}

    public List<IntervjuisanjeKandidata> getIntervjuisanjeKandidata() {return intervjuisanjeKandidata;}
    public void setIntervjuisanjeKandidata(List<IntervjuisanjeKandidata> intervjuisanjeKandidata) {this.intervjuisanjeKandidata = intervjuisanjeKandidata;}
}
