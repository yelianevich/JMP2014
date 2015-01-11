(function(){
	'use strict'
	var app = angular.module('employeeManagement', []);

	app.controller('EmployeeController', function($http) {
		var self = this;
		$http.get('/employees').success(function(data) {
			self.employees = data;
		});
	});

})();