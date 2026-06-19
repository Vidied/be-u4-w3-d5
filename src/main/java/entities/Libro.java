package entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Libro")
@PrimaryKeyJoinColumn(name = "id")
public class Libro extends Catalogo{

    @Column(nullable = false)
    private String autore;

    @Column(nullable = false)
    private String genere;



    public Libro(){}

    public Libro(String titolo, Long isbn, LocalDate annoPub, int numeroPagine, String autore, String genere) {
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

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
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
