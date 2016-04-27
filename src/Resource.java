/**
 * Created by lstrzalk on 27.04.16.
 */
public class Resource {
    private final int size;
    private int end;
    private final int []buffor;
    public Resource(int size){
        this.size = size;
        this.end = 0;
        buffor = new int[size];
        for(int i = 0; i<size;i++){
            buffor[i]=0;
        }
    }
    public int consume(int amount, int id){
        if(enoughProduced(amount)){
            for(int i = 0; i<amount;i++){
                buffor[end-i]=0;
            }
            end-=amount;
        }
        return amount;
    }
    public int produce(int amount, int id){
        if(enoughPlaces(amount)){
            for(int i = 0; i < amount; i++){
                buffor[end+i]=1;
            }
            end+=amount;
        }
        return amount;
    }
    public boolean enoughPlaces(int amount){return (size - end - amount)>0;}
    public boolean enoughProduced(int amount){return (end - amount) > 0;}
    public int getEnd() {
        return end;
    }
}