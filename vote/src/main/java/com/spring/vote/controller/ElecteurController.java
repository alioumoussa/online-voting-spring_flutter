package com.spring.vote.controller;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import com.spring.vote.helper.ExcelElecteurHelper;
import com.spring.vote.model.Electeur;
import com.spring.vote.services.ElecteurService;
import com.spring.vote.services.ExcelImportService;
import com.spring.vote.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/electeurs")
public class ElecteurController {

    @Autowired
    private ElecteurService electeurService;

 



   @GetMapping("/")
   public List<Electeur> getAllElecteurs() {
       return electeurService.getAllElecteurs();
   }


    // @GetMapping("/dashboard")
    // public String getAllElecteurs(Model model) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
    //     List<Electeur> electeurs = electeurService.getAllElecteurs();
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //     // Déchiffrer les données sensibles récupérées de la base de données
    //     // for (Electeur electeur : electeurs) {
    //         // String numeroTelephoneDechiffre = CryptoUtils.decryptString(electeur.getNumeroTelephone());
    //         // String numeroIdentificationDechiffre =  CryptoUtils.decryptString(electeur.getNumeroIdentification());

    //         // // Mettre à jour les données de l'électeur avec les données déchiffrées
    //         // electeur.setNumeroTelephone(numeroTelephoneDechiffre);
    //         // electeur.setNumeroIdentification(numeroIdentificationDechiffre);

    //         //date en string


    //     // }
    // model.addAttribute("electeurs", electeurs);
    //     return "pages/dashboard.html";
    // }

    @GetMapping("/{id}")
    public Electeur getElecteurById(@PathVariable Long id) {
        return electeurService.getElecteurById(id);
    }

    @GetMapping("updateVoteStatus/{id}")
    public void updateVoteStatus(@PathVariable Long id) {
         electeurService.updateVotedStatus(id);
    }

    @PostMapping("/")
    public void createElecteur(@ModelAttribute Electeur electeur) {
        electeurService.createElecteur(electeur);
    }

    @PutMapping("/{id}")
    public void updateElecteur(@PathVariable Long id, @RequestBody Electeur electeur) {
        electeurService.updateElecteur(id, electeur);
    }

    @DeleteMapping("/{id}")
    public void deleteElecteur(@PathVariable Long id) {
        electeurService.deleteElecteur(id);
    }

    @PostMapping("/inscription")
    public String inscrireElecteur(@ModelAttribute Electeur electeur) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Chiffrer les données sensibles avant de les stocker dans la base de données
        String numeroTelephoneChiffre = CryptoUtils.encryptString(electeur.getNumeroTelephone());
        String numeroIdentificationChiffre = CryptoUtils.encryptString(electeur.getNumeroIdentification());

        // Mettre à jour les données de l'électeur avec les données chiffrées
        electeur.setNumeroTelephone(numeroTelephoneChiffre);
        electeur.setNumeroIdentification(numeroIdentificationChiffre);

        // Enregistrer l'électeur dans la base de données
        electeurService.createElecteur(electeur);

        return "confirmation"; // Redirection vers une page de confirmation
    }


    @PostMapping("/import-electeurs")
public String importElecteursFromExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {
    if (file.isEmpty()) {
        model.addAttribute("message", "Veuillez sélectionner un fichier Excel.");
        return "error";
    }

    try {
        // Vérifiez si le fichier est au format Excel
        if (!ExcelElecteurHelper.hasExcelFormat(file)) {
            model.addAttribute("message", "Le fichier doit être au format Excel.");
            return "error";
        }
        electeurService.save(file);
        model.addAttribute("message", "Les électeurs ont été importés avec succès depuis le fichier Excel.");
    } catch (Exception e) {
        model.addAttribute("message", "Une erreur s'est produite lors de l'importation des électeurs depuis le fichier Excel : " + e.getMessage());
        return "error";
    }

    return "redirect:pages/dashboard"; // Redirection vers la page d'accueil après l'importation des électeurs
}

   @GetMapping("/identification/{numeroIdentification}")
   public ResponseEntity<Electeur> findByNumeroIdentification(@PathVariable String numeroIdentification){
    Electeur elct = electeurService.getElecteurByNumeroIdentification(numeroIdentification);
    if(elct != null){
        return new ResponseEntity<>(elct, HttpStatus.OK);
    }else
    {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
   }

}
