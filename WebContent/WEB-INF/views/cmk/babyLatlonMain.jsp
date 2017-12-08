<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@page import="com.sc.util.DateUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String ip = "192";
String to = DateUtil.getCurrentDateTime();
String from = DateUtil.getDateTimeByResetDay(to,-1);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     
	<style type="text/css">  
		*{font-size:12px;}  
		body,html{margin:0px;padding:0px;overflow:hidden;}  
		#tip{position:absolute;z-index:100000;width:auto;padding:5px;top:50%; left:40%;  MARGIN-RIGHT: auto; MARGIN-LEFT: auto; color:#039;font-weight:bold;background:#FFF;display:none;}  
		
	</style>  
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>
	<script type="text/javascript" src="<%=basePath%>cheldd/map/goome.maps.js" charset="gb2312"></script>  
	<script type="text/javascript" src="<%=basePath%>cheldd/map/jquery_v1.6.2.js" charset="gb2312"></script> 
	<script type="text/javascript" src="<%=basePath%>cheldd/map/cn.js" charset="gb2312"></script>  
	<script type="text/javascript" src="<%=basePath%>cheldd/map/popupmarker.js" charset="gb2312"></script>  
	<script type="text/javascript" src="<%=basePath%>cheldd/map/core.js" charset="gb2312"></script>  
	<script type="text/javascript" src="<%=basePath%>cheldd/map/playback.js" charset="gb2312"></script>  
	<script type="text/javascript" src="<%=basePath%>cheldd/map/My97DatePicker/WdatePicker.js" defer="defer"></script>
	<script type="text/javascript">  
	function restFrame(){  
		// alert(window.parent.document.body.clientHeight);
	    var windowheight = window.parent.document.body.clientHeight;
		var windowwidth = $(window).width(); 
	    var topMenuHeight = 22;//顶部菜单区域的整体高度  
	    var divCanvas = document.getElementById("map");   
	  //  divCanvas.style.height= (windowheight- topMenuHeight)+"px";  
	   divCanvas.style.height= (windowheight)+"px";  
	    divCanvas.style.width= windowwidth+"px";  
	}  
	//,"http://his-dx.vehicling.net/"  
	var PlayBack = new PlayBack("map",65979,"GT02",65,'','<%=basePath%>');  
	function init(){  
	    restFrame();  
	    PlayBack.createMap("cn");  
	}  
	document.onmousemove = mouseCoords;  
	  
	//getMaxDate生成客户端本地时间  
	function getMaxDate(){  
	    var t = new Date();  
	    var maxDate = [t.getFullYear(), t.getMonth()+1, t.getDate()].join('-');  
	    maxDate += ' ' + t.toLocaleTimeString();  
	    return maxDate;   
	}  
	//getMinDate生成客户端本地时间   
	function getMinDate(){  
	    var t = new Date();  
	    t.setMonth(t.getMonth() - 2);//最小时间少2个月  
	    var maxDate = [t.getFullYear(), t.getMonth()+1, t.getDate()].join('-');  
	    maxDate += ' ' + t.toLocaleTimeString();  
	    return maxDate;   
	}  
	</script>
  </head>
  
  <body onResize="restFrame();" onLoad="init();">  
<span id="tip">正在加载数据,请耐心等待.......</span>  
<div style="font-size:12px;height:25px;text-align:center;background:#C5CFD6;border-bottom:1px solid #999;">  

    <input type="text" name="from" id="from" value="<%=from %>" size="22"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: getMaxDate(),minDate:getMinDate()})" class="Wdate" />  
    到：  
    <input type="text" name="to" id="to" value="<%=to %>" size="22"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate: getMaxDate(),minDate:getMinDate()})" class="Wdate" />  
    频    率：  
    <select name="freq" id="freq" onchange="PlayBack.Frequency = this.value"> 
        <option value="1000" selected>正常速度</option>  
        <option value="500" >正常速度（2倍）</option>  
        <option value="100">正常速度（10倍）</option>  
        <option value="10">正常速度（100倍）</option>  
    </select>  
    秒  
    <input id="PLAY" onClick="PlayBack.getDataFrist(document.getElementById('from').value,document.getElementById('to').value,document.getElementById('freq').value,'<%=ip%>','<%=basePath%>babyLatlon/list.htm')"  
    	type="button" value="开始回放" />  
    <input id="STOP" onClick="PlayBack.stopPlay()" type="button" value="停止播放" style="display:none;"/>  
    <input id="mdTime" type="hidden" value=""/>  
</div>  
<div id="map"></div>  
</body>
</html> 
