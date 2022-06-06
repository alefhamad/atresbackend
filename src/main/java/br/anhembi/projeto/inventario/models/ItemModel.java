package br.anhembi.projeto.inventario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "items")
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @Column(name = "date", length = 50, nullable = false)
    private String date;

    @Column(name = "quantitiy", length = 50, nullable = false)
    private long quantitiy;

    @Column(name = "is_assigned", length = 50, nullable = false)
    private boolean isAssigned;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id", unique = true, nullable = true)
    @JsonIgnoreProperties("items")
    private PacienteModel paciente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getQuantitiy() {
        return quantitiy;
    }

    public void setQuantitiy(long quantitiy) {
        this.quantitiy = quantitiy;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel paciente) {
        this.paciente = paciente;
    }
    
}
