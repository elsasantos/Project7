/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.aor.webservice.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Aires
 */
@Embeddable
public class SellProductPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sell_idOrder")
    private long sellidOrder;
    @Basic(optional = false)
    @NotNull
    @Column(name = "productCollection_idProduct")
    private long productCollectionidProduct;

    public SellProductPK() {
    }

    public SellProductPK(long sellidOrder, long productCollectionidProduct) {
        this.sellidOrder = sellidOrder;
        this.productCollectionidProduct = productCollectionidProduct;
    }

    public long getSellidOrder() {
        return sellidOrder;
    }

    public void setSellidOrder(long sellidOrder) {
        this.sellidOrder = sellidOrder;
    }

    public long getProductCollectionidProduct() {
        return productCollectionidProduct;
    }

    public void setProductCollectionidProduct(long productCollectionidProduct) {
        this.productCollectionidProduct = productCollectionidProduct;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) sellidOrder;
        hash += (int) productCollectionidProduct;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SellProductPK)) {
            return false;
        }
        SellProductPK other = (SellProductPK) object;
        if (this.sellidOrder != other.sellidOrder) {
            return false;
        }
        if (this.productCollectionidProduct != other.productCollectionidProduct) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.aor.webservice.entity.SellProductPK[ sellidOrder=" + sellidOrder + ", productCollectionidProduct=" + productCollectionidProduct + " ]";
    }

}
