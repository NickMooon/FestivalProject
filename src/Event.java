import MyException.FullUserException;
import Users.RegistratedUsers;

import java.util.ArrayList;

public class Event {
    static Event festival = new Event();
    FestivalInfo rockFestivalInfo = new FestivalInfo();
    ArrayList<RegistratedUsers> users = new ArrayList<>();

    public static void main(String[] args) {
        try {
            festival.registration();
        }
        catch (FullUserException e){
            System.out.println("Регистрация невозможна. Все билеты проданы.");
        }
    }
    public void registration() throws FullUserException{
        if (users.size()>rockFestivalInfo.maxUsers)  throw new FullUserException();
        users.add(new RegistratedUsers());
    }
}
