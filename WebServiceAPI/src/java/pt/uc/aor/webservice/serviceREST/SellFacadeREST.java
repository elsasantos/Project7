    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.serviceREST;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.apache.commons.logging.Log;
import pt.uc.aor.webservice.entity.Sell;
import pt.uc.aor.webservice.entity.SellProduct;
import pt.uc.aor.webservice.facade.ClientFacade;
import pt.uc.aor.webservice.facade.Order;
import pt.uc.aor.webservice.facade.ProductFacade;
import pt.uc.aor.webservice.facade.SellFacade;
import pt.uc.aor.webservice.facade.SellProductFacade;

/**
 *
 * @author Elsa
 */
@Stateless
@Path("sell")
public class SellFacadeREST extends AbstractFacade<Sell> {

    Log log;
    @Inject
    private Order order;
    @Inject
    private ClientFacade clientFacade;
    @Inject
    private ProductFacade productFacade;
    @Inject
    private SellFacade sellFacade;
    @Inject
    private SellProductFacade sellProductFacade;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    public SellFacadeREST() {
        super(Sell.class);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Sell entity) {
        super.edit(entity);
    }

//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Long id) {
//        super.remove(super.find(id));
//    }
    /**
     * Mostra os detalhes da encomenda X pelo id da mesma
     *
     * @param id
     * @return
     */
//    @GET
//    @Path("{id}")
//    @Produces({"application/xml", "application/json"})
//    public Sell find(@PathParam("id") Long id) {
//        return super.find(id);
//    }
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
     * Cria uma nova encomenda
     *
     * @param hashmap
     * @param apkKey
     */
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public void createSell(HashMap<Integer, Integer> hashmap, String apkKey) {
        order.makeSell(hashmap, apkKey);
    }

    /**
     * Elimina/Cancela a encomenda em curso
     *
     * @param idSell
     * @param apkKey
     */
    @DELETE
    @Path("{idSell}")
    public void removeSell(@PathParam("idSell") Long idSell, String apkKey) {
        order.removeSell(idSell, apkKey);
    }

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
        return sellFacade.sellsByUser(idUser);
    }

    /**
     * Detalhes da encomenda em curso
     *
     * @param idSell
     * @return
     */
    @GET
    @Path("{idSell}")
    @Produces({"application/json"})
    public List<SellProduct> detailBySell(@PathParam("idSell") Long idSell) {
        return sellFacade.detailBySell(idSell);
    }
}
