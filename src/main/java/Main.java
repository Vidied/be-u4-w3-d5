import entities.Genere;
import entities.Libro;
import entities.Periodicita;
import entities.Rivista;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4w3d5");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Libro l1 = new Libro(
                "Cicciobello tutorial",
                123123123L,
                LocalDate.of(2000, 10, 10),
                20,
                "Bello di mamma",
                Genere.TUTORIAL
        );
        em.persist(l1);

        Rivista r1 = new Rivista(
                "Rivista isola dei famosi",
                123123122L,
                LocalDate.of(2002, 12, 12),
                12,
                Periodicita.MENSILE
        );
        em.persist(r1);

        em.getTransaction().commit();

        System.out.println("Salvataggio in DB con successo!");

        em.close();
        emf.close();
    }
}
