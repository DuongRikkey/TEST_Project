package business.features;

import business.entity.Product;

public interface IProducFeature extends IGenericFeature<Product,String>{
    void sortProductByPrice();
    void searchProductByName(String nameProduct);
}
