<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Submit a new comment">
    <s:layout-component name="body" >
	<a href="javascript:history.back()" class="backlink">Back</a>
	<s:form beanclass="${actionBean.path}" focus="">
	    <s:errors />
            <s:hidden name="commentId" value="${actionBean.commentId}" />

	    <label for="contentForm">Content:</label>
            <p><s:textarea name="content" id="contentForm" /></p>
	    <br />
	    <br />
             <p style="text-align: center;">
                 <s:submit name="save" value="Comment!" />
             </p>
	</s:form>
    </s:layout-component>
</s:layout-render>