class Election {
  int? id;
  String? titre;
  DateTime? dateDebut;
  DateTime? dateFin;

  
  Election({
    this.id,
    this.titre,
    this.dateDebut,
    this.dateFin,
  });

  factory Election.fromJson(Map<String, dynamic> json) => Election(
        id: json["id"],
        titre: json["titre"],
        dateDebut: DateTime.parse(json["dateDebut"]),
        dateFin: DateTime.parse(json["dateFin"]),
      );

  Map<String, dynamic> toJson() => {
        "id": id,
        "titre": titre,
        "dateDebut": dateDebut!.toIso8601String(),
        "dateFin": dateFin!.toIso8601String(),
      };
}
