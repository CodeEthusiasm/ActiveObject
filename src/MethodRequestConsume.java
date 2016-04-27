/**
 * Created by lstrzalk on 27.04.16.
 */
public class MethodRequestConsume extends MethodRequest{
    int amount;
    int id;
    Future f;
    public MethodRequestConsume(Resource res, int amount, Future f, int id) {
        super(res);
        this.amount=amount;
        this.f=f;
        this.id = id;
    }
    @Override
    public boolean guard(){
        return res.enoughProduced(amount);
    }
    @Override
    public void execute(){
        f.setValue(res.consume(amount, id));
    }
}