package com.spring.vote.controller;

import com.spring.vote.model.Election;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.spring.vote.services.ElectionService;

@RestController
@RequestMapping("/elections")
public class ElectionController {

    @Autowired
    private ElectionService electionService;

    @GetMapping("/")
    public List<Election> getAllElections() {
        return electionService.getAllElections();
    }

    @GetMapping("/{id}")
    public Election getElectionById(@PathVariable Long id) {
        return electionService.getElectionById(id);
    }

    @PostMapping("/")
    public void createElection(@RequestBody Election election) {
        electionService.createElection(election);
    }

    @PutMapping("/{id}")
    public void updateElection(@PathVariable Long id, @RequestBody Election election) {
        electionService.updateElection(id, election);
    }

    @DeleteMapping("/{id}")
    public void deleteElection(@PathVariable Long id) {
        electionService.deleteElection(id);
    }
}

