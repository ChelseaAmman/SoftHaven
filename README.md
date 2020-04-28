# SoftHaven
SoftHaven is a gateway for all information regarding ship entrances and departures from two ports. This software provides views for communication between 3 agents and a traffic monitoring client.<br/>


# Group Members
Chelsea Amman<br/>
Joe Schmidt<br/>
Mercedes Thompson<br/>

# Branches

Work: Intial Project, Includes Traffic Management Module<br/>
SoftHaven: Restrucutered Project. Includes PortCall and Traffic Management Modules. Due to issue with Glassfish, the project in the Work
branch had to be rebuilt.<br/>
master: Final Project, all components together<br/>

# Software Components
-Java<br/>
-NodeJS<br/>
-MySQL<br/>
-HTML<br/>
-MongoDB<br/>
-Glassfish<br/>
-Intellij<br/>

# Software Architecture
![Software Architecture Image](https://github.com/ChelseaAmman/SoftHaven/blob/master/readmeimages/architecture%20(1).png)
The architecture above was followed closely by our team. We did not stray from this model at all for the most part.

# How to setup the app
Prerequisites:<br/>
-MongoDB is installed<br/>
-MySQL Workbench is installed<br/>
-Glassfish-5.1.0 is installed<br/>
-IntelliJ Ultimate Edition is installed (To be able to use Glassfish)<br/>
- JDK 1.8.240 EE<br/>

Procedures (MongoDB):<br/>
-Install MongoDB Community Edition<br/>
-Open System Properties (search 'Environment Variables in Windows search)<br/>
-Open Environment Variables and added path for MongoDB's bin folder to the System Paths<br/>
-Open ais_transmitter folder after extracting files<br/>
-Right click on the setup script<br/>
-Select 'Properties'<br/>
-Check the box for 'Unblock' and clicked apply<br/>
-Open Powershell as administrator<br/>
-cd command into the ais_transmitter folder<br/>
-Run command 'set-executionpolicy remotesigned' and then type A to accept all<br/>
-Run command & ".\setup.ps1"<br/>

At this point I ran into an issue with the script being unable to find the files needed in the aisdk_201809 folder. As a quick solution I just copied those files into the base folder but im sure this is just a minor issue with the script or something.

-Rerun the script after copying the files needed<br/>
-Verify everything imports without issue<br/>
-Open MongoDB and clicked the 'Connect' button. Did not need to input any info into the text field.<br/>

Procedures (MySQL):<br/>
-Open MySQL Workbench<br/>
-To create table for berthing records, copy/paste the following into a SQL file and click on the lightning bolt.<br/>
CREATE TABLE `berthingrecord` (<br/>
  `IMO` mediumint unsigned NOT NULL,<br/>
  `Quay` varchar(128) DEFAULT NULL,<br/>
  `BerthNumbers` mediumint DEFAULT NULL,<br/>
  `EstimatedTimeofArrival` datetime DEFAULT NULL,<br/>
  `ActualTimeofArrival` datetime DEFAULT NULL,<br/>
  `EstimatedTimeofDeparture` datetime DEFAULT NULL,<br/>
  `ActualTimeofDeparture` datetime DEFAULT NULL,<br/>
  `Status` smallint DEFAULT NULL,<br/>
  PRIMARY KEY (`IMO`)<br/>
) ENGINE=InnoDB DEFAULT CHARSET=latin1<br/>
<br/>
<br/>
-Do the same for the prearrival table<br/>
CREATE TABLE `prearrival` (<br/>
  `ShipName` varchar(128) NOT NULL,<br/>
  `CallSign` varchar(30) DEFAULT NULL,<br/>
  `IMO` mediumint unsigned NOT NULL,<br/>
  `AgentInfo` varchar(128) DEFAULT NULL,<br/>
  `ArrivingFrom` varchar(128) DEFAULT NULL,<br/>
  `EstimatedTimeofArrival` date DEFAULT NULL,<br/>
  `BerthNumber` smallint DEFAULT NULL,<br/>
  `NextPort` varchar(128) DEFAULT NULL,<br/>
  `EstimatedTimeofDeparture` date DEFAULT NULL,<br/>
  `DischargingCargoDesc` varchar(128) DEFAULT NULL,<br/>
  `DischargingCargoAmount` int DEFAULT NULL,<br/>
  `LoadingCargoDesc` varchar(128) DEFAULT NULL,<br/>
  `LoadingCargoAmount` int DEFAULT NULL,<br/>
  `NumOfPassengersOnArrival` int DEFAULT NULL,<br/>
  `NumOfPassengersOnDeparture` int DEFAULT NULL,<br/>
  `FormValidation` tinyint(1) DEFAULT '0',<br/>
  PRIMARY KEY (`IMO`)<br/>
) ENGINE=InnoDB DEFAULT CHARSET=latin1<br/>
<br/>
<br/>
Procedures (Intellij):
-Download the project ZIP<br/>
-Extract<br/>
-Open Intellij<br/>
-Click 'Open'and select the extracted folder<br/>
-Once the project is open, select 'Add Configuration' in the upper right-hand corner<br/>
-Click the '+' and select 'Glassfish Local'<br/>
-Set URL to 'http://localhost:8080/SoftHavenLL/Login.jsp'<br/>
-Select Domain1 and when prompted choose 'Fix' and select 'SoftHaven: War exploded'<br/>
-Click apply and select run to start the app!<br/>
-Login page, username: user, password; pa$$

Procedures (Glassfish):
-Click JDBC tab
-Click JDBC Connection Pools
-Add the following
![JDBCConnection Pool](https://github.com/ChelseaAmman/SoftHaven/blob/master/readmeimages/jdbcconnection.png)
![JDBC Connection Pool Properties](https://github.com/ChelseaAmman/SoftHaven/blob/master/readmeimages/jdbcconnectionprop.png)
-Return to the JDBC tab and select JDBC Resources
-Input the following
![JDBC Resource](https://github.com/ChelseaAmman/SoftHaven/blob/master/readmeimages/jdbcresource.png)


