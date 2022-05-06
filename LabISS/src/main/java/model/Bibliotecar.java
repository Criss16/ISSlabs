package model;

import java.util.Objects;

public class Bibliotecar extends Utilizator{
    private Integer codAngajat;

    public Bibliotecar(Integer id, String nume, String cnp, String adresa, String telefon, String parola, Integer codUser, Integer codAngajat) {
        super(id, nume, cnp, adresa, telefon, parola, codUser);
        this.codAngajat = codAngajat;
    }

    public Bibliotecar() {
    }

    public Bibliotecar(String nume, String cnp, String adresa, String telefon, String parola, Integer codUser, Integer codAngajat) {
        super(nume, cnp, adresa, telefon, parola, codUser);
        this.codAngajat = codAngajat;
    }

    public Integer getCodAngajat() {
        return codAngajat;
    }

    public void setCodAngajat(Integer codAngajat) {
        this.codAngajat = codAngajat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bibliotecar)) return false;
        if (!super.equals(o)) return false;
        Bibliotecar that = (Bibliotecar) o;
        return Objects.equals(codAngajat, that.codAngajat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codAngajat);
    }

    @Override
    public String toString() {
        return "Bibliotecar{" +
                "codAngajat=" + codAngajat +
                '}';
    }
}
