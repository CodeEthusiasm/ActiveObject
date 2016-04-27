/**
 * Created by lstrzalk on 27.04.16.
 */
import java.util.Random;
public class Consument extends Thread{

    private final int id;
    private final Resource res;
    private final Proxy proxy;
    private final Random rand = new Random();
    private final int amount;

    public Consument(Resource res, Proxy proxy, int id){
        this.id=id;
        this.res = res;
        this.proxy = proxy;
        this.amount = rand.nextInt(100)+1;
    }
    @Override
    public void run(){
        while(true){
            Future f = proxy.methodConsume(amount, id);
            while(!f.isAvailable()){
                try{
                    Thread.sleep(2000);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println("Consumer  "+id+ "has just eaten " + amount + "elements. Currently in resource: "+ res.getEnd());
        }
    }
}