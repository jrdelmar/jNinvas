/**
 * Created by joannarosedelmar on 18/3/17.
 */

'use strict';

/* Services */
angular
    .module('RDash')
    .factory('Breadcrumb',function( ){

    return {
        getData: function(){

            console.log("here in service");
           /*$rootScope.$on('$routeChangeSuccess', function() {
                console.log("change in route success");
                $scope.meta = {page:{title:"Home", section: "Search"}};
                console.log("here=>" + JSON.stringify($state));
            });*/

    }
    }
});


