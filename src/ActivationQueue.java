/**
 * Created by lstrzalk on 27.04.16.
 */
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
public class ActivationQueue {
    private Queue<MethodRequest> activationQueue;
    private Queue<MethodRequestProduce> produceQueue;
    private Queue<MethodRequestConsume> consumeQueue;
    boolean waited=false;
    public ActivationQueue(){
        activationQueue = new ConcurrentLinkedQueue<MethodRequest>();
        produceQueue = new ConcurrentLinkedQueue<MethodRequestProduce>();
        consumeQueue = new ConcurrentLinkedQueue<MethodRequestConsume>();
    }


    public synchronized void enqueue(MethodRequest mr){
        if(waited)
        {
            waited=false;
            notify();
        }
        activationQueue.add(mr);
        if(mr instanceof MethodRequestConsume)
            consumeQueue.add((MethodRequestConsume)mr);
        else
            produceQueue.add((MethodRequestProduce)mr);
    }
    public synchronized MethodRequest dequeue() throws InterruptedException {
        if(activationQueue.isEmpty())
        {
            waited=true;
            this.wait();
        }
        if(activationQueue.poll() instanceof MethodRequestConsume)
            return consumeQueue.poll();
        else
            return produceQueue.poll();
    }
    public boolean isQueueEmpty(){
        return activationQueue.isEmpty();
    }
}