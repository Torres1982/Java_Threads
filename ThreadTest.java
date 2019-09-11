import java.lang.Thread;
import java.util.Arrays;

public class ThreadTest {
    public static void main(String[] args) {
        int threadTasks[] = {2, 5, 8};
        
        try {
            for (int i = 0; i < threadTasks.length; i++) {
                ThreadTask task = new ThreadTask(threadTasks[i]);
                Thread thread = new Thread(new ThreadNumber(task));
                thread.setName("Thread-Multiple-"+i);
                thread.start();
                thread.join();
            }
        } catch(InterruptedException e) {
            System.out.println("Exception occurred! " + e);
        } catch (IllegalThreadStateException e1) {
            System.out.println("Exception occurred! " + e1);
        }
        
        // ThreadTask task1 = new ThreadTask(2);
        // ThreadTask task2 = new ThreadTask(5);
        // ThreadTask task3 = new ThreadTask(8);
        
        // Thread t1 = new Thread(new ThreadNumber(task1));
        // t1.setName("Multiple-2-Thread");
        // Thread t2 = new Thread(new ThreadNumber(task2));
        // t2.setName("Multiple-5-Thread");
        // Thread t3 = new Thread(new ThreadNumber(task3));
        // t3.setName("Multiple-8-Thread");
        
        // try {
        //     t1.start();
        //     t1.join();
        //     t2.start();
        //     t2.join();
        //     t3.start();
        //     t3.join();
        // } catch(InterruptedException e) {
        //     System.out.println("Exception occurred! " + e);
        // } catch (IllegalThreadStateException e1) {
        //     System.out.println("Exception occurred! " + e1);
        // }
    }
}

class ThreadNumber implements Runnable {
    private ThreadTask task;
    
    public ThreadNumber(ThreadTask task) {
        this.task = task;
    }
    
    @Override
    public void run() {
        System.out.println("Waiting for " + Thread.currentThread().getName() +  " to complete...");
        
        synchronized(task) {
            task.displayMultiples(task.getNumber());
        }
        
        System.out.println(Thread.currentThread().getName() + " has completed its task!\n");
    }
}

class ThreadTask {
   private int number;
   
   public int getNumber() { return number; }
   public void setNumber(int number) { this.number = number; }
   
   public ThreadTask(int number) {
       this.number = number;
   }
   
   public void displayMultiples(int number) {
       int nextMultiple = 0;
       
        for (int i = 0; i < 3; i++) {
            nextMultiple += number;
            System.out.println(nextMultiple);
            
            try {
                Thread.currentThread().sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted!");
            }
        }
   }
}
