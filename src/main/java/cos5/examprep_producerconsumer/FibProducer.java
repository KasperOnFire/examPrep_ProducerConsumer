package cos5.examprep_producerconsumer;

import java.util.concurrent.BlockingQueue;

public class FibProducer implements Runnable {

    protected BlockingQueue s1 = null;
    protected BlockingQueue s2 = null;

    public FibProducer(BlockingQueue s1, BlockingQueue s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        boolean moreNumbers = true;
        while (moreNumbers) {
            try {
                Object n = s1.poll();
                if (n == null) {
                    moreNumbers = false;
                } else {
                    System.out.println("Calculating " + n);
                    s2.put(fib((long) n));
                }
            } catch (InterruptedException e) {

            }
        }

    }

    private long fib(long n) {
        if ((n == 0) || (n == 1)) {
            return n;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

}
