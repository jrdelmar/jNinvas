package com.jrdm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fooock.shodan.ShodanRestApi;
import com.fooock.shodan.model.banner.Banner;
import com.fooock.shodan.model.banner.SslInfo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jrdm.common.PropertyService;
import com.jrdm.common.Utility;
import com.jrdm.exploit.*;
import com.jrdm.exploit.model.ExploitResponse;
import com.jrdm.model.*;
import com.jrdm.mongodb.dao.HostDAO;
import com.jrdm.mongodb.dao.MongoDBConnect;
import com.jrdm.mongodb.model.DummyExploit;
import org.apache.log4j.Logger;

/**
 * Created by joannarosedelmar on 19/3/17.
 */
public class SearchServlet extends HttpServlet{

    public SearchServlet() {
        super();
    }

    final static Logger logger = Logger.getLogger(SearchServlet.class);

    /**
     * Main Screen: Search Api Key and Ip Host
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject) parser.parse(request.getReader());

        String paramApiKey = obj.get("api").getAsString();
        String paramHost = obj.get("hostIp").getAsString();

        logger.debug("param api=" + paramApiKey + " host=>" + paramHost);
        logger.debug("-----Search Vulnerability-----");

        String hostIp ;
        String apiKey ;

        if(Utility.isNotNullEmpty( paramHost ))
            hostIp = paramHost.trim();
        else throw new NullPointerException();

        if(Utility.isNotNullEmpty(paramApiKey.trim())){
            apiKey = paramApiKey.trim();
        } else {
            //get default API Key if exists, else throw exception
            PropertyService propertyService = new PropertyService();
            apiKey=propertyService.getDefaultShodanKey();
        }

        if(!Utility.isNotNullEmpty(apiKey)) throw new NullPointerException();



        ShodanRestApi api = new ShodanRestApi(apiKey);
        HostDto hostDto = new HostDto();
        List<ExploitDto> exploitDto = new ArrayList<ExploitDto>();

        api.hostByIp( hostIp )
                .subscribe(t -> {
                    hostDto.convertHost(t);
                    List<String> cveList = Utility.cleanArray( t.getVulnerabilities() );

                    logger.debug("=============================START=============================");
                    logger.debug(" Search Vulnerabilities => " + cveList.toString());

                    if (cveList.size() > 0){
                        for (String cve : cveList){
                            ShodanExploitService shodanExploitService = new ShodanExploitService();
                            List<ExploitDto> exploitDtoList = shodanExploitService.search(apiKey, cve);
                            //logger.debug("exploitDtoList returned=>" + exploitDtoList.toString());
                            exploitDto.addAll(exploitDtoList);

                            //find alias also
                            ExploitAliasService aliasService = new ExploitAliasService();
                            String[] alias = aliasService.retrieveAliasOfCve(cve);

                            if(alias.length>0){
                                for (String a : alias){
                                    List<ExploitDto> exploitAliasDtoList = shodanExploitService.search(apiKey, a);
                                    //logger.debug("exploitDtoList returned for alias=>" + exploitAliasDtoList.toString());
                                    exploitDto.addAll(exploitAliasDtoList);
                                }
                            }

                            logger.debug("exploitDtoList returned=>" + exploitDto.toString());

                        }
                    } else {
                        logger.debug("No vulnerabilities from Shodan");
                    }
                }, e -> e.printStackTrace());

        try {

            hostDto.setExploitDto(exploitDto);
            List<BannerDto> bannerList = hostDto.getBannerDto();
            List<BannerDto> filteredList = new ArrayList<BannerDto>();
            List<Misconfiguration> consoMisList = new ArrayList<Misconfiguration>();

            if(Utility.isNotNullEmpty(bannerList)){
                for (BannerDto banner : bannerList){
                    //filter by port
                    boolean addToList = true;
                    for(BannerDto b : filteredList){
                        if (Integer.compare(b.getPort(),banner.getPort()) == 0 ){
                            addToList=false;
                            break;
                        }
                    }
                    if(addToList){

                        //each banner may have a list of misconfigurations
                        MisconfigService msservice = new MisconfigService();
                        List<Misconfiguration> misList = msservice.search(banner);
                        consoMisList.addAll(misList);
                        filteredList.add(banner);
                        //logger.debug(" Banner Added => IpStr=" + banner.getIpStr() + " Port:" + banner.getPort());
                        //logger.debug(" Search Misconfigurations from Banners => " + misList.toString());
                    }
                }
            }


            hostDto.setMisconfigurationList(consoMisList);
            hostDto.setBannerDto(filteredList);

            /**
             * get total count
             */
            hostDto.setTotalMisconfigurations(consoMisList.size());
            hostDto.setTotalPorts(filteredList.size());
            hostDto.setTotalServices(filteredList.size());
            int v = 0;
            if(Utility.isNotNullEmpty(hostDto.getExploitDto())){
               v = hostDto.getExploitDto().size();
            }
            hostDto.setTotalVulnerabilities(v);

            if(Utility.isNotNullEmpty(hostDto.getIpStr())){
                saveInDb(hostDto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //debug:
        //logger.debug("HOSTDTO=>" + hostDto.toString());

        //return to the screen
        String json = new Gson().toJson(hostDto);
        response.setContentType("application/json");
        response.getWriter().write(json);

    }

    private void saveInDb(HostDto hostDto){
        //Save this host to the mongoDb
        MongoDBConnect connect = new MongoDBConnect();
        HostDAO hostDAO = new HostDAO(connect.getConnection());
        hostDAO.createHost(hostDto);
        logger.debug("Host Added Successfully with id="+hostDto.getId() + " insertdated=" + hostDto.getInsertedDate());

    }


    /**
     * History Screen - Get all items (default - retrieve all)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        MongoDBConnect connect = new MongoDBConnect();
        HostDAO hostDAO = new HostDAO(connect.getConnection());
        List<HostDto> dbHosts = hostDAO.readAll();

        //return to the screen
        String json = new Gson().toJson(dbHosts);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }


}
