<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
           uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp" title="Submit a new project">
    <s:layout-component name="body">
        <s:form beanclass="${beanPackage}.project.CreateActionBean" focus="">
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
	    
	    <s:layout-render name="/WEB-INF/jsp/attachment/form.jsp" title="Pictures" attachment="newAttachments" ></s:layout-render>
	    
	    <p style="text-align: center;">
		<s:submit name="submit" value="Add project!" onclick="check();" class="btn btn-primary" />
	    </p>
        </s:form>
    </s:layout-component>
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
    </script>
</s:layout-render>

