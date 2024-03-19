import 'package:get/get.dart';
import 'package:vote/models/Candidat.dart';
import 'package:vote/services/candidat_service.dart';

import '../models/Vote.dart';
import '../services/vote_service.dart';

class CandidatController extends GetxController {
  final candidats = <dynamic>[].obs;
  final votes = [].obs;
  final count = 0.obs;

  @override
  void onInit() {
    super.onInit();
    fetchCandidats();
    getVotes();
    // getElection();
  }

  void fetchCandidats() async {
    try {
      final data = await CandidatService.getCandidats();
      candidats.assignAll(data);
    } catch (e) {
      print(e.toString());
    }
  }

  // void getElection() async {
  //   try {
  //     final election = await CandidatService.getElection();
  //     print("elelction : " + election.titre);
  //   } catch (e) {
  //     print(e.toString());
  //   }
  // }

  void getVotes() async {
    try {
      final fetchedVotes = await VoteService().getAllVotes();
      // Appliquer la transformation en utilisant map
      final voteIds = fetchedVotes.map((vote) => vote.candidat.id).toList();
      // print(voteIds);

      // Assigner les votes à la liste votes
      votes.assignAll(voteIds);
      // countVotesForCandidate(candidatId);

      print(votes);
    } catch (e) {
      print('Erreur lors de la récupération des votes: $e');
    }
  }

  // Fonction pour filtrer les votes par ID de candidat
  List filterAndMapVotesByCandidat(int candidatId) {
    print("fffffff.....");

    final filtredVotes = votes
        .where((vote) =>
            vote == candidatId) // Filtrer les votes par ID de candidat
        .toList();
    count.value = filtredVotes.length;
    // Convertir le résultat en liste
    return filtredVotes;
  }

  int countVotesForCandidate(List votes) {
    count.value = votes.length;
    return votes.length;
  }

  double calculatePercentage(int votesCount, int totalVotesCount) {
    return (votesCount / totalVotesCount) * 100;
  }
}
