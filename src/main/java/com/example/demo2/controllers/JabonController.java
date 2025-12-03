package com.example.demo2.controllers;

import com.example.demo2.models.Jabon;
import com.example.demo2.models.Tienda;
import com.example.demo2.services.JabonService;
import com.example.demo2.repository.TiendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jabones")
@CrossOrigin(origins = "https://garyfloo.github.io//")
public class JabonController {

    @Autowired
    private JabonService jabonService;

    @Autowired
    private TiendaRepository tiendaRepository;

    // Obtener todos los jabones
    @GetMapping
    public List<Jabon> getAll() {
        return jabonService.getAll();
    }

    // Obtener un jabón por ID
    @GetMapping("/{id}")
    public Jabon getById(@PathVariable Long id) {
        return jabonService.getById(id);
    }

    // Crear jabón sin tienda
    @PostMapping
    public Jabon create(@RequestBody Jabon jabon) {
        return jabonService.create(jabon);
    }

    // Crear jabón asociado a tienda existente
    @PostMapping("/tienda/{tiendaId}")
    public Jabon createInTienda(@PathVariable Long tiendaId, @RequestBody Jabon jabon) {
        Tienda tienda = tiendaRepository.findById(tiendaId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + tiendaId));

        jabon.setTienda(tienda);
        return jabonService.create(jabon);
    }

    // Obtener jabones por tienda
    @GetMapping("/tienda/{tiendaId}")
    public List<Jabon> getJabonesPorTienda(@PathVariable Long tiendaId) {
        Tienda tienda = tiendaRepository.findById(tiendaId)
                .orElseThrow(() -> new RuntimeException("Tienda no encontrada con id: " + tiendaId));
        return tienda.getJabones();
    }

    // Actualizar jabón
    @PutMapping("/{id}")
    public Jabon update(@PathVariable Long id, @RequestBody Jabon jabon) {
        return jabonService.update(id, jabon);
    }

    // Eliminar jabón
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        jabonService.delete(id);
    }
}




