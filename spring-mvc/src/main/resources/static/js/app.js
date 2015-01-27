(function(){
	'use strict';
	var app = angular.module('employeeManagement', []);

	app.controller('EmployeesController', function($http) {

		this.fetchEmployees = function() {
			var self = this;
			$http.get('/employees').success(function(data) {
				self.employees = data.map(function(employee) {
					employee.started = new Date(employee.started);
					return employee;
				}) || [];
			});
		};

		this.fetchEmployees();
	});

	app.directive('employeeViewEdit', function(){
		return {
			restrict: 'E',
			templateUrl: 'template/employee-view-edit.html',
			controllerAs: 'emplForm',
			controller: function($http) {
				this.isEdit = false;

				this.edit = function() {
					this.isEdit = true;
				};

				this.cancel = function(employeeEdit, isDirty) {
					isDirty && $http.get('/employees/' + employeeEdit.id)
						.success(function(employeeSaved) {
							var dateObj = new Date(employeeSaved.started);
							employeeSaved.started = dateObj;
							angular.copy(employeeSaved, employeeEdit);
						});
				};

				this.save = function(employeeEdit) {
					$http.put('/employees', employeeEdit)
						.error(function(){
							alert("Could not save employee :(");
						});
					this.isEdit = false;
				};

				this.delete = function(employee) {
					this.isEdit = false;
					$http.delete('/employees/' + employee.id)
						.error(function(){
							alert("Could not delete employee :(");
						});
				};

				this.editing = function() {
					return this.isEdit === true;
				};

				this.viewing = function() {
					return this.isEdit === false;
				};
			}
		};
	});

	app.directive('employeeAdd', function() {
		return {
			restrict: 'E',
			templateUrl: 'template/employee-add.html',
			controllerAs: 'addEmployeeCtrl',
			controller: function($http) {
				this.employee = {};
				this.isEdit = false;

				this.edit = function() {
					this.isEdit = true;
				};

				this.cancel = function() {
					this.employee = {};
					this.isEdit = false;
				};

				this.editing = function() {
					return this.isEdit === true;
				};

				this.viewing = function() {
					return this.isEdit === false;
				};
				
				this.addEmployee = function() {
					var self = this;
					$http.post('/employees', this.employee)
						.error(function() {
							alert("Could not save new employee :(");
						})
						.success(function(){
							self.isEdit = false;
							self.employee = {};
						});
				};
			}
		};
	});

})();