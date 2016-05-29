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
        if(activationQueue.peek() instanceof MethodRequestConsume)
            return consumeQueue.peek();
        else
            return produceQueue.peek();
    }
    public synchronized MethodRequestConsume consDequeue() throws InterruptedException {
        if(consumeQueue.isEmpty())
        {
            waited=true;
            this.wait();
        }
        MethodRequestConsume cons=consumeQueue.poll();
        activationQueue.remove(cons);
        return cons;
    }
    public synchronized MethodRequestProduce prodDequeue() throws InterruptedException {
        if(produceQueue.isEmpty())
        {
            waited=true;
            this.wait();
        }
        MethodRequestProduce prod=produceQueue.poll();
        activationQueue.remove(prod);
        return prod;
    }
    public synchronized void remove(MethodRequest mr){
        if(mr instanceof MethodRequestConsume)
            consumeQueue.remove(mr);
        else
            produceQueue.remove(mr);
        activationQueue.remove(mr);
    }
}