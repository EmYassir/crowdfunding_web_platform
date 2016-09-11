<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Update a project">
    <s:layout-component name="body">
	<a href="javascript:history.back()" class="backlink">Back</a>
        <s:form beanclass="${beanPackage}.project.UpdateActionBean" focus="">
            <div><s:hidden name="id"/></div>
            <s:errors />
	    <label for="nameForm">Name</label>
	    <p><s:text name="name" id="nameForm" /></p>
            	
	    <label for="projectDescriptionForm">Description</label>
	    <p><s:textarea name="description" cols="80" rows="12" id="projectDescriptionForm" /></p>

            <label for="appendedPrependedInputForm">Price</label>
	    <div class="input-prepend input-append">
		<span class="add-on">$</span>
		<s:text name="price" class="span2" id="appendedPrependedInputForm" />
		<span class="add-on">.00</span>
	    </div>
               
            <label for="isSalableForm">Is this product ready to ship?</label>
	    <p><s:checkbox name="isSalable" id="isSalableForm" /></p>
	    
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
		     <br />
		     </span>
		 </c:forEach>
	     </c:if>

	    <s:layout-render name="/WEB-INF/jsp/attachment/form.jsp" title="Attachments" attachment="newAttachments" ></s:layout-render>
               
	    <p style="text-align: center;">
               <s:submit name="update" value="Update project!" onclick="check();" class="btn btn-primary" />
	    </p>
	</s:form>
    <script type="text/javascript">
        function check()
        {
            var price = document.getElementById('price').value;
            var filter = /^[0-9]{5}$/;
            if(!filter.test(price))
            {
                alert('Le prix doit etre un nombre decimal');
            }
        }
        function hide(index) {
            document.getElementsByName('attachmentsToSave['+index+']').item(0).checked='';
            document.getElementById('savedAttachment'+index).style.display='none';
        }
    </script>
    </s:layout-component>
</s:layout-render>
