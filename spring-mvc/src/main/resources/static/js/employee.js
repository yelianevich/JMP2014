(function(){
	'use strict';
	var app = angular.module('employee', []);

	app.directive('employeeViewEdit', function(){
		return {
			restrict: 'E',
			templateUrl: 'template/employee-view-edit.html',
			controllerAs: 'emplForm',
			controller: function($http, $scope) {
				this.isEdit = false;

				this.edit = function() {
					this.isEdit = true;
				};

				this.cancel = function(employeeEdit, isDirty) {
					isDirty && $scope.$emit('employeeChanged');
				};

				this.save = function(employeeEdit) {
					$http.put('/employees', employeeEdit)
						.error(function(){
							alert("Could not save employee :(");
						})
						.success(function(){
							$scope.$emit('employeeChanged');
						});
					this.isEdit = false;
				};

				this.delete = function(employee) {
					this.isEdit = false;
					$http.delete('/employees/' + employee.id)
						.error(function(){
							alert("Could not delete employee :(");
						})
						.success(function(){
							$scope.$emit('employeeChanged')
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
			controller: function($http, $scope) {
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
							$scope.$emit('employeeChanged');
						});
				};
			}
		};
	});
})();