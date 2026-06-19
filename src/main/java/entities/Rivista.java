package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "riviste")
@PrimaryKeyJoinColumn(name = "id")
public class Rivista extends Catalogo{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Periodicita periodicita;

    public Rivista(){}

    public Rivista(String titolo, Long isbn, LocalDate annoPub, int numeroPagine, Periodicita periodicita) {
        super(titolo, isbn, annoPub, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "id=" + getId() +
                ", titolo: " + getTitolo() +
                ", isbn: " + getIsbn() +
                ", anno pubblicazione: " + getAnnoPub() +
                ", numero pagine: " + getNumeroPagine() +
                ", periodicita: " + periodicita +
                '}';
    }
}
