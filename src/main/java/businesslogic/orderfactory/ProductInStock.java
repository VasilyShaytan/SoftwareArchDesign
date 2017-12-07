package businesslogic.orderfactory;


public class ProductInStock {
    private long productId;
    private String productName;
    private int productCount;
    private int productPrice;

    public ProductInStock(long pid, String pn, int pc, int pp) {
        this.productId = pid;
        this.productName = pn;
        this.productCount = pc;
        this.productPrice = pp;
    }

    public long getProductId() {
        return productId;
    }
    public String getProductName() {
        return productName;
    }
    public int getProductCount() {
        return productCount;
    }
    public int getProductPrice() {
        return productPrice;
    }

    public int setProductCount(int productCount) {
        return this.productCount = productCount;
    }

    public int subProductCount(int count){
        return productCount - count;
    }



}
