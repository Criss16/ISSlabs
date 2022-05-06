package model;

import java.util.Objects;

public class Utilizator {
    private Integer id;
    private String nume;
    private String cnp;
    private String adresa;
    private String telefon;
    private String parola;
    private Integer codUser;


    public Utilizator(Integer id, String nume,String cnp, String adresa, String telefon, String parola, Integer codUser) {
        this.id = id;
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.telefon = telefon;
        this.parola = parola;
        this.codUser = codUser;
    }

    public Utilizator() {
    }

    public Utilizator(String nume,String cnp, String adresa, String telefon, String parola, Integer codUser) {
        this.nume = nume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.telefon = telefon;
        this.parola = parola;
        this.codUser = codUser;
    }

    public Utilizator(String parola, Integer codUser) {
        this.parola = parola;
        this.codUser = codUser;
    }

    public Integer getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getParola() {
        return parola;
    }

    public Integer getCodUser() {
        return codUser;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public void setCodUser(Integer codUser) {
        this.codUser = codUser;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cnp='" + cnp + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", parola='" + parola + '\'' +
                ", codUser=" + codUser +
                '}';
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utilizator)) return false;
        Utilizator that = (Utilizator) o;
        return Objects.equals(id, that.id) && Objects.equals(nume, that.nume) && Objects.equals(cnp, that.cnp) && Objects.equals(adresa, that.adresa) && Objects.equals(telefon, that.telefon) && Objects.equals(parola, that.parola) && Objects.equals(codUser, that.codUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nume, cnp, adresa, telefon, parola, codUser);
    }
}
