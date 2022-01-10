package modelo.registro;

public enum ErrorMessage {

    ERROR("Nota: Sólo los usuarios definidos se registran correctamente");

    private final String value;

    ErrorMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
