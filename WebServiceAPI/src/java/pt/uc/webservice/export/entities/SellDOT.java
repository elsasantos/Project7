/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.webservice.export.entities;

import java.util.Date;
import java.util.List;
import pt.uc.aor.webservice.entity.Client;

/**
 *
 * @author Aires
 */
public class SellDOT {

    private Long idOrder;
    private Date actualdate;
    private Date deliverydate;
    private Client clientidClient;
    private List<ProductDOT> productDOT;

    public SellDOT() {
    }

    /**
     * @return the idOrder
     */
    public Long getIdOrder() {
        return idOrder;
    }

    /**
     * @param idOrder the idOrder to set
     */
    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * @return the actualdate
     */
    public Date getActualdate() {
        return actualdate;
    }

    /**
     * @param actualdate the actualdate to set
     */
    public void setActualdate(Date actualdate) {
        this.actualdate = actualdate;
    }

    /**
     * @return the deliverydate
     */
    public Date getDeliverydate() {
        return deliverydate;
    }

    /**
     * @param deliverydate the deliverydate to set
     */
    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    /**
     * @return the clientidClient
     */
    public Client getClientidClient() {
        return clientidClient;
    }

    /**
     * @param clientidClient the clientidClient to set
     */
    public void setClientidClient(Client clientidClient) {
        this.clientidClient = clientidClient;
    }

    /**
     * @return the productDOT
     */
    public List<ProductDOT> getProductDOT() {
        return productDOT;
    }

    /**
     * @param productDOT the productDOT to set
     */
    public void setProductDOT(List<ProductDOT> productDOT) {
        this.productDOT = productDOT;
    }

}
