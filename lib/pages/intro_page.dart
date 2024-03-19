import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:vote/components/button.dart';
import 'package:get/get.dart';
import 'package:vote/routes/get_route.dart';
import 'package:vote/themes/colors.dart' as themeColors;

class IntroPage extends StatelessWidget {
  const IntroPage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: themeColors.primaryColor,
      body: Padding(
        padding: const EdgeInsets.all(25.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            const SizedBox(
              height: 25,
            ),

            //name
            Text(
              "Your vote count",
              style:
                  GoogleFonts.dmSerifDisplay(fontSize: 28, color: Colors.white),
            ),

            //icon
            const SizedBox(
              height: 25,
            ),
            Padding(
              padding: EdgeInsets.all(50.0),
              child: Image.asset('lib/assets/voting-box.png'),
            ),
            const SizedBox(
              height: 25,
            ),

            //title
            Text(
              "Votez pour l'avenir : Votre voix compte !",
              style:
                  GoogleFonts.dmSerifDisplay(fontSize: 28, color: Colors.white),
            ),
            const SizedBox(
              height: 10,
            ),
            //subtitle
            Text(
              "Participez à la démocratie en exprimant votre choix lors des élections. Chaque vote compte pour façonner notre avenir collectif.",
              style: TextStyle(color: Colors.grey[300], height: 2),
            ),
            const SizedBox(
              height: 25,
            ),

            //get started button
            MyButton(
              text: "Get started",
              onTap: () {
                print("On m'a tappe");
                Get.toNamed('/home');
                // Get.toNamed('/auth');
              },
            )
          ],
        ),
      ),
    );
  }
}
