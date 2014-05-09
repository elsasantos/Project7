/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.exception;

/**
 *
 * @author Aires
 */
public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Entity not found.");
    }

    public NotFoundException(String msg) {
        super(msg);
    }

}
