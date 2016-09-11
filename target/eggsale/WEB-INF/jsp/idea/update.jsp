<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Edit an idea">
    <s:layout-component name="body" >
	<a href="javascript:history.back()" class="backlink">Back</a>
	<s:form beanclass="${beanPackage}.idea.UpdateActionBean" focus="">
	    
	    <s:errors />
	    <s:hidden name="ideaId" value="${actionBean.ideaId}" />

	    <label for="titleForm">Title: </label>
	    <p><s:text name="title" id="titleForm" /></p>

	    <label for="contentForm">Content: </label>
	    <p><s:textarea name="content" id="contentForm" cols="80" rows="12"/></p>
            
            <c:if test="${not empty actionBean.attachmentsSaved}">
                <label for="attachmentsSaved">Saved Attachments: </label>
                <c:forEach items="${actionBean.attachmentsSaved}" var="attachment" varStatus="loop">
                    <span id="savedAttachment${loop.index}">
                    <s:link beanclass="${beanPackage}.attachment.DownloadActionBean">
                        <c:out value="${attachment.fileName}" />
                        <s:param name="attachmentId" value="${attachment.id}" />
                    </s:link>
                    <s:checkbox style="display:none" onclick="hide(${loop.index})" checked="${attachment.id}" name="attachmentsToSave[${loop.index}]" value="${attachment.id}" />
                    <img alt="Remove" title="Remove" src="<c:url value="/css/icons/trash.png" />" onclick="hide(${loop.index})" />
                    <br>
                    </span>
                </c:forEach>
            </c:if>

	    <s:layout-render name="/WEB-INF/jsp/attachment/form.jsp" title="Attachments" attachment="newAttachments" ></s:layout-render>

	    <s:link beanclass="${beanPackage}.idea.ShowActionBean" >
		<s:button name="cancel" value="Cancel" />
		<s:param name="ideaId" value="${actionBean.ideaId}" />
	    </s:link>
	    
	    <p style="text-align: center;">
		<s:submit name="save" value="Update idea!" />        
	    </p>
	</s:form>
                    
        <script language="javascript">
            function hide(index) {
                document.getElementsByName('attachmentsToSave['+index+']').item(0).checked='';
                document.getElementById('savedAttachment'+index).style.display='none';
            }
        </script>
    </s:layout-component>
</s:layout-render>

