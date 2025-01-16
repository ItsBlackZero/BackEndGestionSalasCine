package com.api.backendPeliculas.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "pelicula_salaCine")
public class PeliculaSalaCineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPeliculaSala;

    @ManyToOne
    @JoinColumn(name = "idPelicula", referencedColumnName = "idPelicula")
    @NotNull(message = "La película no puede ser nula")
    private PeliculaModel pelicula;

    @ManyToOne
    @JoinColumn(name = "idSalaCine", referencedColumnName = "idSalaCine")
    @NotNull(message = "La sala de cine no puede ser nula")
    private SalaCineModel salaCine;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaPublicacion;

    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFin;

    public Long getIdPeliculaSala() {
        return idPeliculaSala;
    }

    public void setIdPeliculaSala(Long idPeliculaSala) {
        this.idPeliculaSala = idPeliculaSala;
    }

    public PeliculaModel getPelicula() {
        return pelicula;
    }

    public void setPelicula(PeliculaModel pelicula) {
        this.pelicula = pelicula;
    }

    public SalaCineModel getSalaCine() {
        return salaCine;
    }

    public void setSalaCine(SalaCineModel salaCine) {
        this.salaCine = salaCine;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Override
    public String toString() {
        return "PeliculaSalaCineModel{" +
                "idPeliculaSala=" + idPeliculaSala +
                ", pelicula=" + (pelicula != null ? pelicula.getIdPelicula() : "null") +
                ", salaCine=" + (salaCine != null ? salaCine.getIdSalaCine() : "null") +
                ", fechaPublicacion=" + fechaPublicacion +
                ", fechaFin=" + fechaFin +
                '}';
    }
}
