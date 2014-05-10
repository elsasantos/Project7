/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import pt.uc.aor.webservice.entity.Client;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;

/**
 *
 * @author Elsa
 */
@Stateless
public class SellFacade extends AbstractFacade<Sell> {

    @Inject
    private ClientFacade cf;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SellFacade() {
        super(Sell.class);
    }

    public void createSell(List<Product> listproduct, String api) {

        Sell newsell = new Sell();

    }

    public Sell createSellClient(Client client) {
        Sell sell = new Sell(client);
        em.persist(sell);
        return sell;

    }

//MÉTODOS CRIADOS PARA A API:
    /**
     * Lista o histórico das encomendas realizadas pelo User
     *
     * @param idUser
     * @return
     */
    public List<Sell> sellByUser(Long idUser) {
        List<Sell> s = new ArrayList<>();
        try {
            Client c = cf.find(idUser);
            s = em.createNamedQuery("Sell.findByidClient").setParameter("idClient", c).getResultList();
        } catch (NoResultException ex) {
            //TODO log
        }
        return s;
    }

    /**
     * Detalhes da encomenda em curso
     *
     * @param idSell
     * @return
     */
    public List<SellProduct> detailBySell(Long idSell) {
        List<SellProduct> s = new ArrayList<>();
        try {
            s = find(idSell).getSellProductList();
        } catch (NoResultException ex) {
            //TODO log
        }
        return s;
    }

}
