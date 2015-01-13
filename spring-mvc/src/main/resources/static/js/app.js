(function(){
	'use strict';
	var app = angular.module('employeeManagement', []);

	app.controller('EmployeesController', function($http) {

		this.fetchEmployees = function() {
			var self = this;
			$http.get('/employees').success(function(data) {
				self.employees = data.map(function(employee) {
					var dateObj = new Date(employee.started);
					employee.started = dateObj;
					return employee;
				}) || [];
			});
		};

		this.fetchEmployees();
	});
	
	app.controller('EmployeeController', function($http) {
		this.isEdit = false;

		this.edit = function() {
			this.isEdit = true;
		};

		this.cancel = function(employeeEdit) {
			$http.get('/employees/' + employeeEdit.id)
				.success(function(employeeSaved) {
					var dateObj = new Date(employeeSaved.started);
					employeeSaved.started = dateObj;
					angular.copy(employeeSaved, employeeEdit);
				});
			this.isEdit = false;
		};

		this.save = function(employeeEdit) {
			var editCopy = angular.copy(employeeEdit);
			editCopy.started = employeeEdit.started.toISOString().slice(0, 10);
			$http.put('/employees', editCopy)
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
	});

	app.controller('AddEmployeeController', function($http) {
		this.employee = {};

		this.addEmployee = function() {
			if (this.employee !== {}) {
				var editCopy = angular.copy(this.employee);
				editCopy.started = this.employee.started.toISOString().slice(0, 10);
				$http.post('/employees', editCopy)
					.error(function(){
						alert("Could not save new employee :(");
				});
				this.employee = {};
			}
		};
	});
})();