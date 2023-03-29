package server.bodyhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoBasicaDto {

    private int id_configuracion;

    @NotEmpty(message = "Se requiere un logo para la empresa")
    private String logo_empresa;

    @NotEmpty(message = "Se requiere un nombre para la empresa")
    private String nombre_empresa;

    private String eslogan;

    @NotEmpty(message = "Se requiere el rut de la empresa")
    private String rut;

    private String url_facebook;

    private String url_whatsapp;

    private String url_tiktok;

    private String url_youtube;

    private String url_twitter;

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
