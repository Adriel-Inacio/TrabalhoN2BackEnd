package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

// CAMADA: Model
// RESPONSABILIDADE: representar um evento cadastrado na tabela eventos (migration V1).
// SITUAÇÃO: implementada.
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, length = 200)
    private String local;

    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Column(name = "capacidade_maxima", nullable = false)
    private Integer capacidadeMaxima;

    protected Evento() { }

    public Evento(String nome, String descricao, String local, LocalDateTime dataHora, Integer capacidadeMaxima) {
        this.nome = nome;
        this.descricao = descricao;
        this.local = local;
        this.dataHora = dataHora;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public String getLocal() { return local; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Integer getCapacidadeMaxima() { return capacidadeMaxima; }
}
