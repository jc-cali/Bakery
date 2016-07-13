package org.jcapps.bakery;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JC on 7/13/16.
 */
public class ShoppingCart implements Serializable{

    public static ShoppingCart mInstance;


    public static synchronized ShoppingCart getInstance() {

        if (mInstance == null) {
            mInstance = new ShoppingCart();
        }
        return mInstance;
    }

}
