import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:vote/models/Electeur.dart';

class ElecteurService {
  static const String baseUrl =
      'https://192.168.107.11:8080/electeurs/';
  Future<Electeur?> getElecteur(String nni) async {
    final response = await http.get(Uri.parse(baseUrl +'identification/' + nni));
    if (response.statusCode == 200) {
      Electeur electeur = Electeur.fromJson(jsonDecode(response.body));
      return electeur;
    } else {
      // throw Exception('Failed to load electeur');
      return null;
    
    }
  }

 Future<bool> updateVoteStatus(String electeurId) async{
  final response = await http.get(Uri.parse(baseUrl + 'updateVoteStatus' + electeurId));
  if (response.statusCode == 200){
  return true;
 } else{
  return false;
 }
}
}