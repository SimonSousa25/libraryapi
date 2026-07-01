package io.github.springbootproject.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor", schema = "public")
@Getter @Setter // annotations que fazem com que os Getters e Setters sejam gerados em tempo de compilação
public class Autor {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;

    @OneToMany(mappedBy = "autor")
    private List<Livro> livros;

//    @Deprecated
//    public Autor() {
//         para uso do framework
//    }
//
//    public Autor(String nome, LocalDate dataNascimento, String nacionalidade) {
//        this.nome = nome;
//        this.dataNascimento = dataNascimento;
//        this.nacionalidade = nacionalidade;
//    }
}
