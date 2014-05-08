/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.facade;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Aires
 */
@Stateless
public class makingSell {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Inject
    private ClientFacade cf;
    @Inject
    private ProductFacade pf;
    @Inject
    private SellFacade sf;
    @Inject
    private SellProductFacade spf;

}
