import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:vote/themes/colors.dart';

import '../controllers/electeur_controller.dart';

class AuthPage extends StatelessWidget {
  AuthPage({Key? key}) : super(key: key);
  final lc = Get.put(ElecteurController());

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: primaryColor,
        elevation: 0,
        leading: Icon(
          Icons.menu,
          color: Color.fromARGB(255, 247, 241, 241),
        ),
        title: Text(
          "Authentification",
          style: TextStyle(color: Color.fromARGB(255, 247, 241, 241)),
          textAlign: TextAlign.center,
        ),
      ),
      body: Center(
        child: Card(
          margin: const EdgeInsets.all(8),
          child: Form(
              // key: lc.formKey,
              child: Padding(
            padding: const EdgeInsets.all(16),
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Expanded(
                  child: ListView(
                    children: [
                      const Text("Votre numero national",
                          style: TextStyle(
                              fontSize: 32, fontWeight: FontWeight.bold)),
                      const SizedBox(height: 48),
                      TextFormField(
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                            borderSide: BorderSide(color: primaryColor),
                          ),
                          labelText: "NNI",
                        ),
                        validator: (value) => value == null || value.isEmpty
                            ? 'Entrez votre NNI'
                            : null,
                        onChanged: (value) {
                          lc.nni.value = value;
                        },
                      ),
                      const SizedBox(height: 16),
                      TextFormField(
                        obscureText: true,
                        decoration: InputDecoration(
                          border: OutlineInputBorder(
                            borderSide: BorderSide(color: primaryColor),
                          ),
                          labelText: "Votre phone",
                        ),
                        validator: (value) => value == null || value.isEmpty
                            ? 'Entrez votre téléphone'
                            : null,
                        onChanged: (value) {
                          lc.phone.value = value;
                        },
                      ),
                      const SizedBox(height: 16),
                      SizedBox(
                        width: double.infinity,
                        height: 48,
                        child: ElevatedButton(
                          style: ButtonStyle(
                            backgroundColor:
                                MaterialStateProperty.all(primaryColor),
                            shape: MaterialStateProperty.all(
                              RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(10),
                              ),
                            ),
                          ),
                          onPressed: () async {
                            // if (!lc.formKey.currentState!.validate()) return;
                            await lc.findbynni();
                          },
                          child: const Text(
                            "Saisir",
                            style: TextStyle(fontSize: 16, color: Colors.white),
                          ),
                        ),
                      ),
                      const SizedBox(height: 16),
                    ],
                  ),
                ),
              ],
            ),
          )),
        ),
      ),
    );
  }
}
