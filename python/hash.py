import random

class HashTable:
    def __init__(self, size):
        self.table_size = size
        # Membuat array berisi list kosong untuk menampung chaining [[key, value], ...]
        self.table = [[] for _ in range(self.table_size)]

    # Fungsi Hash menggunakan modulo
    def _hash_function(self, key):
        return key % self.table_size

    # 1. INPUT DATA
    def insert(self, key, value):
        index = self._hash_function(key)
        
        # Cek jika key sudah ada di dalam chain, jika ada maka update nilainya
        for item in self.table[index]:
            if item[0] == key:
                item[1] = value
                print(f"Data dengan key {key} berhasil diupdate pada indeks {index}.")
                return
        
        # Jika belum ada, tambahkan pasangan [key, value] baru ke dalam list (Separate Chaining)
        self.table[index].append([key, value])
        
        # Deteksi collision (jika isi list di indeks tersebut lebih dari 1)
        if len(self.table[index]) > 1:
            print(f"Data {key} masuk ke indeks {index} (Terjadi Collision! Ditangani dengan Separate Chaining).")
        else:
            print(f"Data {key} berhasil dimasukkan ke indeks {index}.")

    # 2. HAPUS DATA
    def delete(self, key):
        index = self._hash_function(key)
        for item in self.table[index]:
            if item[0] == key:
                self.table[index].remove(item)
                print(f"Data dengan key {key} berhasil dihapus dari indeks {index}.")
                return
        print(f"Gagal: Data dengan key {key} tidak ditemukan!")

    # 3. CARI DATA
    def search(self, key):
        index = self._hash_function(key)
        for item in self.table[index]:
            if item[0] == key:
                print(f"Data Ditemukan! Key: {key}, Value: {item[1]} (Berada di indeks {index})")
                return
        print(f"Hasil: Data dengan key {key} tidak ditemukan!")

    # Menampilkan visualisasi Hash Table beserta rantainya
    def display(self):
        print("\n--- Visualisasi Hash Table ---")
        for i in range(self.table_size):
            if self.table[i]:
                # Menggabungkan data chain menjadi bentuk visual [k:v] -> [k:v]
                chain = " -> ".join([f"[{item[0]} : {item[1]}]" for item in self.table[i]])
                print(f"Indeks {i}: {chain} -> None")
            else:
                print(f"Indeks {i}: None")
        print("------------------------------\n")


def main():
    # Menggunakan ukuran tabel 41 (Angka prima agar sebaran data bagus)
    # Dengan 100 data, rata-rata setiap indeks akan terisi 2-3 data (terjadi collision sehat)
    ukuran_tabel = 41
    ht = HashTable(ukuran_tabel)

    # -------------------------------------------------------------
    # PROSES OTOMATIS: GENERATE 100 DATA RANDOM UNIK SAAT START-UP
    # -------------------------------------------------------------
    # random.sample mengambil 100 angka acak unik dari rentang 1-1000 secara instan
    angka_unik = random.sample(range(1, 1001), 100)

    print("Memulai program... Menginputkan 100 data random awal secara otomatis:\n")
    for angka in angka_unik:
        ht.insert(angka, f"Nilai-{angka}")
    print("\n=== 100 Data awal berhasil dimasukkan ke dalam Hash Table! ===\n")
    # -------------------------------------------------------------

    # Menu Interaktif
    while True:
        print("=== MENU HASH TABLE (PYTHON) ===")
        print("1. Input Data Baru")
        print("2. Hapus Data")
        print("3. Cari Data")
        print("4. Tampilkan Tabel (Visualisasi Chain)")
        print("5. Keluar")
        
        pilihan = input("Pilih menu: ")
        
        if pilihan == '1':
            try:
                key = int(input("Masukkan Key (Angka): "))
                value = input("Masukkan Value (String): ")
                ht.insert(key, value)
            except ValueError:
                print("Input tidak valid! Key harus berupa angka.")
        elif pilihan == '2':
            try:
                key = int(input("Masukkan Key yang ingin dihapus: "))
                ht.delete(key)
            except ValueError:
                print("Input tidak valid! Key harus berupa angka.")
        elif pilihan == '3':
            try:
                key = int(input("Masukkan Key yang ingin dicari: "))
                ht.search(key)
            except ValueError:
                print("Input tidak valid! Key harus berupa angka.")
        elif pilihan == '4':
            ht.display()
        elif pilihan == '5':
            print("Program selesai. Sampai jumpa!")
            break
        else:
            print("Pilihan tidak valid! Silakan masukkan angka 1-5.")

if __name__ == "__main__":
    main()