package question;

import modelo.singleuser.User;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnUserJsonResponse implements Question<User> {

    @Override
    public User answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(User.class);
    }

    public static ReturnUserJsonResponse returnUserJsonResponse(){
        return new ReturnUserJsonResponse();
    }
}
