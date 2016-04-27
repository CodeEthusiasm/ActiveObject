/**
 * Created by lstrzalk on 27.04.16.
 */
public class MethodRequestProduce extends MethodRequest {
    int amount;
    int id;
    Future f;
    public MethodRequestProduce(Resource res, int amount, Future f,int id) {
        super(res);
        this.amount=amount;
        this.id= id;
        this.f=f;
    }
    @Override
    public boolean guard(){
        return (res.enoughPlaces(amount));
    }
    @Override
    public void execute(){
        f.setValue(res.produce(amount, id));
    }
}