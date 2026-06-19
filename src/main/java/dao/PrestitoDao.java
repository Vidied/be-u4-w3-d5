package dao;

import entities.Prestito;
import entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {

    private final EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void saveUtente(Utente utente){

        EntityTransaction t = em.getTransaction();

        t.begin();

        em.persist(utente);

        t.commit();
        System.out.println("Utente: " + utente.getNome() + " " + utente.getCognome() + " è stato aggiunto con successo!");
    }

    public void salvaPrestito (Prestito prestito){

        EntityTransaction t = em.getTransaction();

        t.begin();

        em.persist(prestito);

        t.commit();
        System.out.println("Prestito per: " + prestito.getElementoPrestato().getTitolo() + " registrato con successo!");
    }

    public List<Prestito> findPrestitiAttivi (Long nT) {
        return em.createQuery("SELECT c FROM Prestito c WHERE c.utente.numeroTessera = :numT AND c.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("numT", nT)
                .getResultList();
    }

    public List<Prestito> findPrestitiScaduti() {
        return em.createQuery("SELECT c FROM Prestito c WHERE c.dataRestituzionePrevista < :oggi AND c.dataRestituzioneEffettiva IS NULL", Prestito.class)
                .setParameter("oggi", LocalDate.now())
                .getResultList();
    }
}
