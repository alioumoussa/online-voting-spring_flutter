package com.spring.vote.services;

import com.spring.vote.helper.ExcelCandidatHelper;
import com.spring.vote.model.Candidat;
import com.spring.vote.model.Electeur;
import com.spring.vote.repository.CandidatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    public List<Candidat> getAllCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat getCandidatById(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }

    public void createCandidat(Candidat candidat) {
        candidatRepository.save(candidat);
    }

    public void updateCandidat(Long id, Candidat candidat) {
        if (candidatRepository.existsById(id)) {
            candidat.setId(id);
            candidatRepository.save(candidat);
        }
    }

    public void deleteCandidat(Long id) {
        candidatRepository.deleteById(id);
    }

    public void save(MultipartFile file) {
    try {
      List<Candidat> candidats = ExcelCandidatHelper.excelToCandidats(file.getInputStream());
      candidatRepository.saveAll(candidats);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
}
