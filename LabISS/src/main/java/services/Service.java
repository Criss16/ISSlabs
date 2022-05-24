package services;

import model.*;
import observer.Observable;
import observer.Observer;
import repository.AbonatRepo;
import repository.BibliotecarRepo;
import repository.CarteRepo;
import repository.ImprumutRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Service implements Observable {
    private AbonatRepo abonatRepo;
    private BibliotecarRepo bibliotecarRepo;
    private CarteRepo carteRepo;
    private ImprumutRepo imprumutRepo;


    public Service(AbonatRepo abonatRepo, BibliotecarRepo bibliotecarRepo, CarteRepo carteRepo, ImprumutRepo imprumutRepo) {
        this.abonatRepo = abonatRepo;
        this.bibliotecarRepo = bibliotecarRepo;
        this.carteRepo = carteRepo;
        this.imprumutRepo = imprumutRepo;
    }

    public void register(String cnp, String nume, String adresa, String telefon, String parola, Integer codUser,Integer codAng, Integer nrImpr){
        try{
            if (codAng == -1)
            {
                //creez abonat
                Abonat abonat = new Abonat(nume,cnp,adresa,telefon,parola,codUser,0);
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

    public Integer getUserId(Integer userCode, String password)
    {
        return abonatRepo.findByUserPass(userCode, password).getId();
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
            notifyObservers();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteCarte(Integer idCarte){
        try{
            Carte carte = new Carte();
            carte.setId(idCarte);
            carteRepo.delete(carte);
            notifyObservers();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public Iterable<Carte> getAllCartiDisponibile(){
        return carteRepo.findAllDisponibil();
    }

    public Iterable<Carte> getAllCartiImprumutate(Integer abonat){
        List<Carte> carti = new ArrayList<>();
        for (Imprumut i : imprumutRepo.getAllByAbonat(abonat))
        {
            Carte carte = carteRepo.findById(i.getIdCarte());
            if (carte.getStare() == TipStare.imprumutat && i.getDataReturnare() == null)
                carti.add(carte);
        }
        return carti;
    }

    public void addImprumut(String titlu, String autor, Integer cod, Integer abonat) throws Exception
    {
        Carte carte = carteRepo.findByTitluAutorCod(titlu,autor,cod);
        Integer idCarte;
        if (carte == null || carte.getStare() == TipStare.imprumutat)
            throw new Exception("Cartea nu poate fi imprumutata!");
        else
        {
            idCarte = carte.getId();
            Imprumut imprumut = new Imprumut(abonat,idCarte);
            imprumutRepo.add(imprumut);
            carte.setData(LocalDate.now());
            carte.setStare(TipStare.imprumutat);
            carteRepo.update(carte,idCarte);
            notifyObservers();
        }
    }

    public void returnareCarte(String titlu, String autor, Integer cod, Integer abonat) throws Exception {
        Carte carte = carteRepo.findByTitluAutorCod(titlu,autor,cod);
        Integer idCarte;
        if (carte == null || carte.getStare() == TipStare.disponibil)
            throw new Exception("Cartea nu poate fi returnata!");
        else
        {
            idCarte = carte.getId();
            Imprumut returnat = imprumutRepo.getByAbonatCarte(abonat,idCarte);
            returnat.setDataReturnare(LocalDate.now().toString());
            imprumutRepo.update(returnat);
            carte.setData(LocalDate.now());
            carte.setStare(TipStare.disponibil);
            carteRepo.update(carte,idCarte);
            notifyObservers();
        }
    }

    public void prelungireTermen(String titlu, String autor, Integer cod) throws Exception {
        Carte carte = carteRepo.findByTitluAutorCod(titlu,autor,cod);
        Integer idCarte;
        if (carte == null || carte.getStare() == TipStare.disponibil)
            throw new Exception("Cartea nu poate avea termenul prelungit!");
        else
        {
            idCarte = carte.getId();
            carte.setData(carte.getData().plusWeeks(2));
            carteRepo.update(carte,idCarte);
            notifyObservers();
        }
    }


    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observers.size());
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        System.out.println(observers.size());
        observers.forEach(Observer :: update);
    }
}
