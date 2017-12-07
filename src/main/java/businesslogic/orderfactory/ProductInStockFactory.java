package businesslogic.orderfactory;

public class ProductInStockFactory {
    public static ProductInStock createProductInStock(long pid, String pn, int pc, int pp) {
        return new ProductInStock(pid, pn, pc, pp);
    }

}
