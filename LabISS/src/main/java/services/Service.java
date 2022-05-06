package services;

import model.*;
import repository.AbonatRepo;
import repository.BibliotecarRepo;
import repository.CarteRepo;

import javax.swing.*;
import java.time.LocalDate;

public class Service {
    private AbonatRepo abonatRepo;
    private BibliotecarRepo bibliotecarRepo;
    private CarteRepo carteRepo;


    public Service(AbonatRepo abonatRepo, BibliotecarRepo bibliotecarRepo, CarteRepo carteRepo) {
        this.abonatRepo = abonatRepo;
        this.bibliotecarRepo = bibliotecarRepo;
        this.carteRepo = carteRepo;
    }

    public void register(String cnp, String nume, String adresa, String telefon, String parola, Integer codUser,Integer codAng, Integer nrImpr){
        try{
            if (codAng == -1)
            {
                //creez abonat
                Abonat abonat = new Abonat(cnp,nume,adresa,telefon,parola,codUser,0);
                if (abonatRepo.findByUserPass(codUser,parola) != null)
                    throw new Exception("Username deja existent!");
                abonatRepo.add(abonat);
            }
            else
            if (nrImpr == -1)
            {
                //creez bibliotecar
                Bibliotecar bibliotecar = new Bibliotecar(nume,cnp,adresa,telefon,parola,codUser,codAng);
                if (bibliotecarRepo.findByUserPass(codUser,parola) != null)
                    throw new Exception("Username deja existent!");
                bibliotecarRepo.add(bibliotecar);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public String login(Utilizator user) throws Exception {
        Bibliotecar b = bibliotecarRepo.findByUserPass(user.getCodUser(),user.getParola());
        if (b == null)
        {
            Abonat a = abonatRepo.findByUserPass(user.getCodUser(), user.getParola());
            if (a == null)
                throw new Exception("User inexistent!");
            else
                return "abonat";
        }
        else
            return "bibliotecar";
    }

    public Iterable<Carte> getAllCarti(){
        return carteRepo.findAll();
    }


    public void addCarte(String titlu, String autor, Integer codEx, String categorie){
        Carte carte = new Carte(titlu,autor,codEx,categorie,TipStare.disponibil,null);
        try{
            carteRepo.add(carte);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCarte(Integer idCarte){
        try{
            Carte carte = new Carte();
            carte.setId(idCarte);
            carteRepo.delete(carte);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
