/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eero Tirkkonen
 */
@Service
public class VoteService {
    
    @Autowired
    private VoteRepository voteRepository;
    
    public Vote initializeVote(Long id) {
        Vote v = null;
        if (voteRepository.findByJokeId(id) == null) {
            v = new Vote(id, 0, 0);
            voteRepository.save(v);
        }
        if (v == null) {
            return null;
        } else {
            return v;
        }
        
    }
    
    public void vote(Long id, String value) {
        Vote vote = this.voteRepository.findByJokeId(id);
        if (vote == null) {
            vote = new Vote(id, 0, 0);
        }
        if ("up".equals(value)) {
            vote.setUpVotes(vote.getUpVotes() + 1);
        } else if ("down".equals(value)) {
            vote.setDownVotes(vote.getDownVotes() + 1);
        }
        voteRepository.save(vote);
        
    }
}
