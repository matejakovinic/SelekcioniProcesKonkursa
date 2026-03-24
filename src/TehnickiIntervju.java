import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TehnickiIntervju extends Intervju{

    protected List<Integer> poeniZaPitanja = new ArrayList<>();

    public TehnickiIntervju(String intervjuer, int brojPitanja, int minBrojPoenaZaProlaz, LocalDate sprovestiOd, LocalDate aktivdanDo, int brojKruga) {
        super(intervjuer, brojPitanja, minBrojPoenaZaProlaz, sprovestiOd, aktivdanDo, brojKruga);
    }

    public void definisiPoene(List<Integer> poeni){
        if(poeni == null || poeni.size() != getBrojPitanja()){
            System.out.println("Nisu definisani poeni za sva pitanja");
            return;
        }
            this.poeniZaPitanja = poeni;
    }

    @Override
    public int odrediBrojPoena(String odgovori){
        String[] delovi = odgovori.split(",");
        int suma = 0;
        for (int i = 0; i < delovi.length; i++) {
            String deo = delovi[i];        // npr "1-TACNO"
            String[] split = deo.split("-");

            if (split.length < 2) continue;

            String odgovor = split[1];

            if (odgovor.equalsIgnoreCase("TACNO")) {
                suma += poeniZaPitanja.get(i);
            }
        }

        return suma;
    }

    public List<Integer> getPoeniZaPitanja() {return poeniZaPitanja;}

    public void setPoeniZaPitanja(List<Integer> poeniZaPitanja) {this.poeniZaPitanja = poeniZaPitanja;}
}
