package com.gruptd.medicPet.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * Una implementació personalitzada de la interfície AccessDeniedHandler de Spring Security, que redirigeix l'usuari a una pàgina d'error 403.
 *
 * @author pablomorante
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    /**
     * Gestiona un error d'accés denegat redirigint l'usuari a una pàgina d'error 403.
     *
     * @param request la sol·licitud HTTP que provoca l'error d'accés denegat
     * @param response la resposta HTTP que s'enviarà a l'usuari
     * @param accessDeniedException l'excepció que provoca l'error d'accés denegat
     * @throws IOException si es produeix un error d'I/O mentre es gestiona la sol·licitud o la resposta
     * @throws ServletException si es produeix un error de servlet mentre es gestiona la sol·licitud o resposta
     * 
     * @author pablomorante
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.sendRedirect("/error/error403");
    }
}
