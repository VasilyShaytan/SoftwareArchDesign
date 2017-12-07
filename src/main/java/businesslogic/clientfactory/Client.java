package businesslogic.clientfactory;

public class Client {
    private long userId;
    private String login;
    private String password;
    private String name;
    private String surname;
    private int roleId;
    private int money;

    Client(long id, String login, String password, String name, String surname, int roleId, int money){
        this.userId = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.money = money;
    }


    public long getUserId(){return userId;}
    public String getLogin(){return login;}
    public String getPassword(){return password;}
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public int getRoleId(){return roleId;}
    public int getMoney(){return money;}

    public void setNewId(int newId){ userId = newId;}
    public void setMoney(int money){this.money = money;}
    public void subMoney(int amount){
        money = money - amount;
    }
    public void addMoney(int amount){
        money = money + amount;
    }
}
