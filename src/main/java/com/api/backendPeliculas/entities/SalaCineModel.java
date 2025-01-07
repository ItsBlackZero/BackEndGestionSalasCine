package com.api.backendPeliculas.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salaCine")
public class SalaCineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSalaCine;

    @NotBlank(message = "El nombre de la sala es obligatorio")
    private String nombre;

    @NotNull(message = "El estado de la sala es obligatoria")
    private Integer estado; // 1 = activo, 0 = inactivo

//    @OneToMany(mappedBy = "salaCine", cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonBackReference
//    private List<PeliculaSalaCineModel> peliculaSalaCine = new ArrayList<>();


    public Long getIdSalaCine() {
        return idSalaCine;
    }

    public void setIdSalaCine(Long idSalaCine) {
        this.idSalaCine = idSalaCine;
    }

    public @NotBlank(message = "El nombre de la sala es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre de la sala es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotNull(message = "El estado de la sala es obligatoria") Integer getEstado() {
        return estado;
    }

    public void setEstado(@NotNull(message = "El estado de la sala es obligatoria") Integer estado) {
        this.estado = estado;
    }
}
