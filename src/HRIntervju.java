import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HRIntervju extends Intervju {

    private List<String> ocekivaneOsobine = new ArrayList<>();

    public HRIntervju(String intervjuer, int brojPitanja,
                      int minBrojPoenaZaProlaz,
                      LocalDate sprovestiOd,
                      LocalDate aktivdanDo,
                      int brojKruga) {

        super(intervjuer, brojPitanja,
                minBrojPoenaZaProlaz,
                sprovestiOd, aktivdanDo,
                brojKruga);
    }

    @Override
    public int odrediBrojPoena(String odgovori) {

        String[] osobine = odgovori.split(",");
        int poeni = 0;
        for (String o : osobine) {
            if (ocekivaneOsobine.contains(o.trim())) {
                poeni++;
            }
        }
        return poeni;
    }

    public void setOcekivaneOsobine(List<String> osobine) {this.ocekivaneOsobine = osobine;}
    public List<String> getOcekivaneOsobine() {return ocekivaneOsobine;}
}
