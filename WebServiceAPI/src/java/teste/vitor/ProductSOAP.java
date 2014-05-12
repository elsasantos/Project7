/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package teste.vitor;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.facade.ProductFacade;

/**
 *
 * @author Aires
 */
@WebService(serviceName = "ProductSOAP")
public class ProductSOAP {
    @EJB
    private ProductFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Product entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Product entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Product entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Product find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Product> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Product> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    @WebMethod(operationName = "findByCategory")
    public List<Product> findByCategory(@WebParam(name = "idCategory") Long idCategory) {
        return ejbRef.findByCategory(idCategory);
    }

    @WebMethod(operationName = "searchByProduct")
    public List<Product> searchByProduct(@WebParam(name = "column") String column, @WebParam(name = "word") String word) {
        return ejbRef.searchByProduct(column, word);
    }

}
