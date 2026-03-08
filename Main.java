import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Akun> daftarAkun = new ArrayList<>();
        ArrayList<Billing> daftarBilling = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        // Akun Admin Default
        daftarAkun.add(new Akun("admin", "admin123", "admin"));

        while (true) {
            System.out.println("\n=== SELAMAT DATANG DI WARNET ===");
            System.out.println("1. Login");
            System.out.println("2. Registrasi Pelanggan");
            System.out.println("3. Exit");
            System.out.print("Pilih: ");
            int menuAwal = sc.nextInt();

            if (menuAwal == 1) {
                System.out.print("Username: "); String user = sc.next();
                System.out.print("Password: "); String pass = sc.next();
                
                Akun logedIn = null;
                for (Akun a : daftarAkun) {
                    if (a.username.equals(user) && a.password.equals(pass)) {
                        logedIn = a;
                        break;
                    }
                }

                if (logedIn != null) {
                    System.out.println("\nLogin Berhasil! Selamat datang, " + logedIn.username);
                    
                    if (logedIn.role.equals("admin")) {
                        int pAdmin;
                        do {
                            System.out.println("\n--- MENU ADMIN ---");
                            System.out.println("1. Tambah Billing (Auto ID)");
                            System.out.println("2. Tampilkan Semua Billing");
                            System.out.println("3. Update Durasi");
                            System.out.println("4. Hapus Billing");
                            System.out.println("5. Logout");
                            System.out.print("Pilih: ");
                            pAdmin = sc.nextInt();

                            if (pAdmin == 1) {
                                // ID TIDAK DIINPUT MANUAL LAGI
                                System.out.print("Nama Pelanggan: "); String nama = sc.next();
                                System.out.print("Nomor PC: "); int pc = sc.nextInt();
                                
                                // Validasi PC oleh Admin
                                boolean pcTersedia = true;
                                for (Billing b : daftarBilling) {
                                    if (b.nomorPC == pc) { pcTersedia = false; break; }
                                }

                                if (pcTersedia) {
                                    System.out.print("Durasi: "); int dur = sc.nextInt();
                                    // Panggil constructor yang baru (3 parameter saja)
                                    daftarBilling.add(new Billing(nama, pc, dur));
                                    System.out.println("Billing berhasil ditambahkan!");
                                } else {
                                    System.out.println("Gagal! PC sedang digunakan.");
                                }

                            } else if (pAdmin == 2) {
                                if (daftarBilling.isEmpty()) {
                                    System.out.println("Belum ada pelanggan yang menyewa PC.");
                                } else {
                                    for (Billing b : daftarBilling) b.tampilkanData();
                                }
                            } else if (pAdmin == 3) {
                                if (daftarBilling.isEmpty()) {
                                    System.out.println("Belum ada pelanggan yang menyewa PC.");
                                } else {
                                    System.out.print("Cari ID Billing: "); int cari = sc.nextInt();
                                    boolean ditemukan = false;
                                    for (Billing b : daftarBilling) {
                                        if (b.idBilling == cari) {
                                            System.out.print("Durasi Baru: "); b.durasi = sc.nextInt();
                                            System.out.println("Durasi berhasil diperbarui!");
                                            ditemukan = true;
                                            break;
                                        }
                                    }
                                    if (!ditemukan) System.out.println("ID Billing tidak ditemukan.");
                                }
                            } else if (pAdmin == 4) {
                                if (daftarBilling.isEmpty()) {
                                    System.out.println("Belum ada pelanggan yang menyewa PC.");
                                } else {
                                    System.out.print("Hapus ID Billing: "); int hapus = sc.nextInt();
                                    boolean ditemukanHapus = false;
                                    for (int i = 0; i < daftarBilling.size(); i++) {
                                        if (daftarBilling.get(i).idBilling == hapus) {
                                            daftarBilling.remove(i);
                                            System.out.println("Data Terhapus!");
                                            ditemukanHapus = true;
                                            break;
                                        }
                                    }
                                    if (!ditemukanHapus) System.out.println("ID Billing tidak ditemukan.");
                                }
                            }
                        } while (pAdmin != 5);

                    } else {
                        int pUser;
                        do {
                            System.out.println("\n--- MENU PELANGGAN ---");
                            System.out.println("1. Sewa PC (Tambah Billing)");
                            System.out.println("2. Lihat Billing Saya");
                            System.out.println("3. Logout");
                            System.out.print("Pilih: ");
                            pUser = sc.nextInt();

                            if (pUser == 1) {
                                System.out.print("Nomor PC yang ingin disewa: ");
                                int pcInput = sc.nextInt();
                                
                                boolean pcTersedia = true;
                                for (Billing b : daftarBilling) {
                                    if (b.nomorPC == pcInput) {
                                        pcTersedia = false;
                                        break;
                                    }
                                }

                                if (pcTersedia) {
                                    System.out.print("Durasi (Jam): ");
                                    int durInput = sc.nextInt();
                                    // Menggunakan constructor yang sama dengan Admin
                                    daftarBilling.add(new Billing(logedIn.username, pcInput, durInput));
                                    System.out.println("Berhasil menyewa PC #" + pcInput);
                                } else {
                                    System.out.println("Maaf, PC #" + pcInput + " sedang digunakan!");
                                }
                            } else if (pUser == 2) {
                                boolean ada = false;
                                for (Billing b : daftarBilling) {
                                    if (b.namaPelanggan.equals(logedIn.username)) {
                                        b.tampilkanData();
                                        ada = true;
                                    }
                                }
                                if (!ada) System.out.println("Kamu belum menyewa PC.");
                            }
                        } while (pUser != 3);
                    }
                } else {
                    System.out.println("Login Gagal!");
                }

            } else if (menuAwal == 2) {
                System.out.print("Username Baru: "); String uBaru = sc.next();
                System.out.print("Password Baru: "); String pBaru = sc.next();
                daftarAkun.add(new Akun(uBaru, pBaru, "pelanggan"));
                System.out.println("Registrasi Berhasil!");
            } else if (menuAwal == 3) {
                System.out.println("Terima Kasih Telah Menggunakan Sistem Kami!");
                break;
            }
        }
    }
}