package lk.ijse.backend.dto;

public class CartDTO {
    private String userEmail;
    private int itemCode;
    private int quantity;
    private String image;
    private String name;
    private double price;

    public CartDTO() {
    }

    public CartDTO(String userEmail, int itemCode, int quantity, String image, String name, double price) {
        this.userEmail = userEmail;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getItemCode() {
        return itemCode;
    }

    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "userEmail='" + userEmail + '\'' +
                ", itemCode=" + itemCode +
                ", quantity=" + quantity +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
