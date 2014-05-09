/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.webservice.Soap;

import java.util.HashMap;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import pt.uc.aor.webservice.facade.Order;

/**
 *
 * @author Aires
 */
@WebService(serviceName = "NewWebService")
public class NewWebService {

    Log log;
    @EJB
    private Order ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "makeSell")
    @Oneway
    public void makeSell(@WebParam(name = "hashmap") HashMap<Integer, Integer> hashmap, @WebParam(name = "apkKey") String apkKey) {
        log.info("makeSell SOAP(" + apkKey + ")");
        ejbRef.makeSell(hashmap, apkKey);
    }

    @WebMethod(operationName = "removeSell")
    @Oneway
    public void removeSell(@WebParam(name = "id") long id, @WebParam(name = "apkKey") String apkKey) {
        ejbRef.removeSell(id, apkKey);
    }

    @WebMethod(operationName = "removeProductSell")
    @Oneway
    public void removeProductSell(@WebParam(name = "idProduct") long idProduct, @WebParam(name = "idSell") long idSell, @WebParam(name = "apkKey") String apkKey) {
        ejbRef.removeProductSell(idProduct, idSell, apkKey);
    }

    @WebMethod(operationName = "editProductSell")
    @Oneway
    public void editProductSell(@WebParam(name = "idProduct") long idProduct, @WebParam(name = "idSell") long idSell, @WebParam(name = "apkKey") String apkKey, @WebParam(name = "quantity") int quantity) {
        ejbRef.editProductSell(idProduct, idSell, apkKey, quantity);
    }

}
