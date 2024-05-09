// package com.spring.vote;

// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.security.*;
// import java.security.spec.PKCS8EncodedKeySpec;
// import java.security.spec.X509EncodedKeySpec;
// import java.util.Base64;

// public class KeyStorageExample {

//     public static void main(String[] args) {
//         try {
//             // Générer une paire de clés RSA
//             KeyPair keyPair = generateKeyPair();

//             // Stocker les clés dans des fichiers
//             storeKeysToFile(keyPair.getPublic(), "public_key.pem");
//             storeKeysToFile(keyPair.getPrivate(), "private_key.pem");

//             // Lire les clés depuis les fichiers
//             PublicKey publicKey = readPublicKeyFromFile("public_key.pem");
//             PrivateKey privateKey = readPrivateKeyFromFile("private_key.pem");

//             // Afficher les clés lues
//             System.out.println("Clé publique lue : " + publicKey);
//             System.out.println("Clé privée lue : " + privateKey);

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     // Générer une paire de clés RSA
//     public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
//         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//         keyPairGenerator.initialize(2048);
//         return keyPairGenerator.generateKeyPair();
//     }

//     // Stocker une clé dans un fichier
//     public static void storeKeysToFile(Key key, String fileName) throws IOException {
//         try (FileOutputStream fos = new FileOutputStream(fileName)) {
//             fos.write(key.getEncoded());
//         }
//     }

//     // Lire la clé publique depuis un fichier
//     public static PublicKey readPublicKeyFromFile(String fileName) throws Exception {
//         byte[] publicKeyBytes = Files.readAllBytes(Paths.get(fileName));
//         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//         X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
//         return keyFactory.generatePublic(publicKeySpec);
//     }

//     // Lire la clé privée depuis un fichier
//     public static PrivateKey readPrivateKeyFromFile(String fileName) throws Exception {
//         byte[] privateKeyBytes = Files.readAllBytes(Paths.get(fileName));
//         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//         PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
//         return keyFactory.generatePrivate(privateKeySpec);
//     }
// }
