import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:vote/models/Candidat.dart';
import 'package:vote/themes/colors.dart';

class CandidatTile extends StatelessWidget {
  // Candidat candidat;
  final String name;
  final String partiName;
  final String imageUrl;
  final String desc;
  final votesCount;
  final double percentage;
  CandidatTile(
      {Key? key,
      required this.name,
      required this.partiName,
      required this.imageUrl,
      required this.desc,
      required this.votesCount,
      required this.percentage})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    double width = MediaQuery.of(context).size.width / 3;
    double height = width / 3; // One-third of the width
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Card(
        // Define the shape of the card

        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(4),
          // side: BorderSide(
          //   // color: primaryColor,
          // ),
        ),
        // color: Color.fromARGB(255, 191, 209, 233),
        // elevation: 20,
        // Define how the card's content should be clipped
        clipBehavior: Clip.antiAliasWithSaveLayer,
        // Define the child widget of the card
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            // Add padding around the row widget
            Padding(
              padding: const EdgeInsets.all(15),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  // Add an image widget to display an image
                  Image.asset(
                    'lib/assets/voting-box.png',
                    height: 100,
                    width: 100,
                    fit: BoxFit.cover,
                  ),
                  // Add some spacing between the image and the text
                  Container(width: 20),
                  // Add an expanded widget to take up the remaining horizontal space
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: <Widget>[
                        // Add some spacing between the top of the card and the title
                        Container(height: 5),
                        // Add a title widget
                        Text(
                          name,
                          style: GoogleFonts.dmSerifDisplay(
                            fontSize: 18,
                          ),
                        ),
                        // Add some spacing between the title and the subtitle
                        Container(height: 5),
                        // Add a subtitle widget
                        Text(
                          partiName,
                          style: MyTextSample.body1(context)!.copyWith(
                            color: Colors.grey[500],
                          ),
                        ),
                        // Add some spacing between the subtitle and the text
                        Container(height: 10),
                        // Add a text widget to display some text
                        Text(
                          desc,
                          maxLines: 3,
                          style: MyTextSample.subhead(context)!.copyWith(
                            color: Colors.grey[700],
                          ),
                        ),
                        Container(height: 10),
                        SizedBox(height: 4),
                        Text('Votes: $votesCount'),
                        Text('Pourcentage: ${percentage.toStringAsFixed(2)}%'),
                      ],
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
