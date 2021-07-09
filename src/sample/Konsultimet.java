package sample;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Konsultimet {
    private String profesori;
    private String lenda;
    private String studenti;
    private Timestamp data;




    public Konsultimet(String profesori, String lenda, String studenti, Timestamp data){
        this.profesori = profesori;
        this.lenda = lenda;
        this.studenti = studenti;
        this.data = data;
    }

    public Konsultimet(){
        this.profesori = "";
        this.lenda = "";
        this.studenti = "";
        this.data = new Timestamp(new Date().getTime());
    }

    public void setProfesori(String profesori) {
        this.profesori = profesori;
    }

    public String getProfesori() {
        return profesori;
    }

    public void setStudenti(String studenti) {
        this.studenti = studenti;
    }

    public String getStudenti() {
        return studenti;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Timestamp getData() {
        return data;
    }

    public void setLenda(String lenda) {
        this.lenda = lenda;
    }

    public String getLenda() {
        return lenda;
    }
}
