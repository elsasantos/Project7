/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.facade;

import java.util.Date;
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
public class Order {

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
        if (cf.existApi(apkKey)) {
            Sell sell = new Sell();
            sell.setActualdate(new Date());
            buyer = cf.findClientByApi(apkKey);
            sell.setClientidClient(buyer);
            Product product;
            for (Integer idproduto : hashmap.keySet()) {
                product = pf.find(idproduto);
                //atualiza stock
                product.setQuantity(product.getQuantity() - hashmap.get(idproduto));
                // atualiza a data de entrega
                if (product.getQuantity() < 0) {
                    sell.setDeliverydate(product.getRepositiondate());
                }

                SellProduct sellProduct = new SellProduct(hashmap.get(idproduto), sell, product);
                sell.getSellProductList().add(sellProduct);
                product.getSellProductList().add(sellProduct);
                sf.edit(sell);
                pf.edit(product);
                spf.edit(sellProduct);

            }
            cf.edit(buyer);
        } else {
            System.out.println("Não existe este Api");

        }

    }

    public void removeSell(long id, String apkKey) {
        Sell sell = sf.find(id);
        for (SellProduct sellproduct : sell.getSellProductList()) {
            spf.remove(spf.find(sellproduct.getIdSellProduct()));
        }
        sf.remove(sell);

    }

}
