public class Billing {
    private static int counter = 1;
    private static final int HARGA_PER_JAM = 5000; 

    private int idBilling;
    private String namaPelanggan;
    private int nomorPC;
    private int durasi;

    public Billing(String namaPelanggan, int nomorPC, int durasi) {
        this.idBilling = counter++;
        this.namaPelanggan = namaPelanggan;
        this.nomorPC = nomorPC;
        this.durasi = durasi;
    }

    public int getIdBilling() { return idBilling; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public int getNomorPC() { return nomorPC; }
    public int getDurasi() { return durasi; }

    public void setDurasi(int durasi) {
        if (durasi > 0) {
            this.durasi = durasi;
        } else {
            System.out.println("Durasi harus lebih dari 0 jam!");
        }
    }

    public void tampilkanData() {
        System.out.println("ID: " + idBilling + " | Pelanggan: " + namaPelanggan +
                           " | PC: " + nomorPC + " | Durasi: " + durasi + " Jam");
    }

    public void tampilkanData(boolean denganBiaya) {
        tampilkanData(); 
        if (denganBiaya) {
            System.out.println("--> Total Biaya: Rp. " + (durasi * HARGA_PER_JAM));
        }
    }
}