/**
 * Created by lstrzalk on 27.04.16.
 */
public class Resource {
    private final int size;
    private int current;
    public Resource(int size){
        this.size = size;
        this.current=0;
    }
    public int consume(int amount, int id){
        if(enoughProduced(amount)){
            current-=amount;
            Main.overallConsumed+=amount;
            System.out.println(Main.overallConsumed+" "+Main.overallPoduced+" "+(Main.overallConsumed+Main.overallPoduced));
        }
        return amount;
    }
    public int produce(int amount, int id){
        if(enoughPlaces(amount)){
            current+=amount;
            Main.overallPoduced+=amount;
            System.out.println(Main.overallConsumed+" "+Main.overallPoduced+" "+(Main.overallConsumed+Main.overallPoduced));
        }
        return amount;
    }
    public boolean enoughPlaces(int amount){return (current + amount)<size;}
    public boolean enoughProduced(int amount){return (current - amount) > 0;}
    public int currentlyInBuff() {
        return current;
    }
}