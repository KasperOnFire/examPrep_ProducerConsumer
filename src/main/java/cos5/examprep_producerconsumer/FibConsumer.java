package cos5.examprep_producerconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FibConsumer implements Runnable {

    protected BlockingQueue s2 = null;

    public FibConsumer(BlockingQueue s2) {
        this.s2 = s2;
    }

    @Override
    public void run() {
        boolean moreNumbers = true;
        int sumTotal = 0;
        while (moreNumbers) {
            try {
                Object o = s2.take();
                if (o == null) {
                    moreNumbers = false;
                } else {
                    long n = (long) o;
                    System.out.println("New number: " + n);
                    sumTotal += n;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(FibConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Final Total: " + sumTotal);
    }

}
