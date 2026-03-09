# Post Test Praktikum Pemrograman Berorientasi Objek
**Tema:** Sistem Manajemen Billing Warnet  
**Nama:** Muhammad Rafli Pernanda  
**NIM:** 2409106040  
**Program Studi:** Informatika - Universitas Mulawarman

---

## 1. Deskripsi Program
Program ini merupakan sistem manajemen operasional warnet yang mengelola penyewaan PC. Program mendukung sistem multi-user (Admin dan Pelanggan) dengan fitur dasar seperti login dan registrasi. Implementasi menggunakan bahasa Java dengan konsep Pemrograman Berorientasi Objek (OOP).

## 2. Struktur Class
Program ini menggunakan lebih dari 2 class untuk mengorganisir data:
1. **`Akun.java`**: Menyimpan kredensial user (Username, Password) dan peran (Admin/Pelanggan).
2. **`Billing.java`**: Menyimpan detail transaksi penyewaan, nomor PC, durasi, dan ID unik.
3. **`Main.java`**: Pengendali alur utama program, validasi input, dan manajemen ArrayList.



## 3. Fitur Utama (CRUD)
* **Create**: Admin dapat menambah billing; Pelanggan dapat melakukan penyewaan PC mandiri.
* **Read**: Menampilkan seluruh data penyewaan (Admin) atau billing milik pribadi (Pelanggan).
* **Update**: Admin memiliki otoritas untuk memperbarui durasi billing jika pelanggan menambah waktu.
* **Delete**: Admin dapat menghapus data billing setelah sesi pelanggan berakhir.

## 4. Keunggulan & Validasi Program
Untuk menjamin integritas data, program ini dilengkapi dengan:
* **Unique ID & Username**: ID Billing otomatis (auto-increment) dan pencegahan duplikasi username saat registrasi.
* **Resource Validation**: Pembatasan nomor PC (1-10) dan pengecekan ketersediaan PC (satu PC hanya untuk satu user).
* **Robust Input**: Penggunaan `nextLine()` dan pembersihan buffer Scanner untuk menangani input nama yang mengandung spasi (contoh: "Budiono Siregar").
* **Single Session**: Pelanggan tidak dapat menyewa lebih dari satu PC secara bersamaan.

## 5. Dokumentasi Program (Screenshot)

### A. Menu Awal & Registrasi
![Menu Awal](/assets/menu-awal.png)
*Keterangan: Tampilan awal program untuk Login dan Registrasi.*

![Registrasi](/assets/registrasi.png)
*Keterangan: Halaman registrasi untuk membuat akun baru, memastikan username unik dan role ditentukan.*

---

### B. Menu Admin & Fitur CRUD
![Menu Admin](/assets/menu-admin.png)
*Keterangan: Setelah login sebagai admin, menampilkan opsi untuk mengelola billing dan user.*

![Admin Create](/assets/admin-create.png)
*Keterangan: Form untuk menambahkan transaksi billing baru beserta nomor PC dan durasi.*

![Admin Read](/assets/admin-read.png)
*Keterangan: Daftar semua billing yang tercatat; data dapat dilihat penuh oleh admin.*

![Admin Update](/assets/admin-update.png)
*Keterangan: Admin dapat mengubah durasi atau detail lain pada billing yang sedang berjalan.*

![Admin Delete](/assets/admin-delete.png)
*Keterangan: Fitur untuk menghapus billing setelah pelanggan selesai menggunakan PC.*

---

### C. Menu Pengguna & Operasi Mandiri
![Menu User](/assets/menu-user.png)
*Keterangan: Tampilan awal untuk pelanggan setelah login, menampilkan opsi sewa PC atau melihat riwayat billing.*

![User Create](/assets/user-create.png)
*Keterangan: Pelanggan dapat membuat bill sendiri dengan memilih nomor PC yang tersedia serta durasi.*

![User Read](/assets/user-read.png)
*Keterangan: Pelanggan hanya dapat melihat riwayat billing miliknya sendiri untuk menjaga privasi.*

---

### D. Penutup
Dokumentasi ini melengkapi deskripsi fitur, alur, dan validasi program. Selamat menggunakan Sistem Manajemen Billing Warnet! Jika ada saran atau temuan bug, silakan hubungi pengembang.

---