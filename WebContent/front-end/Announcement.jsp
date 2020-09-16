<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="EMPLOYEE.model.*"%>
<%@ page import="ANNOUNCEMENT.model.*"%>



<%
	ANNService annSvc = new ANNService();
	List<ANNVO> list = annSvc.getAll();
	pageContext.setAttribute("list", list);
%>




<!DOCTYPE html>
<html>
<%@ include file="/front-end/Header/header.jsp"%>
<link href="<%= request.getContextPath() %>/front-end/css/front-ann.css" rel="stylesheet">
<head>
<title>�H�H�i�i</title>
</head>
<body>
	<div id="anncontainer">
		<div class='annBox'>
			<div class="annBoxheader">
			<i class="fas fa-paw"></i>�����̷s���i
			</div>
			<div class="anntablebox">
				<table id="anntable">

					<tr>
						<th ></th>
						<th ></th>

					</tr>
					<tbody>
<%  int rowsPerPage = 4;  //�C��������    
    int rowNumber=0;      //�`����
    int pageNumber=0;     //�`����      
    int whichPage=1;      //�ĴX��
    int pageIndexArray[]=null;
    int pageIndex=0; 
%>

<%  
    rowNumber=list.size();
    if (rowNumber%rowsPerPage !=0)
         pageNumber=rowNumber/rowsPerPage + 1;
    else pageNumber=rowNumber/rowsPerPage;    

    pageIndexArray=new int[pageNumber]; 
    for (int i=1 ; i<=pageIndexArray.length ; i++)
         pageIndexArray[i-1]=i*rowsPerPage-rowsPerPage;
%>

<%  try {
       whichPage = Integer.parseInt(request.getParameter("whichPage"));
       pageIndex=pageIndexArray[whichPage-1];
    } catch (NumberFormatException e) { //�Ĥ@�����檺�ɭ�
       whichPage=1;
       pageIndex=0;
    } catch (ArrayIndexOutOfBoundsException e) { //�`���Ƥ��~�����~����
         if (pageNumber>0){
              whichPage=pageNumber;
              pageIndex=pageIndexArray[pageNumber-1];
         }
    } 
%>

						<c:forEach var="ANNVO" items="${list}" begin="<%=pageIndex%>"
 							end="<%=pageIndex+rowsPerPage-1%>"> 
							<tr class='tr-ann'>
								<td class='td-1'><img
									src='<%= request.getContextPath() %>/back-end/emp/ANNImg?annno=${ANNVO.annno}'>
								</td>
								<td class='td-2'><span><fmt:formatDate
											value="${ANNVO.annchangedate}" pattern="yyyy-MM-dd" /></span>
									<h3>${ANNVO.anntitle}</h3>
									<div class="textdiv">${ANNVO.anntext}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			<div class='page'>
		

  <%if (rowsPerPage<rowNumber) {%>
    <%if(pageIndex>=rowsPerPage){%>
        <A href="<%=request.getRequestURI()%>?whichPage=1">�ܲĤ@��</A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">�W�@�� </A>&nbsp;
    <%}%>
  
    <%if(pageIndex<pageIndexArray[pageNumber-1]){%>
        <A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">�U�@�� </A>&nbsp;
        <A href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>">�̫ܳ�@��</A>&nbsp;
    <%}%>
  <%}%>  

<br><br>

<%if (pageNumber>1) {%>
    <FORM METHOD="post" ACTION="<%=request.getRequestURI()%>">   
       <select size="1" name="whichPage">
         <%for (int i=1; i<=pageNumber; i++){%>
            <option value="<%=i%>">���ܲ�<%=i%>��
         <%}%> 
       </select>
       <input type="submit" value="�T�w" >  
    </FORM>
  <%}%>
			
			
			
			</div> 
		</div>
		
	</div>
	
	<!-- Banner Section Begin -->
	<div class="banner-section spad">
	
		<div class="container-fluid"></div>
	</div>

	<%@ include file="/front-end/Footer/footer.jsp"%>
	<c:if test="${LoginMem!=null}">
		<%@ include file="/chatRoom/chatRoom.jsp"%>
	</c:if>
</body>

<script src="<%= request.getContextPath() %>/front-end/js/front-qa.js"></script>
<script src="https://kit.fontawesome.com/dd027c1f63.js"
	crossorigin="anonymous"></script>

</html>