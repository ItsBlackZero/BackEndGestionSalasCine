package com.api.backendPeliculas.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pelicula")

public class PeliculaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPelicula;

    @NotBlank(message = "El nombre de la pelicula es obligatorio")
    private String nombre;

    @NotNull(message = "La duracion de la pelicula es obligatoria")
    private Double duracion;

    @Column(nullable = false)
    private int estado = 1; // 1 = activo, 0 = inactivo

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Long getIdPelicula() {
        return idPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        this.idPelicula = idPelicula;
    }

    public @NotBlank(message = "El nombre de la pelicula es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre de la pelicula es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "La duracion de la pelicula es obligatoria") Double getDuracion() {
        return duracion;
    }

    public void setDuracion(@NotNull(message = "La duracion de la pelicula es obligatoria") Double duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "PeliculaModel{" +
                "idPelicula=" + idPelicula +
                ", nombre='" + nombre + '\'' +
                ", duracion=" + duracion +
                ", estado=" + estado +
                '}';
    }
}
