package businesslogic;

public class ErrorCodes {
    public static int success = 0;
    public static int incorrectLoginPassword = 1;
    public static int mistake = 2;
    public static int busyLogin = 3;
    public static int busyClientOrder = 4;


    public static String getError(int num){
        switch (num){
            case 0:
                return "Действие выполнено успешно";
            case 1:
                return "Incorrect Login or Password";
            case 2:
                return "Mistake";
            case 3:
                return "Такой логин уже занят!";
            case 4:
                return "Такой заказ уже создан!";
        }
        return "Error!";
    }
}
