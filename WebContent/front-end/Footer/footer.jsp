<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<footer class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="footer-left">
                        <div class="footer-logo">
                            <a href="#"><img src="" alt=""></a>
                        </div>
                        <ul>
                            <li>Address: 桃園市中大路中央大學工程二館</li>
                            <li>Phone: +886 123 4567</li>
                            <li>Email: 00dog@gmail.com</li>
                        </ul>
                        <div class="footer-social">
                            <a href="#"><i class="fa fa-facebook"></i></a>
                            <a href="#"><i class="fa fa-instagram"></i></a>
                            <a href="#"><i class="fa fa-twitter"></i></a>
                            <a href="#"><i class="fa fa-pinterest"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-2 offset-lg-1">
                    <div class="footer-widget">
                        <h5>介面五大優點</h5>
                        <ul>
                            <li><a href="#">多重繼承</a></li>
                            <li><a href="#">定義規格</a></li>
                            <li><a href="#">貼標籤</a></li>
                            <li><a href="#">型態轉換</a></li>
                            <li><a href="#">降低相依性</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="footer-widget">
                        <h5>MVC三部曲</h5>
                        <ul>
                            <li><a href="#">驗證參數、處理錯誤</a></li>
                            <li><a href="#">永續層存取</a></li>
                            <li><a href="#">轉交View</a></li>                           
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="newslatter-item">
                        <h5>現在就加入我們吧</h5>
                    	<a href="<%=request.getContextPath()%>/front-end/Q&A.jsp">Q&A</a>
                        
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright-reserved">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="copyright-text">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </div>
                        <div class="payment-pic">
                            <img src="" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <script src="<%=request.getContextPath()%>/front-end/js/jquery-3.3.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery-ui.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.countdown.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/jquery.nice-select.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.zoom.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.dd.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/jquery.slicknav.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/owl.carousel.min.js"></script>
	<script src="<%=request.getContextPath()%>/front-end/js/main.js"></script>
	
<%-- 	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
	
<script>
	var MyPointN = "/NoticeWS/${LoginMem.memNo}";
	var hostN = window.location.host;
	var pathN = window.location.pathname;
	var webCtxN = pathN.substring(0, pathN.indexOf('/', 1));
	var endPointURLN = "ws://" + window.location.host + webCtxN + MyPointN;
	var selfN = '${LoginMem.memNo}';
	var webSocketN;
	function connectN() {
		
		// create a websocket
		webSocketN = new WebSocket(endPointURLN);

		webSocketN.onopen = function(event) {
			console.log("Connect Success!");
		};

		webSocketN.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("notice" === jsonObj.type) {
				swal('您有新通知！', jsonObj.contain, 'success');
			}
		};
		webSocketN.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	$("#agame").click(function(e){
		e.preventDefault();
		swal({
            title: "注意！",
            html: "前方有高難度的挑戰！確定繼續？",
            type: "question", // type can be "success", "error", "warning", "info", "question"
            showCancelButton: true,
            showCloseButton: true,
        }).then(function(result) {
            if (result) {
            	window.location.href = '<%= request.getContextPath()%>/front-end/selfProject/irikuji.jsp';
            }
        }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
            swal("取消", "嫩！不敢進來484？", "error");
        });
	})

	$(function() {
		//通知socket
		connectN();
		//聊天室socket
// 		ChatConnect();
		//GOOGLEMAP
		if (typeof init == 'function') {
			init();
		}
		if (typeof initMap == 'function') {
			initMap();
		}
		//商城socket
		if (typeof connectM == 'function') {
			connectM();
		}
	});// $f
	
</script>
