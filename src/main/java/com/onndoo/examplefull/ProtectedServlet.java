package com.onndoo.examplefull;

import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/protected/dashboard")
@RolesAllowed("admin")
public class ProtectedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("<h1>Área protegida: solo accesible para administradores</h1>");
    }
}