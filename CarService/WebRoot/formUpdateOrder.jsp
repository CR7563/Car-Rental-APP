<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	Map<String, Object> mapmsg = (Map<String, Object>)request.getAttribute("mapmsg");
	List<Map<String, Object>> listMessage = (List<Map<String, Object>>)request.getAttribute("listMessage");
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
 function dosubmit(){
   var th = document.form2;
   th.action="<%=path%>/servlet/OrderAction?action_flag=updateOrderState&orderId=<%=mapmsg.get("orderId")%>";
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

		<div class="formbody">

			<div class="formtitle">
				<span>基本信息</span>
			</div>

			<ul class="forminfo">
				<li><label>订单用户</label><input onfocus=this.blur() name="houseName" type="text" class="dfinput" value="<%=mapmsg.get("orderUserName")%>" /></li>
				<li><label>订单价格</label><input  onfocus=this.blur() name="houseMoney" type="text" class="dfinput"  value="<%=mapmsg.get("orderMoney")%>元"/></li>
						<li><label>更换客房</label>

					<div class="vocation">
						<select class="select1" name="chapterMsg">
							<%
								if (!listMessage.isEmpty()) {
									/*  for(Map<String,Object> map:list){ */
									for (int i = 0; i < listMessage.size(); i++) {
										Map<String, Object> map = listMessage.get(i);
							%>

							<option value="<%=map.get("houseId")%>"><%=map.get("houseName")%></option>

							<%
								}
								}
							%>
						</select>
					</div>
				<li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="javascript:dosubmit();"  value="确认修改" /></li>
			</ul>

		</div>
	</form>
</body>
</html>
