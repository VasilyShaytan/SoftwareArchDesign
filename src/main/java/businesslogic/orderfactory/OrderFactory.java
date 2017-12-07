package businesslogic.orderfactory;

public class OrderFactory {
    public static Order createOrder(long oid, long coid, long pid, int pc){
        return new Order(oid, coid, pid, pc);
    }
}
