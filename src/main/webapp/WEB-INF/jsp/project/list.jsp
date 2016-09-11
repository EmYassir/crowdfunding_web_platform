 <%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Some great projects are already underway!">
    <s:layout-component name="body">

    <c:if test="${not empty actionBean.currentUser}">
	<p>You have an awesome idea ? <s:link beanclass="${beanPackage}.project.CreateActionBean">Submit a new project !</s:link></p>
    </c:if>
    <div class="pagination">
        <c:if test="${actionBean.currentPage > 0}">
            <s:link beanclass="${beanPackage}.project.ListActionBean">
                <s:param name="currentPage" value="${actionBean.currentPage - 1}"/>
                prev page
            </s:link>
        </c:if>
        <c:if test="${actionBean.projects.size() == actionBean.projectsPerPage}">
            <s:link beanclass="${beanPackage}.project.ListActionBean">
                <s:param name="currentPage" value="${actionBean.currentPage + 1}"/>
                next page
            </s:link>
        </c:if>
    </div>
	
    <c:forEach var="project" items="${actionBean.projects}" varStatus="loop">
        <c:if test="${loop.index < actionBean.projectsPerPage}">
	    <%@include file="/WEB-INF/jsp/project/show_small.jsp" %>
        </c:if>
    </c:forEach>
    </s:layout-component>
</s:layout-render>
