import dao.CatalogoDao;
import dao.PrestitoDao;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");
        EntityManager em = emf.createEntityManager();
        CatalogoDao catalogoDao = new CatalogoDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);

        em.getTransaction().begin();

        //Creazione dei libbri
        Libro l1 = new Libro(
                "Cicciobello tutorial",
                1234567891234L,
                LocalDate.of(2000, 10, 10),
                20,
                "Bello di mamma",
                Genere.TUTORIAL
        );

        Libro l2 = new Libro(
                "Cicciobello tutorial: Adolescenza",
                1234567894321L,
                LocalDate.of(2000, 11, 10),
                30,
                "Bello di mamma",
                Genere.TUTORIAL
        );

        Libro l3 = new Libro(
                "Cicciobello tutorial: Pubertà",
                1234567894322L,
                LocalDate.of(2000, 12, 10),
                40,
                "Bello di mamma",
                Genere.TUTORIAL
        );

        em.persist(l1);
        em.persist(l2);
        em.persist(l3);


        //Creazione delle riviste
        Rivista r1 = new Rivista(
                "Rivista isola dei famosi",
                1234567891233L,
                LocalDate.of(2000, 12, 12),
                12,
                Periodicita.MENSILE
        );

        Rivista r2 = new Rivista(
                "Rivista isola dei famosi: Il ritorno",
                1234567891333L,
                LocalDate.of(2003, 12, 12),
                12,
                Periodicita.MENSILE
        );

        Rivista r3 = new Rivista(
                "Rivista isola dei famosi: Il ritorno parte 2",
                1234567893333L,
                LocalDate.of(2004, 12, 12),
                12,
                Periodicita.MENSILE
        );

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);

        //Creazione utente
        Utente davide = new Utente("Davide", "Pan", LocalDate.of(2001, 10, 19), 1234L);
        prestitoDao.saveUtente(davide);

        em.getTransaction().commit();

        System.out.println("Salvataggio in DB con successo!");

        //Test ricerca
        Long isbnDaCercare = 1234567891234L;
        Catalogo elementoTrovato = catalogoDao.findByIsbn(isbnDaCercare);
        System.out.println("Elemento con titolo: " + elementoTrovato.getTitolo() + "è stato trovato!");

        //Test rimozione
        catalogoDao.findAndRemoveByIsbn(isbnDaCercare);

        //Test ricerca per anno
        List<Catalogo> perAnno = catalogoDao.findByAnno(2000);
        System.out.println("Elementi trovati per anno 2000: " + perAnno.size());
        for (Catalogo c : perAnno){
            System.out.println(c.getTitolo());
        }

        //Test ricerca per autore
        List<Catalogo> perAutore = catalogoDao.findByAutore("Bello di mamma");
        System.out.println("Elementi trovati per autore Bello di mamma: " + perAutore.size());
        for (Catalogo c : perAutore){
            System.out.println(c.getTitolo());
        }

        //Test ricerca per titolo
        List<Catalogo> perTitolo = catalogoDao.findByTitolo("Famosi");
        System.out.println("Elementi trovati per titolo Famosi: " + perTitolo.size());
        for (Catalogo c : perTitolo){
            System.out.println(c.getTitolo());
        }



        //Test salvataggio prestito
        Prestito prestitoAttivo = new Prestito(davide, l2, LocalDate.now(), null);
        prestitoDao.salvaPrestito(prestitoAttivo);

        //Test salvataggio prestito scaduto
        //Minus per simulare un prestito vecchio che in automatico darà scaduto
        LocalDate vecchio = LocalDate.now().minusDays(40);
        Prestito prestitoScaduto = new Prestito(davide, l3, vecchio, null);
        prestitoDao.salvaPrestito(prestitoScaduto);

        //Test ricerca prestiti attivi per tessera
        List<Prestito> attivi = prestitoDao.findPrestitiAttivi(1234L);
        System.out.println("Prestiti totali attivi per Davide: " + attivi.size());
        for (Prestito p : attivi) {
            System.out.println("- " + p.getElementoPrestato().getTitolo() + " (Scade il: " + p.getDataRestituzionePrevista() + ")");
        }

        //Test ricerca prestiti scaduti per tessera
        List<Prestito> scaduti = prestitoDao.findPrestitiScaduti();
        System.out.println("Prestiti attualmente scaduti nel sistema: " + scaduti.size());
        for (Prestito p : scaduti) {
            System.out.println("- " + p.getElementoPrestato().getTitolo() + " preso da " + p.getUtente().getNome() + " (Scaduto il: " + p.getDataRestituzionePrevista() + ")");
        }

        em.close();
        emf.close();
    }
}
