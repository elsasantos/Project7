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
import org.apache.commons.logging.Log;
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

    Log log;

    public SOAP() {
    }

    //Métodos da entidade Product:
    @WebMethod
    @Override
    public List<Product> findAllProducts() throws NoResultException {
        //log.info("SOAP -> Product.findAllProducts()");
        return productf.findAllProducts();
    }

    @WebMethod
    public List<Product> findProductByCategory(Long idCategory) throws NoResultException {
        // log.info("SOAP -> Product.findProductByCategory(" + idCategory + ")");
        return productf.findProductByCategory(idCategory);
    }

    @WebMethod
    public List<Product> searchByProduct(String column, String word) throws NoResultException {

        log.info("SOAP -> Product.findProductByCategory(Column" + column + ", Word " + word + ")");
        return productf.searchByProduct(column, word);
    }

    @WebMethod
    public Product findProductById(Long id) throws NoResultException {
        log.info("SOAP -> Product.findProductByID(Id" + id + ")");
        return productf.findProductById(id);
    }

    @WebMethod
    public Product findProductByDesignation(String brand, String model, String version) throws NoResultException {
        log.info("SOAP -> Product.findProductByDesignatio(brand" + brand + ", model " + model + ")");
        return productf.findProductByDesignation(brand, model, version);
    }

    @WebMethod
    @Override
    public List<Category> findAllCategory() {
        return categoryf.findAllCategory();
    }

//Métodos da entidade Sell:
    @WebMethod
    public void makeSell(HashMap<Integer, Integer> hashmap, String apkKey) {
        log.info("SOAP -> MAKESELL(API" + apkKey + ")");
        order.makeSell(hashmap, apkKey);
    }

    @WebMethod
    public void removeSell(long id, String apkKey) {
        log.info("SOAP -> removeSell(API" + apkKey + ", id" + id + ")");
        order.removeSell(id, apkKey);
    }

    @WebMethod
    public List<Sell> sellsByUser(Long idUser) throws NoResultException {
        log.info("SOAP -> sellsByUser(id" + idUser + ")");
        return sellf.sellsByUser(idUser);
    }

    @WebMethod
    public List<SellProduct> detailBySell(Long idSell) throws NoResultException {
        log.info("SOAP -> detailBySell(id" + idSell + ")");
        return sellf.detailBySell(idSell);
    }

//Métodos da entidade SellProduct:
    @WebMethod
    public void addProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        log.info("SOAP -> addProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        order.addProductSell(idProduct, idSell, apkKey, quantity);
    }

    @WebMethod
    public void editProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        log.info("SOAP -> addProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        order.editProductSell(idProduct, idSell, apkKey, quantity);
    }

    @WebMethod
    public void removeProductSell(long idProduct, long idSell, String apkKey) {
        log.info("SOAP -> removeProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        order.removeProductSell(idProduct, idSell, apkKey);
    }

    @WebMethod
    public List<SellProduct> detailSell(Long idSell) throws NoResultException {
        log.info("SOAP -> removeProductSell(id" + idSell + " )");
        return sellproductf.detailSell(idSell);
    }

}
