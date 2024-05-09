package com.spring.vote.services;

import com.spring.vote.model.Vote;
import com.spring.vote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository; 

    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    public Vote getVoteById(Long id) {
        return voteRepository.findById(id).orElse(null);
    }

    public void createVote(Vote vote) {
        voteRepository.save(vote);
    }

    public void updateVote(Long id, Vote vote) {
        if (voteRepository.existsById(id)) {
            vote.setId(id);
            voteRepository.save(vote);
        }
    }

    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }
}
