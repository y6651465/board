<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>simpleMap</title>
<script
  src="https://code.jquery.com/jquery-3.2.1.js"
  integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  crossorigin="anonymous"></script>
<script src="https://apis.skplanetx.com/tmap/js?version=1&format=javascript&appKey=8d8489bc-66bb-3016-87ed-04f89ff27e37"></script>
<script type="text/javascript">
var bool = true;
var drawline = true;
var my = {
		
		setLat: function (data) {
			this.lat = data;
		},
		setLng: function (data) {
			this.lng = data;
		},
		getLat: function () {
			return this.lat;
		},
		getLng: function () {
			return this.lng;
		}
}
			
	

navigator.geolocation.getCurrentPosition(function(position) {
	my.setLat(position.coords.latitude);
	my.setLng(position.coords.longitude);
	
	
    var pos = {
      lat: position.coords.latitude,
      lng: position.coords.longitude
      
    };
    console.log(pos.lat);
    console.log(pos.lng);

  });
  console.dir(my);
  console.log("my : ",my.getLat());
  console.log("my : ",my.getLng());
  
function initTmap(locState){
    centerLL = new Tmap.LonLat(14135199.7637174, 4518258.6620310);
    map = new Tmap.Map({div:'map_div',
                        width:'100%', 
                        height:'70%',
                        transitionEffect:"resize",
                        animation:true
                    }); 
         searchPOI(locState);
          addMarkerLayer(); 
          if(!drawline) {
             searchRoute()
          }
};

function addMarkerLayer(){
    markerLayer = new Tmap.Layer.Markers("marker");
    map.addLayer(markerLayer);
};
function addMarker(options){
    var size = new Tmap.Size(12,19);
    var offset = new Tmap.Pixel(-(size.w/2), -size.h);
    var icon = new Tmap.Icon("https://developers.skplanetx.com/upload/tmap/marker/pin_b_s_simple.png",size,offset);
    var marker = new Tmap.Markers(options.lonlat,icon,options.label);
    markerLayer.addMarker(marker);
    marker.events.register("mouseover", marker, onOverMouse);
    marker.events.register("mouseout", marker, onOutMouse);
    marker.idString = options.id;
    marker.events.register("click", marker, onClickMouse);
}
function onOverMouse(e){
    this.popup.show();
}
function onOutMouse(e){
    this.popup.hide();   
}
function onClickMouse(e){
    getDataFromId(this.idString);
}

function searchPOI(searchP){
    tdata = new Tmap.TData();
    tdata.events.register("onComplete", tdata, onCompleteTData);
    var center = map.getCenter();
    tdata.getPOIDataFromSearch(encodeURIComponent(searchP), {centerLon:center.lon, centerLat:center.lat});
}

function onCompleteTData(e){
    if(jQuery(this.responseXML).find("searchPoiInfo pois poi").text() != ''){
        jQuery(this.responseXML).find("searchPoiInfo pois poi").each(function(){
            var name = jQuery(this).find("name").text();
            var id = jQuery(this).find("id").text();
            var lon = jQuery(this).find("frontLon").text();
            var lat = jQuery(this).find("frontLat").text();
            if ($("#sX") == null && $("#sY") == null){
	            var options = {
	                label:new Tmap.Label(name),
	                lonlat:new Tmap.LonLat(lon, lat),
	                id:id
	            };
            }
            else {
            	var option = {
            			
            	}
            }
            addMarker(options);
        });
    }else {
        alert('검색결과가 없습니다.');
    }
    map.zoomToExtent(markerLayer.getDataExtent());
    tdata.events.unregister("onComplete", tdata, onCompleteTData);
}

function getDataFromId(id){
    tdata = new Tmap.TData();
    tdata.events.register("onComplete", tdata, onCompleteTDataID);
    tdata.getPOIDataFromId(id);
}

