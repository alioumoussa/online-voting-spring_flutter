package com.spring.vote.controller;

import com.spring.vote.model.Vote;
import com.spring.vote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/")
    public List<Vote> getAllVotes() {
        return voteService.getAllVotes();
    }

    @GetMapping("/{id}")
    public Vote getVoteById(@PathVariable Long id) {
        return voteService.getVoteById(id);
    }

    @PostMapping("/")
    public void createVote(@RequestBody Vote vote) {
        voteService.createVote(vote);
    }

    @PutMapping("/{id}")
    public void updateVote(@PathVariable Long id, @RequestBody Vote vote) {
        voteService.updateVote(id, vote);
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
    }
}
