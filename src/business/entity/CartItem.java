package business.entity;

import business.features.imp.CartFeatureImpl;
import business.features.imp.ProductFeatureImpl;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CartItem {
    private int cartItemId;
    private Product product;
    private double price;
    private int quantity;

    public CartItem() {
    }

    public CartItem(int cartItemId, Product product, double price, int quantity) {
        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //Input Data Cart
    public void inputData(Scanner sc,  List<CartItem> cartItems, List<Product> products,String Product){
        this.cartItemId =inputCartId(cartItems);
        this.product = inputProduct(sc,Product);
        this.quantity = inputCategoryQuantiry(sc,this.product);
        this.price = this.product.getProductPrice() * this.quantity;
    }

    private int inputCategoryQuantiry(Scanner sc, Product product) {
        int qtyProducts;
        do {
            while (true){
                try{
                    System.out.println("Enter the quantity of the product: ");
                    qtyProducts = Integer.parseInt(sc.nextLine());
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("Nhập sai cú pháp phải là số nguyên");
                }
            }
            if (product.getStock()<qtyProducts){
                System.err.println("Số lượng mua không được nhiều hơn số hàng trong kho");
            }else {
                return qtyProducts;
            }

        }while (true);
    }



    private Product inputProduct(Scanner sc,String Product) {


            Optional<Product> optionalProduct = ProductFeatureImpl.products.stream().
                    filter(product1 -> product1.getProductId().equals(Product)).findFirst();
            if (optionalProduct.isPresent()) {
                return optionalProduct.get();
            }

        return null;
    }

    private int inputCartId (List<CartItem> cartItems) {
        return cartItems.stream().mapToInt(CartItem::getCartItemId).max().orElse(0)+1;
    }

    //Display Cart
    public void displayData(){
        System.out.printf("ID : %3d | ProductName: %15s  | Price: %5.2f  | Quantity: %5s \n"
                ,this.cartItemId,this.product.getProductName(),this.price,this.quantity);
    }

}
