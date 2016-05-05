/**
 * Created by lstrzalk on 27.04.16.
 */
public class Scheduler extends Thread {
    private ActivationQueue aq;
    private ActivationQueue expectant;

    public Scheduler() {
        aq = new ActivationQueue();
        expectant=new ActivationQueue();
    }

    public void enqueue(MethodRequest mr) {
        aq.enqueue(mr);
    }

    @Override
    public void run(){
        boolean locker=false;
        while (Main.end) {
            MethodRequest mr = null;
            if (expectant.isQueueEmpty()) {
                try {
                    mr = aq.dequeue();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                if (locker == true) {
                    try {
                        mr = aq.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    locker = false;
                } else {
                    try {
                        mr = expectant.dequeue();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


            if (mr != null) {
                if (mr.guard())
                    mr.execute();
                else {
                    expectant.enqueue(mr);
                    locker = true;
                }
            }
        }
    }
}