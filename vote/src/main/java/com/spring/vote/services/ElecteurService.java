package com.spring.vote.services;

import com.spring.vote.helper.ExcelElecteurHelper;
import com.spring.vote.model.Electeur;
import com.spring.vote.repository.ElecteurRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ElecteurService {

    @Autowired
    private ElecteurRepository electeurRepository;

    public List<Electeur> getAllElecteurs() {
        return electeurRepository.findAll();
    }

    public Electeur getElecteurById(Long id) {
        return electeurRepository.findById(id).orElse(null);
    }

    public void createElecteur(Electeur electeur) {
        electeurRepository.save(electeur);
    }

    public void updateElecteur(Long id, Electeur electeur) {
        if (electeurRepository.existsById(id)) {
            electeur.setId(id);
            electeurRepository.save(electeur);
        }
    }

    public void deleteElecteur(Long id) {
        electeurRepository.deleteById(id);
    }


    public void save(MultipartFile file) {
    try {
      List<Electeur> electeurs = ExcelElecteurHelper.excelToElecteurs(file.getInputStream());
      electeurRepository.saveAll(electeurs);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }

  //Trouver un electeur par son numero d'identification
  public Electeur getElecteurByNumeroIdentification(String numIdent){
    return electeurRepository.findOneByNumeroIdentification(numIdent);
  }

      public void updateVotedStatus(Long electeurId) {
        Electeur electeur = electeurRepository.findById(electeurId)
            .orElseThrow(() -> new EntityNotFoundException("Electeur not found with id: " + electeurId));

        electeur.setVoted(true); // Mettez à jour le statut de vote de l'électeur
        electeurRepository.save(electeur); // Enregistrer les modifications dans la base de données
    }
}
