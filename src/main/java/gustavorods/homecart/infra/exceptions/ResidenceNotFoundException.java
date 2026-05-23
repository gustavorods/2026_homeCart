package gustavorods.homecart.infra.exceptions;

public class ResidenceNotFoundException extends RuntimeException {
    public ResidenceNotFoundException(int code) {
        super(code + " not found");
    }
}
