package businesslogic.orderfactory;

public class ManagerOrder {
    private long ManagerOrderId;
    private long UserId;
    private int amount;
    private byte statusPOrder;
    private byte statusFullPayOrder;

    ManagerOrder(long moid, long uid, int am, byte spo, byte sfpo){
        this.ManagerOrderId = moid;
        this.UserId = uid;
        this.amount = am;
        this.statusPOrder = spo;
        this.statusFullPayOrder = sfpo;
    }

    public long getManagerOrderId() {
        return ManagerOrderId;
    }
    public long getUserId() { return UserId; }
    public int getAmount() { return amount; }
    public byte getStatusPOrder() { return statusPOrder; }
    public byte getStatusFullPayOrder() { return statusFullPayOrder;}
}
