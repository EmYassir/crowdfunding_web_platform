<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Login">
    <s:layout-component name="body">
	<s:form beanclass="${beanPackage}.user.LoginActionBean" focus="">
	    <s:errors />
	    <label for="emailForm">Email:</label>
	    <p><s:text name="email" id="emailForm" /></p>

	    <label for="passwordForm">Password:</label>
	    <p><s:password name="password" id="passwordForm" /></p>

	    <p style="text-align: center;">
		<s:submit class="btn btn-primary" name="login" value="Log in!" />
	    </p>
	</s:form>	
	
	<p style="margin-top: 40px; text-align: center;">
	    Tired of having to remember yet another email/password combination?<br /><br />
	    <button class="btn" onclick="navigator.id.request({siteName: 'Eggsale'})">Log in using Persona</button>
	</p>
		
	<s:form id="assertion_form" beanclass="${beanPackage}.user.PersonaLoginActionBean">
	    <s:hidden id="assertion_input" name="assertion" value="" />
	    <s:submit name="login" id="assertion_submit" value="Login" style="display:none;" />
	</s:form>
		
	<!-- Inclusion of the persona lib -->
	<script src="https://login.persona.org/include.js"></script>
	
	<script>
	    var currentUser = 
	    <c:choose>
		<c:when test="${empty sessionScope.user}">
		    null;
		</c:when>
		<c:otherwise>
		    "${sessionScope.user.email}";
		</c:otherwise>
	    </c:choose>
		
	    navigator.id.watch({
		loggedInUser: currentUser,
		onlogin: function(assertion) {
		    document.getElementById("assertion_input").setAttribute('value', assertion);		    
		    document.getElementById("assertion_submit").click();
		},
		onlogout: function() {
		    document.getElementById("assertion_input").setAttribute('value', 'deconnexion');
		    document.getElementById("assertion_form").submit();
		}
	    });</script>	
	</s:layout-component>
</s:layout-render>
