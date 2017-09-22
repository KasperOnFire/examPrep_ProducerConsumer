package cos5.examprep_producerconsumer;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FibRunner {

    public static void main(String[] args) {

        /*
        General part:
        We use threads when we  have a task (or several) that does not need to be done by one person.
        Just like dividing work in a group - you split the task up to do it faster.
        A Race condition is when two or more threads try to change the same data on the same time. WHen this happens, you might end up
        with something completely different from expected. You fix this by synchronizing objects, meaning that a synchronized object/method has to be done
        before any other thread can access it.
        The producer/Consumer problem is that sometime you might have a thread waiting for objects, since one side may be faster than the other. This is solved in
        java by making a queue of objects, here a BlockingQueue, that the producers can add items to, and the consumers can take items from.
      
        i had trouble making the program terminate, since the hiunts say to use put() and take() on s2. But if my consumer has take() it never ends,
        since it will wait until an object appears. I changed that to poll() with a 3 second timeout, to give the producerthreads a chance to finish.
         */
        BlockingQueue s1 = new ArrayBlockingQueue(12);
        s1.add(4L);
        s1.add(5L);
        s1.add(8L);
        s1.add(12L);
        s1.add(21L);
        s1.add(22L);
        s1.add(34L);
        s1.add(35L);
        s1.add(36L);
        s1.add(37L);
        s1.add(42L);

        BlockingQueue s2 = new ArrayBlockingQueue(12);
        runThreads(6, s1, s2);

    }

    public static void runThreads(int count, BlockingQueue s1, BlockingQueue s2) {
        try {
            ExecutorService es = Executors.newCachedThreadPool();
            for (int i = 0; i < count; i++) {
                es.execute(new FibProducer(s1, s2));
            }
            es.execute(new FibConsumer(s2));
            es.shutdown();
            es.awaitTermination(5, TimeUnit.SECONDS);
            System.out.println("Closing Down");
        } catch (InterruptedException ex) {
            Logger.getLogger(FibRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
