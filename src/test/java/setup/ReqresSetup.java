package setup;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.apache.log4j.PropertyConfigurator;

import static util.Log4jValues.LOG4J_PROPERTIES_FILE_PATH;
import static util.Utilities.*;

public class ReqresSetup {
    public static final String BASE_URL = "https://reqres.in/api";
    public static final String RESOURCE_REGISTER = "/register";
    public static final String RESOURCE_SINGLE_USER = "/users/%s";
    protected Actor actor = new Actor("Carlos");

    protected void setup() {
        setUpLog4j();
        actorCan();
    }

    private void actorCan() {
        actor.can(CallAnApi.at(BASE_URL));
    }

    private void setUpLog4j() {
        PropertyConfigurator.configure(osPathModify(defineOS(), getUserDir() + LOG4J_PROPERTIES_FILE_PATH.getValue()));
    }

    private String osPathModify(String defineOS, String s) {
        return s;
    }
}