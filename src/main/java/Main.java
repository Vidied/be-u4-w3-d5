import dao.CatalogoDao;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");
        EntityManager em = emf.createEntityManager();
        CatalogoDao catalogoDao = new CatalogoDao(em);

        em.getTransaction().begin();

        Libro l1 = new Libro(
                "Cicciobello tutorial",
                1234567891234L,
                LocalDate.of(2000, 10, 10),
                20,
                "Bello di mamma",
                Genere.TUTORIAL
        );
        em.persist(l1);

        Rivista r1 = new Rivista(
                "Rivista isola dei famosi",
                1234567891233L,
                LocalDate.of(2002, 12, 12),
                12,
                Periodicita.MENSILE
        );
        em.persist(r1);

        em.getTransaction().commit();

        System.out.println("Salvataggio in DB con successo!");

        Long isbnDaCercare = 1234567891234L;
        Catalogo elementoTrovato = catalogoDao.findByIsbn(isbnDaCercare);
        System.out.println("Elemento con titolo: " + elementoTrovato.getTitolo() + "è stato trovato!");

        catalogoDao.findAndRemoveByIsbn(isbnDaCercare);

        em.close();
        emf.close();
    }
}
