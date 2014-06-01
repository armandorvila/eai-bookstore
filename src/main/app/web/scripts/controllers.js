'use strict';

/* Controllers */

bookstoreApp.controller('MainController', [
		'$scope',
		'$http',
		function($scope, $http) {
			$scope.customer = {};

			$scope.total = function(book){
				if(book && book.price && book.amount){
					return book.price * book.amount;
				}
				else if (book && book.price){
					return book.price;
				}
				else {
					return 0;
				}
			}
			
			$scope.submitOrder = function() {
				$scope.errorMessage = undefined;
				$scope.errorMessage = undefined;
				var xsrf = $.param({
					name : $scope.customer.name,
					nif : $scope.customer.nif,
					address : $scope.customer.address,
					mail : $scope.customer.email,
					loan : $scope.customer.loan,
					book : $scope.book.isbn,
					amount : $scope.book.amount,
				});
				
				$http({
				    method: 'POST',
				    url: '/orders/',
				    data: xsrf,
				    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
				}).
			    success(function(data, status, headers, config) {
			       $scope.successMessage = 'Your order has been recived, you will be notified as soon as posible.'
			    	   $scope.customer = {};
			       		$scope.book = undefined;
			    }).
			      error(function(data, status, headers, config) {
			    	  $scope.errorMessage = "Sorry, Your order couldn't be sent, try again later."
			      });
			};

			$scope.getBooks = function(val) {
				return $http.get('/books').then(function(res) {
					return res.data;
				});
			};

			$scope.getLocations = function(val) {
				return $http.get(
						'http://maps.googleapis.com/maps/api/geocode/json', {
							params : {
								address : val,
								sensor : false
							}
						}).then(function(res) {
					return res.data.results;
				});
			};
		} ]);

bookstoreApp.controller('LanguageController', [ '$scope', '$translate',
		function($scope, $translate) {
			$scope.changeLanguage = function(languageKey) {
				$translate.use(languageKey);
			};
		} ]);

bookstoreApp.controller('MenuController', [ '$scope', function($scope) {
} ]);
