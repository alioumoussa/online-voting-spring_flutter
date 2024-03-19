// To parse this JSON data, do
//
//     final Candidat = CandidatFromJson(jsonString);

import 'dart:convert';

List<Candidat> CandidatFromJson(String str) => List<Candidat>.from(json.decode(str).map((x) => Candidat.fromJson(x)));

String CandidatToJson(List<Candidat> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Candidat {
    int id;
    String nom;
    String photoUrl;
    DateTime dateNaissance;
    PartiPolitique partiPolitique;

    Candidat({
        required this.id,
        required this.nom,
        required this.photoUrl,
        required this.dateNaissance,
        required this.partiPolitique,
    });

    factory Candidat.fromJson(Map<String, dynamic> json) => Candidat(
        id: json["id"],
        nom: json["nom"],
        photoUrl: json["photoUrl"],
        dateNaissance: DateTime.parse(json["dateNaissance"]),
        partiPolitique: PartiPolitique.fromJson(json["partiPolitique"]),
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "nom": nom,
        "photoUrl": photoUrl,
        "dateNaissance": "${dateNaissance.year.toString().padLeft(4, '0')}-${dateNaissance.month.toString().padLeft(2, '0')}-${dateNaissance.day.toString().padLeft(2, '0')}",
        "partiPolitique": partiPolitique.toJson(),
    };
}

class PartiPolitique {
    int id;
    String description;
    String programme;
    String photoUrl;
    DateTime dateFondation;
    String nom;

    PartiPolitique({
        required this.id,
        required this.description,
        required this.programme,
        required this.photoUrl,
        required this.dateFondation,
        required this.nom,
    });

    factory PartiPolitique.fromJson(Map<String, dynamic> json) => PartiPolitique(
        id: json["id"],
        description: json["description"],
        programme: json["programme"],
        photoUrl: json["photoURL"],
        dateFondation: DateTime.parse(json["dateFondation"]),
        nom: json["nom"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "description": description,
        "programme": programme,
        "photoURL": photoUrl,
        "dateFondation": "${dateFondation.year.toString().padLeft(4, '0')}-${dateFondation.month.toString().padLeft(2, '0')}-${dateFondation.day.toString().padLeft(2, '0')}",
        "nom": nom,
    };
}
