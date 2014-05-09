/*
 * Projeto 7 -TecnoAPI
 * Elsa Santos & VitorAires  *
 */

package pt.uc.webservice.export.entities;

/**
 *
 * @author Aires
 */
public class CategoryDot {

    private Long idCategory;
    private String category;

    public CategoryDot() {
    }

    /**
     * @return the idCategory
     */
    public Long getIdCategory() {
        return idCategory;
    }

    /**
     * @param idCategory the idCategory to set
     */
    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}
