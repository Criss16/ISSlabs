package repository;

import model.Abonat;
import model.Bibliotecar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class BibliotecarRepo {
    private JdbcUtils dbUtils;

    public BibliotecarRepo(Properties props) {
        dbUtils=new JdbcUtils(props);
    }

    public void add(Bibliotecar elem) {
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into bibliotecari(nume,cnp,adresa,telefon,cod_user,parola,codAngajat) values (?,?,?,?,?,?,?)")){
            preStmt.setString(1,elem.getNume());
            preStmt.setString(2,elem.getCnp());
            preStmt.setString(3,elem.getAdresa());
            preStmt.setString(4,elem.getTelefon());
            preStmt.setInt(5,elem.getCodUser());
            preStmt.setString(6,elem.getParola());
            preStmt.setInt(7,elem.getCodAngajat());
            int result=preStmt.executeUpdate();
        }catch (SQLException ex){
            System.err.println("Error DB "+ex);
        }
    }

    /*public Bibliotecar findById(Integer id) {
        Connection con = dbUtils.getConnection();
        Bibliotecar bibliotecar = new Bibliotecar();
        try(PreparedStatement preStmt = con.prepareStatement("select * from bibliotecari where id_bibliotecar=?")){
            preStmt.setLong(1,id);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_bibliotecar");
                    String aName = result.getString("nume");
                    String aCnp = result.getString("cnp");
                    String aAdresa = result.getString("adresa");
                    String aTelefon = result.getString("telefon");
                    Integer aCodUser = result.getInt("cod_user");
                    String aParola = result.getString("parola");
                    Integer aCodAng = result.getInt("codAngajat");
                    Bibliotecar bibliotecarFound = new Bibliotecar(aName,aCnp,aAdresa,aTelefon,aParola,aCodUser,aCodAng);
                    bibliotecar = bibliotecarFound;
                    bibliotecar.setId(id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return bibliotecar;
    }*/

    public Bibliotecar findByUserPass(Integer cod,String pass) {
        Connection con = dbUtils.getConnection();
        Bibliotecar bibliotecar = null;
        try(PreparedStatement preStmt = con.prepareStatement("select * from bibliotecari where cod_user=? and parola=?")){
            preStmt.setInt(1,cod);
            preStmt.setString(2,pass);
            try(ResultSet result=preStmt.executeQuery()){
                while(result.next()){
                    Integer Id = result.getInt("id_bibliotecar");
                    String aName = result.getString("nume");
                    String aCnp = result.getString("cnp");
                    String aAdresa = result.getString("adresa");
                    String aTelefon = result.getString("telefon");
                    Integer aCodUser = result.getInt("cod_user");
                    String aParola = result.getString("parola");
                    Integer aCodAng = result.getInt("codAngajat");
                    Bibliotecar bibliotecarFound = new Bibliotecar(aName,aCnp,aAdresa,aTelefon,aParola,aCodUser,aCodAng);
                    bibliotecar = bibliotecarFound;
                    bibliotecar.setId(Id);
                }
            }
        }catch (SQLException e){
            System.err.println("Error DB "+e);
        }
        return bibliotecar;
    }
}
