"usestrict";

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

//create a new module, and load the other pluggable modules 
var module = angular.module('ShoppingApp', ['ngResource','ngStorage'])

module.factory('productDAO',function($resource) {
    return $resource('/api/products/:id');
});

module.factory('categoryDAO',function($resource) {
    return $resource('/api/categories/:cat');
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
            $window.location ='sign_in.html';
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

module.factory('registerDAO',function($resource) {
    return $resource('/api/register');
});

module.factory('signInDAO',function($resource) {
    return $resource('/api/customers/:username');
});