function onCompleteTDataID(e){
    if(jQuery(this.responseXML).find("poiDetailInfo").text() != ''){
        jQuery(this.responseXML).find("poiDetailInfo").each(function(){
            var name = jQuery(this).find("name").text();
            var lat = jQuery(this).find("lat").text();
            var lon = jQuery(this).find("lon").text();
            if(bool){
                $("#sX").val(lon);
                 $("#sY").val(lat);
                 $("#sName").val(name);
                 $("input[name='start']").val(name);
                 
                 bool = false;
            }
            else {
                $("#eX").val(lon);
                 $("#eY").val(lat);
                 $("#eName").val(name);
                 $("input[name='end']").val(name);
                 drawline = false;
            }
            
        });
    }else {
        alert('검색결과가 없습니다.');
    }
    
    tdata.events.unregister("onComplete", tdata, onCompleteTDataID);
}




//경로 정보 로드
function searchRoute(){
   var routeFormat = new Tmap.Format.KML({extractStyles:true, extractAttributes:true});
    var startX = $("#sX").val();
    var startY = $("#sY").val();
    var endX = $("#eX").val();
    var endY = $("#eY").val();
    var startName = $("#sName").val();
    var endName = $("#eName").val();
    var urlStr = "https://apis.skplanetx.com/tmap/routes/pedestrian?version=1&format=xml";
        urlStr += "&startX="+startX;
        urlStr += "&startY="+startY;
        urlStr += "&endX="+endX;
        urlStr += "&endY="+endY;
        urlStr += "&startName="+encodeURIComponent(startName);
        urlStr += "&endName="+encodeURIComponent(endName);
        urlStr += "&appKey=c5393eed-f92d-3f88-9967-7fa33351302c";
        
    var prtcl = new Tmap.Protocol.HTTP({
                                        url: urlStr,
                                        format:routeFormat
                                        });
    var routeLayer = new Tmap.Layer.Vector("route", {protocol:prtcl, strategies:[new Tmap.Strategy.Fixed()]});
    routeLayer.events.register("featuresadded", routeLayer, onDrawnFeatures);
    routeLayer.events.register("featuresadded", routeLayer, information);
    map.addLayer(routeLayer);
    
    
}
//경로 그리기 후 해당영역으로 줌
function onDrawnFeatures(e){
    map.zoomToExtent(this.getDataExtent());
}
function information(e) {
   var event = e.features[0].attributes;
   var htime = Math.floor(event.totalTime / 3600);
   var h = "";
   if(htime != 0){
      h += Math.floor(event.totalTime / 3600) + "시간";
   }
   var stime = 0;
   if((event.totalTime % 60) > 30){
      stime = 1;
   }
    h +=  "약" + (Math.floor((event.totalTime % 3600) / 60) + stime)  + "분";         
//    h +=  Math.floor((event.totalTime % 3600) / 60) + "분" +  event.totalTime % 60 + "초";         
   $("#time").html(h);
   $("#mter").html((event.totalTime / 1000).toFixed(1) + "KM");
   console.log(event.totalDistance);
   console.log(event.totalTime);
}
function myLocation () {
	$("#sX").val(my.getLng());
	$("#sY").val(my.getLat());
	$("#sName").val("현위치");
	$("input[name=start]").val("현위치");
	  console.log("my : ",my.getLat());
	  console.log("my : ",my.getLng());
	  console.log($("#sName").val());
}
</script>
</head>
<body>
<body>
출발지 <input type="text" name="start" />
<input type="submit" id="searchStart" value="검색"/> <input type="button" onclick="myLocation()" value="현위치"><br>
도착지 <input type="text" name="end" />
<input type="submit" id="searchEnd" value="검색"/>
<button id="mapSearch">길찾기</button>
<div id="map_div"></div>
<div id="result">
   <p>시간 : <span id="time"> </span> 거리 : <span id="mter"></span></p>
</div>

<input type="hidden" id="sX">
<input type="hidden" id="sY">
<input type="hidden" id="sName">
<input type="hidden" id="eX">
<input type="hidden" id="eY">
<input type="hidden" id="eName">
<script>

  
$("#searchStart").click(function () {
   if($("#map_div").html() != "") {
      $("#map_div").html("");
   }
   var locXY = $("input[name='start']").val();
   initTmap(locXY, "b");
})
$("#searchEnd").click(function () {
   if($("#map_div").html() != "") {
      $("#map_div").html("");
   }   
   var locXY = $("input[name='end']").val();
   initTmap(locXY, "b");
})
$("#mapSearch").click(function () {
   if($("#map_div").html() != "") {
      $("#map_div").html("");
   }
   initTmap("test", "a");
})

</script>
</body>
</html>