import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:vote/controllers/electeur_controller.dart';
import 'package:vote/services/auth_service.dart';

import '../themes/colors.dart';

class OTPPage extends StatelessWidget {
  OTPPage({Key? key}) : super(key: key);

  final _authService = AuthService();
  final _controller = Get.find<ElecteurController>();
  final _otpController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: primaryColor,
        elevation: 0,
        leading: Icon(
          Icons.arrow_back,
          color: Color.fromARGB(255, 54, 53, 53),
        ),
        title: Text(
          "Authentification",
          style: TextStyle(color: Color.fromARGB(255, 247, 241, 241)),
          textAlign: TextAlign.center,
        ),
      ),
      body: Center(
        child: Padding(
          padding: const EdgeInsets.all(16.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text('Entrez votre code OTP :'),
              SizedBox(height: 16),
              TextFormField(
                controller: _otpController,
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: 'Code OTP',
                ),
              ),
              SizedBox(height: 16),
              ElevatedButton(
                onPressed: () {
                  _verifyOtp();
                },
                child: Text('Valider'),
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _verifyOtp() async {
    String otp = _otpController.text.trim();
    if (otp.isNotEmpty) {
      try {
        // Appel de la fonction pour vérifier l'OTP dans le service d'authentification
        await _authService.signInWithOtp(_controller.verificationId.value, otp);
        _controller.isLogedIn.value = true; // Modifier la valeur de isLogedIn à true
        Get.offAllNamed('/home'); // Rediriger vers la page d'accueil après une connexion réussie
      } catch (error) {
        // Gérer les erreurs lors de la vérification de l'OTP
        print('Erreur de vérification de l\'OTP : $error');
        Get.snackbar('Erreur', 'Code OTP invalide. Veuillez réessayer.');
      }
    } else {
      Get.snackbar('Erreur', 'Veuillez saisir un code OTP valide.');
    }
  }
}
