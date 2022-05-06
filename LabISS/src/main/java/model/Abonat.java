package model;

import java.util.Objects;

public class Abonat extends Utilizator{
    private Integer nrImprumuturi;

    public Abonat(Integer id, String nume,String cnp, String adresa, String telefon, String parola, Integer codUser, Integer nrImprumuturi) {
        super(id, nume,cnp, adresa, telefon, parola, codUser);
        this.nrImprumuturi = nrImprumuturi;
    }

    public Abonat() {
    }

    public Abonat(String nume, String cnp, String adresa, String telefon, String parola, Integer codUser, Integer nrImprumuturi) {
        super(nume, cnp, adresa, telefon, parola, codUser);
        this.nrImprumuturi = nrImprumuturi;
    }

    public Integer getNrImprumuturi() {
        return nrImprumuturi;
    }

    public void setNrImprumuturi(Integer nrImprumuturi) {
        this.nrImprumuturi = nrImprumuturi;
    }

    @Override
    public String toString() {
        return "Abonat{" +
                "nrImprumuturi=" + nrImprumuturi +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Abonat)) return false;
        if (!super.equals(o)) return false;
        Abonat abonat = (Abonat) o;
        return Objects.equals(nrImprumuturi, abonat.nrImprumuturi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nrImprumuturi);
    }
}
