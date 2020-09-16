<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.uprod.model.*" %>
<%@ page import="java.util.*"%>




<head>
<title>二手拍賣首頁</title>
<%@ include file="/front-end/Header/header.jsp" %>
<%
    UprodService uprodSvc = new UprodService();
    List<UprodVO> list = uprodSvc.getAll();
    pageContext.setAttribute("list",list);
%>
    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>                      
    <!-- ##### 商品主畫面開始 ##### -->
    <section class="shop_grid_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3">
                    <div class="shop_sidebar_area">

                        <!-- ##### Single Widget ##### -->
                        <div class="widget catagory mb-50">
                            <!-- 左側選單最上面的標題 -->
<!--                             <h6 class="widget-title mb-30">左側選單最上面的標題</h6> -->

                            <!--  左側選單小區域 -->
                            <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <h8><a href="#">毛孩伙食</a></h8>
                                        
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#shoes" class="collapsed">
                                         <h8><a href="#">日常用品</a></h8>
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#accessories" class="collapsed">
                                         <h8><a href="#">清潔用品</a></h8>
                                  
                                    
                                      
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <br>
                        <br>
                        <br>
                         <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <h8><a href="listAllEmpBySeller.jsp">賣家商品管理</a></h8>
                                      
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#shoes" class="collapsed">
                                         <h8><a href="addEmp.jsp">賣家上架商品</a></h8>
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#accessories" class="collapsed">
                                         <h8><a href="listAllEmpBySellerDetail.jsp">賣家訂單管理</a></h8>
                                  
                                    
                                      
                                    </li>
                                </ul>
                            </div>
                            
                            <br>
                        <br>
                        <br>
                         <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <h8><a href="listAllEmpByCustomer.jsp">買家訂單管理</a></h8>  
                                    </li>
                                </ul>
                            </div>
                        <!-- ##### Single Widget ##### -->
<!--                         <div class="widget price mb-50"> -->
<!--                             Widget Title -->
<!--                             <h6 class="widget-title mb-30">左側選單中間標題</h6> -->
<!--                             Widget Title 2 -->
<!--                             <p class="widget-title2 mb-30">調整價錢</p> -->

<!--                             <div class="widget-desc"> -->
<!--                                 <div class="slider-range"> -->
<!--                                     <div data-min="49" data-max="360" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="49" data-value-max="360" data-label-result="Range:"> -->
<!--                                         <div class="ui-slider-range ui-widget-header ui-corner-all"></div> -->
<!--                                         <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span> -->
<!--                                         <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span> -->
<!--                                     </div> -->
<!--                                     <div class="range-price">Range: $49.00 - $360.00  要更改價錢拉霸的的最打最小值請更改上面div裡的"data-min="49" data-max="360" data-value-min="49" data-value-max="360""</div> -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </div>                  -->
                    </div>
                </div>

                <div class="col-12 col-md-8 col-lg-9">
                    <div class="shop_grid_product_area">
                        <div class="row">
                            <div class="col-12">
                                <div class="product-topbar d-flex align-items-center justify-content-between">
                                    <!-- Total Products -->
                                    <div class="total-products">
                                        <p><span><%@ include file="page1.file" %></span></p>  
                                    </div>                                    
                                </div>
                            </div>
                        </div>
<!-- 						商品區開始 -->

                        <div class="row">
<!-- 動態取商品所需的資料出來吧 -->

<c:forEach var="uprodVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                            <!-- 一項商品的資料開始 -->
                            <div class="col-12 col-sm-6 col-lg-4"> 
                                <div class="single-product-wrapper">                                   
                                    <!-- Product Image -->
                                    <div class="product-img">
<!--                                     商品的圖片 -->
                                        <img class="pics" src="<%=request.getContextPath()%>/front-end/uprod/showpic.do?ProdNo=${uprodVO.getProdNo()}" />                                                                       
                                        <!-- 追蹤按鈕 -->
                                        <div class="product-favourite">
                                            <a href="#" class="favme fa fa-heart"></a>
                                        </div>
                                    </div>

                                    <!-- Product Description -->
                                    <div class="product-description">                                        
                                         
                                            <h6>${uprodVO.prodName}</h6>
                                            <p class="product-price">價格:${uprodVO.price}</p>
                                            
                                            <!-- 商品詳情 -->
                                            <table>
                                            <tr>
                                            <td>
                                            <FORM METHOD="post" ACTION="uprod.do" >
                                            <input type="hidden" name="ProdNo" value="${uprodVO.prodNo}">
                                            <input type="hidden" name="action" value="getOne_For_Display">
                                            <input type="submit" value="商品詳情">
                                            </FORM>
                                            </td>
                                            <!-- 購買按鈕 -->
                                            <td>
                                              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/uprod/uprod.do">
											     <input type="hidden" name="ProdNo"  value="${uprodVO.getProdNo()}">							     
											     <input type="hidden" name="update1"  value="1">
											     <input type="hidden" name="action"	value="getOne_For_Update">
											     <input type="submit" value="購買商品">
											  </FORM>
                                            </td>
                                            </tr> 
                                            </table>
                                        
                                    </div>
                                </div>
                            </div>
                            </c:forEach>
                     
