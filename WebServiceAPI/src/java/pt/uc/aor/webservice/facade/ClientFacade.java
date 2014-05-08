/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.uc.aor.webservice.entity.Client;

/**
 *
 * @author Elsa
 */
@Stateless
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "WebServicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }

    public Client findClientByApi(String api) {
        Query query = em.createNamedQuery("Client.findByApikey", Client.class).setParameter("apikey", api);
        return (Client) query.getSingleResult();
    }

    public Boolean existApi(String api) {

        Query query = em.createNamedQuery("Client.findByApikey", Client.class).setParameter("apikey", api);

        if (query.getMaxResults() > 0) {
            return true;
        } else {
            return false;
        }

    }

}
