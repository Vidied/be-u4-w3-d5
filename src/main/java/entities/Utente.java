package entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false, name = "data_di_nascita")
    private LocalDate dataNascita;

    @Column (nullable = false, unique = true, name = "numero_di_tessera")
    private Long numeroTessera;



    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Prestito> ListaPrestiti= new ArrayList<>();



    public Utente(){}

    public Utente(String nome, String cognome, LocalDate dataNascita, Long numeroTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.numeroTessera = numeroTessera;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Long getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(Long numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public List<Prestito> getListaPrestiti() {
        return ListaPrestiti;
    }

    public void setListaPrestiti(List<Prestito> listaPrestiti) {
        ListaPrestiti = listaPrestiti;
    }


    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", numeroTessera=" + numeroTessera +
                '}';
    }
}
