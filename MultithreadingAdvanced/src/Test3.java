import java.util.concurrent.Semaphore;

public class Test3 {

    //信号量 Semaphore
    //锁是信号量里第一种特殊情况，叫做：二元信号量
    // 每次申请一个可用资源，计数器就-1（就是 P 操作），每次释放一个可用资源，计数器就+1（就是 V 操作）
    // 当信号量的计数已经是 0 了，再次进行 P 操作，就会阻塞等待。
    // 锁就可以视为“二元信号量”，可用资源就一个，计数器的取值，非 0 即1
    // 信号量就是把锁推广到了一般情况，可用资源不只有一个的时候，该怎么处理？

    public static void main(String[] args) throws InterruptedException {
        //当申请的资源比资源数多了之后，就进入阻塞状态。
        Semaphore semaphore = new Semaphore(4);
        semaphore.acquire();
        System.out.println("申请成功");
        semaphore.acquire();
        System.out.println("申请成功");
        semaphore.acquire();
        System.out.println("申请成功");
        semaphore.acquire();
        System.out.println("申请成功");
        //因为资源已经被使用完了，所以这里就是阻塞状态了。
        semaphore.acquire();
        System.out.println("申请成功");
    }

    public static void main1(String[] args) throws InterruptedException {
        //表示可用资源有 4 个
        // 当申请的资源比资源数多了之后，就进入阻塞状态。
        Semaphore semaphore = new Semaphore(4);
        //申请资源，P 操作
        semaphore.acquire(2);
        //释放资源 V 操作
        semaphore.release(2);
    }
}
