/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.facade;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import pt.uc.aor.webservice.entity.Product;

/**
 *
 * @author Elsa
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    //MÉTODOS CRIADOS PARA A API:
    /**
     * Lista os produtos por categoria
     *
     * @param idCategory
     * @return
     */
    public List<Product> findByCategory(Long idCategory) {
        List<Product> p = null;
        try {
            return p = em.createNamedQuery("Product.findByCategoriaidCategoria").setParameter("categoria", idCategory).getResultList();
        } catch (NoResultException ex) {
            //TODO log
        }
        return p = new ArrayList<>();
    }

    /**
     * Lista os produtos segundo uma chave de pesquisa
     *
     * @param column
     * @param word
     * @return
     */
    public List<Product> searchByProduct(String word, String column) {
        List<Product> p = null;
        try {
            if (column.equals("Designation")) {
                p = em.createNamedQuery("Product.findByWord").setParameter("word", "%" + word + "%").getResultList();
            }
            if (column.equals("Category")) {
                p = em.createNamedQuery("Product.findByCategoriaName").setParameter("category", "%" + word + "%").getResultList();
            }
            if (column.equals("Description")) {
                p = em.createNamedQuery("Product.findByDescription").setParameter("description", "%" + word + "%").getResultList();
            }
            return p;
        } catch (NoResultException ex) {
            //TODO log
            return p = new ArrayList<>();
        }
    }
}
