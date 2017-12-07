package businesslogic.orderfactory;

public class Order {
    private long OrderId;
    private long ClientOrderId;
    private long ProductId;
    private int ProductCount;

    Order(long oid, long coid, long pid, int pc) {
        this.OrderId = oid;
        this.ClientOrderId = coid;
        this.ProductId = pid;
        this.ProductCount = pc;
    }

    public long getOrderId() {
        return OrderId;
    }

    public long getClientOrderId() {
        return ClientOrderId;
    }

    public long getProductId() {
        return ProductId;
    }

    public int getProductCount() {
        return ProductCount;
    }
}
