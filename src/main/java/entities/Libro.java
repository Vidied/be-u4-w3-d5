package entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "libri")
@PrimaryKeyJoinColumn(name = "id")
public class Libro extends Catalogo{

    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genere genere;



    public Libro(){}

    public Libro(String titolo, Long isbn, LocalDate annoPub, int numeroPagine, String autore, Genere genere) {
        super(titolo, isbn, annoPub, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }



    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + getId() +
                ", titolo: " + getTitolo() +
                ", isbn: " + getIsbn() +
                ", anno pubblicazione: " + getAnnoPub() +
                ", numero pagine: " + getNumeroPagine() +
                ", autore: " + autore +
                ", genere: " + genere +
                '}';
    }
}
