package repository;

import javafx.scene.control.Alert;
import model.Carte;
import model.TipStare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

public class CarteRepo {
    private JdbcUtils dbUtils;

    public CarteRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }


    public void add(Carte elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into carti(titlu,autor,cod_exemplar,categorie,stare) values (?,?,?,?,?)")){
            preStmt.setString(1,elem.getTitlu());
            preStmt.setString(2,elem.getAutor());
            preStmt.setInt(3,elem.getCodExemplar());
            preStmt.setString(4,elem.getCategorie());
            preStmt.setString(5,elem.getStare().toString());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
    }


    public void delete(Carte elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from carti where id_carte=?")){
            preStmt.setInt(1,elem.getId());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
    }

    public void update(Carte elem, Integer id) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update carti set data=?, stare=? where id_carte=?")){
            preStmt.setString(1,elem.getData().toString());
            preStmt.setString(2,elem.getStare().toString());
            preStmt.setInt(3,id);
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
    }


    public Carte findById(Integer id) {
        Connection con = dbUtils.getConnection();
        Carte carte = new Carte();
        try(PreparedStatement preStmt = con.prepareStatement("select * from carti where id_carte=?")){
            preStmt.setInt(1,id);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_carte");
                    String aTitlu = result.getString("titlu");
                    String aAutor = result.getString("autor");
                    Integer aCodEx = result.getInt("cod_exemplar");
                    String aCategorie = result.getString("categorie");
                    String aStare = result.getString("stare");
                    LocalDate aData = LocalDate.parse(result.getString("data"));
                    Carte carteFound = new Carte(aTitlu,aAutor,aCodEx,aCategorie,TipStare.parseEnum(aStare),aData);
                    carte = carteFound;
                    carte.setId(id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return carte;
    }

    public Carte findByTitluAutorCod(String titlu, String autor, Integer cod) {
        Connection con = dbUtils.getConnection();
        Carte carte = null;
        try(PreparedStatement preStmt = con.prepareStatement("select * from carti where titlu=? and autor=? and cod_exemplar=?")){
            preStmt.setString(1,titlu);
            preStmt.setString(2,autor);
            preStmt.setInt(3,cod);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_carte");
                    String aTitlu = result.getString("titlu");
                    String aAutor = result.getString("autor");
                    Integer aCodEx = result.getInt("cod_exemplar");
                    String aCategorie = result.getString("categorie");
                    String aStare = result.getString("stare");
                    LocalDate aData;
                    if (result.getString("data") != null)
                    {
                        aData = LocalDate.parse(result.getString("data"));
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie,TipStare.parseEnum(aStare),aData);
                    }
                    else
                    {
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie);
                    }
                    carte.setId(Id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return carte;
    }


    public Iterable<Carte> findAll() {
        Connection con=dbUtils.getConnection();
        List<Carte> carti = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from carti")){
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_carte");
                    String aTitlu = result.getString("titlu");
                    String aAutor = result.getString("autor");
                    Integer aCodEx = result.getInt("cod_exemplar");
                    String aCategorie = result.getString("categorie");
                    String aStare = result.getString("stare");
                    LocalDate aData;
                    Carte carte;
                    if (result.getString("data") != null)
                    {
                        aData = LocalDate.parse(result.getString("data"));
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie,TipStare.parseEnum(aStare),aData);
                    }
                    else
                    {
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie);
                    }
                    carte.setId(Id);
                    carti.add(carte);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return carti;
    }

    public Iterable<Carte> findAllDisponibil() {
        Connection con=dbUtils.getConnection();
        List<Carte> carti = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from carti where stare = ?")){
            String stare = "disponibil";
            preStmt.setString(1,stare);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_carte");
                    String aTitlu = result.getString("titlu");
                    String aAutor = result.getString("autor");
                    Integer aCodEx = result.getInt("cod_exemplar");
                    String aCategorie = result.getString("categorie");
                    String aStare = result.getString("stare");
                    LocalDate aData;
                    Carte carte;
                    if (result.getString("data") != null)
                    {
                        aData = LocalDate.parse(result.getString("data"));
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie,TipStare.parseEnum(aStare),aData);
                    }
                    else
                    {
                        carte = new Carte(aTitlu,aAutor,aCodEx,aCategorie);
                    }
                    carte.setId(Id);
                    carti.add(carte);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return carti;
    }
}
