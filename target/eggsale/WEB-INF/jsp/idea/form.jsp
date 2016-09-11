<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Submit a new idea">
    <s:layout-component name="body" >
	    <s:form beanclass="${beanPackage}.idea.CreateActionBean" focus="">
		<s:errors />
		<s:hidden name="projectId" value="${actionBean.projectId}" />
		<label for="titleForm">Title</label>
		<p><s:text name="title" id="titleForn" /></p>
		<label for="contentForm">Content</label>
		<p><s:textarea name="content" id="contentForm" cols="80" rows="12"/></p>
		<s:layout-render name="/WEB-INF/jsp/attachment/form.jsp" title="Attachments" attachment="newAttachments" ></s:layout-render>
		<p style="text-align: center;">
		    <s:submit name="save" value="Submit idea!" class="btn btn-primary"/>
		</p>
	    </s:form>
	</s:layout-component>
</s:layout-render>

