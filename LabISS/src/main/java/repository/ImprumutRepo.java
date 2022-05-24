package repository;

import model.Imprumut;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class ImprumutRepo {
    private SessionFactory sessionFactory;


    public ImprumutRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Iterable<Imprumut> getAllByAbonat(Integer abonat)
    {
        Iterable<Imprumut> imprumuturi = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                imprumuturi = session.createQuery("from Imprumut where id_abonat="+abonat, Imprumut.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        return imprumuturi;
    }

    public void add (Imprumut elem){
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                session.save(elem);
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la inserare "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
    }

    public Imprumut getByAbonatCarte(Integer abonat, Integer carte){
        Iterable<Imprumut> imprumuturi = null;
        Imprumut imprumut = null;
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                imprumuturi = session.createQuery("from Imprumut where id_abonat="+abonat +" and id_carte="+carte, Imprumut.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                System.err.println("Eroare la select "+ex);
                if (tx != null)
                    tx.rollback();
            }
        }
        for (Imprumut i : imprumuturi)
        {
            if (i.getDataReturnare() == null)
                imprumut = i;
        }
        return imprumut;
    }

    public void update(Imprumut elem){
        try(Session session = sessionFactory.openSession()){
            Transaction tx=null;
            try{
                tx = session.beginTransaction();
                session.update(elem);
                tx.commit();

            } catch(RuntimeException ex){
                System.err.println("Eroare la update "+ex);
                if (tx!=null)
                    tx.rollback();
            }
        }
    }
}
