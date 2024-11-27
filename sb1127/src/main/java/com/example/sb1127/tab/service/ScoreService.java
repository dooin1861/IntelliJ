package com.example.sb1127.tab.service;

import com.example.sb1127.tab.model.Score;
import com.example.sb1127.tab.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score saveScore(Score score) {
        return scoreRepository.save(score);
    }

    public Score findById(Long id) {
        return scoreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Score not found"));
    }

    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }

    public List<Score> findAllScores() {
        return scoreRepository.findAll();
    }
}