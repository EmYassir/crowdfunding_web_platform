<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="stripes"
	uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<s:layout-definition>
    
    <c:set var="nbFiles" value="10" scope="page"/>
    
    <label for="${attachment}">${title}:</label>
    <c:forEach begin="0" end="${nbFiles - 1}" varStatus="loop">
        <s:file name="${attachment}[${loop.index}]"/>
    </c:forEach>
    
    <a name="addFile" onclick="add()">Upload another file</a>
    
    <script language="javascript">
        for (var i=1;i<${nbFiles};i++)
        {
            var name = "${attachment}["+i+"]";
            document.getElementsByName(name).item(0).hidden=true;
        }
        var num = 1;
        function add() {
            if(num > ${nbFiles}) {return;}
            var name = "${attachment}["+num+"]";
            document.getElementsByName(name).item(0).hidden=false;
            num=num+1;
            if(num==${nbFiles})
            {
                document.getElementsByName("addFile").item(0).hidden=true
            }
        }
    </script>
    
</s:layout-definition>
