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
                sc.nextLine();
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

                    if (logedIn.getRole().equals("admin")) {
                        int pAdmin;
                        do {
                            System.out.println("\n--- MENU ADMIN ---");
                            System.out.println("1. Tambah Billing");
                            System.out.println("2. Tampilkan Semua Billing");
                            System.out.println("3. Update Durasi");
                            System.out.println("4. Hapus Billing");
                            System.out.println("5. Logout");
                            System.out.print("Pilih: ");
                            pAdmin = sc.nextInt();

                            if (pAdmin == 1) {
                                sc.nextLine();
                                System.out.print("Nama Pelanggan: "); String nama = sc.nextLine();
                                System.out.print("Nomor PC: "); int pc = sc.nextInt();

                                if (pc < 1 || pc > 10) {
                                    System.out.println("Gagal! Nomor PC tidak valid. Hanya tersedia PC 1-10.");
                                } else {
                                    boolean pcTersedia = true;
                                    for (Billing b : daftarBilling) {
                                        if (b.getNomorPC() == pc) { pcTersedia = false; break; }
                                    }

                                    if (pcTersedia) {
                                        System.out.print("Durasi: "); int dur = sc.nextInt();
                                        daftarBilling.add(new Billing(nama, pc, dur));
                                        System.out.println("Billing berhasil ditambahkan!");
                                    } else {
                                        System.out.println("Gagal! PC sedang digunakan.");
                                    }
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
                                        if (b.getIdBilling() == cari) {
                                            System.out.print("Durasi Baru: ");
                                            int durasiBaru = sc.nextInt();
                                            b.setDurasi(durasiBaru);
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
                                        if (daftarBilling.get(i).getIdBilling() == hapus) {
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
                                boolean sudahSewa = false;
                                for (Billing b : daftarBilling) {
                                    if (b.getNamaPelanggan().equals(logedIn.getUsername())) {
                                        sudahSewa = true;
                                        break;
                                    }
                                }

                                if (sudahSewa) {
                                    System.out.println("Gagal! Kamu masih memiliki billing aktif. Selesaikan dulu sewa sebelumnya.");
                                } else {
                                    System.out.print("Nomor PC yang ingin disewa (1-10): ");
                                    int pcInput = sc.nextInt();

                                    if (pcInput < 1 || pcInput > 10) {
                                        System.out.println("Gagal! Nomor PC tidak valid. Hanya tersedia PC 1-10.");
                                    } else {
                                        boolean pcTersedia = true;
                                        for (Billing b : daftarBilling) {
                                            if (b.getNomorPC() == pcInput) {
                                                pcTersedia = false;
                                                break;
                                            }
                                        }

                                        if (pcTersedia) {
                                            System.out.print("Durasi (Jam): ");
                                            int durInput = sc.nextInt();
                                            daftarBilling.add(new Billing(logedIn.getUsername(), pcInput, durInput));
                                            System.out.println("Berhasil menyewa PC #" + pcInput);
                                        } else {
                                            System.out.println("Maaf, PC #" + pcInput + " sedang digunakan pelanggan lain!");
                                        }
                                    }
                                }
                            } else if (pUser == 2) {
                                boolean ada = false;
                                for (Billing b : daftarBilling) {
                                    if (b.getNamaPelanggan().equals(logedIn.getUsername())) {
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
                sc.nextLine();
                System.out.print("Masukkan Username Baru: "); String uBaru = sc.nextLine();

                boolean usernameExist = false;
                for (Akun a : daftarAkun) {
                    if (a.getUsername().equalsIgnoreCase(uBaru)) {
                        usernameExist = true;
                        break;
                    }
                }

                if (usernameExist) {
                    System.out.println("Gagal! Username sudah digunakan. Silakan cari nama lain.");
                } else {
                    System.out.print("Masukkan Password Baru: ");
                    String pBaru = sc.nextLine();
                    daftarAkun.add(new Akun(uBaru, pBaru, "pelanggan"));
                    System.out.println("Registrasi Berhasil! Silakan Login.");
                }
            } else if (menuAwal == 3) {
                System.out.println("Terima Kasih Telah Menggunakan Sistem Kami!");
                break;
            }
        }
    }
}