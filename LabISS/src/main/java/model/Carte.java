package model;

import java.time.LocalDate;
import java.util.Objects;

public class Carte {
    private Integer id;
    private String titlu;
    private String autor;
    private Integer codExemplar;
    private String categorie;
    private TipStare stare;
    private LocalDate data;


    public Carte(Integer id, String titlu, String autor, Integer codExemplar, String categorie, TipStare stare, LocalDate data) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.codExemplar = codExemplar;
        this.categorie = categorie;
        this.stare = stare;
        this.data = data;
    }

    public Carte(String titlu, String autor, Integer codExemplar, String categorie, TipStare stare, LocalDate data) {
        this.titlu = titlu;
        this.autor = autor;
        this.codExemplar = codExemplar;
        this.categorie = categorie;
        this.stare = stare;
        this.data = data;
    }

    public Carte(String titlu, String autor, Integer codExemplar, String categorie) {
        this.titlu = titlu;
        this.autor = autor;
        this.codExemplar = codExemplar;
        this.categorie = categorie;
        this.stare = TipStare.disponibil;
        this.data = null;
    }

    public Carte() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getCodExemplar() {
        return codExemplar;
    }

    public void setCodExemplar(Integer codExemplar) {
        this.codExemplar = codExemplar;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public TipStare getStare() {
        return stare;
    }

    public void setStare(TipStare stare) {
        this.stare = stare;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", autor='" + autor + '\'' +
                ", codExemplar=" + codExemplar +
                ", categorie='" + categorie + '\'' +
                ", stare=" + stare +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carte)) return false;
        Carte carte = (Carte) o;
        return Objects.equals(id, carte.id) && Objects.equals(titlu, carte.titlu) && Objects.equals(autor, carte.autor) && Objects.equals(codExemplar, carte.codExemplar) && Objects.equals(categorie, carte.categorie) && stare == carte.stare && Objects.equals(data, carte.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titlu, autor, codExemplar, categorie, stare, data);
    }
}
