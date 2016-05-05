/**
 * Created by lstrzalk on 27.04.16.
 */
import java.util.Random;

public class Producer extends Thread {
    private final int id;
    private final Resource res;
    private final Proxy proxy;
    private final Random rand = new Random();
    private int amount;
    public Producer(Resource res, Proxy proxy, int id){
        this.id = id;
        this.res = res;
        this.proxy = proxy;
        this.amount = rand.nextInt(100)+1;
        this.start();
    }
    @Override
    public void run(){
        long i=0;
        int j=0;
        while(Main.end){
            Future f = proxy.methodProduce(amount, id);
            while(!f.isAvailable()) {
                //i++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //System.out.println("Producer  "+id+ " has just produced " + amount+" buff = "+res.currentlyInBuff() +" i = "+i);
            j++;
        }
    }
}