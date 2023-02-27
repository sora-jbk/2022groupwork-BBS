/**
 * 
 */


var form = document.getElementsByClassName('coment')[0];

form.addEventListener('input',function(){
	var s = document.getElementsByClassName('coment')[0].value;
	var length = 0;
	form.maxLength=240;
	for (var i = 0; i < s.length; i++) {
	    var c = s.charCodeAt(i);
	    if ( c != 0x0a &&　c >= 0x0 && c <= 0xff ) {
	      length += 1;
	    } else {
	      length += 2;
	      form.maxLength--;
	    }
  	}
  
	var l = document.getElementById('length');
	l.innerText = length+"/240";
});


var author = document.getElementsByClassName("name")[0];

author.addEventListener('input',
	function(){
		var str = author.value;
		console.log(str);
		
		author.maxLength = 20;
		
		for(i=0 ; i<str.length ; i++){
			var c = str.charCodeAt(i);
			console.log(c);
			if(c>0xff){
				author.maxLength--;
			}
		}
		
		
	}
);

 //以下コピーペースト
 window.addEventListener("DOMContentLoaded", () => {
  // textareaタグを全て取得
  const textareaEls = document.querySelectorAll("textarea");

  textareaEls.forEach((textareaEl) => {
    // デフォルト値としてスタイル属性を付与
    textareaEl.setAttribute("style", `height: ${textareaEl.scrollHeight}px;`);
    // inputイベントが発生するたびに関数呼び出し
    textareaEl.addEventListener("input", setTextareaHeight);
  });

  // textareaの高さを計算して指定する関数
  function setTextareaHeight() {
    this.style.height = "auto";
    this.style.height = `${this.scrollHeight}px`;
  }
});