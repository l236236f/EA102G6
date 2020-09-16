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
                    <div id='login-panel' class="login-panel"><i class="fa fa-user"></i>登入</div>
                    <div>
                    <ul>
                    	<li><a href="<%=request.getContextPath()%>/front-end/mem/loginMem.jsp">會員登入</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/vendor/loginVendor.jsp">廠商登入</a></li>
                        <li><a href="<%=request.getContextPath()%>/back-end/emp/loginEmp.jsp">員工登入</a></li>
                    </ul> 
                    </div>  
                </div>
                <div class="ht-right">
                    <div id='register-panel' class="login-panel"><i class="fa fa-user"></i>註冊</div>
                    <div>
                    <ul>
                    	<li><a href="<%=request.getContextPath()%>/front-end/mem/registerMem.jsp">會員註冊</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/vendor/registerVendor.jsp">廠商註冊</a></li>  
                    </ul>  
                    </div>
                </div>
	
			<%}else{ %>
                <div class="ht-right">
                    <div class="login-panel">
                    	<form method="post" action='<%=request.getContextPath()%>/front-end/mem/mem.do'>
                   			<input type='hidden' name='action' value='logout'>
                   			<input type='submit' value='登出'>
                    	</form>
                    </div>  
                </div>
                <div class="ht-right">
                    <div class="login-panel">
                    	<a href="<%=request.getContextPath()%>/front-end/mem/homeMem.jsp"><i class="fa fa-user"></i><%=memVO.getMemName()%>你好 : )</a> 
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
                        			<!-- 全站搜尋區 -->
                                <div class="select-option">
                                    <select class="sorting" name="allSearch"> <!--去框--> 
                                        <option value="shop">商城</option>
                                        <option value="selfshop">二手專區</option>
                                        <option value="article">討論區</option>
                                        <option value="gathering">揪團</option>
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
                                    <!-- 全站搜尋區 -->
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
                    	<li><a href="">最新消息</a>
                            <ul class="dropdown">
                                <li><a href="#">公告</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/Q&A.jsp">Q&A</a></li>
                            </ul>
                        </li> 
                        <li><a href="<%= request.getContextPath()%>/front-end/foscare/foscareHome.jsp">寄養</a>
                        	<ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/selfProject/irikuji.jsp" id="agame">禁止進入</a></li>
                            </ul>
                        
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shop.jsp">二手市集</a>
                            <ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopA.jsp">毛孩伙食</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopB.jsp">日常用品</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/uprod/used_shopC.jsp">清潔用品</a></li>
                            </ul>
                        </li>   
                        <li><a href="<%= request.getContextPath()%>/front-end/shop/shopHome.jsp">商城</a>
                            <ul class="dropdown">
                                <li><a href="#">罐罐飼料區</a></li>
                                <li><a href="#">玩具用品區</a></li>
                                <li><a href="#">優惠專區</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/shop/memListAllSprod_order.jsp">交易紀錄</a></li>
                            </ul>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_index.jsp">揪團</a>
                            <ul class="dropdown">
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_add.jsp">建立揪團</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_listAll.jsp">瀏覽揪團</a></li>
                                <li><a href="<%= request.getContextPath()%>/front-end/gat/gat_updateList.jsp">管理揪團</a></li>
                            </ul>
                        </li>
                        <li><a href="<%= request.getContextPath()%>/front-end/article/article.jsp">討論區</a>
                        </li>
                        
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
    
   
    
