package dao;

import entities.Catalogo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class CatalogoDao {

    private final EntityManager em;

    public CatalogoDao(EntityManager em){
        this.em = em;
    }



    public void save(Catalogo e) {
        EntityTransaction t = em.getTransaction();


        t.begin();


        em.persist(e);


        t.commit();
        System.out.println("Elemento: " + e.getTitolo() + " con ISBN: " + e.getIsbn() +" è stato salvato con successo nel DB");
    }

    public Catalogo findByIsbn(Long isbn){
        return em.createQuery("SELECT c FROM Catalogo c WHERE c.isbn = :isbn", Catalogo.class)
                .setParameter("isbn", isbn)
                .getSingleResult();
    }

    public void findAndRemoveByIsbn(Long isbn){
        Catalogo trovato = findByIsbn(isbn);

        if(trovato !=null) {
            EntityTransaction t = em.getTransaction();

            t.begin();

            em.remove(trovato);

            t.commit();
            System.out.println("Elemento con ISBN: " + isbn + " è stato rimosso con successo dal DB");
        } else {
            System.out.println("Nessun elemento con ISBN: " + isbn + " è stato trovato!");
        }
    }

    public List<Catalogo> findByAnno (int anno) {
        return em.createQuery("SELECT c FROM Catalogo c WHERE EXTRACT(YEAR FROM c.annoPub) = :anno", Catalogo.class)
                .setParameter("anno", anno)
                .getResultList();
    }
    
    public List<Catalogo> findByTitolo(String titolo){
        //LOWER per ovviamente rendere il testo case insensitive rendendo tutto minuscolo
        return em.createQuery("SELECT c FROM Catalogo c WHERE LOWER(c.titolo) LIKE :titolo", Catalogo.class)
                .setParameter("titolo","%" + titolo + "%")
                .getResultList();
    }
    //Siccome autore è presente solo in libro cerco direttamente in libro
    public List<Catalogo> findByAutore(String autore){
        return em.createQuery("SELECT c FROM Libro c WHERE c.autore = :autore", Catalogo.class)
                .setParameter("autore", autore)
                .getResultList();
    }
}
