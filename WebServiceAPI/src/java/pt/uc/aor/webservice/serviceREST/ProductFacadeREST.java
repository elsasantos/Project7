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
import pt.uc.aor.webservice.entity.Category;
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.facade.CategoryFacade;

/**
 *
 * @author Elsa
 */
@Stateless
@Path("product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    @Inject
    private CategoryFacade cf;

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    public ProductFacadeREST() {
        super(Product.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Product entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, Product entity) {
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
    public Product find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Product> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Product> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
     * Lista os produtos por categoria
     *
     * @param idCategory
     * @return
     */
    @GET
    @Path("category/{idCategory}")
    @Produces({"application/json"})
    public List<Product> findByCategory(@PathParam("idCategory") Long idCategory) {
        List<Product> p = new ArrayList<>();
        try {
            Category c = cf.find(idCategory);
            p = em.createNamedQuery("Product.findByCategoriaidCategoria").setParameter("categoria", c).getResultList();
        } catch (NoResultException ex) {
            //TODO log
        }
        return p;
    }

    /**
     * Lista os produtos segundo uma chave de pesquisa
     *
     * @param column
     * @param word
     * @return //
     */
    @GET
    @Path("search/{column}/{word}")
    @Produces({"application/json"})
    public List<Product> searchByProduct(@PathParam("column") String column, @PathParam("word") String word) {
        List<Product> p = new ArrayList<>();
        try {
            if (column.equals("Designation")) {
                p = em.createNamedQuery("Product.findByWord").setParameter("word", "%" + word + "%").getResultList();
            }
            if (column.equals("Category")) {
                p = em.createNamedQuery("Product.findByCategoriaName").setParameter("word", "%" + word + "%").getResultList();
            }
            if (column.equals("Description")) {
                p = em.createNamedQuery("Product.findByDescription").setParameter("description", "%" + word + "%").getResultList();
            }
        } catch (NoResultException ex) {
            //TODO log
        }
        return p;
    }

}
