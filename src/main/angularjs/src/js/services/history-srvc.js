/**
 * Created by joannarosedelmar on 26/3/17.
 */

angular
    .module('RDash')
    .factory('HistoryFactory', ['$http', function($http) {
    var factory = {};
    //factory.data = [];

    factory.getData = function () {
        return $http.get('/search');
    };

    return factory;

}]);