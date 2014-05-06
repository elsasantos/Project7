/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Elsa
 */
@Entity
@Table(name = "attribute")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Attribute.findAll", query = "SELECT a FROM Attribute a"),
    @NamedQuery(name = "Attribute.findByIdAttribute", query = "SELECT a FROM Attribute a WHERE a.idAttribute = :idAttribute"),
    @NamedQuery(name = "Attribute.findByAttribute", query = "SELECT a FROM Attribute a WHERE a.attribute = :attribute")})

public class Attribute implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAttribute")
    private Long idAttribute;

    @Size(max = 255)
    @Column(name = "attribute")
    private String attribute;

    @JoinColumn(name = "CATEGORY_idCategory", referencedColumnName = "idCategory")
    @ManyToOne
    private Category cATEGORYidCategory;

    public Attribute() {
    }

    public Attribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public Long getIdAttribute() {
        return idAttribute;
    }

    public void setIdAttribute(Long idAttribute) {
        this.idAttribute = idAttribute;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Category getCATEGORYidCategory() {
        return cATEGORYidCategory;
    }

    public void setCATEGORYidCategory(Category cATEGORYidCategory) {
        this.cATEGORYidCategory = cATEGORYidCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAttribute != null ? idAttribute.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Attribute)) {
            return false;
        }
        Attribute other = (Attribute) object;
        if ((this.idAttribute == null && other.idAttribute != null) || (this.idAttribute != null && !this.idAttribute.equals(other.idAttribute))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pt.uc.aor.webservice.entity.Attribute[ idAttribute=" + idAttribute + " ]";
    }

}
