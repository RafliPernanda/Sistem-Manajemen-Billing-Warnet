import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final int MAX_PC = 10;
    private static ArrayList<Akun> daftarAkun = new ArrayList<>();
    private static ArrayList<Billing> daftarBilling = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initData();
        tampilkanMenuUtama();
    }

    private static void initData() {
        daftarAkun.add(new Admin("admin", "admin123"));
    }

    private static void tampilkanMenuUtama() {
        while (true) {
            System.out.println("\n=== SELAMAT DATANG DI WARNET ===");
            System.out.println("1. Login");
            System.out.println("2. Registrasi Pelanggan");
            System.out.println("3. Exit");
            System.out.print("Pilih: ");
            
            int menuAwal = sc.nextInt();
            sc.nextLine(); 

            switch (menuAwal) {
                case 1: handleLogin(); break;
                case 2: handleRegistrasi(); break;
                case 3: 
                    System.out.println("Terima Kasih Telah Menggunakan Sistem Kami!");
                    return; 
                default: 
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void handleLogin() {
        System.out.print("Username: "); String user = sc.nextLine();
        System.out.print("Password: "); String pass = sc.nextLine();

        Akun logedIn = null;
        for (Akun a : daftarAkun) {
            if (a.getUsername().equals(user) && a.getPassword().equals(pass)) {
                logedIn = a;
                break;
            }
        }

        if (logedIn != null) {
            System.out.println("\nLogin Berhasil! Selamat datang, " + logedIn.getUsername());
            
            logedIn.tampilkanTipeAkun(); 

            if (logedIn instanceof Admin) {
                menuAdmin();
            } else if (logedIn instanceof Pelanggan) {
                menuPelanggan((Pelanggan) logedIn);
            }
        } else {
            System.out.println("Login Gagal! Username atau password salah.");
        }
    }

    private static void handleRegistrasi() {
        System.out.print("Masukkan Username Baru: "); String uBaru = sc.nextLine();
        for (Akun a : daftarAkun) {
            if (a.getUsername().equalsIgnoreCase(uBaru)) {
                System.out.println("Gagal! Username sudah digunakan.");
                return;
            }
        }
        System.out.print("Masukkan Password Baru: "); String pBaru = sc.nextLine();
        daftarAkun.add(new Pelanggan(uBaru, pBaru));
        System.out.println("Registrasi Berhasil! Silakan Login.");
    }

    private static void menuAdmin() {
        int pilihan;
        do {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Tambah Billing");
            System.out.println("2. Tampilkan Semua Billing");
            System.out.println("3. Update Durasi");
            System.out.println("4. Hapus Billing");
            System.out.println("5. Logout");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();
            sc.nextLine(); 

            switch (pilihan) {
                case 1: adminTambahBilling(); break;
                case 2: adminTampilkanBilling(); break;
                case 3: adminUpdateDurasi(); break;
                case 4: adminHapusBilling(); break;
                case 5: break; 
                default: System.out.println("Pilihan tidak ada.");
            }
        } while (pilihan != 5);
    }

    private static void adminTambahBilling() {
        System.out.print("Nama Pelanggan: "); String nama = sc.nextLine();
        System.out.print("Nomor PC (1-"+MAX_PC+"): "); int pc = sc.nextInt();
        if (!isPcValidAndTersedia(pc)) return;
        System.out.print("Durasi (Jam): "); int dur = sc.nextInt();
        daftarBilling.add(new Billing(nama, pc, dur));
        System.out.println("Billing berhasil ditambahkan!");
    }

    private static void adminTampilkanBilling() {
        if (daftarBilling.isEmpty()) {
            System.out.println("Belum ada pelanggan yang menyewa PC.");
            return;
        }
        for (Billing b : daftarBilling) {
            b.tampilkanData(true); 
        }
    }

    private static void adminUpdateDurasi() {
        System.out.print("Cari ID Billing: "); int cari = sc.nextInt();
        for (Billing b : daftarBilling) {
            if (b.getIdBilling() == cari) {
                System.out.print("Durasi Baru: ");
                b.setDurasi(sc.nextInt());
                System.out.println("Durasi berhasil diperbarui!");
                return;
            }
        }
        System.out.println("ID Billing tidak ditemukan.");
    }

    private static void adminHapusBilling() {
        System.out.print("Hapus ID Billing: "); int hapus = sc.nextInt();
        for (int i = 0; i < daftarBilling.size(); i++) {
            if (daftarBilling.get(i).getIdBilling() == hapus) {
                daftarBilling.remove(i);
                System.out.println("Data Terhapus!");
                return;
            }
        }
        System.out.println("ID Billing tidak ditemukan.");
    }

    private static void menuPelanggan(Pelanggan user) {
        int pilihan;
        do {
            System.out.println("\n--- MENU PELANGGAN ---");
            System.out.println("1. Sewa PC");
            System.out.println("2. Lihat Tagihan Saya");
            System.out.println("3. Logout");
            System.out.print("Pilih: ");
            pilihan = sc.nextInt();

            switch (pilihan) {
                case 1: pelangganSewaPc(user); break;
                case 2: pelangganLihatBilling(user); break;
                case 3: break;
                default: System.out.println("Pilihan tidak ada.");
            }
        } while (pilihan != 3);
    }

    private static void pelangganSewaPc(Pelanggan user) {
        for (Billing b : daftarBilling) {
            if (b.getNamaPelanggan().equals(user.getUsername())) {
                System.out.println("Gagal! Kamu masih memiliki sewa aktif.");
                return;
            }
        }
        System.out.print("Nomor PC yang ingin disewa (1-"+MAX_PC+"): "); int pcInput = sc.nextInt();
        if (!isPcValidAndTersedia(pcInput)) return;
        System.out.print("Durasi (Jam): "); int durInput = sc.nextInt();
        daftarBilling.add(new Billing(user.getUsername(), pcInput, durInput));
        System.out.println("Berhasil menyewa PC #" + pcInput);
    }

    private static void pelangganLihatBilling(Pelanggan user) {
        boolean ada = false;
        for (Billing b : daftarBilling) {
            if (b.getNamaPelanggan().equals(user.getUsername())) {
                b.tampilkanData(true); 
                ada = true;
            }
        }
        if (!ada) System.out.println("Kamu belum menyewa PC.");
    }

    private static boolean isPcValidAndTersedia(int pc) {
        if (pc < 1 || pc > MAX_PC) {
            System.out.println("Gagal! PC tidak valid. Hanya tersedia PC 1-" + MAX_PC + ".");
            return false;
        }
        for (Billing b : daftarBilling) {
            if (b.getNomorPC() == pc) {
                System.out.println("Gagal! PC sedang digunakan.");
                return false;
            }
        }
        return true;
    }
}