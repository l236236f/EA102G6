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
                            <li>Address: ��饫���j�������j�Ǥu�{�G�]</li>
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
                        <h5>�������j�u�I</h5>
                        <ul>
                            <li><a href="#">�h���~��</a></li>
                            <li><a href="#">�w�q�W��</a></li>
                            <li><a href="#">�K����</a></li>
                            <li><a href="#">���A�ഫ</a></li>
                            <li><a href="#">���C�̩ۨ�</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-2">
                    <div class="footer-widget">
                        <h5>MVC�T����</h5>
                        <ul>
                            <li><a href="#">���ҰѼơB�B�z���~</a></li>
                            <li><a href="#">����h�s��</a></li>
                            <li><a href="#">���View</a></li>                           
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="newslatter-item">
                        <h5>�{�b�N�[�J�ڭ̧a</h5>
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
				swal('�z���s�q���I', jsonObj.contain, 'success');
			}
		};
		webSocketN.onclose = function(event) {
			console.log("Disconnected!");
		};
	}
	$("#agame").click(function(e){
		e.preventDefault();
		swal({
            title: "�`�N�I",
            html: "�e�観�����ת��D�ԡI�T�w�~��H",
            type: "question", // type can be "success", "error", "warning", "info", "question"
            showCancelButton: true,
            showCloseButton: true,
        }).then(function(result) {
            if (result) {
            	window.location.href = '<%= request.getContextPath()%>/front-end/selfProject/irikuji.jsp';
            }
        }, function(dismiss) { // dismiss can be "cancel" | "overlay" | "esc" | "cancel" | "timer"
            swal("����", "��I�����i��484�H", "error");
        });
	})

	$(function() {
		//�q��socket
		connectN();
		//��ѫ�socket
// 		ChatConnect();
		//GOOGLEMAP
		if (typeof init == 'function') {
			init();
		}
		if (typeof initMap == 'function') {
			initMap();
		}
		//�ӫ�socket
		if (typeof connectM == 'function') {
			connectM();
		}
	});// $f
	
</script>
