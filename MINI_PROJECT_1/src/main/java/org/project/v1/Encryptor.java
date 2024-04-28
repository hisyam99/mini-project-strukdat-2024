package org.project.v1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Encryptor {

    // Metode untuk memulai proses enkripsi
    public static String encrypt(String password) {
        // Langkah 1: Enkripsi menggunakan Step 1
        String step1 = step1(password);
        System.out.println("Step 1 : " + step1);

        // Langkah 2: Enkripsi menggunakan Step 2
        String step2 = step2(step1);
        System.out.println("Step 2 : " + step2);

        // Langkah 3: Enkripsi menggunakan Step 3
        String step3 = step3(step2);
        System.out.println("Step 3 : " + step3);

        // Mengembalikan hasil enkripsi
        return step3;
    }

    // Langkah 1: Enkripsi
    private static String step1(String password) {
        // Membuat antrian untuk menyimpan karakter-karakter hasil enkripsi
        Queue<Character> queue = new LinkedList<>();

        // Mengiterasi setiap karakter dalam password
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            // Jika karakter adalah huruf, geser 5 posisi ke depan dalam urutan abjad
            if (Character.isLetter(c)) {
                char shiftedChar = (char) (c + 5);
                if (Character.isUpperCase(c)) {
                    // Jika karakter adalah huruf kapital, atur ulang jika hasil geserannya melebihi 'Z'
                    if (shiftedChar > 'Z') {
                        shiftedChar = (char) (shiftedChar - 26);
                    }
                } else {
                    // Jika karakter adalah huruf kecil, atur ulang jika hasil geserannya melebihi 'z'
                    if (shiftedChar > 'z') {
                        shiftedChar = (char) (shiftedChar - 26);
                    }
                }
                // Tambahkan karakter yang telah dienkripsi ke dalam antrian
                queue.add(shiftedChar);
            } else {
                // Jika bukan huruf, tambahkan langsung ke dalam antrian tanpa perubahan
                queue.add(c);
            }
        }
        // Membangun kembali string hasil enkripsi dari antrian
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            result.append(queue.poll());
        }
        return result.toString();
    }

    // Langkah 2: Enkripsi
    private static String step2(String password) {
        // Memecah password menjadi tiga bagian
        String depan = password.substring(0, 3);
        String tengah = password.substring(3, password.length() - 3);
        String akhir = password.substring(password.length() - 3);
        // Memindahkan bagian akhir ke awal, bagian tengah tetap, dan bagian depan ke belakang
        return akhir + tengah + depan;
    }

    // Langkah 3: Enkripsi
    private static String step3(String password) {
        // Membuat tumpukan untuk membalik urutan karakter dalam password
        Stack<Character> stack = new Stack<>();
        for (char c : password.toCharArray()) {
            stack.push(c);
        }
        // Membangun kembali string hasil enkripsi dari tumpukan
        StringBuilder reversedPassword = new StringBuilder();
        while (!stack.isEmpty()) {
            reversedPassword.append(stack.pop());
        }
        return reversedPassword.toString();
    }
}
