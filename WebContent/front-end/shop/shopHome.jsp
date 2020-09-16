<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.shop_product.model.*" %>
<%@ page import="shopping.Sprod" %>



<head>
<title>商城頁面</title>
<%@ include file="/front-end/Header/header.jsp" %>
<% 
pageContext.setAttribute("none", "none");
%>

<%  
    Shop_productJNDIDAO dao = new Shop_productJNDIDAO();
    List<Shop_productVO> list = dao.selectShophomeProduct(0);//上架狀態
    pageContext.setAttribute("list",list);
    
    VendorDAO venDao = new VendorDAO(); //為取得廠商名稱
    List<VendorVO> venList = venDao.getAll();
    pageContext.setAttribute("venList",venList);
%>

<%
    Vector<Sprod> buylist = (Vector<Sprod>) session.getAttribute("shoppingcart");
%>

    <link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/shop/css/core-style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/front-end/shop/style.css">
	
	
</head><style>



</style>
<body style="background-image: url('<%= request.getContextPath() %>/front-end/shop/img/bg-img/bgPicture.jpg'); background-size: cover;">

 <!-- ##### Header Area Start ##### -->
 <%
	if (buylist != null && (buylist.size() >= 0)) {

        int prodcount = 0;//計算商品種類數，顯示於購物車圖示
        for (int index = 0; index < buylist.size(); index++) {
            Sprod order = buylist.get(index);
            prodcount += 1;
        }
%>
    <header class="header_area" style="bgcolor:black;">
        <div class="classy-nav-container breakpoint-off d-flex align-items-center justify-content-between" style="background-image: url('<%= request.getContextPath() %>/front-end/shop/img/bg-img/bgPicture.jpg'); background-size: cover;">
            <!-- Classy Menu -->
            <nav class="classy-navbar" id="essenceNav">
                <!-- Navbar Toggler -->
                <div class="classy-navbar-toggler">
                    <span class="navbarToggler"><span></span><span></span><span></span></span>
                </div>
            
            <!-- Header Meta Data -->
            <div class="header-meta d-flex clearfix justify-content-end" >
                <!-- Cart Area -->
                <div class="cart-area">
                    <a href="#" id="essenceCartBtn"><img src="<%= request.getContextPath() %>/front-end/shop/img/core-img/bag.svg" alt=""> <span><%=prodcount%></span></a>
                </div>
            </div>
        </div>
    </header> 
<!-- ##### Header Area End ##### -->

    <!-- ##### Right Side Cart Area ##### -->
    <div class="cart-bg-overlay"></div>

    <div class="right-side-cart-area">

        <!-- Cart Button -->

        <div class="cart-button">
            <a href="#" id="rightSideCart"><img src="<%= request.getContextPath() %>/front-end/shop/img/core-img/bag.svg" alt=""> <span><%=prodcount%></span></a>
        </div>

        <div class="cart-content d-flex">

            <!-- Cart List Area -->
            <div class="cart-list" style="background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%);">

    <%  
        for (int index = 0; index < buylist.size(); index++) {
         Sprod order = buylist.get(index);
    %>       
                <!-- Single Cart Item -->
                <form name="shoppingForm" action="Shopping.html" method="POST">
	                <div class="single-cart-item">
	                    <a href="#" class="product-image">
	                        <img src="<%=request.getContextPath()%>/GetPhotos?prodNo=<%=order.getProdNo()%>" class="cart-thumb" alt="">
	                        <!-- Cart Item Desc -->
	<!--                         刪除清單單項商品 -->
	                        <div class="cart-item-desc">
	                          <span class="product-remove"><i class="fa fa-close" aria-hidden="true">
	                                                            
	                          </i></span>
	         
	                            <span class="badge"><%=venDao.findByPrimaryKey(order.getVenNo()).getVenName()%></span>
	                            <h6><%=order.getName()%></h6>
	                            <p class="price">$<%=order.getPrice()*order.getQuantity()%></p>
	                        </div>
	                    </a>
	                </div>
		              <input type="hidden" name="action" value="DELETE">
		              <input type="hidden" name="del" value="<%= index %>">
        			  <div><input type="submit" value="刪除" style="background-color: lightgreen;"></div>
	             </form>
    <%
        }
    %>        
            </div>

            <!-- Cart Summary -->
            <div class="cart-amount-summary" style="background-image: linear-gradient(120deg, #d4fc79 0%, #96e6a1 100%);">
			
			    <h2>購物概況</h2>
			   	<ul class="summary-table">
			   	<li>			                    	
			        <span>商品名稱:</span> 
			        <br><br>
			        <span>數量</span> <span>小計</span>
			    </li>
			    <%
			        int sprodTotal = 0;  //總價
			        for (int index = 0; index < buylist.size(); index++) {
			            Sprod order = buylist.get(index);
			            sprodTotal += order.getPrice()*order.getQuantity();
			    %>               
			                    <li>			                    	
			                    	<span><%=order.getName()%>:</span> 
			                    	<br><br>
			                    	<span><%=(order.getQuantity()<=0)?0:order.getQuantity() %></span> 
			                    	<span>$<%=(order.getPrice()*order.getQuantity()<=0)?0:order.getPrice()*order.getQuantity()%></span>
			                    </li>
			    <%
			        }
			    %>
			    	<li><span>total:</span> <span><%=(sprodTotal<=0)?0:sprodTotal%></span></li>
			    </ul>
			    <br>
			    	<div class="checkout-btn mt-100">                       
				       <form name="checkoutForm" action="Shopping.html" method="POST">
				       		<input type="hidden" name="action"  value="CHECKOUT"> 
				       		<input type="submit" value="結帳" style="background-color: lightblue;">
				       </form>
			       	</div>
			       	<br>
       			<div>
       				<a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp"><input type="button" value="繼續購物" style="background-color: lightblue;"></a>  
                </div>
            </div>
         
<%
    }
