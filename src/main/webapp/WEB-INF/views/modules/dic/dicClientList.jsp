<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户资料管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dic/dicClient/">客户资料列表</a></li>
		<shiro:hasPermission name="dic:dicClient:edit"><li><a href="${ctx}/dic/dicClient/form">客户资料添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dicClient" action="${ctx}/dic/dicClient/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>车牌号：</label>
				<form:input path="numberPlate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>电话</th>
				<th>客户名称</th>
				<th>车牌号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="dic:dicClient:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dicClient">
			<tr>
				<td><a href="${ctx}/dic/dicClient/form?id=${dicClient.id}">
					${dicClient.phone}
				</a></td>
				<td>
					${dicClient.name}
				</td>
				<td>
					${dicClient.numberPlate}
				</td>
				<td>
					<fmt:formatDate value="${dicClient.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="dic:dicClient:edit">
					<td>
    					<a href="${ctx}/dic/dicClient/form?id=${dicClient.id}">修改</a>
						<a href="${ctx}/dic/dicClient/delete?id=${dicClient.id}" onclick="return confirmx('确认要删除该客户资料吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>