/**
 * Created by lstrzalk on 27.04.16.
 */
public class Main {
    public static int overallPoduced=0;
    public static int overallConsumed=0;
    public static boolean end=true;
    public static void main (String [] args) throws InterruptedException {
        int n=100;
        final long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        long time;
        Scheduler scheduler = new Scheduler();
        Resource resource = new Resource(200);
        Proxy proxy = new Proxy(scheduler, resource);
        Producer prod[]=new Producer[n];
        Consument cons[]=new Consument[n];
        for (int i = 0; i < n; i++){
            prod[i]=new Producer(resource, proxy,i+1);
            cons[i]=new Consument(resource, proxy,i+1);
        }
        do {
            endTime = System.currentTimeMillis();
            time=endTime-startTime;
        }while (time<500);
        end=false;
        for (int i = 0; i < n; i++){
            prod[i].join();
            cons[i].join();
        }
        scheduler.join();
        System.out.println(overallConsumed+" "+overallPoduced);
    }
}