%>
        </div>
    </div>

    <!-- ##### Right Side Cart End ##### -->

    <!-- ##### Shop Grid Area Start ##### -->
    <section class="shop_grid_area section-padding-80">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-4 col-lg-3">
                    <div class="shop_sidebar_area">

                        <!-- ##### Single Widget ##### -->
                        <div class="widget catagory mb-50">
                            <!-- Widget Title -->
                            <h6 class="widget-title mb-30">商城選單</h6>

                            <!--  Catagories  -->
                            <div class="catagories-menu">
                                <ul id="menu-content2" class="menu-content collapse show">
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#clothing">
                                        <a href="#">商品列表</a>
                                        <ul class="sub-menu collapse show" id="clothing">
                                            <li><a href="<%=request.getContextPath()%>/front-end/shop/shopHome.jsp">全部商品</a></li>
                                            <li><a href="#">罐罐飼料區</a></li>
                                            <li><a href="#">玩具用品區</a></li>
                                            <li><a href="#">優惠專區</a></li>
                                            
                                        </ul>
                                    </li>
                                    <!-- Single Item -->
                                    <li data-toggle="collapse" data-target="#shoes" class="collapsed">
                                        <a href="#">會員專區</a>
                                        <ul class="sub-menu collapse" id="shoes">
                                            <li><a href="<%=request.getContextPath()%>/front-end/shop/memListAllSprod_order.jsp">訂單資料</a></li>
                                            <li><a href="#">評價商品</a></li>
                                            <li><a href="#">檢舉商品</a></li>
                                            <li><a href="#">聯絡客服</a></li>                                    
                                        </ul>
                                    </li>                             
                                </ul>
                            </div>
                        </div>

                        <!-- ##### Single Widget ##### -->
                        <div class="widget price mb-50">
                            <!-- Widget Title -->
                            <h6 class="widget-title mb-30">來調整自己想要的價格區間吧!</h6>
                            <!-- Widget Title 2 -->
                            <p class="widget-title2 mb-30">請調整區間</p>

                            <div class="widget-desc">
                                <div class="slider-range">
                                    <div data-min="0" data-max="10000" data-unit="$" class="slider-range-price ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" data-value-min="49" data-value-max="360" data-label-result="Range:">
                                        <div class="ui-slider-range ui-widget-header ui-corner-all"></div>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                        <span class="ui-slider-handle ui-state-default ui-corner-all" tabindex="0"></span>
                                    </div>
                                    <div class="range-price">價格範圍: $0 - $10000</div>
                                </div>
                            </div>
                        </div>

                        <div class="widget brands mb-50">
