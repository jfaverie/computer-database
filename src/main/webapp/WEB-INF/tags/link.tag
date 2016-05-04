<%@ tag body-content="empty"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="cursor" required="true" type="Integer"%>
<%@ attribute name="offset" required="true" type="Integer"%>
<%@ attribute name="sortCol" required="true" type="Integer"%>
<%@ attribute name="sortType" required="true" type="Integer"%>
<%@ attribute name="search" required="true"%>
<%@ attribute name="idRef" required="false"%>
<%@ attribute name="msg" required="true"%>
<%@ attribute name="context" required="true"%>
<%@ attribute name="classRef" required="false"%>


<a class="${(not empty classRef) ? classRef : "
	" }" id="${(not empty idRef) ? idRef : "
	"}" href="${context}?page=${cursor}&nbel=${offset}&colId=${sortCol}&colType=${sortType}&search=${search}">
	${msg} </a>