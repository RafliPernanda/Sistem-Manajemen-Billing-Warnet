public class Billing {
    private static int counter = 1;

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

    public int getIdBilling() {
        return idBilling;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public int getNomorPC() {
        return nomorPC;
    }

    public int getDurasi() {
        return durasi;
    }


    public void setNamaPelanggan(String namaPelanggan) {
        if (namaPelanggan != null && !namaPelanggan.trim().isEmpty()) {
            this.namaPelanggan = namaPelanggan;
        } else {
            System.out.println("Nama pelanggan tidak boleh kosong!");
        }
    }

    public void setNomorPC(int nomorPC) {
        if (nomorPC >= 1 && nomorPC <= 10) {
            this.nomorPC = nomorPC;
        } else {
            System.out.println("Nomor PC tidak valid!");
        }
    }

    public void setDurasi(int durasi) {
        if (durasi > 0) {
            this.durasi = durasi;
        } else {
            System.out.println("Durasi harus lebih dari 0 jam!");
        }
    }

    public void tampilkanData() {
        System.out.println("ID: " + getIdBilling() +
                           " | Pelanggan: " + getNamaPelanggan() +
                           " | PC: " + getNomorPC() +
                           " | Durasi: " + getDurasi() + " Jam");
    }
}