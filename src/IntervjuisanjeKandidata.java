import java.time.LocalDate;

public class IntervjuisanjeKandidata {

    private int poeni;
    private LocalDate odradjeno;
    private Intervju intervju;
    private Kandidat kandidat;
    private Status status;


    public IntervjuisanjeKandidata(Intervju intervju,Kandidat kandidat) {
        this.kandidat = kandidat;
        this.intervju = intervju;
    }

    public int getPoeni() {return poeni;}
    public void setPoeni(int poeni) {this.poeni = poeni;}

    public LocalDate getOdradjeno() {return odradjeno;}
    public void setOdradjeno(LocalDate odradjeno) {this.odradjeno = odradjeno;}

    public Intervju getIntervju() {return intervju;}
    public void setIntervju(Intervju intervju) {this.intervju = intervju;}

    public Kandidat getKandidat() {return kandidat;}
    public void setKandidat(Kandidat kandidat) {this.kandidat = kandidat;}

    public Status getStatus() {return status;}
    public void setStatus(Status status) {this.status = status;}
}