<!--                         	訂單問題回報區 -->
                        	<%@ include file="memShopWebsocket.jsp" %>
                            <!-- Widget Title 2 -->
                            
                            <div class="widget-desc">
                            <p class="widget-title2 mb-30">廠商</p>
                                <ul>
                                    <li><a href="#">胃拳集團</a></li>
                                    <li><a href="#">蹦瓜娛樂</a></li>
                                    <li><a href="#">筒依集團</a></li>
                                    <li><a href="#">大衛集團</a></li>
                                    <li><a href="#">達美樂</a></li>
                                    <li><a href="#">八仙樂園</a></li>
                                    <li><a href="#">最強蝸牛</a></li>
                                    <li><a href="#">鮑伯旅店</a></li>
                                </ul>
                            </div>                     
                        </div>
                    </div>
                </div>

                <div class="col-12 col-md-8 col-lg-9">
                    <div class="shop_grid_product_area">
 <%@ 
 			include file="shopHomeChangePage1.file" 
%>
                        <div class="row">
                            <div class="col-12">
                                <div class="product-topbar d-flex align-items-center justify-content-between">
                                    <!-- Total Products -->
                                    <div class="total-products">
                                        <p>總共找到了<span><%=rowNumber%></span>個商品</p>
                                    </div>                                 
                                </div>
                            </div>
                        </div>

                        <div class="row">
                        
        
            <c:forEach var="shop_productVO" items="${list}" varStatus="shopindex" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                      
                            <!-- Single Product -->
                            <div class="col-12 col-sm-6 col-lg-4">
                                <div class="single-product-wrapper" style="width:220px;height: 420px; background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%); border-radius:15px;">
                                    <!-- Product Image -->
                 <form name="shoppingForm" action="Shopping.html" method="POST">
                                    <div class="product-img" style="width:220px;height: 220px;">
                                        <img src="<%=request.getContextPath()%>/GetPhotos?prodNo=${shop_productVO.prodNo}" alt="" style="width:100%; height:100%;">
<!--                                         Hover Thumb -->
                                        <img class="hover-img" src="<%=request.getContextPath()%>/GetPhotos?prodNo=${shop_productVO.prodNo}" alt="">

                                        <!-- Product Badge -->
                                       
                                        <!-- Favourite -->
                                        <div class="product-favourite">
                                            <a href="" class="favme fa fa-heart"></a>
                                        </div>
                                    </div>

                                    <!-- Product Description -->
                                    <div class="product-description" style="text-align:center;">                                      
                                        <div style="height:40px;"> 
                                            <h6>${shop_productVO.prodName}</h6>
                                        </div>
                                        <p class="product-price"><h6 style="align-items: center;">NT:${shop_productVO.price}元</h6></p>                                     
                                           	<div class="product-badge offer-badge" name='report' style ="opacity:0.8;">
	                                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#staticBackdrop_${shopindex.index}">
												  查看商品詳情
												</button>
                                       	 	</div>
<!--=============================================================選擇購買的數量================================================ -->
<br>
<table id="shoptabtabtab" style="margin:auto;" >
<tr>
<td>
<input class="shopminminmin" name="quantity" type="button" value="-" style="width:20px;border:5px ;background-image: linear-gradient(-225deg, #7DE2FC 0%, #B9B6E5 100%);" />
<input class="shopProductText_box" readonly name="quantity" type="text" size="10" value="1"  style="color: blue;background-color: yellow; text-align:center;"/> 
<input class="shopaddaddadd" name="quantity" type="button" value="+" style="width:20px;border:5px ;background-image: linear-gradient(-225deg, #7DE2FC 0%, #B9B6E5 100%);"/>
</td>
</tr>
</table>

