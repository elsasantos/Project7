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
import pt.uc.aor.webservice.entity.Client;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;
import pt.uc.aor.webservice.facade.ClientFacade;

/**
 *
 * @author Elsa
 */
@Stateless
@Path("sell")
public class SellFacadeREST extends AbstractFacade<Sell> {

    @Inject
    private ClientFacade cf;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    public SellFacadeREST() {
        super(Sell.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Sell entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Sell entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    /**
     * Mostra os detalhes da encomenda X pelo id da mesma
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Sell find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Sell> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Sell> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Lista o histórico das encomendas realizadas pelo User
     *
     * @param idUser
     * @return
     */
    @GET
    @Path("sellsUser/{idUser}")
    @Produces({"application/json"})
    public List<Sell> sellByUser(@PathParam("idUser") Long idUser) {
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
    @GET
    @Path("detailsSell/{idSell}")
    @Produces({"application/json"})
    public List<SellProduct> detailBySell(@PathParam("idSell") Long idSell) {
        List<SellProduct> s = new ArrayList<>();
        try {
            s = find(idSell).getSellProductList();

        } catch (NoResultException ex) {
            //TODO log
        }
        return s;
    }

}
