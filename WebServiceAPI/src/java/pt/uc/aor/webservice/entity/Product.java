/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */
package pt.uc.aor.webservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Aires
 */
@Entity
@Table(name = "product")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
    @NamedQuery(name = "Product.findByIdProduct", query = "SELECT p FROM Product p WHERE p.idProduct = :idProduct"),
    @NamedQuery(name = "Product.findByBrand", query = "SELECT p FROM Product p WHERE  p.quantity>0 AND  p.brand LIKE :brand"),
    @NamedQuery(name = "Product.findByModel", query = "SELECT p FROM Product p WHERE p.quantity>0 AND  p.model LIKE :model"),
    @NamedQuery(name = "Product.findByVersion", query = "SELECT p FROM Product p WHERE p.quantity>0 AND  p.version LIKE :version"),
    @NamedQuery(name = "Product.findByWord", query = "SELECT p FROM Product p WHERE p.quantity>0 AND (p.brand LIKE :word OR p.model LIKE :word OR p.version LIKE :word )"),
    @NamedQuery(name = "Product.findByDescription", query = "SELECT p FROM Product p WHERE p.description LIKE :description"),
    @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price"),
    @NamedQuery(name = "Product.findByQuantity", query = "SELECT p FROM Product p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Product.findByRepositiondate", query = "SELECT p FROM Product p WHERE p.repositiondate = :repositiondate"),
    @NamedQuery(name = "Product.findByCategoriaName", query = "SELECT p FROM Product p WHERE p.idCategory.category LIKE :word"),
    @NamedQuery(name = "Product.findByCategoriaidCategoria", query = "SELECT p FROM Product p WHERE p.idCategory = :categoria")})
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProduct")
    private Long idProduct;
    @Size(max = 255)
    @Column(name = "brand")
    private String brand;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "model")
    private String model;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "repositiondate")
    @Temporal(TemporalType.DATE)
    private Date repositiondate;
    @Size(max = 255)
    @Column(name = "version")
    private String version;
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    @ManyToOne
    private Category idCategory;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<SellProduct> sellProductList;

    public Product() {
    }

    public Product(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getRepositiondate() {
        return repositiondate;
    }

    public void setRepositiondate(Date repositiondate) {
        this.repositiondate = repositiondate;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Category getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Category idCategory) {
        this.idCategory = idCategory;
    }

    @XmlTransient
    public List<SellProduct> getSellProductList() {
        return sellProductList;
    }

    public void setSellProductList(List<SellProduct> sellProductList) {
        this.sellProductList = sellProductList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProduct != null ? idProduct.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.idProduct == null && other.idProduct != null) || (this.idProduct != null && !this.idProduct.equals(other.idProduct))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.aor.webservice.entity.Product[ idProduct=" + idProduct + " ]";
    }

}
