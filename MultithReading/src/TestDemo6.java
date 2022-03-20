public class TestDemo6 {

    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            System.out.println("Thread");
        });
        t.start();
    }
}
