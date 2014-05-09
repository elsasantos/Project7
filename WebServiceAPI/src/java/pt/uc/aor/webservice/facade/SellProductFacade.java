/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.aor.webservice.entity.SellProduct;

/**
 *
 * @author Aires
 */
@Stateless
public class SellProductFacade extends AbstractFacade<SellProduct> {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SellProductFacade() {
        super(SellProduct.class);
    }

    public SellProduct searchByProductSell(long idProduct, long idSell) {
        Query query = em.createNamedQuery("SellProduct.findBySellProduct", SellProduct.class);
        query.setParameter("idProduct", idProduct);
        query.setParameter("idOrder", idSell);
        return (SellProduct) query.getSingleResult();
    }
}
