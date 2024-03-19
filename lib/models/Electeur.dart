// To parse this JSON data, do
//
//     final electeur = electeurFromJson(jsonString);

import 'dart:convert';

Electeur electeurFromJson(String str) => Electeur.fromJson(json.decode(str));

String electeurToJson(Electeur data) => json.encode(data.toJson());

class Electeur {
  int? id;
  String? nom;
  String? prenom;
  String? adresse;
  String? numeroIdentification;
  String? numeroTelephone;
  DateTime? dateNaissance;
  bool? voted;

  Electeur({
    this.id,
    this.nom,
    this.prenom,
    this.adresse,
    this.numeroIdentification,
    this.numeroTelephone,
    this.dateNaissance,
    this.voted,
  });

  factory Electeur.fromJson(Map<String, dynamic> json) => Electeur(
        id: json["id"],
        nom: json["nom"],
        prenom: json["prenom"],
        adresse: json["adresse"],
        numeroIdentification: json["numeroIdentification"],
        numeroTelephone: json["numeroTelephone"],
        dateNaissance: json["dateNaissance"] == null
            ? null
            : DateTime.parse(json["dateNaissance"]),
        voted: json["voted"],
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "nom": nom,
        "prenom": prenom,
        "adresse": adresse,
        "numeroIdentification": numeroIdentification,
        "numeroTelephone": numeroTelephone,
        "dateNaissance":
            "${dateNaissance!.year.toString().padLeft(4, '0')}-${dateNaissance!.month.toString().padLeft(2, '0')}-${dateNaissance!.day.toString().padLeft(2, '0')}",
        "voted": voted,
      };
}
