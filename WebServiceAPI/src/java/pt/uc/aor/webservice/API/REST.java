/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.API;

import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pt.uc.aor.webservice.entity.Category;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;
import pt.uc.aor.webservice.serviceREST.CategoryFacadeREST;
import pt.uc.aor.webservice.serviceREST.ProductFacadeREST;
import pt.uc.aor.webservice.serviceREST.SellFacadeREST;
import pt.uc.aor.webservice.serviceREST.SellProductFacadeREST;

/**
 *
 * @author Elsa
 */
public class REST implements APInterface {

    @Inject
    private ProductFacadeREST productf;
    @Inject
    private SellFacadeREST sellf;
    @Inject
    private SellProductFacadeREST sellproductf;
    Log log;
    @Inject
    private CategoryFacadeREST categoryf;

    public REST() {
        this.log = LogFactory.getLog(REST.class);
    }

    //Métodos da entidade Product:
    public List<Product> findAllProducts() throws NoResultException {
        log.info("REST -> Product.findAllProducts()");
        return productf.findAllProducts();
    }

    public List<Product> findProductByCategory(Long idCategory) throws NoResultException {
        log.info("REST -> Product.findProductByCategory(" + idCategory + ")");
        return productf.findProductByCategory(idCategory);
    }

    public List<Product> searchByProduct(String column, String word) throws NoResultException {
        log.info("REST -> Product.findProductByCategory(Column" + column + ", Word " + word + ")");
        return productf.searchByProduct(column, word);
    }

    public Product findProductById(Long id) throws NoResultException {
        log.info("REST -> Product.findProductByID(Id" + id + ")");
        return productf.findProductById(id);
    }

    public Product findProductByDesignation(String brand, String model, String version) throws NoResultException {
        log.info("REST -> Product.findProductByDesignatio(brand" + brand + ", model " + model + ")");
        return productf.findProductByDesignation(brand, model, version);
    }

    public List<Category> findAllCategory() throws NoResultException {
        return categoryf.findAllCategory();
    }

//Métodos da entidade Sell:
    public void makeSell(HashMap<Long, Integer> hashmap, String apkKey) {
        log.info("REST -> MAKESELL(API" + apkKey + ")");
        sellf.createSell(hashmap, apkKey);
    }

    public void removeSell(long id, String apkKey) {
        log.info("REST -> removeSell(API" + apkKey + ", id" + id + ")");
        sellf.removeSell(id, apkKey);
    }

    public List<Sell> sellsByUser(Long idUser) throws NoResultException {
        log.info("REST -> sellsByUser(id" + idUser + ")");
        return sellf.sellsByUser(idUser);
    }

    public List<SellProduct> detailBySell(Long idSell) throws NoResultException {
        log.info("REST -> detailBySell(id" + idSell + ")");
        return sellf.detailBySell(idSell);
    }

//Métodos da entidade SellProduct:
    public void addProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        log.info("REST -> addProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        sellproductf.addProductSell(idProduct, idSell, apkKey, quantity);
    }

    public void editProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        log.info("REST -> addProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        sellproductf.editProductSell(idProduct, idSell, apkKey, quantity);
    }

    public void removeProductSell(long idProduct, long idSell, String apkKey) {
        log.info("REST -> removeProductSell(id" + idSell + ",idProduct" + apkKey + " )");
        sellproductf.removeProductSell(idProduct, idSell, apkKey);
    }

    public List<SellProduct> detailSell(Long idSell) throws NoResultException {
        log.info("REST -> removeProductSell(id" + idSell + " )");
        return sellproductf.detailSell(idSell);
    }

}
