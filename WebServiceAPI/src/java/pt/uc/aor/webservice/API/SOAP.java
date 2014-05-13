/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.API;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.NoResultException;
import pt.uc.aor.webservice.entity.Category;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;
import pt.uc.aor.webservice.facade.CategoryFacade;
import pt.uc.aor.webservice.facade.Order;
import pt.uc.aor.webservice.facade.ProductFacade;
import pt.uc.aor.webservice.facade.SellFacade;
import pt.uc.aor.webservice.facade.SellProductFacade;

/**
 *
 * @author Elsa
 */
@WebService
@Stateless
public class SOAP implements APInterface {

    @Inject
    private ProductFacade productf;
    @Inject
    private Order order;
    @Inject
    private SellFacade sellf;
    @Inject
    private SellProductFacade sellproductf;
    @Inject
    private CategoryFacade categoryf;

    public SOAP() {
    }

    //Métodos da entidade Product:
    @WebMethod
    public List<Product> findAllProducts() throws NoResultException {
        return productf.findAllProducts();
    }

    @WebMethod
    public List<Product> findProductByCategory(Long idCategory) throws NoResultException {
        return productf.findProductByCategory(idCategory);
    }

    @WebMethod
    public List<Product> searchByProduct(String column, String word) throws NoResultException {
        return productf.searchByProduct(column, word);
    }

    @WebMethod
    public Product findProductById(Long id) throws NoResultException {
        return productf.findProductById(id);
    }

    @WebMethod
    public Product findProductByDesignation(String brand, String model, String version) throws NoResultException {
        return productf.findProductByDesignation(brand, model, version);
    }

    @WebMethod
    public List<Category> findAllCategory() {
        return categoryf.findAllCategory();
    }

//Métodos da entidade Sell:
    @WebMethod
    public void makeSell(HashMap<Integer, Integer> hashmap, String apkKey) {
        order.makeSell(hashmap, apkKey);
    }

    @WebMethod
    public void removeSell(long id, String apkKey) {
        order.removeSell(id, apkKey);
    }

    @WebMethod
    public List<Sell> sellsByUser(Long idUser) throws NoResultException {
        return sellf.sellsByUser(idUser);
    }

    @WebMethod
    public List<SellProduct> detailBySell(Long idSell) throws NoResultException {
        return sellf.detailBySell(idSell);
    }

//Métodos da entidade SellProduct:
    @WebMethod
    public void addProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        order.addProductSell(idProduct, idSell, apkKey, quantity);
    }

    @WebMethod
    public void editProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        order.editProductSell(idProduct, idSell, apkKey, quantity);
    }

    @WebMethod
    public void removeProductSell(long idProduct, long idSell, String apkKey) {
        order.removeProductSell(idProduct, idSell, apkKey);
    }

    @WebMethod
    public List<SellProduct> detailSell(Long idSell) throws NoResultException {
        return sellproductf.detailSell(idSell);
    }

}
