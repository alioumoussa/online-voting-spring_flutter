import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:vote/models/Electeur.dart';

class ElecteurService {
  static const String baseUrl =
      'https://192.168.177.11:8080/electeurs/identification/';
  Future<Electeur?> getElecteur(String nni) async {
    final response = await http.get(Uri.parse(baseUrl + nni));
    if (response.statusCode == 200) {
      Electeur electeur = Electeur.fromJson(jsonDecode(response.body));
      return electeur;
    } else {
      // throw Exception('Failed to load electeur');
      return null;
    
    }
  }
}
