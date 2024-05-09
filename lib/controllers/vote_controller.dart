import 'package:get/get.dart';
import 'package:vote/models/Candidat.dart';
import 'package:vote/services/candidat_service.dart';
import 'package:vote/services/vote_service.dart';

import '../models/Election.dart';
import '../models/Vote.dart';

class VoteController extends GetxController {
  final election = Election().obs;
  // final votes = <Vote>[].obs;
  final voteService = VoteService();
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
    getElection();
    // getVotes();
  }

  void getElection() async {
    try {
      final elec = await CandidatService.getElection();
      election.value = elec;
      print("Election : " + elec.dateFin.toString());
    } catch (e) {
      print(e.toString());
    }
  }

}
