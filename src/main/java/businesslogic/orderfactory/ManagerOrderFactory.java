package businesslogic.orderfactory;

public class ManagerOrderFactory {
    public static ManagerOrder createManagerOrder(long moid, long uid, int am, byte spo, byte sfpo){
        return new ManagerOrder(moid, uid, am, spo, sfpo);
    }
}
