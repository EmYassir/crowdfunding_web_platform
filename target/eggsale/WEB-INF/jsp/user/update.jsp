<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Update user profile:">
    <s:layout-component name="body">
	<a href="javascript:history.back()" class="backlink">Back</a>
	<s:form beanclass="${actionBean.path}" focus="">
	    <s:errors />
	    <h3>Edit information</h3>
	    <label for="emailForm">Email*:</label>
	    <p><s:text name="email" id="emailForm" /></p>
	    
	    <label for="usernameForm">Pseudo:</label>
	    <p><s:text name="username" id="usernameForm" /></p>

	    <label for="descriptionForm">Description:</label>
	    <p><s:textarea name="description" id="descriptionForm" cols="80" rows="12"/></p>
	    <p style="text-align: center;">
		<s:submit name="updateData" value="Validate" class="btn" />
	    </p>
	    <p style="text-align: center;"><strong>You can select an avatar by creating an account on <a href="https://secure.gravatar.com/">Gravatar</a>.</strong></p>
	</s:form>
	<s:form beanclass="${actionBean.path}" focus="">
	    <h3>Change password</h3>
	    <label for="passwordForm">Your current password*:</label>
	    <p><s:password name="password" id="passwordForm" /></p>

	    <label for="password2Form">New password*:</label>
	    <p><s:password name="password2" id="password2Form" /></p>
	    <p style="text-align: center;">
		<s:submit name="updatePassword" value="Validate" class="btn" />
	    </p>
	</s:form>
	<s:form beanclass="${actionBean.path}" focus="">
	    <h3>Delete account</h3>
	    <label for="confirmDeletion">Delete this account:</label>
	    <p style="text-align: center;">
		<s:checkbox name="confirmDeletion" id="confirmDeletion" />
	    </p>
	    <p style="text-align: center;">
		<s:submit name="deleteAccount" value="Validate" class="btn" />
	    </p>
	</s:form>
    </s:layout-component>
</s:layout-render>
