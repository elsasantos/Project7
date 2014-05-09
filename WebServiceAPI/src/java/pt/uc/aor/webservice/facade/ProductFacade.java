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

    public List<Product> findByCategory(Long idCategory) {
        log.info("Product.findByCategoriaidCategoria(" + idCategory + ")");
        List<Product> p = null;

        try {
            Category c = cf.find(idCategory);
            return p = em.createNamedQuery("Product.findByCategoriaidCategoria").setParameter("categoria", c).getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhum Categoria'" + idCategory + "'.");
        }
        return p = new ArrayList<>();
    }

    /**
     * Lista os produtos segundo uma chave de pesquisa
     *
     * @param column
     * @param word
     * @return //
     */
    public List<Product> searchByProduct(String column, String word) {
        log.info("Product.Product.findByWord(" + word + ")");
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
            log.info("Não encontrou nenhum Produto '" + word + "'.");
        }
        return p;
    }

}
