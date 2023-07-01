package com.fastporte.ServiceService.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class CustomErrorController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping(PATH)
    public String handleError(HttpServletRequest request) {
        // Obtener el código de estado del error
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            // Error 404: Página no encontrada
            return "error404"; // Reemplaza "error404" con el nombre de tu vista de error 404
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            // Error 500: Error interno del servidor
            return "error500"; // Reemplaza "error500" con el nombre de tu vista de error 500
        } else {
            // Otro tipo de error
            return "error"; // Reemplaza "error" con el nombre de tu vista de error genérico
        }
    }

    public String getErrorPath() {
        return PATH;
    }

}