<!-- 						一項商品的資料結束 -->                      
                        </div>
                    </div>

<!--                     商品區結束 -->
                    <!-- 最下面的頁數按鈕開始 -->
                    <nav aria-label="navigation">
                   
                    <%@ include file="page3.file" %>    
                    </nav>
<!--                     最下面的頁數按鈕結束 -->
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Shop Grid Area End ##### -->

<!--     ##### Footer Area Start ##### -->
<!--     <footer class="footer_area clearfix"> -->
<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 Single Widget Area -->
<!--                 <div class="col-12 col-md-6"> -->
<!--                     <div class="single_widget_area d-flex mb-30"> -->
<!--                         Logo -->
<!--                         <div class="footer-logo mr-50"> -->
<!--                             <a href="#"><img src="img/core-img/logo2.png" alt=""></a> -->
<!--                         </div> -->
<!--                         Footer Menu -->
<!--                         <div class="footer_menu"> -->
<!--                             <ul> -->
<!--                                 <li><a href="shop.html">Shop</a></li> -->
<!--                                 <li><a href="blog.html">Blog</a></li> -->
<!--                                 <li><a href="contact.html">Contact</a></li> -->
<!--                             </ul> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 Single Widget Area -->
<!--                 <div class="col-12 col-md-6"> -->
<!--                     <div class="single_widget_area mb-30"> -->
<!--                         <ul class="footer_widget_menu"> -->
<!--                             <li><a href="#">Order Status</a></li> -->
<!--                             <li><a href="#">Payment Options</a></li> -->
<!--                             <li><a href="#">Shipping and Delivery</a></li> -->
<!--                             <li><a href="#">Guides</a></li> -->
<!--                             <li><a href="#">Privacy Policy</a></li> -->
<!--                             <li><a href="#">Terms of Use</a></li> -->
<!--                         </ul> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="row align-items-end"> -->
<!--                 Single Widget Area -->
<!--                 <div class="col-12 col-md-6"> -->
<!--                     <div class="single_widget_area"> -->
<!--                         <div class="footer_heading mb-30"> -->
<!--                             <h6>Subscribe</h6> -->
<!--                         </div> -->
<!--                         <div class="subscribtion_form"> -->
<!--                             <form action="#" method="post"> -->
<!--                                 <input type="email" name="mail" class="mail" placeholder="Your email here"> -->
<!--                                 <button type="submit" class="submit"><i class="fa fa-long-arrow-right" aria-hidden="true"></i></button> -->
<!--                             </form> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 Single Widget Area -->
<!--                 <div class="col-12 col-md-6"> -->
<!--                     <div class="single_widget_area"> -->
<!--                         <div class="footer_social_area"> -->
<!--                             <a href="#" data-toggle="tooltip" data-placement="top" title="Facebook"><i class="fa fa-facebook" aria-hidden="true"></i></a> -->
<!--                             <a href="#" data-toggle="tooltip" data-placement="top" title="Instagram"><i class="fa fa-instagram" aria-hidden="true"></i></a> -->
<!--                             <a href="#" data-toggle="tooltip" data-placement="top" title="Twitter"><i class="fa fa-twitter" aria-hidden="true"></i></a> -->
<!--                             <a href="#" data-toggle="tooltip" data-placement="top" title="Pinterest"><i class="fa fa-pinterest" aria-hidden="true"></i></a> -->
<!--                             <a href="#" data-toggle="tooltip" data-placement="top" title="Youtube"><i class="fa fa-youtube-play" aria-hidden="true"></i></a> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->

<!-- <div class="row mt-5"> -->
<!--                 <div class="col-md-12 text-center"> -->
<!--                     <p> -->
<!--                         Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
<!--     Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a> -->
<!--     Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
<!--                     </p> -->
<!--                 </div> -->
<!--             </div> -->

<!--         </div> -->
<!--     </footer> -->
<!--     ##### Footer Area End ##### -->

<!--     jQuery (Necessary for All JavaScript Plugins) -->
<!--     <script src="js/jquery/jquery-2.2.4.min.js"></script> -->
<!--     Popper js -->
<!--     <script src="js/popper.min.js"></script> -->
<!--     Bootstrap js -->
<!--     <script src="js/bootstrap.min.js"></script> -->
<!--     Plugins js -->
<!--     <script src="js/plugins.js"></script> -->
<!--     Classy Nav js -->
<!--     <script src="js/classy-nav.min.js"></script> -->
<!--     Active js -->
<!--     <script src="js/active.js"></script> -->

</body>
<%@ include file="/front-end/Footer/footer.jsp" %>
</html>