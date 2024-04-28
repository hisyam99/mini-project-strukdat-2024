package org.project.v1;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input dari pengguna
        boolean exit = false; // Variabel untuk menentukan apakah program harus keluar

        // Perulangan menu
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. Encrypt");
            System.out.println("2. Decrypt");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1/2/3): ");
            int menuOption = scanner.nextInt(); // Menerima pilihan menu dari pengguna
            scanner.nextLine();

            switch (menuOption) {
                case 1: // Jika pilihan adalah Encrypt
                    System.out.println("Masukkan Password untuk Enkripsi: ");
                    String inputEncrypt = scanner.nextLine(); // Menerima password dari pengguna
                    if (isValidPassword(inputEncrypt)) { // Memeriksa apakah password valid
                        String encryptedPassword = Encryptor.encrypt(inputEncrypt); // Melakukan enkripsi password
                        System.out.println("Password terenkripsi: " + encryptedPassword); // Menampilkan password terenkripsi
                        exit = askForMainMenu(scanner); // Meminta pengguna apakah ingin kembali ke menu utama
                    } else {
                        System.out.println("Panjang password harus antara 8 hingga 15 karakter"); // Pesan kesalahan jika password tidak valid
                    }
                    break;
                case 2: // Jika pilihan adalah Decrypt
                    System.out.println("Masukkan Password untuk Dekripsi: ");
                    String inputDecrypt = scanner.nextLine(); // Menerima password dari pengguna
                    if (isValidPassword(inputDecrypt)) { // Memeriksa apakah password valid
                        String decryptedPassword = Decryptor.decrypt(inputDecrypt); // Melakukan dekripsi password
                        System.out.println("Password terdekripsi: " + decryptedPassword); // Menampilkan password terdekripsi
                        exit = askForMainMenu(scanner); // Meminta pengguna apakah ingin kembali ke menu utama
                    } else {
                        System.out.println("Panjang password harus antara 8 hingga 15 karakter"); // Pesan kesalahan jika password tidak valid
                    }
                    break;
                case 3: // Jika pilihan adalah Keluar
                    exit = true; // Menandakan agar program keluar dari loop utama
                    break;
                default: // Jika pilihan tidak valid
                    System.out.println("Menu tidak valid."); // Pesan kesalahan jika pilihan menu tidak valid
                    break;
            }
        }
    }

    // Metode untuk memeriksa validitas password
    private static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 15 && password.matches("[a-zA-Z]+");
    }

    // Metode untuk meminta pengguna apakah ingin kembali ke menu utama
    private static boolean askForMainMenu(Scanner scanner) {
        System.out.println("Apakah Anda ingin kembali ke menu utama? (Y/n)");
        String response = scanner.nextLine(); // Menerima jawaban dari pengguna
        return response.equalsIgnoreCase("n"); // Mengembalikan false jika jawaban adalah "n", true jika jawaban adalah yang lainnya
    }
}
