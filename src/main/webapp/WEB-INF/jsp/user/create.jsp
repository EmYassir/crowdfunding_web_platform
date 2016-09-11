<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Become our newest user">
    <s:layout-component name="body">
	<s:form beanclass="${actionBean.path}" focus="" class="formcenter">
	    <s:errors />
	    <label for="emailForm">Email*</label>
	    <s:text name="email" id="emailForm" />
	    
	    <label for="passwordForm">Password*</label>
	    <s:password name="password" id="passwordForm" />

	    <label for="password2Form">Re-type Password*</label>
	    <s:password name="password2" id="password2Form" />

	    <label for="usernameForm">Name or nickname*</label>
	    <s:text name="username" id="usernameForm" />

	    <label for="descriptionForm">Tell us a little about yourself</label>
	    <s:textarea name="description" id="descriptionForm" cols="80" rows="12"/>
	    
	    <p style="text-align: center;">
		<s:submit name="save" value="Register!" class="btn-large btn-primary" />
	    </p>
	</s:form>
    </s:layout-component>
</s:layout-render>
