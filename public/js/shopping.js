"usestrict";


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
});

module.controller('CustomerController',function(registerDAO) {
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
});

module.factory('registerDAO',function($resource) {
    return $resource('/api/register');
});

