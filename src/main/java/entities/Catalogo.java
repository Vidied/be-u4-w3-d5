package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class  Catalogo {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false, name = "codice_isbn", unique = true)
    private Long isbn;

    @Column(nullable = false, name = "anno_pubblicazione")
    private LocalDate annoPub;

    @Column(nullable = false,  name = "numero_pagine")
    private int numeroPagine;




    public Catalogo (){}

    public Catalogo(String titolo, Long isbn, LocalDate annoPub, int numeroPagine) {
        this.titolo = titolo;
        this.isbn = isbn;
        this.annoPub = annoPub;
        this.numeroPagine = numeroPagine;
    }




    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public LocalDate getAnnoPub() {
        return annoPub;
    }

    public void setAnnoPub(LocalDate annoPub) {
        this.annoPub = annoPub;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    
}
