import java.util.*;

// Struktur untuk menyimpan pasangan Key dan Value
class HashNode {
    int key;
    String value;

    public HashNode(int key, String value) {
        this.key = key;
        this.value = value;
    }
}

class HashTable {
    private int TABLE_SIZE;
    private LinkedList<HashNode>[] table;

    // Inisialisasi Hash Table dengan array of LinkedList
    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.TABLE_SIZE = size;
        table = new LinkedList[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Fungsi Hash sederhana menggunakan modulo
    private int hashFunction(int key) {
        return key % TABLE_SIZE;
    }

    // 1. INPUT DATA
    public void insert(int key, String value) {
        int index = hashFunction(key);
        
        // Cek apakah key sudah ada, jika ada update valuenya
        for (HashNode node : table[index]) {
            if (node.key == key) {
                node.value = value;
                System.out.println("Data dengan key " + key + " berhasil diupdate pada indeks " + index);
                return;
            }
        }
        
        // Jika belum ada, tambahkan node baru ke dalam LinkedList (Separate Chaining)
        table[index].add(new HashNode(key, value));
        
        // Mengecek apakah terjadi collision (jika ukuran LinkedList > 1)
        if (table[index].size() > 1) {
            System.out.println("Data dimasukkan ke indeks " + index + " (Terjadi Collision! Ditangani dengan Separate Chaining).");
        } else {
            System.out.println("Data berhasil dimasukkan ke indeks " + index);
        }
    }

    // 2. HAPUS DATA
    public void delete(int key) {
        int index = hashFunction(key);
        for (HashNode node : table[index]) {
            if (node.key == key) {
                table[index].remove(node);
                System.out.println("Data dengan key " + key + " berhasil dihapus dari indeks " + index);
                return;
            }
        }
        System.out.println("Gagal: Data dengan key " + key + " tidak ditemukan!");
    }

    // 3. CARI DATA
    public void search(int key) {
        int index = hashFunction(key);
        for (HashNode node : table[index]) {
            if (node.key == key) {
                System.out.println("Data Ditemukan! Key: " + key + ", Value: " + node.value + " (Berada di indeks " + index + ")");
                return;
            }
        }
        System.out.println("Hasil: Data dengan key " + key + " tidak ditemukan!");
    }

    // Menampilkan isi Hash Table untuk melihat Chaining
    public void display() {
        System.out.println("\n--- Visualisasi Hash Table ---");
        for (int i = 0; i < TABLE_SIZE; i++) {
            System.out.print("Indeks " + i + ": ");
            for (HashNode node : table[i]) {
                System.out.print("[" + node.key + " : " + node.value + "] -> ");
            }
            System.out.println("null");
        }
        System.out.println("------------------------------\n");
    }
}

public class Main {
    public static void main(String[] args) {
        // Mengubah ukuran tabel menjadi 101 (angka prima agar distribusi hash bagus)
        // Jika Anda ingin sengaja melihat BANYAK collision, Anda bisa ubah ke 20 atau 30.
        int ukuranTabel = 101; 
        HashTable ht = new HashTable(ukuranTabel); 
        
        // -------------------------------------------------------------
        // PROSES OTOMATIS: GENERATE 100 DATA RANDOM UNIK SAAT START-UP
        // -------------------------------------------------------------
        Set<Integer> angkaUnik = new HashSet<>();
        Random random = new Random();
        
        // Loop akan berjalan sampai HashSet benar-benar berisi 100 angka berbeda
        while (angkaUnik.size() < 100) {
            // Men-generate angka acak antara 1 sampai 1000
            int angkaRandom = random.nextInt(1000) + 1; 
            angkaUnik.add(angkaRandom); // Jika angka sudah ada, HashSet otomatis menolak
        }
        
        // Masukkan 100 angka unik yang sudah digenerate ke dalam Hash Table
        System.out.println("Memulai program... Menginputkan 100 data random awal secara otomatis:");
        for (int angka : angkaUnik) {
            // Menggunakan nilai angka sebagai Key, dan String sebagai Valuenya
            ht.insert(angka, "Nilai-" + angka); 
        }
        System.out.println("=== 100 Data awal berhasil diinputkan! ===\n");
        // -------------------------------------------------------------

        // Menu Interaktif
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;

        while (pilihan != 5) {
            System.out.println("=== MENU HASH TABLE ===");
            System.out.println("1. Input Data Baru");
            System.out.println("2. Hapus Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Tampilkan Tabel (Visualisasi Chain)");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Key (Angka): ");
                    int inKey = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Masukkan Value (String): ");
                    String inVal = scanner.nextLine();
                    ht.insert(inKey, inVal);
                    break;
                case 2:
                    System.out.print("Masukkan Key yang ingin dihapus: ");
                    int delKey = scanner.nextInt();
                    ht.delete(delKey);
                    break;
                case 3:
                    System.out.print("Masukkan Key yang ingin dicari: ");
                    int searchKey = scanner.nextInt();
                    ht.search(searchKey);
                    break;
                case 4:
                    ht.display();
                    break;
                case 5:
                    System.out.println("Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}