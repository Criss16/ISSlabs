package repository;

import model.Abonat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class AbonatRepo {
    private JdbcUtils dbUtils;

    public AbonatRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    public void add(Abonat elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into abonati(nume,cnp,adresa,telefon,cod_user,parola,nr_imprumuturi) values (?,?,?,?,?,?,?)")){
            preStmt.setString(1,elem.getNume());
            preStmt.setString(2,elem.getCnp());
            preStmt.setString(3,elem.getAdresa());
            preStmt.setString(4,elem.getTelefon());
            preStmt.setInt(5,elem.getCodUser());
            preStmt.setString(6,elem.getParola());
            preStmt.setInt(7,elem.getNrImprumuturi());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
    }

    public Abonat findById(Integer id) {
        Connection con = dbUtils.getConnection();
        Abonat abonat = new Abonat();
        try(PreparedStatement preStmt = con.prepareStatement("select * from abonati where id_abonat=?")){
            preStmt.setInt(1,id);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_abonat");
                    String aName = result.getString("nume");
                    String aCnp = result.getString("cnp");
                    String aAdresa = result.getString("adresa");
                    String aTelefon = result.getString("telefon");
                    Integer aCodUser = result.getInt("cod_user");
                    String aParola = result.getString("parola");
                    Integer aNrImprumuturi = result.getInt("nr_imprumuturi");
                    Abonat abonatFound = new Abonat(aName,aCnp,aAdresa,aTelefon,aParola,aCodUser,aNrImprumuturi);
                    abonat = abonatFound;
                    abonat.setId(id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return abonat;
    }

    public Abonat findByUserPass(Integer cod,String pass) {
        Connection con = dbUtils.getConnection();
        Abonat abonat = null;
        try(PreparedStatement preStmt = con.prepareStatement("select * from abonati where cod_user=? and parola=?")){
            preStmt.setInt(1,cod);
            preStmt.setString(2,pass);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_abonat");
                    String aName = result.getString("nume");
                    String aCnp = result.getString("cnp");
                    String aAdresa = result.getString("adresa");
                    String aTelefon = result.getString("telefon");
                    Integer aCodUser = result.getInt("cod_user");
                    String aParola = result.getString("parola");
                    Integer aNrImprumuturi = result.getInt("nr_imprumuturi");
                    Abonat abonatFound = new Abonat(aName,aCnp,aAdresa,aTelefon,aParola,aCodUser,aNrImprumuturi);
                    abonat = abonatFound;
                    abonat.setId(Id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return abonat;
    }
}
