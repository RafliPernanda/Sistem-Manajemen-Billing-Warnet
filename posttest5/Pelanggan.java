public class Pelanggan extends Akun {
    
    public Pelanggan(String username, String password) {
        super(username, password);
    }

    @Override
    public void tampilkanTipeAkun() {
        System.out.println("[Login sebagai: Pelanggan Warnet]");
    }
}