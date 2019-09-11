"usestrict";

//create a new module, and load the other pluggable modules 
var module = angular.module('ShoppingApp', ['ngResource','ngStorage'])

module.factory('productDAO',function($resource) {
    return$resource('/api/products/:id');
});

module.controller('ProductController',function(productDAO) {
    
});
