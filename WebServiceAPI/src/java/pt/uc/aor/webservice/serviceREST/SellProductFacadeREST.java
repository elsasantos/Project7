/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.serviceREST;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;
import pt.uc.aor.webservice.facade.SellFacade;

/**
 *
 * @author Elsa
 */
@Stateless
@Path("sellproduct")
public class SellProductFacadeREST extends AbstractFacade<SellProduct> {

    @Inject
    private SellFacade sellfacade;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    public SellProductFacadeREST() {
        super(SellProduct.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(SellProduct entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, SellProduct entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public SellProduct find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<SellProduct> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<SellProduct> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    //MÉTODOS CRIADOS PARA A API:
    /**
     * Mostra os detalhes da encomenda X pelo id da mesma
     *
     * @param idSell
     * @return
     */
    @GET
    @Path("details/{idSell}")
    @Produces({"application/json"})
    public List<SellProduct> detailSell(@PathParam("idSell") Long idSell) {
        List<SellProduct> sp = new ArrayList<>();
        try {
            Sell s = sellfacade.find(idSell);
            sp = em.createNamedQuery("SellProduct.findBySell").setParameter("sell", s).getResultList();
        } catch (NoResultException ex) {
            //TODO log
        }
        return sp;

    }
}
