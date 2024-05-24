<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<form name="messageForm" id="messageForm" method="post" action="${retUrl}">
	<input type="hidden" name="${hiddenName1 }" value="${hiddenValue1 }" />
	<input type="hidden" name="${hiddenName2 }" value="${hiddenValue2 }" />
	<input type="hidden" name="${hiddenName3 }" value="${hiddenValue3 }" />
	<input type="hidden" name="${hiddenName4 }" value="${hiddenValue4 }" />
	<input type="hidden" name="${hiddenName5 }" value="${hiddenValue5 }" />
	<input type="hidden" name="${hiddenName6 }" value="${hiddenValue6 }" />
	<input type="hidden" name="${hiddenName7 }" value="${hiddenValue7 }" />
	<input type="hidden" name="${hiddenName8 }" value="${hiddenValue8 }" />
	<input type="hidden" name="${hiddenName9 }" value="${hiddenValue9 }" />
	<input type="hidden" name="${hiddenName10 }" value="${hiddenValue10 }" />
	<input type="hidden" name="${hiddenName11 }" value="${hiddenValue11 }" />
	<input type="hidden" name="${hiddenName12 }" value="${hiddenValue12 }" />
</form>
<script type="text/javascript">
	
	<c:if test="${retType eq ':null_submit'}">
		document.messageForm.submit();
	</c:if>
	
	<c:if test="${retType eq ':submit'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		document.messageForm.submit();
	</c:if>
	
	<c:if test="${retType eq ':back'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		history.back();
	</c:if>
	
	<c:if test="${retType eq ':location'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		location.href="${retUrl}";
	</c:if>
	
	<c:if test="${retType eq ':close'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		window.close();
	</c:if>
	
	<c:if test="${retType eq ':reload_parent'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		opener.parent.location.reload();
	</c:if>
	
	<c:if test="${retType eq ':reload_parent_close'}">
		alert("${fn:replace(message, '<br>', '\\n')}");
		opener.parent.location.reload();
		window.close();
	</c:if>

</script>