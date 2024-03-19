import 'dart:io';

import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:vote/controllers/candidat_controller.dart';
import 'package:vote/pages/home_page.dart';
import 'package:vote/pages/intro_page.dart';
import 'package:firebase_core/firebase_core.dart';

import 'config/http_certificat.dart';
import 'routes/get_route.dart';

void main() async{
  HttpOverrides.global = DevHttpOverrides();
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
  // options: DefaultFirebaseOptions.currentPlatform,
);
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  final candidatController = Get.put(CandidatController());

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      initialRoute: AppRoutes.intro,
      getPages: AppRoutes.routes,
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: IntroPage(),
    );
  }
}
