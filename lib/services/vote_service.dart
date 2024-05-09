import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:vote/controllers/electeur_controller.dart';
import 'package:vote/models/Vote.dart';
import 'package:vote/services/electeur_service.dart';

class VoteService {
  static const String baseUrl = 'https://192.168.107.11:8080/votes/';

  static Future<void> createVote(Vote vote) async {
    final response = await http.post(
      Uri.parse(baseUrl),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(vote.toJson()),
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to create vote');
    } else {
      ElecteurService()
          .updateVoteStatus(ElecteurController().electeur.value.id.toString());
      print("Vote stocke");
    }
  }

  Future<List<Vote>> getAllVotes() async {
    final response = await http.get(Uri.parse(baseUrl));

    if (response.statusCode == 200) {
      final List data = jsonDecode(response.body);
      return data.map((json) => Vote.fromJson(json)).toList();
    } else {
      throw Exception('Failed to fetch votes');
    }
  }

  List<Vote> filterVotesByCandidateId(List<Vote> allVotes, int candidateId) {
    return allVotes.where((vote) => vote.candidat.id == candidateId).toList();
  }

  int countVotesForCandidate(List<Vote> votes) {
    return votes.length;
  }

  double calculatePercentage(int votesCount, int totalVotesCount) {
    return (votesCount / totalVotesCount) * 100;
  }
}
