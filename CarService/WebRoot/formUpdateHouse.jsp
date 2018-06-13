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
   th.action="<%=path%>/servlet/HotelAction?action_flag=UpdateMessage&houseId=<%=mapmsg.get("houseId")%>";
		th.submit();
	}
</script>
</head>

<body>
	<form name="form2" action="" method="post"  enctype="multipart/form-data">
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
				<li><label>客房名称</label><input name="houseName" type="text" class="dfinput" value="<%=mapmsg.get("houseName")%>" /></li>
				<li><label>客房价格</label><input name="houseMoney" type="text" class="dfinput"  value="<%=mapmsg.get("houseMoney")%>"/></li>
						<li><label>客房类型</label>

					<div class="vocation">
						<select class="select1" name="chapterMsg">
							<%
								if (!listMessage.isEmpty()) {
									/*  for(Map<String,Object> map:list){ */
									for (int i = 0; i < listMessage.size(); i++) {
										Map<String, Object> map = listMessage.get(i);
							%>

							<option value="<%=map.get("typeId")%>,<%=map.get("typeName")%>"><%=map.get("typeName")%></option>

							<%
								}
								}
							%>
						</select>
					</div>
				<li><label>客房图片</label><input name="houseImage" type="file" class="text2" size="60" ></li>
				<li><label>&nbsp;</label><input name="" type="button" class="btn" onclick="javascript:dosubmit();"  value="确认修改" /></li>
			</ul>

		</div>
	</form>
</body>
</html>
