/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.sopa;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
@WebService(serviceName = "TesteProd")
@Stateless()
public class TesteProd {
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

    @WebMethod(operationName = "findAllProducts")
    public List<Product> findAllProducts() {
        return ejbRef.findAllProducts();
    }

    @WebMethod(operationName = "findProductByCategory")
    public List<Product> findProductByCategory(@WebParam(name = "idCategory") Long idCategory) {
        return ejbRef.findProductByCategory(idCategory);
    }

    @WebMethod(operationName = "searchByProduct")
    public List<Product> searchByProduct(@WebParam(name = "column") String column, @WebParam(name = "word") String word) {
        return ejbRef.searchByProduct(column, word);
    }

    @WebMethod(operationName = "findProductById")
    public Product findProductById(@WebParam(name = "id") Long id) {
        return ejbRef.findProductById(id);
    }

    @WebMethod(operationName = "findProductByDesignation")
    public Product findProductByDesignation(@WebParam(name = "brand") String brand, @WebParam(name = "model") String model, @WebParam(name = "version") String version) {
        return ejbRef.findProductByDesignation(brand, model, version);
    }

}
