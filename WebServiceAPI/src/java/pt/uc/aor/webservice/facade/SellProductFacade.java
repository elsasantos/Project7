/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */
package pt.uc.aor.webservice.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;

/**
 *
 * @author Aires
 */
@Stateless
public class SellProductFacade extends AbstractFacade<SellProduct> {

    @Inject
    private SellFacade sellfacade;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SellProductFacade() {
        super(SellProduct.class);
    }

    public SellProduct createSellProduct(int quantity, Sell sell, Product product) {
        SellProduct sellProduct = new SellProduct(quantity, sell, product);
        em.persist(sellProduct);
        return sellProduct;
    }

    public SellProduct searchByProductSell(long idProduct, long idSell) {
        Query query = em.createNamedQuery("SellProduct.findBySellProduct", SellProduct.class);
        query.setParameter("idProduct", idProduct);
        query.setParameter("idOrder", idSell);
        return (SellProduct) query.getSingleResult();
    }
//MÉTODOS CRIADOS PARA A API:

    /**
     * Mostra os detalhes da encomenda X pelo id da mesma
     *
     * @param idSell
     * @return
     */
    public List<SellProduct> detailSell(Long idSell) {
        log.info("SellProduct.detailSell");
        List<SellProduct> sp = new ArrayList<>();
        try {
            Sell s = sellfacade.find(idSell);
            sp = em.createNamedQuery("SellProduct.findBySell").setParameter("sell", s).getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Produto na encomenda com o id " + idSell + ".");
        }
        return sp;
    }

}
