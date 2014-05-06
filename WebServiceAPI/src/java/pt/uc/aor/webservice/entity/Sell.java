/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.uc.aor.webservice.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Elsa
 */
@Entity
@Table(name = "sell")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sell.findAll", query = "SELECT s FROM Sell s"),
    @NamedQuery(name = "Sell.findByIdOrder", query = "SELECT s FROM Sell s WHERE s.idOrder = :idOrder"),
    @NamedQuery(name = "Sell.findByActualdate", query = "SELECT s FROM Sell s WHERE s.actualdate = :actualdate"),
    @NamedQuery(name = "Sell.findByDeliverydate", query = "SELECT s FROM Sell s WHERE s.deliverydate = :deliverydate")})
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrder")
    private Long idOrder;
    @Column(name = "actualdate")
    @Temporal(TemporalType.DATE)
    private Date actualdate;
    @Column(name = "deliverydate")
    @Temporal(TemporalType.DATE)
    private Date deliverydate;
    @ManyToMany
    private Collection<Product> productCollection;

    @JoinColumn(name = "Client_idClient", referencedColumnName = "idClient")
    @ManyToOne
    private Client clientidClient;

    public Sell() {
    }

    public Sell(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Date getActualdate() {
        return actualdate;
    }

    public void setActualdate(Date actualdate) {
        this.actualdate = actualdate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    @XmlTransient
    public Collection<Product> getProductCollection() {
        return productCollection;
    }

    public void setProductCollection(Collection<Product> productCollection) {
        this.productCollection = productCollection;
    }

    public Client getClientidClient() {
        return clientidClient;
    }

    public void setClientidClient(Client clientidClient) {
        this.clientidClient = clientidClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrder != null ? idOrder.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sell)) {
            return false;
        }
        Sell other = (Sell) object;
        if ((this.idOrder == null && other.idOrder != null) || (this.idOrder != null && !this.idOrder.equals(other.idOrder))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.aor.webservice.entity.Sell[ idOrder=" + idOrder + " ]";
    }

}
