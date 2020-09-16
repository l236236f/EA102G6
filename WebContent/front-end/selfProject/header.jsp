<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>



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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

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
</style>

<header class="header-section">
        <div class="header-top">
            <div class="container">
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
                                        <option value="shop">賭神</option>
                                    </select>
                                </div>
                            <div class="input-group" class="flright">
                                <!-- <input type="text" placeholder="What do you need?"> -->
                                <form action="" method="post">
                                    
                                    <input type="text" name="Searchone" id="cookies">
                                    <button  class="headerSearch"><i class="ti-search" disabled></i></button>
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
                        <li><a href="">德撲</a></li>
                        <li><a href="">橋牌</a></li>   
                        <li><a href="">橋牌</a></li>
                        <li><a href="">橋牌</a></li>
                        <li><a href="">橋牌</a></li>
                        
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
    
   
    
