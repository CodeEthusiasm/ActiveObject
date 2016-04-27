/**
 * Created by lstrzalk on 27.04.16.
 */
public class Scheduler extends Thread{
    private ActivationQueue aq;
    public Scheduler(){
        aq = new ActivationQueue();
    }
    public void enqueue(MethodRequest mr){aq.enqueue(mr);}

    @Override
    public void run(){
        while(true){
            MethodRequest mr = aq.dequeue();

            if(mr!=null){
                if(mr.guard())
                    mr.execute();
                else
                    aq.enqueue(mr);
            }
        }
    }
}