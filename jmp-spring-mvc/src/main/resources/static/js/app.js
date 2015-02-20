(function(){
	'use strict';
	var app = angular.module('employeeManagement', ['employee']);

	app.controller('EmployeesController', ['$http', '$scope', function($http, $scope) {
		var self = this;
		self.employees = [];

		$scope.$on('employeeChanged', function(event, args) {
			$http.get('/employees').success(function(data) {
				self.employees = data.map(function(employee) {
					employee.started = new Date(employee.started);
					return employee;
				}) || [];
			});
		});

		$scope.$emit('employeeChanged');
	}]);

})();