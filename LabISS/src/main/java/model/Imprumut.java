package model;



import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "imprumuturi", schema = "public")
public class Imprumut {
    Integer idImprumut;
    Integer idAbonat;
    Integer idCarte;
    String dataReturnare;


    public Imprumut(Integer idAbonat, Integer idCarte, String dataReturnare) {
        this.idAbonat = idAbonat;
        this.idCarte = idCarte;
        this.dataReturnare = dataReturnare;
    }

    public Imprumut(Integer idAbonat, Integer idCarte) {
        this.idAbonat = idAbonat;
        this.idCarte = idCarte;
        this.dataReturnare = null;
    }

    public Imprumut() {
    }

    @Id
    @Column(name = "id_imprumut")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Integer getIdImprumut() {
        return idImprumut;
    }

    public void setIdImprumut(Integer idImprumut) {
        this.idImprumut = idImprumut;
    }

    @Column(name = "id_abonat")
    public Integer getIdAbonat() {
        return idAbonat;
    }

    public void setIdAbonat(Integer idAbonat) {
        this.idAbonat = idAbonat;
    }

    @Column(name = "id_carte")
    public Integer getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Integer idCarte) {
        this.idCarte = idCarte;
    }

    @Column(name = "data_ret")
    public String getDataReturnare() {
          return dataReturnare;
    }

    public void setDataReturnare(String dataReturnare) {
        this.dataReturnare = dataReturnare;
    }

    @Override
    public String toString() {
        return "Imprumut{" +
                "idImprumut=" + idImprumut +
                ", idAbonat=" + idAbonat +
                ", idCarte=" + idCarte +
                ", dataReturnare=" + dataReturnare +
                '}';
    }
}
