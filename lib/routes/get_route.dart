import 'package:get/get.dart';
import 'package:vote/models/Candidat.dart';
import 'package:vote/pages/candidat_details.dart';
import 'package:vote/pages/electeur_auth.dart';
import 'package:vote/pages/home_page.dart';
import 'package:vote/pages/intro_page.dart';
import 'package:vote/pages/opt_page.dart';

class AppRoutes {
  static const String intro = '/';
  static const String home = '/home';
  static const String details = '/details';
  static const String auth = '/auth';
  static const String otp = '/otp';
  static final routes = [
    GetPage(name: home, page: () => HomePage()),
    GetPage(name: intro, page: () => IntroPage()),
    GetPage(
        name: details, page: () => CandidatDetails(candidat: Get.arguments)),
    GetPage(name: auth, page: () => AuthPage()),
    GetPage(name: otp, page: () => OTPPage())
  ];
}
