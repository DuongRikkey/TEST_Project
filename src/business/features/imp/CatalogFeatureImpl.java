package business.features.imp;

import business.entity.CartItem;
import business.entity.Catalog;
import business.entity.Product;
import business.features.ICatalogFeature;

import java.util.ArrayList;
import java.util.List;

public class CatalogFeatureImpl implements ICatalogFeature {
    public static List<Catalog> catalogs=new ArrayList<>();
    static {
       catalogs.add(new Catalog(1, "Catalog 1", "Catalog 1"));
       catalogs.add(new Catalog(2, "Catalog 2", "Catalog 2"));
       catalogs.add(new Catalog(3, "Catalog 4", "Catalog 5"));


       ProductFeatureImpl.products.add(new Product("P1234","dUONG","CCC",12,15,catalogs.get(1), true));
       ProductFeatureImpl.products.add(new Product("P1239","dUONGg","CCC",12,15,catalogs.get(2), true));

       CartFeatureImpl.cartItems.add(new CartItem(1,ProductFeatureImpl.products.get(0),12,15));
    }

    @Override
    public void addAndUpdate(Catalog catalog) {
        int indexCheck=findIndexById(catalog.getCatalogId());
        if(indexCheck==-1){
            catalogs.add(catalog);
        }else {
            catalogs.set(indexCheck, catalog);

        }

    }

    @Override
    public void remove(Integer id) {
        catalogs.remove(findIndexById(id));

    }

    @Override
    public int findIndexById(Integer id) {
        return catalogs.stream().
                map(Catalog::getCatalogId).toList().
                indexOf(id);
    }
}
