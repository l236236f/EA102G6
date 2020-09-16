<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.vendor.model.*"%>
<%@ page import="java.util.*"%>

<% MemVO memVO = (MemVO) session.getAttribute("LoginMem"); %>

<link href="https://fonts.googleapis.com/css?family=Muli:300,400,500,600,700,800,900&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css" type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/font-awesome.min.css"	type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/themify-icons.css" type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/elegant-icons.css" type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/owl.carousel.min.css"	type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/nice-select.css"	type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/slicknav.min.css"	type="text/css">
<link rel="stylesheet"	href="<%=request.getContextPath()%>/front-end/css/style.css" type="text/css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>



<style>
  		.xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  		}
  		.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  		}
  		.header-top .ht-right .login-panel {
  		   padding-right: 20px;
  		}
  		a:hover, a:focus {
			text-decoration: none;
			outline: none;
			color: blue;
		}
		
 		#login-panel+div, #register-panel+div { 
 			position: absolute; 
 			width:90px;
 			margin-top:50px;  
 			text-align:center;
 			border:#ccc;
 			background: #fff; 
 			z-index:10;
  			display:none; 
		} 
		#login-panel+div ul, #register-panel+div ul{
			list-style:none;
		}
		#login-panel:hover+div{ 
 			display:block; 
		} 
 		#login-panel+div:hover{  
 			display:block; 
 		} 
 		#register-panel:hover+div{ 
 			display:block; 
 		} 
 		#register-panel+div:hover{  
 			display:block; 
 		} 
 		
/* ---------------LHSBar-------------------- */
 		#LHSBar{ 
 			padding-left:30px;
 			padding-right:30px; 
 		} 
		
</style>

	<header class="header-section">
	<input type='hidden' id='LoginMem' value='${LoginMem.memNo}'>
        <div class="header-top">
            <div class="container">
                <div class="ht-left">
                    <div class="mail-service">
                    </div>
                    <div class="phone-service">
                        <!-- <i class=" fa fa-phone"></i> -->
                        
                    </div>
                </div>
                <%if (memVO == null) {%>
                <div class="ht-right">
                    <div id='login-panel' class="login-panel"><i class="fa fa-user"></i>�n�J</div>
                    <div>
                    <ul>
                    	<li><a href="<%=request.getContextPath()%>/front-end/mem/loginMem.jsp">�|���n�J</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/vendor/loginVendor.jsp">�t�ӵn�J</a></li>
                        <li><a href="<%=request.getContextPath()%>/back-end/emp/loginEmp.jsp">���u�n�J</a></li>
                    </ul> 
                    </div>  
                </div>
                <div class="ht-right">
                    <div id='register-panel' class="login-panel"><i class="fa fa-user"></i>���U</div>
                    <div>
                    <ul>
                    	<li><a href="<%=request.getContextPath()%>/front-end/mem/registerMem.jsp">�|�����U</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/vendor/registerVendor.jsp">�t�ӵ��U</a></li>  
                    </ul>  
                    </div>
                </div>
	
			<%}else{ %>
                <div class="ht-right">
                    <div class="login-panel">
                    	<form method="post" action='<%=request.getContextPath()%>/front-end/mem/mem.do'>
                   			<input type='hidden' name='action' value='logout'>
                   			<input type='submit' value='�n�X'>
                    	</form>
                    </div>  
                </div>
                <div class="ht-right">
                    <div class="login-panel">
                    	<a href="<%=request.getContextPath()%>/front-end/mem/homeMem.jsp"><i class="fa fa-user"></i><%=memVO.getMemName()%>�A�n : )</a> 
                    </div> 
                </div>
                <%} %>
            </div>
        </div>
        <div class="container">
            <div class="inner-header">
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="logo">
                            <a href="<%= request.getContextPath()%>/front-end/index.jsp">
                                <img src="<%= request.getContextPath()%>/front-end/img/logo.png" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-7 col-md-7">
                        <div class="advanced-search headerG6" >
                        			<!-- �����j�M�� -->
                                <div class="select-option">
                                    <select class="sorting" name="allSearch"> <!--�h��--> 
                                        <option value="shop">�ӫ�</option>
                                        <option value="selfshop">�G��M��</option>
                                        <option value="article">�Q�װ�</option>
                                        <option value="gathering">����</option>
                                    </select>
                                </div>
                            <div class="input-group" class="flright">
                                <!-- <input type="text" placeholder="What do you need?"> -->
                                <form action="" method="post">
                                    
                                    <input type="text" name="Searchone" id="cookies">
                                    <button type="" class="headerSearch"><i class="ti-search" disabled></i></button>
                                    <input type="hidden" name="action" value="allSerch">
                                    <input type="hidden" name="serchType" id="serchType" >
                                    <input type="hidden" name="serchContain" id="serchContain" >
                                    <!-- �����j�M�� -->
                                </form>
                                
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3 text-right col-md-3">
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="nav-item">
            <div class="container">
                <nav class="nav-menu mobile-menu">
                    <ul>
                    	<li><a href="">�̷s����</a>
                            <ul class="dropdown">
                                <li><a href="#">���i</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/Q&A.jsp">Q&A</a></li>
                            </ul>
                        </li> 
                        <li><a href="<%= request.getContextPath()%>/front-end/foscare/foscareHome.jsp">�H�i</a>
                        	<ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/selfProject/irikuji.jsp" id="agame">�T��i�J</a></li>
                            </ul>
                        
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shop.jsp">�G�⥫��</a>
                            <ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopA.jsp">��ĥ뭹</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopB.jsp">��`�Ϋ~</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopC.jsp">�M��Ϋ~</a></li>
                            </ul>
                        </li>   
                        <li><a href="<%= request.getContextPath()%>/front-end/shop/shopHome.jsp">�ӫ�</a>
                            <ul class="dropdown">
                                <li><a href="#">�����}�ư�</a></li>
                                <li><a href="#">����Ϋ~��</a></li>
                                <li><a href="#">�u�f�M��</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/shop/memListAllSprod_order.jsp">�������</a></li>
                            </ul>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_index.jsp">����</a>
                            <ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_add.jsp">�إߴ���</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_listAll.jsp">�s������</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_updateList.jsp">�޲z����</a></li>
                            </ul>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/article/article.jsp">�Q�װ�</a>
                        </li>
                        
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
    
   
    
