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

module.factory('saleDAO',function($resource) {
    return $resource('/api/sales');
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

module.controller('ShoppingCartController',function(cart, saleDAO, $sessionStorage, $window) {
    this.items = cart.getItems();
    this.total = cart.getTotal();
    this.selectedProduct = $sessionStorage.selectedProduct;
    this.totalPrice = cart.getTotal();
    
    this.buy = function(product) {
        $sessionStorage.selectedProduct = product;
        $window.location ='purchase.html';
    };
    
    this.checkOutCart = function() {
        let customer = $sessionStorage.customer;
        cart.setCustomer(customer);
        saleDAO.save(cart);
        delete $sessionStorage.cart;
        $window.location = "confirm.html";
    };
    
    this.addToCart = function(quantity) {
        let product = $sessionStorage.selectedProduct;
        let item = new SaleItem(product, quantity);
        cart.addItem(item);
        $sessionStorage.cart = cart;
        $window.location = "cart.html";
    };
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
        this.products = productDAO.query();    
    };
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
    
    this.signOut = function() {
        delete $sessionStorage.customer;
        $window.location.href ='index.html';
    }
    
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
            ctrl.signInMessage ='Sign in failed. Please try again.';
        });
    };
    
    this.checkSignIn = function () {
        // has the customer been added to the session?
        if ($sessionStorage.customer) {
            this.signedIn = true;
            let today = (new Date()).getDay();
            //today == 0 ? "Monday" : today == 1 ? "Tuesday" : today == 2 ? "Wednesday" : today == 3 ? "Thursday" : today == 4 : "Friday" : today == 5 ? "Saturday" : today == 6 ? "Sunday"
            switch(today) {
                case 0 :
                    today = "Sunday";
                    break;
                case 1 :
                    today = "Monday";
                    break;
                case 2 :
                    today = "Tuesday";
                    break;
                case 3 :
                    today = "Wednesday";
                    break;
                case 4 :
                    today = "Thursday";
                    break;
                case 5:
                    today = "Friday";
                    break;
                case 6 :
                    today = "Saturday";
                    break;    
            }
            this.welcome = "Welcome " + $sessionStorage.customer.firstName + ", we hope you are having a MEGA " + today;
        } else {
            this.signedIn = false;
        }
    };
});



