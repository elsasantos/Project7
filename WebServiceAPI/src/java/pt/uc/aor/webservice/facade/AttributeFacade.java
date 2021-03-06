/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.uc.aor.webservice.entity.Attribute;

/**
 *
 * @author Elsa
 */
@Stateless
public class AttributeFacade extends AbstractFacade<Attribute> {
    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AttributeFacade() {
        super(Attribute.class);
    }

}
