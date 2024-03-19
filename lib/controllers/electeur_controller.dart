import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';
import 'package:vote/services/electeur_service.dart';

import '../models/Electeur.dart';
import '../services/auth_service.dart';

class ElecteurController extends GetxController {
  var nni = "".obs;
  var phone = "".obs;
  var isLogedIn = false.obs;
  final electeur = Electeur().obs;
  final authService = AuthService();
  var verificationId = ''.obs;
  var smsCode = ''.obs;
  var isLoading = false.obs;
  // Fonction pour vérifier si le numéro de téléphone correspond
  Future<bool> verifyPhoneNumber(Electeur el) async {
    // Votre logique de vérification du numéro de téléphone
    return el.numeroTelephone == phone.value;
  }

  // Fonction pour vérifier si l'électeur a voté
  Future<bool> hasVoted(Electeur el) async {
    // Votre logique de vérification si l'électeur a voté ou non
    return el.voted!;
  }

  // Fonction pour envoyer l'OTP
  void sendOtp(String phoneNumber) async {
    try {
      await authService.sendOtp("+222$phoneNumber");
      print("otp....");
      // Naviguer vers la page OTP
      Get.toNamed('/otp');
    } catch (e) {
      print('Erreur lors de l\'envoi de l\'OTP : $e');
      // Afficher un message d'erreur
    }
  }


  // Vérifier le code OTP et se connecter
  Future<void> signInWithOtp() async {
    isLoading.value = true;
    try {
      await authService.signInWithOtp(verificationId.value, smsCode.value);
    } catch (e) {
      print('Erreur lors de la connexion avec OTP : $e');
    } finally {
      isLoading.value = false;
    }
  }

  // Fonction pour rechercher l'électeur par son numéro national
  Future<void> findbynni() async {
    Electeur? el = await ElecteurService().getElecteur(nni.value);
    if (el == null) {
      print("L'électeur avec NNI =$nni n'existe pas");
      Get.snackbar("Erreur", "Électeur non trouvé");
    } else {
      electeur.value = el;
      // var p = "49565214";
      // sendOTP(p);
      sendOtp(phone.value);
      print('Le numéro de national d\'identité est : ${el.nom}');
      if (await verifyPhoneNumber(el)) {
        if (await hasVoted(el)) {
          print("L'électeur a déjà voté.");
          // Afficher un message ou effectuer une action appropriée
        } else {
          print("L'électeur n'a pas encore voté.");
          // Afficher un message ou effectuer une action appropriée pour rediriger vers la page OTP
          // Get.toNamed('/otp_page');
          // var p = "49565214";
          // sendOTP(p);
        }
      } else {
        print("Le numéro de téléphone ne correspond pas.");
        // Afficher un message ou effectuer une action appropriée
      }
    }
  }
}
