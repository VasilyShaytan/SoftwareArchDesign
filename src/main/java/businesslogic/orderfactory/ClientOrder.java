package businesslogic.orderfactory;

public class ClientOrder {
    private long ClientOrderId;
    private long UserId;
    private int amount;
    private byte statusMOrder;
    private byte statusPPayOrder;
    private byte statusFullPayOrder;

    ClientOrder(long co, long uid, int am, byte smo, byte sppo, byte sfpo){
        this.ClientOrderId = co;
        this.UserId = uid;
        this.amount = am;
        this.statusMOrder = smo;
        this.statusPPayOrder = sppo;
        this.statusFullPayOrder = sfpo;
    }

    public long getClientOrderId() {
        return ClientOrderId;
    }
    public long getUserId() { return UserId; }
    public int getAmount() { return amount; }
    public byte getStatusMOrder() { return statusMOrder; }
    public byte getStatusPPayOrder() { return statusPPayOrder;}
    public byte getStatusFullPayOrder() { return statusFullPayOrder;}

}
