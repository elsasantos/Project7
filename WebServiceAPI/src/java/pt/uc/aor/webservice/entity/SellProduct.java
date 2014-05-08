/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Aires
 */
@Entity
@Table(name = "sell_product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SellProduct.findAll", query = "SELECT s FROM SellProduct s"),
    @NamedQuery(name = "SellProduct.findByQuantity", query = "SELECT s FROM SellProduct s WHERE s.quantity = :quantity")})
public class SellProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSellProduct")
    private Long idSellProduct;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "Sell_idOrder", referencedColumnName = "idOrder", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sell sell;
    @JoinColumn(name = "productCollection_idProduct", referencedColumnName = "idProduct", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public SellProduct() {
    }

    public SellProduct(Integer quantity, Sell sell, Product product) {
        this.quantity = quantity;
        this.sell = sell;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Sell getSell() {
        return sell;
    }

    public void setSell(Sell sell) {
        this.sell = sell;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSellProduct != null ? idSellProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellProduct)) {
            return false;
        }
        SellProduct other = (SellProduct) object;
        if ((this.idSellProduct == null && other.idSellProduct != null) || (this.idSellProduct != null && !this.idSellProduct.equals(other.idSellProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.aor.webservice.entity.SellProduct[ sellProductPK=" + idSellProduct + " ]";
    }

}
