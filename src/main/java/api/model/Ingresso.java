package api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

// CAMADA: Model
// RESPONSABILIDADE: guardar uma aquisição individual e duas chaves estrangeiras.
// SITUAÇÃO: implementada. Migration V3.
@Entity
@Table(name = "ingressos")
public class Ingresso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "adquirido_em", nullable = false)
    private LocalDateTime adquiridoEm;

    protected Ingresso() { }

    public Ingresso(Evento evento, Cliente cliente, LocalDateTime adquiridoEm) {
        this.evento = evento;
        this.cliente = cliente;
        this.adquiridoEm = adquiridoEm;
    }

    public Long getId() { return id; }
    public Evento getEvento() { return evento; }
    public Cliente getCliente() { return cliente; }
    public LocalDateTime getAdquiridoEm() { return adquiridoEm; }
}
