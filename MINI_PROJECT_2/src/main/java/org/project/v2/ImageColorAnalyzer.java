package org.project.v2;

import javax.imageio.ImageIO; // Package untuk bekerja dengan gambar
import java.awt.image.BufferedImage; // Representasi gambar dalam memori
import java.io.File; // Representasi file di sistem
import java.io.IOException; // Exception untuk penanganan kesalahan IO
import java.util.HashMap; // Struktur data HashMap untuk menyimpan frekuensi warna
import java.util.Map; // Interface Map untuk iterasi dan operasi pada HashMap
import java.util.Scanner; // Untuk membaca input dari pengguna

/**
 * Kelas ImageColorAnalyzer untuk menganalisis warna dominan pada gambar.
 */
public class ImageColorAnalyzer {
    private final HashMap<Integer, Integer> colorFrequencyMap; // HashMap untuk menyimpan frekuensi setiap warna

    /**
     * Konstruktor untuk ImageColorAnalyzer.
     */
    public ImageColorAnalyzer() {
        this.colorFrequencyMap = new HashMap<>(); // Inisialisasi HashMap untuk frekuensi warna
    }

    /**
     * Metode untuk membaca dan memproses gambar.
     * @param imagePath Path gambar yang akan dianalisis.
     * @throws IOException Jika terjadi kesalahan saat membaca gambar.
     */
    public void readAndProcessImage(String imagePath) throws IOException {
        File file = new File(imagePath); // Membuat objek File berdasarkan path gambar
        // Gambar yang akan dianalisis
        BufferedImage image = ImageIO.read(file); // Membaca gambar dari file

        // Memproses setiap piksel dalam gambar
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                // Mendapatkan nilai RGB dari piksel pada posisi (x, y)
                // Fungsi getRGB() mengembalikan nilai dalam format ARGB, kita gunakan & 0xFFFFFF untuk memisahkan kanal warna saja
                int color = image.getRGB(x, y) & 0xFFFFFF; // Mask alpha channel

                // Memasukkan atau memperbarui frekuensi warna dalam HashMap
                // Menggunakan metode getOrDefault untuk mendapatkan nilai saat ini dari HashMap
                // Jika warna belum ada dalam HashMap, default-nya adalah 0
                colorFrequencyMap.put(color, colorFrequencyMap.getOrDefault(color, 0) + 1);
            }
        }
    }

    /**
     * Metode untuk mendapatkan warna dominan berdasarkan frekuensinya.
     * @return Warna dominan dalam format integer RGB.
     */
    public int getDominantColor() {
        int dominantColor = 0; // Variabel untuk menyimpan warna dominan
        int maxFrequency = 0; // Variabel untuk menyimpan frekuensi maksimum

        // Menemukan warna dominan dengan frekuensi tertinggi
        for (Map.Entry<Integer, Integer> entry : colorFrequencyMap.entrySet()) {
            // Jika frekuensi warna saat ini lebih besar dari frekuensi maksimum yang tersimpan
            if (entry.getValue() > maxFrequency) {
                // Perbarui frekuensi maksimum dengan frekuensi warna saat ini
                maxFrequency = entry.getValue();
                // Perbarui warna dominan dengan warna saat ini
                dominantColor = entry.getKey();
            }
        }

        return dominantColor; // Mengembalikan warna dominan
    }

    /**
     * Metode untuk mendapatkan frekuensi maksimum dari warna dominan.
     * @return Frekuensi maksimum dari warna dominan.
     */
    public int getMaxFrequency() {
        int maxFrequency = 0; // Variabel untuk menyimpan frekuensi maksimum

        // Iterasi melalui nilai frekuensi dalam HashMap
        for (int frequency : colorFrequencyMap.values()) {
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        return maxFrequency; // Mengembalikan frekuensi maksimum
    }

    /**
     * Metode untuk menampilkan hasil analisis warna.
     */
    public void displayResults() {
        int dominantColor = getDominantColor(); // Dapatkan warna dominan
        int maxFrequency = getMaxFrequency(); // Dapatkan frekuensi maksimum

        // Tampilkan hasil dalam format heksadesimal
        System.out.printf("Warna dominan pada gambar adalah: #%06X%n", dominantColor);
        // Menampilkan jumlah frekuensi warna dominan
        System.out.printf("Jumlah frekuensi warna #%06X: %d Pixel%n", dominantColor, maxFrequency);
    }

    /**
     * Metode utama untuk menjalankan program.
     * @param args Argumen baris perintah, di sini tidak digunakan.
     */
    public static void main(String[] args) {
        ImageColorAnalyzer colorAnalyzer = new ImageColorAnalyzer(); // Buat objek analisis warna
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk input

        System.out.println("Selamat datang di Program Deteksi Warna Dominan pada Gambar!");
        System.out.print("Masukkan path gambar: ");
        String imagePath = scanner.nextLine(); // Membaca path gambar dari pengguna

        try {
            colorAnalyzer.readAndProcessImage(imagePath); // Memproses gambar untuk mendapatkan frekuensi warna
            colorAnalyzer.displayResults(); // Tampilkan hasil analisis warna
        } catch (IOException e) {
            System.err.println("Gagal memuat gambar. Pastikan path yang dimasukkan benar."); // Tangani kesalahan IO
            e.printStackTrace(); // Cetak tumpukan kesalahan
        } finally {
            scanner.close(); // Tutup Scanner untuk membersihkan penggunaan memory untuk scanner
        }
    }
}