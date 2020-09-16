window.onload = function () {

			
					var oDiv = document.getElementById('odiv');

					var footer =oDiv.getElementsByClassName('footer')[0];
					oDiv.style.width = '500px';
					var leftlcolumn =oDiv.getElementsByClassName("leftlColumn");
					var oDiv = document.getElementById('odiv');
					


					for(let i=0;i<leftlcolumn.length;i++){
						openInnerObject(leftlcolumn[i]);
					}
					

					var aSpan = oDiv.getElementsByTagName('span');
					var textArea = oDiv.getElementsByClassName('emoji-wysiwyg-editor');
					var chatrbutton = document.getElementById('chatrbutton');
					moveDiv(chatrbutton);
					moveDiv(oDiv);
					let messageTextArea = oDiv.getElementsByClassName('messageTextArea');
					let img = oDiv.getElementsByTagName('img');
					stopProp(img);
					stopProp(messageTextArea);
					stopProp(oDiv.getElementsByTagName('P'));
					stopProp(oDiv.getElementsByClassName('messageButtun'));
					stopProp(textArea);
					

					for (let i = aSpan.length - 1; i >= 0; i--) {
						dragFn(aSpan[i],oDiv);
					}


					dragFn2(footer.getElementsByTagName('a')[0],footer,oDiv.getElementsByClassName('inner')[0]);	


					$("#odiv .iconClick").click(function(){
						$("#odiv div[name='uploadfile'] .upfilebutton").click();
					});

					oDiv.onclick=function(e){
						
						if(e.target.getAttribute('name')!='leftlColumn' && e.target.getAttribute('class')!='leftbutton'){

							for(let i=0;i<leftlcolumn.length;i++){
								closeInnerObject(leftlcolumn[i]);
								closeObjectW(leftlcolumn[i]);
							}
						};


						if(e.target.getAttribute('name')=='leftbuttonout'|| e.target.getAttribute('name')=='leftbutton'){

							let start = Date.now();
							let width = 0;
							let timer = setInterval(function() {
								let timePassed = Date.now() - start;
								for(let i=0;i<leftlcolumn.length;i++){
									leftlcolumn[i].style.width=width+'px';
								}
								width=width+300/400*20;
								if (timePassed > 400 ) clearInterval(timer);
							}, 20);
							setTimeout(function(){
								for(let i=0;i<leftlcolumn.length;i++){
									openInnerObject(leftlcolumn[i])};
								},500);
						}else if(e.target.getAttribute('name')=='close'){
							toBeClosed(oDiv);
							appear(chatrbutton);
						}else if(e.target.getAttribute('class')=='searchinput'){
							e.preventDefault();
							e.target.focus();
						}

					};






					let upload=oDiv.getElementsByClassName('upfilebutton')[0];
					upload.addEventListener("change",function(){
						var fileList = this.files;
						var reader = new FileReader();
						if(fileList.length>0){
						reader.readAsDataURL(fileList[0]);
						}
					}
					, false);




					chatrbutton.onclick=function(){
						if (chatrbutton.diffTime<150){
							toBeClosed(chatrbutton);
							appear(oDiv);
						};
					};
					$("#odiv .video").hide();
					$("#odiv .videoBox").hide();
					$("#odiv .checkStream").hide();
					$("#odiv .client").click(function(){
						
						$("#odiv .clientBox").toggle();
					});

					$("#odiv .video").click(function(e){
						$("#odiv .video").hide();
						$("#odiv .videoBox").toggle();
					});
					$("#odiv .minusChildVideo").click(function(){
						$("#odiv .localVideo").toggle();
					});
					$("#odiv .closeVideo").click(function(){
						$("#odiv .videoBox").hide();
						$("#odiv .video").show();
						$("#odiv .checkStream").hide();
					});
};//onload

				function stopProp(array){
					for (var i = array.length - 1; i >= 0; i--) {
						array[i].onmousedown=function(e){
							e.stopPropagation();	
						};
						
					}
				}
				function enterImg(obj,html){
					obj.focus();
					insertHtmlAtCaret(html);
				}

				function dragFn2(obj,object,object2) {
					obj.onmousedown = function (ev) {
						var oEv = ev;
						oEv.stopPropagation();
						var oldWidth = object.offsetWidth;
						var oldHeight = object.offsetHeight;
						var oldX = oEv.clientX;
						var oldY = oEv.clientY;
						var oldLeft = object.offsetLeft;
						var oldTop = object.offsetTop;
						var oldbottom2 =Number(object2.style.bottom.replace('px', ''));
						var oldobjHeight =Number(object.style.height.replace('px', ''));
						var oldobjPHeight =Number(object.parentElement.style.height.replace('px', ''));


						document.onmousemove = function (ev) {
							var oEv = ev;

							let disY = (oldTop + (oEv.clientY - oldY)),
							disX = (oldLeft + (oEv.clientX - oldLeft));
							if(disX>oldLeft+oldWidth){
								disX=oldLeft+oldWidth
							}
							if(disY>oldTop+oldHeight){
								disY=oldTop+oldHeight
							}



							if (obj.className == 't') {
								let height =oldobjHeight - (oEv.clientY - oldY);

								if(height <60){
									height=60;
								}else if (height > (oldobjPHeight-100)){
									height=oldobjPHeight-100;
								}

								object.style.height =height  + 'px';
								let bottom =oldbottom2 - (oEv.clientY - oldY)-60;
								if(bottom<height){
									bottom=height;
								}else if(bottom>(oldobjPHeight-100)){
									bottom=(oldobjPHeight-100);
								}

								object2.style.cssText +=  'bottom:'+bottom + 'px';

							}
						};
						document.onmouseup = function () {
							document.onmousemove = null;
						};
						return false;
					};
				}

				function dragFn(obj,object) {
					obj.onmousedown = function (ev) {
						var oEv = ev;
						oEv.stopPropagation();
						var oldWidth = object.offsetWidth;
						var oldHeight = object.offsetHeight;
						var oldX = oEv.clientX;
						var oldY = oEv.clientY;
						var oldLeft = object.offsetLeft;
						var oldTop = object.offsetTop;

						document.onmousemove = function (ev) {
							var oEv = ev;

							let disY = (oldTop + (oEv.clientY - oldY)),
							disX = (oldLeft + (oEv.clientX - oldLeft));
							if(disX>oldLeft+oldWidth){
								disX=oldLeft+oldWidth
							}
							if(disY>oldTop+oldHeight){
								disY=oldTop+oldHeight
							}
							if (obj.className == 'tl') {
								object.style.width = oldWidth - (oEv.clientX - oldX) + 'px';
								object.style.height = oldHeight - (oEv.clientY - oldY) + 'px';
								object.style.left = disX + 'px';
								object.style.top = disY + 'px';
							}
							else if (obj.className == 'bl') {
								object.style.width = oldWidth - (oEv.clientX - oldX) + 'px';
								object.style.height = oldHeight + (oEv.clientY - oldY) + 'px';
								object.style.left = disX + 'px';
								object.style.bottom = oldTop + (oEv.clientY + oldY) + 'px';
							}
							else if (obj.className == 'tr') {
								object.style.width = oldWidth + (oEv.clientX - oldX) + 'px';
								object.style.height = oldHeight - (oEv.clientY - oldY) + 'px';
								object.style.right = oldLeft - (oEv.clientX - oldX) + 'px';
								object.style.top = disY + 'px';
							}
							else if (obj.className == 'br') {
								object.style.width = oldWidth + (oEv.clientX - oldX) + 'px';
								object.style.height = oldHeight + (oEv.clientY - oldY) + 'px';
								object.style.right = oldLeft - (oEv.clientX - oldX) + 'px';
								object.style.bottom = oldTop + (oEv.clientY + oldY) + 'px';
							}
							else if (obj.className == 't') {
								object.style.height = oldHeight - (oEv.clientY - oldY) + 'px';
								object.style.top = disY + 'px';
							}
							else if (obj.className == 'b') {
								object.style.height = oldHeight + (oEv.clientY - oldY) + 'px';
								object.style.bottom = disY + 'px';
							}
							else if (obj.className == 'l') {
								object.style.height = oldHeight + 'px';
								object.style.width = oldWidth - (oEv.clientX - oldX) + 'px';
								object.style.left = disX + 'px';
							}
							else if (obj.className == 'r') {
								object.style.height = oldHeight + 'px';
								object.style.width = oldWidth + (oEv.clientX - oldX) + 'px';
								object.style.right = disX + 'px';
							}
						};
						document.onmouseup = function () {
							document.onmousemove = null;
						};
						return false;
					};
				}


				function closeInnerObject(obj){	

					for(let i=0;i<obj.children.length;i++){
						obj.children[i].style.opacity=0;
						obj.children[i].style.visibility="hidden";
					}

				};
				function openInnerObject(obj){	

					for(let i=0;i<obj.children.length;i++){
						obj.children[i].style.opacity=1;
						obj.children[i].style.visibility="visible";
					}

				};

				function closeObjectW(obj){
					let start = Date.now();
					let width = parseInt(obj.style.width);
					let widthO= parseInt(obj.style.width);
					let timer = setInterval(function() {
						let timePassed = Date.now() - start;
						obj.style.width=width+'px';
						width=width-widthO/400*20;
						if (timePassed > 400 && width <=0 ){
							obj.style.width=0+'px';clearInterval(timer)};
						}, 20);

				};

				function closeObjectH(obj){
					let start = Date.now();
					let height = parseInt(obj.style.height);
					let heightO= parseInt(obj.style.height);
					let timer = setInterval(function() {
						let timePassed = Date.now() - start;
						obj.style.height=height+'px';
						height=height-heightO/100*20;
						if (timePassed > 100 && height <=0 ){
							obj.style.height=0+'px';clearInterval(timer)};
						}, 20);

				};


				function toBeClosed(obj){
					obj.style.opacity=1;
					let objscale=1;
					let start = Date.now();
					let timer = setInterval(function() {
						let timePassed = Date.now() - start;
						obj.style.opacity=obj.style.opacity-(1/20);
						obj.style.transform='scale('+objscale+')';
						objscale=objscale-1/20;	
						if (timePassed > 400 && objscale<=0) clearInterval(timer);
					}, 20);

				};
				function appear(obj){
					let objscale=0;
					let start = Date.now();
					let timer = setInterval(function() {
						let timePassed = Date.now() - start;
						obj.style.opacity=objscale;
						obj.style.transform='scale('+objscale+')';
						objscale=objscale+1/10;	
						if (timePassed > 200 && objscale>=1 ) clearInterval(timer);
					}, 20);
				};

				function appearHeight(obj,objheight){
					let start = Date.now();
					let height = 0;
					if(Number(obj.style.height.replace('px', ''))==0){
						let timer = setInterval(function() {
							let timePassed = Date.now() - start;
							obj.style.height=height+'px';
							height=height+objheight/100*20;
							if (timePassed > 100 && height>objheight ) clearInterval(timer);
						}, 20);
					}
				};

				function moveDiv(obj){

					obj.onmousedown=function (ev) {
						var oevent = ev;
						oevent.stopPropagation();
			// oevent.preventDefault();
			obj.timestart = Date.now();
			var distanceX = oevent.clientX - obj.offsetLeft;
			var distanceY = oevent.clientY - obj.offsetTop;

			document.onmousemove = function (ev) {
				var oevent = ev;
				obj.style.left = oevent.clientX - distanceX + 'px';
				obj.style.top = oevent.clientY - distanceY + 'px';
			};
			document.onmouseup = function (e) {
				obj.diffTime = Date.now() - obj.timestart;
				obj.timestart=0;
				document.onmousemove = null;
				document.onmouseup = null;

			};
		};

	};

	function insertHtmlAtCaret(html) {
		var sel, range;
		if (window.getSelection) {
			sel = window.getSelection();
			if (sel.getRangeAt && sel.rangeCount) {
				range = sel.getRangeAt(0);
				range.deleteContents();

				var el = document.createElement("div");
				el.innerHTML = html;
				var frag = document.createDocumentFragment(), node, lastNode;
				while ((node = el.firstChild)) {
					lastNode = frag.appendChild(node);
				}
				range.insertNode(frag);
				if (lastNode) {
					range = range.cloneRange();
					range.setStartAfter(lastNode);
					range.collapse(true);
					sel.removeAllRanges();
					sel.addRange(range);
				}
			}
		} else if (document.selection && document.selection.type != "Control") {

			document.selection.createRange().pasteHTML(html);
		}
	}


	function shake(e,time,width,height){
		var start=(new Date()).getTime();
		let left = e.offset().left;
		let top  = e.offset().top;
		var now=(new Date()).getTime();
        //總時間的幾分之幾
        var str = e.css('box-shadow');
        e.css('box-shadow',"white 0px 0px 0px 0px");
        var moveW;
        var moveH;
        let timer = setInterval(function() {
        	var a=Math.random()*50;
        	var b=Math.random()*50;
        	var timePassed = Date.now() - start;
        	var fraction=timePassed/time;
        	if(a%2 == 0){
        		moveW=left+width*Math.random();
        	}else{
        		moveW=left-width*Math.random();
        	}

        	if(b%2 == 0){
        		moveH = top + height*Math.random();
        	}else{
        		moveH = top - height*Math.random();
        	}

        	e.offset({left:moveW,top:moveH});
        	if (timePassed > time ) {
        		clearInterval(timer);
        		e.offset({left:left,top:top});
        		 e.css('box-shadow',str);
        	}
        }, 20);
    }
	
	$(function() {
	      // Initializes and creates emoji set from sprite sheet
	      window.emojiPicker = new EmojiPicker({
	      	emojiable_selector: '[data-emojiable=true]',
	      	assetsPath: 'lib/img/',
	      	popupButtonClasses: 'far fa-grin-tongue-squint'
	      });
	      // Finds all elements with `emojiable_selector` and converts them to rich emoji input fields
	      // You may want to delay this step if you have dynamically created input fields that appear later in the loading process
	      // It can be called as many times as necessary; previously converted input fields will not be converted again
	      window.emojiPicker.discover();

	      var face = $('.emoji-picker-icon');
	      var textArea = $('.emoji-wysiwyg-editor');
	      var menu =$('.emoji-menu');
	      face.mousemove(function(e){
	      	e.stopPropagation();
	      	e.preventDefault();
	      	show(menu,face);
	      });
	      face.click(function(e){
	      	e.stopPropagation();
	      	e.preventDefault();
	      	show(menu,face);
	      });
	  });
		function show(menu,face){
			var Left = parseInt(face.offset().left)
			,Top = parseInt(face.offset().top);
			menu.offset({ left: (Left-150) , top: (Top-245)  });
			menu.addClass('visible');		
		}