package server.bodyhealth.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "info_basica")
public class InfoBasica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_configuracion;

    @NotEmpty(message = "Se requiere un logo para la empresa")
    private String logo_empresa;

    @NotEmpty(message = "Se requiere un nombre para la empresa")
    private String nombre_empresa;

    private String eslogan;

    @NotEmpty(message = "Se requiere el rut de la empresa")
    private String rut;

    @Column(length = 700)
    private String url_facebook;

    @Column(length = 700)
    private String url_whatsapp;

    @Column(length = 700)
    private String url_tiktok;

    @Column(length = 700)
    private String url_youtube;

    @Column(length = 700)
    private String url_twitter;

    @Column(length = 700)
    private String url_instagram;

    @NotEmpty(message = "Se requiere el email de la empresa")
    private String email;

    @NotEmpty(message = "Se requiere una direccion para la empresa")
    private String direccion;

    @NotEmpty(message = "Se requiere un telefono para la empresa")
    private String telefono;

    @NotEmpty(message = "Se requiere un pie de pagina para la pagina")
    private String pie_pagina;



}
