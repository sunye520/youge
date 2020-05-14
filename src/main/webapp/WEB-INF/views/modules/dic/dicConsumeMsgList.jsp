<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费记录管理</title>
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
		<li class="active"><a href="${ctx}/dic/dicConsumeMsg/">消费记录列表</a></li>
		<shiro:hasPermission name="dic:dicConsumeMsg:edit"><li><a href="${ctx}/dic/dicConsumeMsg/form">消费记录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="dicConsumeMsg" action="${ctx}/dic/dicConsumeMsg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>车牌号：</label>
				<form:input path="numberPlate" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="phone" htmlEscape="false" maxlength="20" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
                <a href="${ctx}/dic/dicConsumeMsg/export?phone=${dicConsumeMsg.phone}&name=${dicConsumeMsg.name}&numberPlate=${dicConsumeMsg.numberPlate}&sumMoney=${sumMoney}" class="btn btn-primary" >导出数据</a>
            </li>
			<li class="clearfix"></li>
            <li class="btns">消费总金额：${sumMoney}</li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>车牌号</th>
				<th>客户姓名</th>
				<th>客户电话</th>
				<th>消费时间</th>
				<th style="width: 600px;">消费信息</th>
				<th>消费金额</th>
				<shiro:hasPermission name="dic:dicConsumeMsg:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="dicConsumeMsg">
			<tr>
				<td><a href="${ctx}/dic/dicConsumeMsg/form?id=${dicConsumeMsg.id}">
						${dicConsumeMsg.numberPlate}
				</a></td>
				<td>
						${dicConsumeMsg.name}
				</td>
				<td>
						${dicConsumeMsg.phone}
				</td>
				<td>
					<fmt:formatDate value="${dicConsumeMsg.consumeDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${dicConsumeMsg.consumeMsg}
				</td>
				<td>
					${dicConsumeMsg.consumeMoney}
				</td>
				<shiro:hasPermission name="dic:dicConsumeMsg:edit">
					<td>
    					<a href="${ctx}/dic/dicConsumeMsg/form?id=${dicConsumeMsg.id}">修改</a>
						<a href="${ctx}/dic/dicConsumeMsg/delete?id=${dicConsumeMsg.id}" onclick="return confirmx('确认要删除该消费记录吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>