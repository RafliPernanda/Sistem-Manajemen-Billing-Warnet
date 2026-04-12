public class Admin extends Akun {
    
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void tampilkanTipeAkun() {
        System.out.println("[Login sebagai: Administrator Sistem]");
    }
}