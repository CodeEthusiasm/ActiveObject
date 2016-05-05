/**
 * Created by lstrzalk on 27.04.16.
 */
import java.util.Random;
public class Consument extends Thread{

    private final int id;
    private final Resource res;
    private final Proxy proxy;
    private final Random rand = new Random();
    private int amount;

    public Consument(Resource res, Proxy proxy, int id){
        this.id=id;
        this.res = res;
        this.proxy = proxy;
        this.amount = rand.nextInt(100)+1;
        this.start();
    }
    @Override
    public void run(){
       boolean checker=true;
        long i=0;
        int j=0;
        while(Main.end) {
            Future f = proxy.methodConsume(amount, id);
            checker = true;
            while (!f.isAvailable()) {
                //i++;
               /* if(checker==true) {
                    for (i = 0; i < 1000000; i++) ;
                    checker=false;
                }*/
                //else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
            }
            //System.out.println("Consumer  " + id + " has just eaten " + amount + " buff = " + res.currentlyInBuff() + " i = " + i);
            j++;
        }
    }
}