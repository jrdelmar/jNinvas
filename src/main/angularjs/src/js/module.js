angular.module('RDash', ['ui.bootstrap', 'ui.router', 'ngCookies']);


function LinkFormatter(value, row, index) {
    return "<a href='"+row.link+"' target='__blank'>"+value+"</a>";
}
function CopyrightLinkFormatter(value, row, index) {
    return "<a href='"+row.copyrightLink+"' target='__blank'>"+value+"</a>";
}

function RankFormatter(value, row, index) {

    if(row.danger){
        return '<span ng-if="row.danger" tooltip="Danger!" class="text-danger"><i class="fa fa-warning"></i></span>';
    } else if(row.warning){
        return '<span ng-if=row.warning" tooltip="Warning!" class="text-warning"><i class="fa fa-flash"></i></span> ';
    } else if(row.info){
        return '<span ng-if="row.info" class="text-info"><i class="fa fa-flag"></i></span>';
    } else {

        if(row.rankDescription != undefined){

            if(row.rankDescription == 'CRITICAL' ){
                return '<span ng-if="row.danger" tooltip="Danger!" class="text-danger"><i class="fa fa-warning"></i></span>';
            } else if(row.rankDescription=='HIGH'){
                return '<span ng-if="row.danger" tooltip="Danger!" class="text-danger"><i class="fa fa-warning"></i></span>';
            } else if(row.rankDescription=='MEDIUM'){
                return '<span ng-if=row.warning" tooltip="Warning!" class="text-warning"><i class="fa fa-flash"></i></span> ';
            } else if(row.rankDescription=='LOW'){
                return '<span ng-if="row.info" class="text-info"><i class="fa fa-flag"></i></span>';
            }
        }


    }

}

function PortIconFormatter(value, row, index){
    return '<span class="text-danger"><i class="fa fa-warning"></i></span>';
}

function JoinByCommaFormatter( value, row, index){
    return value.join(', ');
}

function CipherFormatter(value, row, index){
    //Bits: {{b.sslInfoDto.cipher.bits}}<br>Version: {{b.sslInfoDto.cipher.version}} <br> Name: Bits: {{b.sslInfoDto.cipher.name}}
    if (value!=undefined){
        return 'Bits: '+ value.bits + '<br>Version: ' + value.version + '<br> Name: Bits: ' + value.name;
    } else return '';

}

function CodeLinkFormatter(value, row, index){

    if(row.codeLink != undefined && row.codeLink != "" ){
        return '<span tooltip="Code Included!" class="text-danger"><a href="'+ row.codeLink + '/" target="__BLANK" class="btn"><i class="fa fa-file-code-o"></i></a></span>'
    } else return '';
}


function rowStyle(row, index) {
    var classes = ['success', 'info', 'warning', 'danger'];
    if (index % 2 === 0 ) {
        return {
            classes: classes[0]
        };
    }
    return {};
}


function rowStyle1(row, index) {
    var classes = ['success', 'info', 'warning', 'danger'];
    if (index % 2 === 0 ) {
        return {
            classes: classes[1]
        };
    }
    return {};
}


function rowStyle2(row, index) {
    var classes = ['success', 'info', 'warning', 'danger'];
    if (index % 2 === 0 ) {
        return {
            classes: classes[2]
        };
    }
    return {};
}

function rowStyle3(row, index) {
    var classes = ['success', 'info', 'warning', 'danger'];
    if (index % 2 === 0 ) {
        return {
            classes: classes[3]
        };
    }
    return {};
}

function rowStyleByRank(row, index){
    var classes = ['success', 'info', 'warning', 'danger'];

    if(row.danger){
        return {
            classes: classes[3]
        };
    } else if(row.warning){
        return {
            classes: classes[2]
        };
    } else if(row.info){
        return {
            classes: classes[1]
        };
    } else {
        if(row.rankDescription != undefined){

            if(row.rankDescription == 'CRITICAL' ){
                return {
                    classes: classes[3]
                };
            } else if(row.rankDescription=='HIGH'){
                return {
                    classes: classes[3]
                };
            } else if(row.rankDescription=='MEDIUM'){
                return {
                    classes: classes[2]
                };
            } else if(row.rankDescription=='LOW'){
                return {
                    classes: classes[1]
                };
            }
        }
    }

    return {};

}


