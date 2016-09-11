<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Edit a comment">
    <s:layout-component name="body" >
		<s:form
                        beanclass="${beanPackage}.comment.UpdateActionBean"
			focus="">
			<s:errors />
                        <s:hidden name="commentId" value="${actionBean.commentId}" />
                        
                        <label for="content">Content:</label>
                        <s:textarea name="content" />
                        
                        <s:link beanclass="${beanPackage}.idea.ShowActionBean" ><s:param name="ideaId" value="${actionBean.ideaId}" /></s:link>
                        
						<s:submit name="save" value="Edit comment!" />
		</s:form>
        </s:layout-component>
</s:layout-render>