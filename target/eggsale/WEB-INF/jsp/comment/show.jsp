<div class="border comment_small">
    <div id="header">
	<div style="float:right;">
	    <c:out value="(${fn:length(comment.votedUp)})"/>
            <s:link beanclass="${beanPackage}.comment.VoteActionBean" event="voteUp">
                <img alt="Vote Up" title="Vote Up" src="<c:url value="/css/icons/vote_up.png" />" />
                <s:param name="commentId" value="${comment.id}"/>
            </s:link>
	    
	    <c:out value="(${fn:length(comment.votedDown)})"/>
            <s:link beanclass="${beanPackage}.comment.VoteActionBean" event="voteDown">
                <span class="voteicon"> <img alt="Vote Down" title="Vote Down" src="<c:url value="/css/icons/vote_down.png" />" /></span>
                <s:param name="commentId" value="${comment.id}"/>
            </s:link>
	    
	    <c:if test="${(actionBean.currentUser eq comment.owner) or (actionBean.currentUser eq actionBean.idea.owner) or (actionBean.currentUser eq actionBean.idea.project.owner) or (actionBean.currentUser.class == 'class fr.ensimag.ack.eggsale.db.entity.Administrator')}">
                <s:link beanclass="${beanPackage}.comment.UpdateActionBean">
                    <img alt="Edit" title="Edit" src="<c:url value="/css/icons/edit.png" />" />
                    <s:param name="commentId" value="${comment.id}" />
                </s:link>
                <s:link beanclass="${beanPackage}.comment.RemoveActionBean">
                    <img alt="Remove" title="Remove" src="<c:url value="/css/icons/trash.png" />" />
                    <s:param name="commentId" value="${comment.id}" />
                </s:link>
	    </c:if>
	</div>
	
	<h3>
            <s:link beanclass="${beanPackage}.user.ShowActionBean">
                <c:out value="${comment.owner.userName}" />
		<s:param name="userId" value="${comment.owner.id}"/>
	    </s:link>
	</h3>
	
	<h4>
	    on <fmt:formatDate type="both" value="${comment.createdAt}" />
	</h4>
    </div>
            
    <p><c:out value="${comment.content}" /></p>
        <c:if test="${not empty comment.updatedAt}"><br><sub>Last time edited at <fmt:formatDate type="both" value="${comment.updatedAt}" /></sub></c:if>
    </p>
</div>