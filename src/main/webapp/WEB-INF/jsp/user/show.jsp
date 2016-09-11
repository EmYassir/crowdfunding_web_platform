<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="User profile">
    <s:layout-component name="body">
	
	<div class="bordershadow">
	    <div id="header">
		<c:if test="${actionBean.currentUser.equals(actionBean.user)}">
		<div class="floatright">
		    <s:link beanclass="${beanPackage}.user.UpdateActionBean" class="btn">Edit profile</s:link>
		    <s:link beanclass="${beanPackage}.project.CreateActionBean" class="btn">Submit a new project</s:link>
		</div>
		</c:if>
		<h2><c:out value="${actionBean.user.userName}" /></h2>
	    </div>
	
	    <div class="floatleft">
		<img src="http://www.gravatar.com/avatar/${actionBean.user.hashedEmail}"
		 alt="Gravatar profile image" />
	    </div>
		 
	    <div class="floatleft">
		<ul style="list-style: none;">
		    <li><strong>Username</strong> : <c:out value="${actionBean.user.userName}" /></li>
		    <li><strong>Email</strong> : <c:out value="${actionBean.user.email}" /></li>
		    <li><strong>Registration date</strong> :
			<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${actionBean.user.createdAt}" /></li>
		    <li><strong>Project(s)</strong> : <c:out value="${actionBean.user.projects.size()}" /></li>
		    <li><strong>Idea(s)</strong> : <c:out value="${actionBean.user.ideas.size()}" /></li>
		    <li><strong>Comment(s)</strong> : <c:out value="${actionBean.user.comments.size()}" /></li>
		</ul>
	    </div>
	    
	    <h3 style="clear: both;">Description :</h3>
	    <p>
	    <c:choose>
		<c:when test="${empty actionBean.user.description}">
		    This user has no description for the moment.
		</c:when>
		<c:otherwise>
		    <c:out value="${actionBean.user.description}" />
		</c:otherwise>
	    </c:choose>	    
	    </p>
	</div>
	
	<h3>Let's discover his amazing projects :</h3>
	<c:choose>
	    <c:when test="${actionBean.user.projects.size() == 0}">
		<p>This user has no project for the moment.</p>
	    </c:when>
	    <c:otherwise>
		<c:forEach var="project" items="${actionBean.user.projects}" varStatus="loop">
		    <%@include file="/WEB-INF/jsp/project/show_small.jsp" %>
		</c:forEach>
	    </c:otherwise>
	</c:choose>
    </s:layout-component>
</s:layout-render>
