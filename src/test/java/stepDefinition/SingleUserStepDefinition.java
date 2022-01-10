package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import modelo.singleuser.Data;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import setup.ReqresSetup;
import static com.google.common.base.Predicates.equalTo;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.CoreMatchers.notNullValue;
import static question.ReturnUserJsonResponse.returnUserJsonResponse;
import static tasks.GetUser.getUser;

public class SingleUserStepDefinition extends ReqresSetup {

    private String userId;

    //Background
    @Given("el administrador está en la pagina web de reqres y quiere enumerar la informacion de un usuario")
    public void elAdministradorEstaEnLaPaginaWebDeReqresYQuiereEnumerarLaInformacionDeUnUsuario() {
        try {
            super.setup();
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
    //Escenario 1
    @When("el administrador ingresa a la base de datos y escoge el usuario {string}")
    public void elAdministradorIngresaALaBaseDeDatosYEscogeElUsuario(String id) {
        try {
            userId = id;
            actor.attemptsTo(
                    getUser()
                            .withTheResource(String.format(RESOURCE_SINGLE_USER, id))
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
    @Then("el sistema imprime en pantalla la respuesta exitosa y los datos del usuario")
    public void elSistemaImprimeEnPantallaLaRespuestaExitosaYLosDatosDelUsuario() {
        try {
            Data userResponse = returnUserJsonResponse().answeredBy(actor).getData();
            actor.should(
                    seeThatResponse("El código de respuesta HTTP debe ser: " + HttpStatus.SC_OK,
                            response -> response.statusCode(HttpStatus.SC_OK)
                    ),
                    seeThat("Recupera la información de un usuario: ",
                            act -> userResponse, notNullValue()
                    )
            );
            actor.should(
                    seeThat("El id de del usuario recuperado es: ",
                            act -> userResponse.getId(), equalTo(Integer.valueOf(userId)))
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());

        }
    }
    //Escenario 2
    @When("el administrador ingresa al sistema y escoge el usuario {string}")
    public void elAdministradorIngresaAlSistemaYEscogeElUsuario (String id) {
        try {
            actor.attemptsTo(
                    getUser()
                            .withTheResource(String.format(RESOURCE_SINGLE_USER, id))
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());

        }
    }
    @Then("el sistema debe mostrar en pantalla usuario no encontrado")
    public void elSistemaDebeMostrarEnPantallaUsuarioNoEncontrado() {
        try {
            actor.should(
                    seeThatResponse("El código de respuesta HTTP debe ser: " + HttpStatus.SC_NOT_FOUND,
                            response -> response.statusCode(HttpStatus.SC_NOT_FOUND)
                    )
            );
        } catch (Exception e){
            Assertions.fail(e.getMessage());
        }
    }
}
