package businesslogic.clientfactory;

public class ClientFactory {

    public static Client createClient(long id, String login, String password, String name, String surname, int roleId, int money){
        return new Client(id, login, password, name, surname, roleId, money);
    }


}
