/**
 * Created by lstrzalk on 27.04.16.
 */
public class Scheduler extends Thread {
    private ActivationQueue aq;

    public Scheduler() {
        aq = new ActivationQueue();
    }

    public void enqueue(MethodRequest mr) {
        aq.enqueue(mr);
    }

    @Override
    public void run(){
        while (true) {
            MethodRequest mr = null;
            try {
                mr = aq.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(mr!=null) {
                if (mr.guard()) {
                    aq.remove(mr);
                    mr.execute();
                } else {
                    if (mr instanceof MethodRequestConsume) {
                        try {
                            aq.prodDequeue().execute();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            aq.consDequeue().execute();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}