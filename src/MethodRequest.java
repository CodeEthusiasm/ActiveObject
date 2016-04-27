/**
 * Created by lstrzalk on 27.04.16.
 */
public abstract class MethodRequest {
    protected Resource res;

    public MethodRequest(Resource res){
        this.res=res;
    }
    public abstract boolean guard();
    public abstract void execute();
}