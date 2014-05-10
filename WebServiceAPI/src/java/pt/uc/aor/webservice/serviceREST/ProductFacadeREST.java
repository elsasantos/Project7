/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.serviceREST;

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
import pt.uc.aor.webservice.entity.Product;
import pt.uc.aor.webservice.facade.CategoryFacade;
import pt.uc.aor.webservice.facade.ProductFacade;

/**
 *
 * @author Elsa
 */
@Stateless
@Path("product")
public class ProductFacadeREST extends AbstractFacade<Product> {

    @Inject
    private CategoryFacade categoryFacade;
    @Inject
    private ProductFacade productFacade;

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
     * Lista todos os produtos existentes na entidade cujo stock é superior a
     * zero
     *
     * @return Lista dos produtos
     */
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Product> findAllProducts() {
        return productFacade.findAllProducts();
    }

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
        return productFacade.findProductByCategory(idCategory);
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
        return productFacade.searchByProduct(column, word);
    }

    /**
     * Lista os detalhes de um determinado produto pelo seu Id
     *
     * @param id
     * @return
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Product findProductById(@PathParam("id") Long id) {
        return productFacade.findProductById(id);
    }

    /**
     * Lista os detalhes do produto segundo os atributos da designação
     *
     * @param brand
     * @param model
     * @param version
     * @return
     */
    @GET
    @Path("findDesignation/{brand}/{model}/{version}")
    @Produces({"application/json"})
    public Product searchByDesignation(@PathParam("brand") String brand, @PathParam("model") String model, @PathParam("version") String version) {
        return productFacade.findProductByDesignation(brand, model, version);
    }
}
