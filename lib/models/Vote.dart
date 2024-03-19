import 'dart:convert';
import "Candidat.dart";
import 'Election.dart';

List<Vote> voteFromJson(String str) =>
    List<Vote>.from(json.decode(str).map((x) => Vote.fromJson(x)));

String voteToJson(List<Vote> data) =>
    json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Vote {
  int? id;
  Candidat candidat;
  Election election;
  DateTime dateVote;

  Vote({
    this.id,
    required this.candidat,
    required this.election,
    required this.dateVote,
  });

  factory Vote.fromJson(Map<String, dynamic> json) => Vote(
        id: json["id"],
        candidat: Candidat.fromJson(json["candidat"]),
        election: Election.fromJson(json["election"]),
        dateVote: DateTime.parse(json["dateVote"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "candidat": candidat.toJson(),
        "election": election.toJson(),
        "dateVote": dateVote.toIso8601String(),
      };
}
