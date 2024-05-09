package com.spring.vote.controller;

import com.spring.vote.model.Candidat;
import com.spring.vote.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    @GetMapping("/")
    public List<Candidat> getAllCandidats() {
        System.out.println("get all candidates");
        return candidatService.getAllCandidats();
    }

    @GetMapping("/{id}")
    public Candidat getCandidatById(@PathVariable Long id) {
        return candidatService.getCandidatById(id);
    }

    @PostMapping("/")
    public void createCandidat(@RequestBody Candidat candidat) {
        candidatService.createCandidat(candidat);
    }

    @PutMapping("/{id}")
    public void updateCandidat(@PathVariable Long id, @RequestBody Candidat candidat) {
        candidatService.updateCandidat(id, candidat);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidat(@PathVariable Long id) {
        candidatService.deleteCandidat(id);
    }
}
