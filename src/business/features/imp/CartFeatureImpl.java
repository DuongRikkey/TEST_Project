package business.features.imp;

import business.entity.CartItem;
import business.entity.Product;
import business.features.ICartFeature;
import business.features.IGenericFeature;

import java.util.ArrayList;
import java.util.List;

public class CartFeatureImpl implements ICartFeature {
    public static List<CartItem> cartItems = new ArrayList<>();



    @Override
    public void addAndUpdate(CartItem cartItem) {
        int index = findIndexById(cartItem.getCartItemId());
          if (index == -1) {
              cartItems.add(cartItem);
          }else {
              cartItems.set(index, cartItem);
          }

    }

    @Override
    public void remove(Integer id) {
       remove(findIndexById(id));
    }

    @Override
    public int findIndexById(Integer id) {
        return cartItems.stream().map(CartItem::getCartItemId).toList().indexOf(id);
    }




}
