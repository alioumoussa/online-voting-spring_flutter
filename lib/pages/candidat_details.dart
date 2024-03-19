// ignore: unnecessary_import
import 'dart:ui';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:google_fonts/google_fonts.dart';
// ignore: unused_import
import 'package:vote/components/button.dart';
import 'package:vote/controllers/vote_controller.dart';
import 'package:vote/models/Electeur.dart';
import 'package:vote/themes/colors.dart';

import '../controllers/candidat_controller.dart';
import '../controllers/electeur_controller.dart';
import '../models/Candidat.dart';
import '../models/Vote.dart';
import '../services/vote_service.dart';

// ignore: must_be_immutable
class CandidatDetails extends StatelessWidget {
  final VoteController voteController = Get.put(VoteController());
  final CandidatController candidatController = Get.find<CandidatController>();
  Candidat candidat;
  final electeurController = Get.put(ElecteurController());

  CandidatDetails({Key? key, required this.candidat}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.transparent,
        elevation: 0,
        foregroundColor: Colors.grey[900],
        leading: Icon(
          Icons.arrow_back_ios,
          color: primaryColor,
        ),
        title: Text(candidat.nom),
        centerTitle: true,
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Expanded(
            child: ListView(
              children: [
                //image
                Image.asset(
                  'lib/assets/aliou1.jpg',
                  width: 100,
                ),
                const SizedBox(
                  height: 5,
                ),

                //candidat infos
                Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        candidat.partiPolitique.nom,
                        style: GoogleFonts.dmSerifDisplay(
                          fontSize: 28,
                        ),
                      ),
                      Text(
                        "Age : 22 Ans",
                        style: TextStyle(
                          fontSize: 14,
                          color: primaryColor,
                        ),
                      ),
                    ],
                  ),
                ),
                const SizedBox(
                  height: 25,
                ),

                //programme
                Text(
                  "Programme ",
                  style: TextStyle(
                      fontSize: 18,
                      color: Colors.grey[900],
                      fontWeight: FontWeight.bold),
                ),

                const SizedBox(
                  height: 10,
                ),

                Text(
                  // "Je suis Aliou Deh, le candidat qui va certainement vous sortir de la miser dont vous vivez daily. Votez maintenant pour vous liberer. C'est maintenant ou jamais ! Je suis Aliou Deh, le candidat qui va certainement vous sortir de la miser dont vous vivez daily. Votez maintenant pour vous liberer. C'est maintenant ou jamais ! Je suis Aliou Deh, le candidat qui va certainement vous sortir de la miser dont vous vivez daily. Votez maintenant pour vous liberer. C'est maintenant ou jamais ! Je suis Aliou Deh, le candidat qui va certainement vous sortir de la miser dont vous vivez daily. Votez maintenant pour vous liberer. C'est maintenant ou jamais ! Je suis Aliou Deh, le candidat qui va certainement vous sortir de la miser dont vous vivez daily. Votez maintenant pour vous liberer. C'est maintenant ou jamais ! ",

                  candidat.partiPolitique.programme,
                  style: TextStyle(
                      color: Colors.grey[600], fontSize: 14, height: 2),
                ),
              ],
            ),
          ),
          //button pour vote
          Container(
            decoration: BoxDecoration(
                color: primaryColor,
                borderRadius: const BorderRadius.only(
                    topLeft: Radius.circular(20.0),
                    topRight: Radius.circular(20.0))),
            padding: const EdgeInsets.all(25),
            child: Column(
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  // ignore: prefer_const_literals_to_create_immutables
                  children: [
                    const Text(
                      '',
                      style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 14),
                    ),
                    const Text(
                      '',
                      // candidatController.calculatePercentage(candidatController.count.value, candidatController.votes.length),,
                      style: TextStyle(
                          color: Colors.white,
                          fontWeight: FontWeight.bold,
                          fontSize: 14),
                    )
                  ],
                ),
                const SizedBox(
                  height: 25,
                ),
                Obx(
                  () =>
                      // final isAuthenticate = electeurController.isLogedIn.value;
                      //  Electeur el = electeurController.electeur.value;
                      TextButton(
                    onPressed: (() {
                      // if (!electeurController.isLogedIn.value) {
                      //   // Redirection vers la page de connexion si l'utilisateur n'est pas connecté
                      //   Get.toNamed('/auth');
                      // } else if (!electeurController.electeur.value.voted!) {
                      // Permettez à l'utilisateur de voter s'il est connecté et n'a pas encore voté
                      Vote v = Vote(
                        candidat: candidat,
                        election: voteController.election.value,
                        dateVote: DateTime.now(),
                      );
                      VoteService.createVote(v);
                      // } else {
                      //   // Redirection vers une page indiquant que l'utilisateur a déjà voté
                      //   Get.toNamed('/home');
                      // }
                      // // print('Voter');
                      // Vote v = Vote(
                      //     candidat: candidat,
                      //     election: voteController.election.value,
                      //     dateVote: DateTime.now());
                      // VoteService.createVote(v);
                    }),
                    child: Container(
                      width: 200,
                      padding: const EdgeInsets.all(20),
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(40),
                          color: Colors.white),
                      child: Row(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text(
                            "Clic to vote",
                            style: TextStyle(
                              color: primaryColor,
                            ),
                          ),
                          const SizedBox(
                            width: 10,
                          ),
                          Icon(
                            Icons.arrow_forward,
                            color: primaryColor,
                          )
                        ],
                      ),
                    ),
                  ),
                ),
              ],
            ),
          )
        ],
      ),
    );
  }
}
