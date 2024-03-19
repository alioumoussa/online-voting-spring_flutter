import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:vote/components/candidat_tile.dart';
import 'package:vote/controllers/candidat_controller.dart';
import 'package:vote/controllers/electeur_controller.dart';
import 'package:vote/controllers/vote_controller.dart';
import 'package:vote/routes/get_route.dart';
import 'package:vote/themes/colors.dart';

import 'candidat_details.dart';

class HomePage extends StatelessWidget {
  final candidatController = Get.find<CandidatController>();
  // final electeurController = Get.find<ElecteurController>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // backgroundColor: Color.fromARGB(255, 191, 209, 233),
      appBar: AppBar(
        backgroundColor: primaryColor,
        elevation: 0,
        leading: Icon(
          Icons.menu,
          color: Color.fromARGB(255, 247, 241, 241),
        ),
        title: Text(
          "Vote",
          style: TextStyle(color: Color.fromARGB(255, 247, 241, 241)),
          textAlign: TextAlign.center,
        ),
      ),
      body: Obx(
        () => ListView.builder(
          itemCount: candidatController.candidats.length,
          itemBuilder: (context, index) {
            final candidat = candidatController.candidats[index];
            final votes =
                candidatController.filterAndMapVotesByCandidat(candidat.id);
            print(candidatController.count);

            // return ListTile(
            //   title: Text(candidat['nom']),
            //   subtitle: Text(candidat['partiPolitique']['nom']),
            //   leading: CircleAvatar(
            //       // backgroundImage: NetworkImage(candidat.photoUrl),
            //       ),
            //   onTap: () {
            //     // Naviguer vers la page des détails du candidat
            //   },
            // );
            return GestureDetector(
              onTap: () {
                // print(electeurController.isLogedIn.value);
                print("");
                Get.toNamed(AppRoutes.details, arguments: candidat);
                // Get.to(CandidatDetails(candidat: candidat));
              },
              child: CandidatTile(
                name: candidat.nom,
                partiName: candidat.partiPolitique.nom,
                imageUrl: '',
                desc: candidat.partiPolitique.description,
                votesCount: candidatController.count.value,
                percentage: candidatController.calculatePercentage(candidatController.count.value, candidatController.votes.length),
              ),
            );
          },
        ),
      ),
    );
  }
}
