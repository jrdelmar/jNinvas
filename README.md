# Non-Intrusive Network Vulnerability Assessment System (NiNVAS) 

A passive alternative to the more traditional active scans employed for vulnerability checks.
This is a web application system using AngularJS, Bootstrap, Java 8 and MongoDB technologies, leverages the exhaustive API-available data-set and functions returned by Shodan such as banner, metadata, and exploit search capabilities.
Not meant to replace active vulnerability scans, this is nevertheless a useful and convenient application whose significant contribution lies in providing a simple yet invaluable resource for misconfiguration and vulnerability initial checks. 

Created in partial fulfilment of the requirements for UPOU MIS ~

## System Overview
NiNVAS was designed to complement the rich database of collected information from Shodan. 
Using [jShodan](https://github.com/fooock/jshodan/) java library, the application issues REST-full search queries via [Shodan’s API](https://api.shodan.io) and processes the result through its extraction modules. 
If Shodan has returned a vulnerability list, NiNVAS launches the Vulnerability Extraction Module which collects vulnerability information from Shodan’s exploit [API](https://exploits.shodan.io/api), and ExploitSearch database.
Created by Security Enthusiast Adam Compton, [ExploitSearch](http://www.exploitsearch.net/) is a website that attempts to cross-reference exploits and vulnerability data from various sources such as Tenable, Mitre, NVD and Rapid7, by crawling publicly available resources like websites, data (RSS) feeds and archived downloads. 


## Basic Functionalities

### Configuration Settings:
#### Vulnerabilities 
User can change the default settings for vulnerability rankings.
The application follows the vulnerability ranking specified in [National Vulnerability Database](https://nvd.nist.gov/cvss.cfm). 
NVD provides severity rankings of "Low," "Medium," and "High" in addition to the numeric CVSS score but these qualitative rankings are simply mapped from the numeric CVSS scores (version 2):
```
LOW: CVSS Base Score of 0.0-3.9
MEDIUM: CVSS Base Score of 4.0-6.9
HIGH: CVSS Base Score 7.0-10.0.

```
* If a vendor provides no details about a vulnerability, NVD will score that vulnerability as a 10.0 (the highest rating). This setting can be changed. 
* If a remote exploit code is found, the vulnerability rank is automatically set to HIGH regardless of the computed CVSS Score. 

#### Misconfigurations
User sets the misconfiguration strings to search and provides the severity rating

```
SEVERE: Firewall 200 OK, router 200 OK, cisco 200 OK
CRITICAL: db -authentication -authorisation, sql -authentication -authorisation
HIGH: default, default password, default WWW-authenticate
```

### Search
User enters the public IP for searching. The following details will be displayed:
* Host information 
* Ports and services opened to the internet
* Vulnerability list
* Banners and misconfiguration strings 

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management
* [Gulp](http://gulpjs.com) - Workflow Automation tool
* [JQuery v3.2](https://jquery.com/) - Javascript Library
* [AngularJS v1.5](https://angularjs.org/) - JavaScript MV* Framework
* [Bootstrap](http://getbootstrap.com) - CSS Framework
* [Rdash-UI](https://github.com/rdash/rdash-ui) - UI Template
* [Java8](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) - Platform
* [MongoDB](https://www.mongodb.com) - Data Management


## Author

* **Joanna Rose Del Mar** 

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE.md) file for details

## Acknowledgments

* Security Adviser - [L.A.Denopol](https://www.linkedin.com/in/louwil-alvin-denopol-gcia-gcih-gwapt-60689725/)
* Adviser - W.L.SeeTho
* UPOU Adviser - R.A.Pugoy 
* UPOU - E.Duran and L.M.Tan


