/**
 * Search Controller
 * @author: jrdelmar
 */

angular
    .module('RDash')
    .controller('SearchCtrl', ['$scope', '$rootScope','$http', SearchCtrl]);

function SearchCtrl($scope, $rootScope, $http) {

    //rootscopes
    $rootScope.alerts = [{
        type:'info',
        msg:"Start search by entering API key and IP Host in the fields above."
    }];
    $rootScope.meta = {page:{title:"Home", section: "Search"}};
    $rootScope.$broadcast( '$scope.meta' );
    $rootScope.$broadcast( '$scope.alerts' );

    //default
    $scope.loader = false;
    $scope.host = {};

    $scope.searchShodan = {};


    $scope.search = function() {

        $rootScope.alerts = [];
        $rootScope.$broadcast( '$scope.alerts' );

        var valid = false;
        if ($scope.searchShodan.hostIp == undefined){

            $rootScope.alerts.push({
                type: 'danger',
                msg: 'Missing Values.'
            });
            $rootScope.$broadcast( '$scope.alerts' );

        } else {
            valid = true;
            $scope.loader = true;
        }

        if(valid){

            var sendThis = {
                api : $scope.searchShodan.api,
                hostIp : $scope.searchShodan.hostIp
            };;;;

            $http({
                method : 'POST',
                url : 'search',
                contentType: 'application/json',
                data : JSON.stringify(sendThis)
            }).success(function(data, status, headers, config) {

                $scope.loader = false;
                $scope.host = data;

                if($scope.host == undefined || $scope.host.ipStr == undefined || $scope.host.ipStr == ''){
                    $rootScope.alerts.push({
                        type: 'warning',
                        msg: 'No results retrieved.'
                    });

                    $rootScope.$broadcast( '$scope.alerts' );
                } else {

                    $scope.total_ports = $scope.host.totalPorts;
                    $scope.total_services = $scope.host.totalServices;
                    $scope.total_vulnerabilities = $scope.host.totalVulnerabilities;
                    $scope.total_misconfigurations = $scope.host.totalMisconfigurations;

                    $('#tableV').bootstrapTable('load',$scope.host.exploitDto);
                    $('#tableM').bootstrapTable('load',$scope.host.misconfigurationList);
                    $('#tableP').bootstrapTable('load',$scope.host.bannerDto);
                    $('#tableS').bootstrapTable('load',$scope.host.bannerDto);

                    $rootScope.alerts.push({
                        type: 'success',
                        msg: 'Successfully Retrieved!'
                    });

                    $rootScope.$broadcast( '$scope.alerts' );

                }


            }).error(function() {
                // called asynchronously if an error occurs
                // or server returns response with an error status.
                $scope.loader = false;

                $rootScope.alerts.push({
                    type: 'danger',
                    msg: 'Internal Server Error!'
                });
                $rootScope.$broadcast( '$scope.alerts' );
            });

        }//end of valid



    };


    //display all data as Json
    $scope.saveHostDetails = function ( saveAs ) {
        var content = "";
        var extension = "";
        var type = "";


        if(saveAs=="json"){
            extension = "_details.json";
            type = "application/json;charset=utf-8;";
            content = angular.toJson($scope.host);

        } else if(saveAs=="csv"){
            extension = "_details.csv";
            type = "text/csv;charset=utf-8;";
            content = convertHostToCSV($scope.host);
        }

        if(type!="" && $scope.host.ipStr!=undefined && $scope.host.ipStr !=""){
            var filename = $scope.host.ipStr + extension;
            var blob = new Blob([content], { type:type });
            var downloadLink = angular.element('<a></a>');
            downloadLink.attr('href',window.URL.createObjectURL(blob));
            downloadLink.attr('download', filename);
            downloadLink[0].click();

        } else {
            alert("No Details!");
            console.log($scope.csv);
        }

    };




}