//TODO: not working
function convertHostToCSV( json ) {

    var csv = "";
    var row = "";
    var header = "";

    if( json == undefined && json.ipStr == "" ){
        return "No Data";
    }

    //This loop will extract the label from 1st index of on array
    var j = 0;
    for (var host in json) {
        //Now convert each value to string and comma-separated
        //console.log("index=>" + host + " isArray=" + angular.isArray(json[host]));
        if( host == "bannerDto" ||
            host == "exploitDto" ||
            host == "misconfigurationList" ){

            if(host == "bannerDto"){
                for (var i = 0; i < json[host].length; i++) {

                    var headerOk = (i == 0) ? true : false;
                    for (var banner in json.bannerDto[i]) {
                        //first row, get headers

                        //if object then iterate again
                        if(typeof (json.bannerDto[i][banner]) == 'object'){

                            for(var b in json.bannerDto[i][banner] ){
                                if(b == "cipher"){
                                    for(var c in json.bannerDto[i][banner][b] ){
                                        if(headerOk) header += '"banner_' + banner + '_'+  b + '_'+  c + '",'; //cipher

                                        if(angular.isArray(json.bannerDto[i][banner][b][c])){
                                            row +=  '"' + json.bannerDto[i][banner][b][c].join(' ')  + '"' + ',';
                                        } else {
                                            row += '"' + json.bannerDto[i][banner][b][c] + '"' + ',';
                                        }
                                    }
                                } else {
                                    if(headerOk) header += '"banner_' + banner + '_'+  b + '",';
                                    if(angular.isArray(json.bannerDto[i][banner][b])){
                                        row += '"' + json.bannerDto[i][banner][b].join(' ')  + '"' + ',';
                                    } else {
                                        row += '"' + json.bannerDto[i][banner][b] + '"'  + ',';
                                    }
                                }
                            }
                        } else {
                            if(headerOk) header += '"banner_' + banner + '",';
                            //console.log("banner results=>" + banner + " value=>" + json.bannerDto[i][banner] );
                            if(angular.isArray(json.bannerDto[i][banner])){
                                row += '"' + json.bannerDto[i][banner].join(' ') + '"' + ',';
                            } else {
                                if(banner == "data"){
                                    console.log("data=>" + json.bannerDto[i][banner]);
                                }
                                row += '"' + json.bannerDto[i][banner] + '"' + ',';
                            }
                        }

                        if(i > 0){
                            row.slice(0, row.length - 1);
                            //add a line break after each row
                            csv += row + '\r\n';
                        }


                    }//json.banner
                }
            }//bannerDto

            if(host == "exploitDto"){
                for (var i = 0; i < json[host].length; i++) {
                    for (var exploit in json.exploitDto[i]) {
                        //if object then iterate again
                        if(typeof (json.exploitDto[i][exploit]) == 'object'){
                            for(var b in json.exploitDto[i][exploit] ){
                                header += '"exploit_' + exploit + '_'+  b + '",';

                                if(angular.isArray(json.exploitDto[i][exploit][b])){
                                    row += '"' + json.exploitDto[i][exploit][b].join(' ')  + '"' + ',';
                                } else {
                                    row += '"' + json.exploitDto[i][exploit][b]  + '"' +  ',';
                                }

                            }
                        } else {
                            header += '"exploit_' + exploit + '",';
                            if(angular.isArray(json.exploitDto[i][exploit])){
                                row += '"' + json.exploitDto[i][exploit].join(' ') + '"'  + ',';
                            } else {
                                row += '"' + json.exploitDto[i][exploit] + '"'  + ',';
                            }
                        }

                    }//json.exploitDto
                }
            }

            if(host == "misconfigurationList"){
                for (var i = 0; i < json[host].length; i++) {
                    for (var misconfig in json.misconfigurationList[i]) {

                        //if object then iterate again
                        if(typeof (json.misconfigurationList[i][misconfig]) == 'object'){
                            for(var b in json.misconfigurationList[i][misconfig] ){

                                if(b == "bannerDto"){
                                    for(var c in json.misconfigurationList[i][misconfig][b] ){
                                        header += '"banner_' + misconfig + '_'+  b + '_'+  c + '",'; //cipher

                                        if(angular.isArray(json.misconfigurationList[i][misconfig][b][c])){
                                            row += '"' + json.misconfigurationList[i][misconfig][b][c].join(' ')  + '"' + ',';
                                        } else {
                                            row += '"' + json.misconfigurationList[i][misconfig][b][c]  +  '"' + ',';
                                        }
                                    }
                                } else {

                                    header += '"misconfig_' + misconfig + '_'+  b + '",';

                                    if(angular.isArray(json.misconfigurationList[i][misconfig][b])){
                                        row += '"' + json.misconfigurationList[i][misconfig][b].join(' ')  + '"' + ',';
                                    } else {
                                        row += '"' + json.misconfigurationList[i][misconfig][b]  + '"' + ',';
                                    }

                                }

                            }
                        } else {
                            header += '"misconfig_' + misconfig + '",';
                            if(angular.isArray(json.misconfigurationList[i][misconfig])){
                                row += '"' + json.misconfigurationList[i][misconfig].join(' ')  + '"' + ',';
                            } else {
                                row +=  '"' + json.misconfigurationList[i][misconfig] + '"' + ',';
                            }

                        }

                    }//json.exploitDto
                }
            }

        //main host details
        } else {
          header += host + ',';

            //if array then join
            if(angular.isArray(json[host])){
                //console.log("array=>" + host  + " join=>" +  json[host].join(" "));
                row += '"' + json[host].join(" ") + '"' + ',';
            } else {
                row +=  '"' +  json[host]  +  '"' + ',';
            }


          //console.log( "host=>" + host + " main host details entries=>" +  json[host]);
        }

    }
    header = header.slice(0, -1);
    csv += header + '\r\n'; //append Label row with line break

    row = row.slice(0, -1);
    csv += row + '\r\n'; //append Label row with line break


    return csv;

//1st loop is to extract each row
/*    for (var i = 0; i < arrData.length; i++) {
        var row = "";
        //2nd loop will extract each column and convert it in string comma-seprated
        for (var index in arrData[i]) {
            row += '"' + arrData[i][index] + '",';
        }
        row.slice(0, row.length - 1);
        //add a line break after each row
        CSV += row + '\r\n';
    }

    return CSV;
    */
}
