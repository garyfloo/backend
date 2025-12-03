package com.example.demo2.controllers;

import com.example.demo2.models.Tienda;
import com.example.demo2.services.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiendas")
@CrossOrigin(
    // CORRECCIÓN 1: Origen corregido a la URL base del frontend en GitHub Pages
    origins = {"https://garyfloo.github.io"},
    // CORRECCIÓN 2: Especificar los métodos permitidos (incluyendo OPTIONS para preflight)
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // Obtener todas las tiendas
    @GetMapping
    public List<Tienda> getAll() {
        return tiendaService.getAll();
    }

    // Crear una nueva tienda
    @PostMapping
    public Tienda create(@RequestBody Tienda tienda) {
        return tiendaService.create(tienda);
    }

    // Obtener una tienda por ID
    @GetMapping("/{id}")
    public Tienda getById(@PathVariable Long id) {
        return tiendaService.getById(id);
    }

    // Eliminar una tienda
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        tiendaService.delete(id);
    }
}







