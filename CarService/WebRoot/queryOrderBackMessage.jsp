<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Map<String, Object> mapmsg = (Map<String, Object>)request.getAttribute("mapmsg");
%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>无标题文档</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<link href="../css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="../js/select-ui.min.js"></script>
<script type="text/javascript" src="../editor/kindeditor.js"></script>


<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
</script>
<script type="text/javascript">
 function ok(){
   var th = document.form2;
   th.action="<%=path%>/servlet/OrderAction?action_flag=updateBackState&orderState=4&orderId=<%=mapmsg.get("orderbackmsgOrderId")%>";
		th.submit();
	}
</script>
</head>

<body>
	<form name="form2" action="" method="post"  >
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">表单</a></li>
			</ul>
		</div>

		<div class="formbody" style="width: 400px">
			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<ul class="forminfo">
				<li>退单用户：<%=mapmsg.get("orderbackmsgUserName") %></li>
				<li>退单原因：<%=mapmsg.get("orderbackmsgContent") %></li>
				<li><input name="" type="button" class="btn" onclick="javascript:ok();"  value="通过" />&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li></li>
			</ul>

		</div>
	</form>
</body>
</html>
