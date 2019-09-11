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
    alert("dewd");
    //load the products
    this.products = productDAO.query();
    //load the categories 
    this.categories = categoryDAO.query();
});


