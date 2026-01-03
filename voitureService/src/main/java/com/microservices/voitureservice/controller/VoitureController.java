package com.microservices.voitureservice.controller;

import com.microservices.voitureservice.entity.Voiture;
import com.microservices.voitureservice.feign.ClientServiceClient;
import com.microservices.voitureservice.model.Client;
import com.microservices.voitureservice.repository.VoitureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voitures")
public class VoitureController {

    @Autowired
    private VoitureRepository voitureRepository;

    @Autowired
    private ClientServiceClient clientServiceClient;

    @GetMapping
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @GetMapping("/{id}")
    public Voiture getVoitureById(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture not found with id: " + id));
    }

    @GetMapping("/client/{id_client}")
    public List<Voiture> getVoituresByClientId(@PathVariable Long id_client) {
        return voitureRepository.findById_client(id_client);
    }

    @GetMapping("/{id}/client")
    public Client getClientByVoitureId(@PathVariable Long id) {
        Voiture voiture = voitureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voiture not found with id: " + id));
        return clientServiceClient.getClientById(voiture.getId_client());
    }

    @PostMapping
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    @DeleteMapping("/{id}")
    public void deleteVoiture(@PathVariable Long id) {
        voitureRepository.deleteById(id);
    }
}

