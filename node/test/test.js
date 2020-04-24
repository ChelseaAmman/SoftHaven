
const connect = require("../services/connect");
const upload = require("../services/upload/upload");
const example = '{ "Class" : "Class A", "ETA" : "ISODate(2019-08-27T06:00:00Z)", "ICES_Rect" : 4421, "MMSI" : 232111000, "PositionReport" : { "CoG" : 3, "Heading" : 204, "NavigationalStatus" : "Moored", "Position" : { "coordinates" : [ 12.59801, 55.686197 ], "type" : "Point" }, "RoT" : 0, "SoG" : 0.1 }, "StaticData" : { "A" : 23, "B" : 37, "Breadth" : 10, "C" : 4, "CallSign" : "GFVB", "D" : 6, "DataSourceType" : "AIS", "Destination" : "COPENHAGEN", "IMO" : 1001130, "Length" : 60, "Name" : "CAPELLA C", "PositionFixingDevice" : "GPS", "VesselType" : "Pleasure" }, "Timestamp" : "ISODate(2018-09-01T00:00:46Z)" }';
//upload.upload(JSON.parse(example));


