package org.project.v1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Decryptor {

    // Metode untuk memulai proses dekripsi
    public static String decrypt(String password) {
        // Langkah 1: Dekripsi menggunakan Step 3
        String decryptedStep1 = step3(password);
        System.out.println("Step 1 : " + decryptedStep1);

        // Langkah 2: Dekripsi menggunakan Step 2
        String decryptedStep2 = step2(decryptedStep1);
        System.out.println("Step 2 : " + decryptedStep2);

        // Langkah 3: Dekripsi menggunakan Step 1
        String decryptedStep3 = step1(decryptedStep2);
        System.out.println("Step 3 : " + decryptedStep3);

        // Mengembalikan hasil dekripsi
        return decryptedStep3;
    }

    // Langkah 1: Dekripsi
    private static String step1(String password) {
        // Membuat antrian untuk menyimpan karakter-karakter hasil dekripsi
        Queue<Character> queue = new LinkedList<>();

        // Mengiterasi setiap karakter dalam password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Jika karakter adalah huruf, geser 5 posisi ke belakang dalam urutan abjad
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (c - 5);
                if (Character.isUpperCase(c)) {
                    // Jika karakter adalah huruf kapital, atur ulang jika hasil geserannya kurang dari 'A'
                    if (shiftedChar < 'A') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                } else {
                    // Jika karakter adalah huruf kecil, atur ulang jika hasil geserannya kurang dari 'a'
                    if (shiftedChar < 'a') {
                        shiftedChar = (char) (shiftedChar + 26);
                    }
                }
                // Tambahkan karakter yang telah didekripsi ke dalam antrian
                queue.add(shiftedChar);
            } else {
                // Jika bukan huruf, tambahkan langsung ke dalam antrian tanpa perubahan
                queue.add(c);
            }
        }
        // Membangun kembali string hasil dekripsi dari antrian
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            result.append(queue.poll());
        }
        return result.toString();
    }

    // Langkah 2: Dekripsi
    private static String step2(String password) {
        // Memecah password menjadi tiga bagian
        String akhir = password.substring(0, 3);
        String tengah = password.substring(3, password.length() - 3);
        String depan = password.substring(password.length() - 3);
        // Memindahkan bagian depan ke belakang, bagian tengah tetap, dan bagian akhir ke awal
        return depan + tengah + akhir;
    }

    // Langkah 3: Dekripsi
    private static String step3(String password) {
        // Membuat tumpukan untuk membalik urutan karakter dalam password
        Stack<Character> stack = new Stack<>();
        for (char c : password.toCharArray()) {
            stack.push(c);
        }
        // Membangun kembali string hasil dekripsi dari tumpukan
        StringBuilder reversedPassword = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedPassword.append(stack.pop());
        }
        return reversedPassword.toString();
    }
}