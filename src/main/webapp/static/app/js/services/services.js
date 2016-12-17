var services = angular.module('myApp.services', []);

// share user information across controllers
services.factory('user', function ($http, $cookies, $q) {
    return {
        login: function (username, password) {
            var deferred = $q.defer();
            $http.post('/rest/login', {
                login: login,
                password: password
            }).success(function (data) {
                // now get some information about the user
                $http.get('/rest/whoami').success(function (data) {
                    $cookies.user = angular.toJson(data);
                    deferred.resolve(data);
                });
            }).error(function (data) {
                deferred.reject(data.error);
            });
            return deferred.promise;
        },
        logout: function () {
            var deferred = $q.defer();
            $http.get('/rest/logout')
                .success(function () {
                    delete $cookies.user;
                    deferred.resolve();
                }).error(function (data) {
                    deferred.reject(data.error);
                });
            return deferred.promise;
        },
        signup: function (name, email, password) {
            return $http.post('/rest/signup', {
                name: name,
                email: email,
                password: password
            });
        },
        get: function () {
            return angular.fromJson($cookies.user);
        }
    };

});
services.factory('redis', function ($http, $q) {
    var keys;
    return {
        getKeys: function () {
            var deferred = $q.defer();
            $http.get('/rest/redis/keys')
                .success(function (data) {
                    keys = data;
                    deferred.resolve(data);
                }).error(function (data, status) {
                    deferred.reject(status);
                });
            return deferred.promise;
        },
        get: function () {
            return keys;
        }
    };

});
