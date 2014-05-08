/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.facade;

import java.util.HashMap;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import pt.uc.aor.webservice.entity.Client;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;

/**
 *
 * @author Aires
 */
@Stateless
public class makingSell {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    Log log;

    @Inject
    private ClientFacade cf;
    @Inject
    private ProductFacade pf;
    @Inject
    private SellFacade sf;
    @Inject
    private SellProductFacade spf;

    private void makeSell(HashMap<Integer, Integer> hashmap, String apkKey) {
        Client buyer;

        Sell sell = new Sell();
        Product product;
        for (Integer idproduto : hashmap.keySet()) {
            product = pf.find(idproduto);
            SellProduct sellProduct = new SellProduct(hashmap.get(idproduto), sell, product);
            sell.getSellProductList().add(sellProduct);
            product.getSellProductList().add(sellProduct);
        }

    }

}
