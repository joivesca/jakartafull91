package com.onndoo.examplefull;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Mostrar el formulario de login
        resp.setContentType("text/html");
        resp.getWriter().write("<html><body>");
        resp.getWriter().write("<h2>Login</h2>");
        resp.getWriter().write("<form action=\"/examplefull-1.0-SNAPSHOT/login\" method=\"post\">");
        resp.getWriter().write("Username: <input type=\"text\" name=\"username\"><br>");
        resp.getWriter().write("Password: <input type=\"password\" name=\"password\"><br>");
        resp.getWriter().write("<input type=\"submit\" value=\"Login\">");
        resp.getWriter().write("</form>");
        resp.getWriter().write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Este servlet se maneja a través del contenedor de servlet de WildFly
        resp.sendRedirect("/examplefull-1.0-SNAPSHOT/protected/dashboard");
    }
}
