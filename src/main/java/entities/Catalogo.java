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

    
}
