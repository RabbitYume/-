<%@page import="dao.impl.PeopleManagerImpl"%>
<%@ page import="entity.People"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<script type="text/javascript">

 function topage(currentpage) {
  var form = document.forms[0];
  form.currentpage.value = currentpage;
  form.submit();
 }
</script>
<head>
<title>peoples message</title>
</head>
<body background="${pageContext.request.contextPath}/img/bg.jpg">
	<div align="center">
		<form id="select" method="post"
			action="${pageContext.request.contextPath}/PeopleSelectServlet">
			<input id="getbyid" type="text" name="id" placeholder="请输入用户编号"
				style="width: 200px; margin-top: 19px;" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="submit" style="width: 70px;">查询</button>
			&nbsp;&nbsp; <input type="button" value="查询所有"
				onclick="window.location.href = 'index.jsp'">&nbsp;&nbsp; <input
				type="button" value="添加"
				onclick="window.location.href = 'insert.jsp'">
		</form>
	</div>
	<form method="POST" id="form">
		<table align="center" class="table table-hover table-striped"
			style="height: 70px; margin-top: 50px; filter: alpha(opacity = 50, Style = 0);"
			border="1" cellspacing="0" cellpadding="10">
			<tr>
				<th>编号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>工资</th>
				<th>职位</th>
				<th>密码</th>
				<th>党员</th>
				<th>简介</th>
				<th>照片</th>
				<th colspan="2">操作</th>
			</tr>
			<c:forEach items="${requestScope.peoples}" var="people">
              <tr>
                <td>${people.p_id}</td>
                <td>${people.p_name}</td>
                <td>${people.p_gender}</td>
                <td>${people.p_wage}</td>
                <td>${people.p_position}</td>
                <td>${people.p_pwd}</td>
                <td>${people.p_dang}</td>
                <td>${people.p_resume}</td>
                <td>${people.p_photo}</td>
               <td>
                 <a href="javascript:void(0);" onclick="fun(${people.p_id})">删除</a>
                 <a href="update.jsp?id=${people.p_id}&op=update">更新</a>
               </td>
              </tr>
         </c:forEach>
		</table>
		<div align="right" style="width: 73%">
			<c:forEach begin="${pageView.startindex}" end="${pageView.endindex}" var="wp">
				<c:if test="${pageView.currentpage==wp}">
					<b>[${wp}]</b>
				</c:if>
				<c:if test="${pageView.currentpage!=wp}">
					<a href="javascript:topage('${wp}')">[${wp}]</a>
				</c:if>
			</c:forEach>
			总共${pageView.totalrecordnumber}页
		</div>
	</form>
</body>
</html>
