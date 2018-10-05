import MyException.FullUserException;
import Users.RegistratedUsers;

import java.util.ArrayList;

public class Event {
    FestivalInfo rockFestivalInfo = new FestivalInfo();
    ArrayList<RegistratedUsers> users = new ArrayList<>();
    try{
        
    }
    public static void main(String[] args) {

    }
    public void Registration() throws FullUserException{
        users.add(new RegistratedUsers());
        if (users.size()>rockFestivalInfo.maxUsers) failRegistration();
    }
    public void failRegistration() throws FullUserException {
        throw new FullUserException();
    }
}
