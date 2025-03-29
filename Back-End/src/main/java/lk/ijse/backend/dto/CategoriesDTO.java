package lk.ijse.backend.dto;

public class CategoriesDTO {
    private String categoryId;
    private String name;
    private String sellerEmail;

    public CategoriesDTO() {
    }

    public CategoriesDTO(String categoryId, String name, String sellerEmail) {
        this.categoryId = categoryId;
        this.name = name;
        this.sellerEmail = sellerEmail;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    @Override
    public String toString() {
        return "CategoriesDTO{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", sellerEmail='" + sellerEmail + '\'' +
                '}';
    }
}