<!-- ========================================================================================================================= -->

                                        <!-- Hover Content -->
                                        <div class="hover-content">
                                            <!-- Add to Cart -->
                                            <div class="add-to-cart-btn">

<!--                                             	<input type="text" name="quantity" >  -->
                                                <input type="submit" class="btn essence-btn" name="Submit" value="加入購物車" style="width:100px;">
                                            </div>
                                        </div>
                                    </div>
                            
                        <input type="hidden" name="name" value="${shop_productVO.prodName}">
                        <input type="hidden" name="prodNo" value="${shop_productVO.prodNo}">
                        <input type="hidden" name="venNo" value="${shop_productVO.venNo}">
                        <input type="hidden" name="price" value="${shop_productVO.price}">
                        <input type="hidden" name="action" value="ADD">
                  </form>
                                </div>
                            </div>
<!--                            商品詳情 -->
						<div class="modal fade" id="staticBackdrop_${shopindex.index}" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h5 class="modal-title" id="staticBackdropLabel">商品詳情</h5>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
						          <span aria-hidden="true">&times;</span>
						        </button>
						      </div>
						      <div class="modal-body">
						      	商品照片:
						      	<div style="width:300px; height:300px;"><img src="<%=request.getContextPath()%>/GetPhotos?prodNo=${shop_productVO.prodNo}" alt="" style="width:100%; height:100%;"></div>
						      	<div>商品介紹:</div>					
						        <div>${shop_productVO.prodIntro}</div>
						        
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
						        <button type="button" class="btn btn-primary">檢舉</button>
						      </div>
						    </div>
						  </div>
						</div>
            </c:forEach>
                            
                            
                                 
                            

                        </div>
                    </div>
                    
                                                <!-- 換頁 -->

                        <ul class="pagination mt-50 mb-70">
                        <%if (rowsPerPage<rowNumber) {%>
                            <%if(pageIndex>=rowsPerPage){%>
                                <li class="page-item" ><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>" style="background-color: lightblue;"><i class="fa fa-angle-left"></i></a>&nbsp;</li>
                            <%}%>
                            
                            <li class="page-item" ><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=1" style="background-color: lightblue;">1</a>&nbsp;</li>
                            <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=2" style="background-color: lightblue;">2</a>&nbsp;</li>
                            <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=3" style="background-color: lightblue;">3</a>&nbsp;</li>
                            <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=4" style="background-color: lightblue;">4</a>&nbsp;</li>
                            <%if(pageIndex<pageIndexArray[pageNumber-1]){%>
                    
                                <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>" style="background-color: lightblue;">最末頁</a>&nbsp;</li>
                                <li class="page-item"><a class="page-link" href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>" style="background-color: lightblue;"><i class="fa fa-angle-right"></i></a>&nbsp;</li>
                            <%}%>
                      <%}%>
                     
<!--                         </ul> -->
                </div>
            </div>
        </div>
        
    </section>
    <!-- ##### Shop Grid Area End ##### -->
	<%@ include file="/front-end/Footer/footer.jsp" %>
	<%@ include file="/chatRoom/chatRoom.jsp"%>
	</body>
	
    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Classy Nav js -->
    <script src="js/classy-nav.min.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
<!--     =================================================================================商品加減相關開始================================================ -->
 
    <script>
    $(function(){
    $(".shopaddaddadd").click(function(){
    var t=$(this).parent().find('input[class*=shopProductText_box]');
    t.val(parseInt(t.val())+1)
    setTotal();
    })
    $(".shopminminmin").click(function(){
    var t=$(this).parent().find('input[class*=shopProductText_box]');
    t.val(parseInt(t.val())-1)
    if(parseInt(t.val())<0){
    t.val(0);
    }
    setTotal();
    })
    function setTotal(){
    var s=0;
    $("#shoptabtabtab td").each(function(){
    s+=parseInt($(this).find('input[class*=shopProductText_box]').val())*parseFloat($(this).find('span[class*=price]').text());
    });
    $("#shoptotaltotaltotal").html(s.toFixed(2));
    }
    setTotal();

    })
    </script>
<!-- =================================================================================商品加減相關結束======================================= -->

</html>
        

    