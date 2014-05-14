/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */
package pt.uc.aor.webservice.facade;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Inject
    private ClientFacade cf;
    @Inject
    private ProductFacade pf;
    @Inject
    private SellFacade sf;
    @Inject
    private SellProductFacade spf;

    public void makeSell(List<Long> idProdutKey, List<Integer> qtd, String apkKey) {
        //   log.info("Order.makeSell(" + apkKey + ")");
//        Date today = new Date();
//        Date dateBuy = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        System.out.println("");
        if (cf.existApi(apkKey)) {
            Client buyer;
            buyer = cf.findClientByApi(apkKey);
            Sell sell = sf.createSellClient(buyer);
            //sell.setActualdate(dateBuy);
            Product product;
            for (int i = 0; i < idProdutKey.size(); i++) {

                // }
                // for (Long idproduto : idP) {
                product = pf.find(idProdutKey.get(i));
                //atualiza stock
                product.setQuantity(product.getQuantity() - qtd.get(i));
                // atualiza a data de entrega
                if (product.getQuantity() < 0) {
                    sell.setDeliverydate(product.getRepositiondate());
                }

                SellProduct sellProduct = spf.createSellProduct(qtd.get(i), sell, product);
                product.getSellProductList().add(sellProduct);
                buyer.getSellList().add(sell);

                sf.edit(sell);
                pf.edit(product);

                cf.edit(buyer);
                spf.edit(sellProduct);
                spf.getEntityManager().persist(sellProduct);
            }

        } else {
            System.out.println("Não existe este Api");

        }

    }

    public void removeSell(long id, String apkKey) {
        Sell sell = sf.find(id);
//        for (SellProduct sellproduct : sell.getSellProductList()) {
//            Product product = pf.find(sellproduct.getProduct().getIdProduct());
//            product.setQuantity(sellproduct.getQuantity());
//            spf.remove(spf.find(sellproduct.getIdSellProduct()));
//            pf.edit(product);//merge
//        }
        sf.remove(sell);
    }

    public void removeProductSell(long idProduct, long idSell, String apkKey) {
        Sell sell = sf.find(idSell);
        Product product = pf.find(idProduct);
        SellProduct sellProduct = spf.searchByProductSell(idProduct, idSell);
        //Atualizar o Stock
        product.setQuantity(product.getQuantity() + sellProduct.getQuantity());
        //remover
        spf.remove(sellProduct);

    }

    public void editProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        Sell sell = sf.find(idSell);
        Product product = pf.find(idProduct);
        SellProduct sellProduct = spf.searchByProductSell(idProduct, idSell);
        //Atualizar o Stock
        product.setQuantity(product.getQuantity() + sellProduct.getQuantity());
        product.setQuantity(quantity);
        pf.edit(product);
        spf.edit(sellProduct);
        //remover
    }

    public void addProductSell(long idProduct, long idSell, String apkKey, int quantity) {
        // a melhorar
        Sell sell = sf.find(idSell);
        Product product = pf.find(idProduct);
        SellProduct sellProduct = spf.searchByProductSell(idProduct, idSell);
        //Atualizar o Stock
        product.setQuantity(product.getQuantity() + sellProduct.getQuantity());
        product.setQuantity(quantity);
        pf.edit(product);
        spf.edit(sellProduct);
        //remover
    }

    public void makeSellTest(long idCliente, long idproduto, int quantity) {
        GregorianCalendar today;
        today = (GregorianCalendar) Calendar.getInstance();

        Client buyer;
        buyer = cf.find(idCliente);
        Sell sell = sf.createSellClient(buyer);

        //sell.setActualdate(new Date());
        //sell.setClientidClient(buyer);
        Product product = pf.find(idproduto);
        //atualiza stock
        product.setQuantity(product.getQuantity() - quantity);
        // atualiza a data de entrega
        if (product.getQuantity() < 0) {
            sell.setDeliverydate(product.getRepositiondate());
        }

        //sf.getEntityManager().persist(sell);
        SellProduct sellProduct = spf.createSellProduct(quantity, sell, product);
//        sell.getSellProductList().add(sellProduct);
        product.getSellProductList().add(sellProduct);
        sell.getSellProductList().add(sellProduct);
        buyer.getSellList().add(sell);

        sf.edit(sell);
        pf.edit(product);

        cf.edit(buyer);
        spf.edit(sellProduct);
        spf.getEntityManager().persist(sellProduct);
        System.out.println("Presto");
    }

}
