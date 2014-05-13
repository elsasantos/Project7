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
import pt.uc.aor.webservice.entity.Category;

/**
 *
 * @author Elsa
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    public List<Category> findAllCategory() {
        log.info("Category.findAllCAtegory");
        List<Category> c = new ArrayList<>();
        try {
            c = em.createNamedQuery("Category.findAll").getResultList();
        } catch (NoResultException ex) {
            log.info("Não encontrou nenhuma Categoria.");
        }
        return c;
    }
}
