/**
 * Created by lstrzalk on 27.04.16.
 */
public class Main {
    public static void main (String [] args){
        Scheduler scheduler = new Scheduler();
        Resource resource = new Resource(200);
        Proxy proxy = new Proxy(scheduler, resource);
        for (int i = 0; i < 200; i++){
            new Producer(resource, proxy,i+1).start();
            new Consument(resource, proxy,i+1).start();
        }
    }
}