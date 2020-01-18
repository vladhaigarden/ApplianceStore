<%@ attribute name="pathPage" %>
<%@ attribute name="name" %>
<%@ attribute name="numberProducts" %>
<%@ attribute name="numberItems" %>
<%@ attribute name="numberPage" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

            <div class="sorting mr-auto">
              <c:set var="strMaxPage">${numberProducts/numberItems}</c:set>
              <fmt:parseNumber var="maxPage" type="number" value="${strMaxPage+(1-(strMaxPage%1))%1}" />

              <c:if test="${(filterBean.numberPage-1) > 1}">
                <a href="${pathPage}&numberPage=1">1</a>
                ...
              </c:if>
              <c:if test="${filterBean.numberPage > 1}">
                <a href="${pathPage}&numberPage=${filterBean.numberPage - 1}">${filterBean.numberPage - 1}</a>
              </c:if>
              <a href="${pathPage}&numberPage=${filterBean.numberPage}"><u>${filterBean.numberPage}</u></a>

              <c:if test="${filterBean.numberPage <maxPage}">
                <a href="${pathPage}&numberPage=${filterBean.numberPage + 1}">${filterBean.numberPage + 1}</a>
              </c:if>
              <c:if test="${(maxPage-filterBean.numberPage) > 1}">
                ...
                <a href="${pathPage}&numberPage=${maxPage}">${maxPage}</a>
              </c:if>
            </div>