package com.onndoo.examplefull.ws.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/saludo")
public class SaludoResource {

    @GET
    public String getSaludo() {
        return "¡Hola desde JAX-RS!";
    }
}
