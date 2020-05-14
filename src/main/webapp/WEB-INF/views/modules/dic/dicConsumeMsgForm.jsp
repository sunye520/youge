<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>消费记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dic/dicConsumeMsg/">消费记录列表</a></li>
		<li class="active"><a href="${ctx}/dic/dicConsumeMsg/form?id=${dicConsumeMsg.id}">消费记录<shiro:hasPermission name="dic:dicConsumeMsg:edit">${not empty dicConsumeMsg.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dic:dicConsumeMsg:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="dicConsumeMsg" action="${ctx}/dic/dicConsumeMsg/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">车牌号：</label>
			<div class="controls">
				<%--<form:input path="numberPlate" htmlEscape="false" maxlength="20" class="input-xlarge required"/>--%>
				<form:select path="clientId" class="input-xlarge required" multiple="false" placeholder="请选择车牌号" >
					<form:options items="${dicClientList}" itemLabel="name"  itemValue="id" htmlEscape="false"  />
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费时间：</label>
			<div class="controls">
				<input name="consumeDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${consumeDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费信息：</label>
			<div class="controls">
				<form:textarea path="consumeMsg" htmlEscape="false" style="width: 400px;height: 200px;"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">消费金额：</label>
			<div class="controls">
				<form:input type="number" path="consumeMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="dic:dicConsumeMsg:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>