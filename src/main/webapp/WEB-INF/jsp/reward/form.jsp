<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Reward an idea">
    <s:layout-component name="body" >
	<a href="javascript:history.back()" class="backlink">Back</a>
	<s:form beanclass="${beanPackage}.reward.CreateActionBean" focus="">
	    <s:errors />
	    <s:hidden name="ideaId" value="${actionBean.ideaId}" />

	    <label for="amount">Amount:</label>
	    <p><s:text name="amount" id="amount" /></p>

	    <p style="text-align: center;">
		<s:submit name="save" value="Reward user!" class="btn" />
	    </p>
	</s:form>
    </s:layout-component>
</s:layout-render>