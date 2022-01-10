package stepDefinition;


import setup.ReqresSetup;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modelo.registro.Response;
import modelo.singleuser.User;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import static modelo.registro.ErrorMessage.ERROR;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static question.ReturnRegisterJsonResponse.returnRegisterJsonResponse;
import static tasks.DoPost.doPost;

public class RegisterSuccessfulStepDefinition extends ReqresSetup {
    private static final Logger LOGGER = Logger.getLogger(RegisterSuccessfulStepDefinition.class);
    private User user;

    //Escenario 1
    @Given("que el usuario se encuentra en el sitio web")
    public void queElUsuarioSeEncuentraEnElSitioWeb() {
        try {
            super.setup();
            user = new User();
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @When("el usuario envie la solicitud de registro con el correo {string} y la contraseña {string}")
    public void elUsuarioEnvieLaSolicitudDeRegistroConElCorreoYLaContrasena (String email, String password) {
        try {
            user.setEmail(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_REGISTER)
                            .andTheBodyRequest(user)
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @Then("el sistema debe mostrar respuesta exitosa con un token de respuesta")
    public void elSistemaDebeMostrarRespuestaExitosaConUnTokenDeRespuesta() {
        try {
            Response actualResponse = returnRegisterJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            actor.should(
                    seeThat("El id generado es 4",
                            act -> actualResponse.getId(), equalTo(4)),
                    seeThat("Se genera un toquen",
                            act -> actualResponse.getToken(), notNullValue())
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }

    //Escenario 2
    @Given("que el usuario no permitido del sitio web intenta registrarse")
    public void queElUsuarioNoPermitidoDelSitioWebIntentaRegistrarse() {
        try {
            super.setup();
            user = new User();
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @When("el usuario realiza la solicitud con el correo {string} y la contraseña {string}")
    public void elUsuarioRealizaLaSolicitudConElCorreoYLaContrasena(String email, String password) {
        try {
            user.setEmail(email);
            user.setPassword(password);
            actor.attemptsTo(
                    doPost()
                            .withTheResource(RESOURCE_REGISTER)
                            .andTheBodyRequest(user)
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
    @Then("el sistema debe mostrar solicitud fallida y un mensaje de error")
    public void elSistemaDebeMostrarSolicitudFallidaYUnMensajeDeError() {
        try {
            Response actualResponse = returnRegisterJsonResponse().answeredBy(actor);
            actor.should(
                    seeThatResponse("El código de respuesta es: " + HttpStatus.SC_BAD_REQUEST,
                            response -> response.statusCode(HttpStatus.SC_BAD_REQUEST)),
                    seeThat("Retorna información",
                            act -> actualResponse, notNullValue())
            );
            actor.should(
                    seeThat("Se genera un error",
                            act -> actualResponse.getError(), equalTo(ERROR.getValue()))
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }
}
