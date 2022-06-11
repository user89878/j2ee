package com.ensah.core.bo;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class note {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String prenom;
    private double cne;
    private double note1;
    private double note2;
    private double moyen ;
    private String val;

    public void setVal(String val) {
        this.val = val;
    }

    public void setMoyen(double moyen) {
        this.moyen = moyen;
    }

    public void setNote2(double note2) {
        this.note2 = note2;
    }

    public void setNote1(double note1) {
        this.note1 = note1;
    }

    public void setCne(double cne) {
        this.cne = cne;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public double getMoyen() {
        return moyen;
    }

    public double getNote2() {
        return note2;
    }

    public double getNote1() {
        return note1;
    }

    public double getCne() {
        return cne;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public long getId() {
        return id;
    }
    public note(long id,String nom,String prenom,double cne,double note1,double note2,double moyen,String val) {
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.cne=cne;
        this.note1=note1;
        this.note2=note2;
        this.moyen=moyen;
        this.val=val;
    }
    public note(){
        super();
    }
}
