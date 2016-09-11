<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="">
    <s:layout-component name="body">

	<div class="bordershadow">
	    <div id="header">
		<div class="floatright">
		<c:if test="${actionBean.project.owner == actionBean.currentUser}">
		    <s:link beanclass="${beanPackage}.project.UpdateActionBean" class="btn">
			<s:param name="id" value="${actionBean.project.id}"/>
			Update this project
		    </s:link>
		</c:if>
		</div>
		<h3><c:out value="${actionBean.project.name}"/></h3>
		<h4><s:link beanclass="${beanPackage}.user.ShowActionBean">${actionBean.project.owner.userName}
		    <s:param name="userId" value="${actionBean.project.owner.id}"/>
		</s:link>
		on <fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${actionBean.project.createdAt}" /></h4>
	    </div>

		<c:choose>
		    <c:when test="${not empty actionBean.project.firstImage}">
	    		<div class="image">        
					<img src="data:image/jpeg;base64,${actionBean.project.firstImage}"/>
            	</div>
		    </c:when>
		</c:choose>
	    
	    <p><c:out value="${actionBean.project.description}" /></p>
	    
        <c:if test="${actionBean.project.isSalable}">    
	    <h3>$<c:out value="${actionBean.project.price}" />	    
	    <c:if test="${not empty actionBean.currentUser && actionBean.project.owner != actionBean.currentUser}">
		<s:form beanclass="${beanPackage}.project.ShowActionBean" focus="" style="display: inline;">
		    <s:hidden name="id" value="${actionBean.project.id}"/>
		    <s:errors />
		    <s:submit name="addToCart" value="Add to cart" class="btn btn-primary"/>
		</s:form>
	    </c:if>
	    </h3>
	</c:if>
	</div>

        <h2>Rewarded ideas</h2>
        <c:forEach var="idea" items="${actionBean.project.ideas}" >
            <c:if test="${not empty idea.reward}">
		<%@include file="/WEB-INF/jsp/idea/show_small.jsp" %>
            </c:if>
        </c:forEach>
            
	<h2>Ideas</h2>	
	<c:choose>
            <c:when test="${not empty actionBean.currentUser}">
            	<p>
                <s:link beanclass="${beanPackage}.idea.CreateActionBean" class="btn">
                	Submit your idea!
                    <s:param name="projectId" value="${actionBean.project.id}" />
                </s:link>
                </p>                
            </c:when>
            <c:otherwise>
            	<p>
                Please log in to create an idea
               	</p>                
            </c:otherwise>
        </c:choose>
        
	<c:forEach var="idea" items="${actionBean.project.ideas}">
            <c:if test="${empty idea.reward}">
                <%@include file="/WEB-INF/jsp/idea/show_small.jsp" %>
            </c:if>
	</c:forEach>

    </s:layout-component>
</s:layout-render>
