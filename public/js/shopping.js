"usestrict";

//create a new module, and load the other pluggable modules 
var module = angular.module('ShoppingApp', ['ngResource','ngStorage'])

module.factory('productDAO',function($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO',function($resource) {
    return $resource('/api/categories/:cat');
});

module.factory('registerDAO',function($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO',function($resource) {
    return $resource('/api/customers/:username');
});


module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;
        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

 class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}

module.controller('ShoppingCartController',function(cart, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.buy = function(product) {
        $sessionStorage.selectedProduct = product;
        alert($sessionStorage.selectedProduct);
        $window.location ='purchase.html';
        //send post to resource api/purchase/:product
        //maybe idk lol
    }
    
    this.selectQuantity = function (quantity) {
        $sessionStorage.quantity = quantity;
        alert($sessionStorage.quantity);
    }
    /*this.addToCart = function() {
        let product = $sessionStorage.selectedProduct
        let quantity = $sessionStorage.selectedProduct.quantity //need to store quantity in sessionstorage
        let item = new SaleItem(product, quantity)
        
    }*/
});

module.controller('ProductController',function(productDAO, categoryDAO) {
    //load the products
    this.products = productDAO.query();
    //load the categories 
    this.categories = categoryDAO.query();
    
    //click handler for the category filter buttons
    this.selectCategory = function(selectedCat) {
        this.products = categoryDAO.query({"cat": selectedCat});
    };
    
    this.selectAllCategory = function() {
        this.products = productDAO.query(); // !       
    }
});

module.controller('CustomerController',function(registerDAO, signInDAO, $sessionStorage, $window) {
    this.signInMessage ="Please sign in to continue.";
    
    this.registerCustomer = function(customer) {
        registerDAO.save(null, customer,
        //success callback
        function() {
            $window.location ='signin.html';
        },
        //error callback
        function(error) {
            console.log(error);
        });
    };
    
    //alias 'this' so that we can access it inside callback functions
    let ctrl = this;
    this.signIn = function(username, password) {
        //get customer from web service
        signInDAO.get({'username': username},
        //success
        function(customer) {
            //also store the retrieved customer
            $sessionStorage.customer = customer;
            //redirect to home
            $window.location.href ='.';
        },
        //fail
        function() {
            alert("fail")
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    
    this.checkSignIn = function () {
        // has the customer been added to the session?
        if ($sessionStorage.customer) {
            this.signedIn = true;
            this.welcome = "Welcome " + $sessionStorage.customer.firstName;
        } else {
            this.signedIn = false;
        }
    };
});



