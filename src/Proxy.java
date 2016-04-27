/**
 * Created by lstrzalk on 27.04.16.
*/
public class Proxy {
    private Scheduler scheduler;
    private Resource res;
    public Proxy(Scheduler scheduler, Resource res){
        this.res = res;
        this.scheduler = scheduler;
        this.scheduler.start();
    }
    public Future methodConsume(int amount, int id){
        Future f = new Future();
        MethodRequestConsume mr = new MethodRequestConsume(res,amount,f, id);
        scheduler.enqueue(mr);
        return f;
    }
    public Future methodProduce(int amount, int id){
        Future f = new Future();
        MethodRequestProduce mr = new MethodRequestProduce(res, amount,f, id);
        scheduler.enqueue(mr);
        return f;
    }
}