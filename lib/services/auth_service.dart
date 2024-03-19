import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';

class AuthService {
  final FirebaseAuth _auth = FirebaseAuth.instance;

  // Envoyer un code OTP au numéro de téléphone
  Future<void> sendOtp(String phoneNumber) async {
    try {
      await _auth.verifyPhoneNumber(
        phoneNumber: phoneNumber,
        verificationCompleted: (credential) async {
          // Cette fonction est appelée automatiquement lorsque la vérification est réussie
          // Vous pouvez gérer la connexion ici si nécessaire
          _auth.signInWithCredential(credential);
        },
        verificationFailed: (FirebaseAuthException e) {
          // Cette fonction est appelée si la vérification échoue
          print('Erreur de vérification: ${e.message}');
        },
        codeSent: (String verificationId, int? resendToken) {
          // Cette fonction est appelée lorsque le code OTP est envoyé avec succès
          // Vous pouvez enregistrer l'ID de vérification pour une utilisation ultérieure
          print(
              'Code OTP envoyé avec succès. ID de vérification: $verificationId');
        },
        codeAutoRetrievalTimeout: (String verificationId) {
        
        },
        timeout: Duration(
            seconds:
                60), // Durée maximale d'attente pour la réception du code OTP
        // forceResendingToken: resendToken, /// Token de renvoi, s'il y a eu un renvoi
      );
    } catch (e) {
      // Gérer les erreurs de manière appropriée
      print("Erreur lors de l'envoi du code OTP: $e");
    }
  }

  // Vérifier le code OTP et se connecter
  Future<void> signInWithOtp(String verificationId, String smsCode) async {
    try {
      print(verificationId);
      AuthCredential credential = PhoneAuthProvider.credential(
        verificationId: verificationId,
        smsCode: smsCode,
      );
      await _auth.signInWithCredential(credential);
       Get.offAllNamed('/home');
    } catch (e) {
      // Gérer les erreurs de manière appropriée
      print('Erreur lors de la connexion avec le code OTP: $e');
    }
  }

  // Vérifier si l'utilisateur est connecté
  Stream<User?> get user => _auth.authStateChanges();
}
