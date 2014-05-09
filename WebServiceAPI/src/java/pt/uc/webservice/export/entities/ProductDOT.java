/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.webservice.export.entities;

import java.util.Date;

/**
 *
 * @author Aires
 */
public class ProductDOT {

    private Long idProduct;
    private String brand;
    private String description;
    private String model;
    private Double price;
    private Integer quantity;
    private Date repositiondate;
    private String version;

    public ProductDOT() {
    }

    /**
     * @return the idProduct
     */
    public Long getIdProduct() {
        return idProduct;
    }

    /**
     * @param idProduct the idProduct to set
     */
    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the repositiondate
     */
    public Date getRepositiondate() {
        return repositiondate;
    }

    /**
     * @param repositiondate the repositiondate to set
     */
    public void setRepositiondate(Date repositiondate) {
        this.repositiondate = repositiondate;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

}
