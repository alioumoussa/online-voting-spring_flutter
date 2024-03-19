import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:vote/models/Candidat.dart';

import '../models/Election.dart';

class CandidatService {
  static const String baseUrl = 'https://192.168.177.11:8080/candidats/';
  static const String baseUrl1 = 'https://192.168.177.11:8080/elections/1';

  static Future<List<Candidat>> getCandidats() async {
    // Map<String, String> headers = {
    //   'Content-Type': 'application/json; charset=utf-8',
    // };
    final response = await http.get(Uri.parse(baseUrl));
    print(response.statusCode);

    if (response.statusCode == 200) {
      // final List jsonData = jsonDecode(response.body);
      final utf8decoder = Utf8Decoder(); // Créer un décodeur UTF-8
      final List jsonData = jsonDecode(utf8decoder.convert(response.bodyBytes));

      return jsonData.map((e) => Candidat.fromJson(e)).toList();

      // return jsonData;
    } else {
      throw Exception('Failed to load candidats');
    }
  }

  static Future<Election> getElection() async {
    final response = await http.get(Uri.parse(baseUrl1));
    if (response.statusCode == 200) {
      Election elec = Election.fromJson(jsonDecode(response.body));
      print(elec);
      return elec;
    } else {
      throw Exception('Failed to load election data');
    }
  }
}
