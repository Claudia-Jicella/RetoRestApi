package question;

import modelo.registro.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ReturnRegisterJsonResponse implements Question<Response>  {

    @Override
    public Response answeredBy(Actor actor) {
        return SerenityRest.lastResponse().as(Response.class);
    }

    public static ReturnRegisterJsonResponse returnRegisterJsonResponse(){
        return new ReturnRegisterJsonResponse();
    }
}
