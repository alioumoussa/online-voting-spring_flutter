package com.spring.vote.services;

import com.spring.vote.model.Election;
import com.spring.vote.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ElectionService {

    @Autowired
    private ElectionRepository electionRepository;

    public List<Election> getAllElections() {
        return electionRepository.findAll();
    }

    public Election getElectionById(Long id) {
        return electionRepository.findById(id).orElse(null);
    }

    public void createElection(Election election) {
        electionRepository.save(election);
    }

    public void updateElection(Long id, Election election) {
        if (electionRepository.existsById(id)) {
            election.setId(id);
            electionRepository.save(election);
        }
    }

    public void deleteElection(Long id) {
        electionRepository.deleteById(id);
    }
}
