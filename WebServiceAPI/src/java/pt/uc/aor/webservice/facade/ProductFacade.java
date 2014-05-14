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
import javax.persistence.TypedQuery;
import pt.uc.aor.webservice.entity.Category;
import pt.uc.aor.webservice.entity.Product;

/**
 *
 * @author Elsa
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Inject
    CategoryFacade cf;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    /**
     * Lista todos os produtos existentes na entidade cujo stock é superior a
     * zero
     *
     * @return Lista dos produtos
     */
    public List<Product> findAllProducts() {
        log.info("Product.findAllProducts");
        List<Product> p = new ArrayList<>();
        try {
            p = em.createNamedQuery("Product.findAll").getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Produto com Stock superior a zero.");
        }
        return p;
    }

    /**
     * Lista os produtos por categoria
     *
     * @param idCategory
     * @return
     */
    public List<Product> findProductByCategory(Long idCategory) {
        log.info("Product.findProductByCategoria(" + idCategory + ")");
        List<Product> p = new ArrayList<>();
        try {
            Category c = cf.find(idCategory);
            p = em.createNamedQuery("Product.findByCategoriaidCategoria").setParameter("categoria", c).getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Categoria'" + idCategory + "'.");
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
    public List<Product> searchByProduct(String column, String word) {
        log.info("Product.searchByProduct(" + word + ")");
        List<Product> p = new ArrayList<>();
        TypedQuery<Product> q;

        try {
            q = em.createNamedQuery("Product.findByBrand", Product.class);
            q.setParameter("brand", "%" + word + "%");
            p = q.getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Produto com a designação " + word + ".");
        }

//        try {
//            if (column.equals("Designation")) {
//                p = em.createNamedQuery("Product.findByWord").setParameter("word", "%" + word + "%").getResultList();
//            }
//            if (column.equals("Category")) {
//                p = em.createNamedQuery("Product.findByCategoriaName").setParameter("word", "%" + word + "%").getResultList();
//            }
//            if (column.equals("Description")) {
//                p = em.createNamedQuery("Product.findByDescription").setParameter("description", "%" + word + "%").getResultList();
//            }
//
//        } catch (NoResultException ex) {
//            log.info("Não encontrou nenhum Produto pela pesquisa '" + word + "'.");
//        }
        return p;
    }

    /**
     * Lista os detalhes de um determinado produto pelo seu Id
     *
     * @param id
     * @return
     */
    public Product findProductById(Long id) {
        log.info("Product.findProductById(" + id + ")");
        Product p = new Product();
        try {
            p = super.find(id);
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Produto com o Id " + id + ".");
        }
        return p;
    }

    /**
     * Lista os detalhes do produto segundo os atributos da designação
     *
     * @param brand
     * @param model
     * @param version
     * @return
     */
    public Product findProductByDesignation(String brand, String model, String version) {
        log.info("Product.findProductByDesignation(" + brand + ", " + model + " and " + version + ".)");
        TypedQuery<Product> q;
        Product p = new Product();
        try {
            q = em.createNamedQuery("Product.findByDesignation", Product.class);
            q.setParameter("brand", "%" + brand + "%").setParameter("model", "%" + model + "%").setParameter("version", "%" + version + "%");
            p = q.getSingleResult();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Produto com a designação " + brand + ", " + model + " e " + version + ".");
        }
        return p;
    }

}
