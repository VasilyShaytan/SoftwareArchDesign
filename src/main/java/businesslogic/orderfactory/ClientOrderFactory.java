package businesslogic.orderfactory;

public class ClientOrderFactory {
    public static ClientOrder createClientOrder(long co, long uid, int am, byte smo, byte sppo, byte sfpo){
        return new ClientOrder(co, uid, am, smo, sppo, sfpo);
    }
}
