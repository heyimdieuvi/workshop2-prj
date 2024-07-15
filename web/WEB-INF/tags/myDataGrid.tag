<%-- 
    Document   : myDataGrid
    Created on : 15-Jul-2024, 16:36:10
    Author     : ADMIN
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="url"%>
<%@attribute name="driver" %>
<%@attribute name="user"%>
<%@attribute name="password"%>
<%@attribute name="dataSource"%>
<%@attribute name="action1"%>
<%@attribute name="action2"%>
<%@attribute name="sql"%>
<%@tag dynamic-attributes="dynaParams" %>
<%-- any content can be specified here e.g.: --%>
<c:if test="${not empty url and not empty driver}">
    <sql:setDataSource url="${url}" driver="${driver}" user="${user}"
                       password="${password}" var="con"/>
</c:if>

<c:if test="${not empty dataSource}">
    <sql:setDataSource dataSource="${dataSource}" var="con"/>
</c:if>

<c:if test="${not empty con}">
    <sql:query dataSource="${con}" var="rs">
        ${sql}
        <c:if test="${not empty dynaParams}">
            <c:forEach var="dynaParam" items="${dynaParams}">
                <sql:param value="${dynaParam.value}"/>
            </c:forEach>
        </c:if>
    </sql:query>
    <c:if test="${not empty rs}">
            <tbody>
                <c:set var="count" value="0"/>
                <c:forEach var="rows" items="${rs.rowsByIndex}">
                    <c:set var="count" value="${count + 1}"/>
                    <tr>
                        <td>${count}</td>
                        <c:forEach var="colValue" items="${rows}" varStatus="status">
                            <c:choose>
                                <c:when test="${rs.columnNames[status.index] == 'productId'}">
                                    <c:set var="id" value="${colValue}"/>
                                    <td>${colValue}</td>
                                </c:when>
                                <c:when test="${rs.columnNames[status.index] == 'gender'}">
                                    <c:choose>
                                        <c:when test="${colValue == true}">
                                            <td>Male</td>
                                        </c:when>
                                        <c:when test="${colValue == false}">
                                            <td>Female</td>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:when test="${rs.columnNames[status.index] == 'isUse'}">
                                    <c:choose>
                                        <c:when test="${colValue == true}">
                                            <td>Active</td>
                                        </c:when>
                                        <c:when test="${colValue == false}">
                                            <td>Inactive</td>
                                        </c:when>
                                    </c:choose>
                                </c:when>
                                <c:when test="${rs.columnNames[status.index] == 'productImage'}">
                                    <td><img src="${colValue}" alt="img"/></td>
                                    </c:when>
                                    <c:when test="${rs.columnNames[status.index] == 'brief'}">
                                   <td><p class="brief"><c:out value="${colValue}"/></p></td>
                                    </c:when>
                                    <c:when test="${rs.columnNames[status.index] == 'birthday' or rs.columnNames[status.index] == 'postedDate'}">
                                        <fmt:formatDate value="${colValue}" pattern="dd/MM/yyyy" var="formattedDate"/>
                                    <td>${formattedDate}</td>
                                </c:when>
                                <c:when test="${rs.columnNames[status.index] == 'roleInSystem'}">
                                    <c:choose>
                                        <c:when test="${colValue == 1}">
                                            <td>Admin</td>
                                        </c:when>
                                        <c:when test="${colValue == 2}">
                                            <td>Manager</td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>Customer</td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>   
                                <c:otherwise>
                                    <td>${colValue}</td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        <td>
                            <a class="btn btn-info" href="${action1}<c:out value='${id}' />">Edit</a>
                            <a class="btn btn-danger" href="${action2}<c:out value='${id}' />">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
    </c:if>
</c:if>

