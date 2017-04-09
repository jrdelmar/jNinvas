/**
 * Search Controller
 * @author: jrdelmar
 */

angular
    .module('RDash')
    .controller('HistoryCtrl', ['$scope', '$rootScope','$http', HistoryCtrl]);

function HistoryCtrl($scope, $rootScope, $http) {

    //breadcrumb
    $rootScope.meta = {page:{title:"Search", section: "History"}};
    $rootScope.$broadcast( '$scope.meta' );

    $rootScope.alerts = [];
    $rootScope.$broadcast( '$scope.alerts' );

    $rootScope.alertDetails = [];
    $rootScope.$broadcast( '$scope.alertsDetails' );

    $scope.loader = true;
    //$scope.data = {};
    $scope.pages = [];
    $scope.data = [];

    $scope.historyTable = {
            sortType: "",
            sortReverse: false,
            pageSize: 100,
            currentPage:0,
            totalPages:0
    };

    //var test = [{"name":"ENTRY [NVD CVE-2014-0346]","sourceDb":"NVD","link":"http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2014-0346","description":"** REJECT **  DO NOT USE THIS CANDIDATE NUMBER.  ConsultIDs: CVE-2014-0160.  Reason: This candidate is a reservation duplicate of CVE-2014-0160.  Notes: All CVE users should reference CVE-2014-0160 instead of this candidate.  All references and descriptions in this candidate have been removed to prevent accidental usage.","copyright":"The MITRE Corporation","copyrightLink":"http://www.mitre.org/","cvssMetric":{"score":10,"rank":5,"rankDescription":"HIGH","scoreFrom":0,"scoreTo":0,"noVectorScore":0},"id":"2014-0346","source":"CVE","bid":[],"cve":["CVE-2014-0346"],"msb":[],"osvdb":[],"port":0,"danger":true,"warning":false,"info":false,"codeExploit":true},{"name":"ENTRY [NVD CVE-2014-2601]","sourceDb":"NVD","link":"http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2014-2601","description":"The server in HP Integrated Lights-Out 2 (aka iLO 2) 2.23 and earlier allows remote attackers to cause a denial of service via crafted HTTPS traffic, as demonstrated by traffic from a CVE-2014-0160 vulnerability-assessment tool.","cvss":"AV:N/AC:L/Au:N/C:N/I:N/A:C","copyright":"The MITRE Corporation","copyrightLink":"http://www.mitre.org/","cvssMetric":{"score":7.8,"vector":"AV:N/AC:L/Au:N/C:N/I:N/A:C","rank":5,"rankDescription":"HIGH","scoreFrom":0,"scoreTo":0,"noVectorScore":0},"id":"2014-2601","source":"CVE","bid":[],"cve":["CVE-2014-2601"],"msb":[],"osvdb":[],"port":0,"danger":true,"warning":false,"info":false,"codeExploit":true},{"name":"ENTRY [NVD CVE-2014-0964]","sourceDb":"NVD","link":"http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2014-0964","description":"IBM WebSphere Application Server (WAS) 6.1.0.0 through 6.1.0.47 and 6.0.2.0 through 6.0.2.43 allows remote attackers to cause a denial of service via crafted TLS traffic, as demonstrated by traffic from a CVE-2014-0160 vulnerability-assessment tool.","cvss":"AV:N/AC:H/Au:N/C:N/I:N/A:C","copyright":"The MITRE Corporation","copyrightLink":"http://www.mitre.org/","cvssMetric":{"score":5.41,"vector":"AV:N/AC:H/Au:N/C:N/I:N/A:C","rank":4,"rankDescription":"MEDIUM","scoreFrom":0,"scoreTo":0,"noVectorScore":0},"id":"2014-0964","source":"CVE","bid":[],"cve":["CVE-2014-0964"],"msb":[],"osvdb":[],"port":0,"danger":false,"warning":true,"info":false,"codeExploit":true},{"name":"ENTRY [NVD CVE-2014-0160]","sourceDb":"NVD","link":"http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2014-0160","description":"The (1) TLS and (2) DTLS implementations in OpenSSL 1.0.1 before 1.0.1g do not properly handle Heartbeat Extension packets, which allows remote attackers to obtain sensitive information from process memory via crafted packets that trigger a buffer over-read, as demonstrated by reading private keys, related to d1_both.c and t1_lib.c, aka the Heartbleed bug.","cvss":"AV:N/AC:L/Au:N/C:P/I:N/A:N","copyright":"The MITRE Corporation","copyrightLink":"http://www.mitre.org/","cvssMetric":{"score":4.97,"vector":"AV:N/AC:L/Au:N/C:P/I:N/A:N","rank":4,"rankDescription":"MEDIUM","scoreFrom":0,"scoreTo":0,"noVectorScore":0},"id":"2014-0160","source":"CVE","bid":["66690"],"cve":["CVE-2014-0160"],"msb":[],"osvdb":[],"port":0,"danger":false,"warning":true,"info":false,"codeExploit":true},{"name":"ENTRY [NVD CVE-2015-0204]","sourceDb":"NVD","link":"http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2015-0204","description":"The ssl3_get_key_exchange function in s3_clnt.c in OpenSSL before 0.9.8zd, 1.0.0 before 1.0.0p, and 1.0.1 before 1.0.1k allows remote SSL servers to conduct RSA-to-EXPORT_RSA downgrade attacks and facilitate brute-force decryption by offering a weak ephemeral RSA key in a noncompliant role.","cvss":"AV:N/AC:H/Au:N/C:N/I:P/A:N","copyright":"The MITRE Corporation","copyrightLink":"http://www.mitre.org/","cvssMetric":{"score":2.58,"vector":"AV:N/AC:H/Au:N/C:N/I:P/A:N","rank":3,"rankDescription":"LOW","scoreFrom":0,"scoreTo":0,"noVectorScore":0},"id":"2015-0204","source":"CVE","bid":[],"cve":["CVE-2015-0204"],"msb":[],"osvdb":[],"port":0,"danger":false,"warning":false,"info":true,"codeExploit":true}] ;
    //var test = [{id:"test",source:"source test"}];
    //$('#table').bootstrapTable('load', test);


    $http({
        method : 'GET',
        url : 'search',
        contentType: 'application/json'
    }).success(function(data, status, headers, config) {

        $scope.loader = false;
        //$scope.data = data;

        var res = data;

        if (res.status == "error"){
            $rootScope.alerts.push({
                type: 'error',
                msg: res.message
            });
            $rootScope.$broadcast( '$scope.alerts' );
        } else {

            $scope.data = data.hostDtoList;

            var totalSize = $scope.data.length;

            if (totalSize == 0){
                $rootScope.alerts.push({
                    type: 'warning',
                    msg: 'No items found!'
                });
            } else {

                $rootScope.alerts.push({
                    type: 'success',
                    msg: res.message
                });
            }
            $rootScope.$broadcast( '$scope.alerts' );

        }//success





    }).error(function() {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
        $scope.loader = false;

        $rootScope.alerts.push({
            type: 'error',
            msg: 'Internal Server Error!'
        });
        $rootScope.$broadcast( '$scope.alerts' );
    });




    //TODO: pagination does not work with sorting, huhuhu.
    /**

    $scope.paginate = function(nextPrevMultiplier) {
        $scope.historyTable.currentPage  += (nextPrevMultiplier * 1);
        $scope.data = $scope.fullData.slice( $scope.historyTable.currentPage * $scope.historyTable.pageSize);
    }

    $scope.goToPage = function(page){
        $scope.historyTable.currentPage  = page - 1 ;
        $scope.data = $scope.fullData.slice( $scope.historyTable.currentPage * $scope.historyTable.pageSize);
    }

    $scope.pageButtonDisabled = function(dir) {
        if($scope.fullData == undefined) return false;
        if (dir == -1) {
            return $scope.historyTable.currentPage == 0;
        }
        return $scope.historyTable.currentPage >= $scope.fullData.length/$scope.historyTable.pageSize - 1;
    }
     **/

    $scope.display = function(d){

        $rootScope.alerts = [];
        $rootScope.$broadcast( '$scope.alerts' );

        $rootScope.alertDetails = []; //always clear
        $rootScope.alertDetails.push({
            type: 'info',
            msg: 'Details retrieved for Host ' + d.ipStr + ' on ' + d.insertedDate
        });
        $rootScope.$broadcast( '$scope.alertsDetails' );


        $scope.host = d;

        $('#tableV').bootstrapTable('load',$scope.host.exploitDto);
        $('#tableM').bootstrapTable('load',$scope.host.misconfigurationList);
        $('#tableP').bootstrapTable('load',$scope.host.bannerDto);
        $('#tableS').bootstrapTable('load',$scope.host.bannerDto);

        $scope.total_ports = $scope.host.totalPorts;
        $scope.total_services = $scope.host.totalServices;
        $scope.total_vulnerabilities = $scope.host.totalVulnerabilities;
        $scope.total_misconfigurations = $scope.host.totalMisconfigurations;

    };;;;

//display all data as Json
    $scope.saveHostDetails = function ( saveAs ) {
        var content = "";
        var extension = "";
        var type = "";

        if(saveAs=="json"){
            extension = "_details.json";
            type = "application/json;charset=utf-8;";
            content = angular.toJson($scope.host);

        } {
            //TODO: CSV output
            alert(" Unsupported file type requested.");
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
        }

    };






}

