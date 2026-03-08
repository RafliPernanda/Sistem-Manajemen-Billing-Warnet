public class Billing {
    public static int counter = 1; // Penghitung otomatis
    public int idBilling;
    public String namaPelanggan;
    public int nomorPC;
    public int durasi;

    public Billing(String namaPelanggan, int nomorPC, int durasi) {
        this.idBilling = counter++; // ID ngambil dari counter, lalu counter naik 1
        this.namaPelanggan = namaPelanggan;
        this.nomorPC = nomorPC;
        this.durasi = durasi;
    }

    public void tampilkanData() {
        System.out.println("ID: " + idBilling + " | Pelanggan: " + namaPelanggan + 
                           " | PC: " + nomorPC + " | Durasi: " + durasi + " Jam");
    }
}