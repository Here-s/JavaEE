class MyBlockQueue {
    private int[] data = new int[1000];
    private int size = 0;
    private int head = 0;
    private int tail = 0;

    //专门的锁对象
    private Object locker = new Object();

    public void put(int value) throws InterruptedException {
        synchronized (locker) {
            if (size == data.length) {
                //队列满了。针对哪个对象加锁，就使用哪个对象 wait
                //put 当中的 wait 要由 take 来唤醒，只要 take 成功一个元素，就可以唤醒了
                locker.wait();
            }
            //队列不满，把新的元素放入 tail 位置上
            data[tail] = value;
            tail++;
            //处理 tail 到达数组末尾的情况
            if (tail >= data.length) {
                tail = 0;
            }
            size++;
            locker.notify();
        }
    }
    public Integer take() throws InterruptedException {
        synchronized (locker) {
            if (size == 0) {
                //说明队列为空，就需要等待，就需要 put 来唤醒
                locker.wait();
            }
            int ret = data[head];
            head++;
            if (head >= data.length) {
                head = 0;
            }
            size--;
            //就说明 take 成功了。然后唤醒 put 中的等待。
            locker.notify();
            return ret;
        }
    }
}
public class MyBlockingQueue {

    //自己实现阻塞队列。先实现普通队列，然后加上线程，再加上阻塞。这里用数组来实现（循环队列）
    //实现循环队列的时候，如何区分是空队列还是满队列。浪费一个格子，head = tail 就认为空
    // head == tail + 1 就认为是满
    //方法二：创建一个变量 size 记录元素个数，如果 size == 0 就是空，如果等于数组大小就是满
    //线程安全就是：保证多线程环境下，调用 put 和 take 没有问题
    // 看了之后，put 和 take 里面的每一行代码都是操作公共的变量。所以直接给整个方法加锁就好了
    // 加了锁之后，就已经是线程安全了，然后就是实现阻塞效果：使用 wait 和 notify 机制
    //  阻塞条件：对于 put 来说：就是队列为满。对于 take 来说：就是队列为空。

    //实现一个简单的 生产者消费者 模型
    private static MyBlockQueue queue = new MyBlockQueue();
    public static void main(String[] args) {

        //如果有多个生产者和多个消费者，就再多创建几个线程
        Thread producer = new Thread(() -> {
            int num = 0;
            while (true) {
                try {
                    System.out.println("生产了：" + num);
                    queue.put(num);
                    num++;
                    //生产者生产的慢了，消费者就得等着生产
                    //Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();

        Thread customer = new Thread(() -> {
            while (true) {
                int num = 0;
                try {
                    num = queue.take();
                    System.out.println("消费了：" + num);
                    //消费慢了，但是可以一直生产。1000 之后，队列满了，所以就阻塞了。直到消费了一个。
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        customer.start();
    }
}
