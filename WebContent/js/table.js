/*添加用户*/
const btnAdd = document.querySelector(".btn-primary");
const tbody = document.querySelector("tbody");
btnAdd.addEventListener("click",function () {
   let tr1 = document.createElement("tr");
   let tb1 = document.createElement("td");
   let tb2 = document.createElement("td");
   let tb3 = document.createElement("td");
   let tb4 = document.createElement("td");
   let tb5 = document.createElement("td");
   let tb6 = document.createElement("td");
   let tb7 = document.createElement("td");
   let tb8 = document.createElement("td");
   let tb9 = document.createElement("td");
   let tb10 = document.createElement("td");
   tb10.innerHTML = `<input type="text" class="form-control hour">`;
   tb9.innerHTML =  `<span/>`;
   tb8.innerHTML =  `<span/>`;
   tb7.innerHTML =  `<span/>`;
   tb6.innerHTML =  `<input type="text" class="form-control three">`;
   tb5.innerHTML =  `<span/>`;
   tb4.innerHTML =  `<input type="text" class="form-control two">`;
   tb3.innerHTML =  `<input type="text" class="form-control one">`;
   tb2.innerHTML =  `*`;
   tb1.innerHTML =  `<div  class="form-check last"> <label class="form-check-label"> <input class="form-check-input" type="checkbox" value checked  disabled="disabled"/> <span class="form-check-sign"></span> </label> </div> `;
   tr1.appendChild(tb1);
   tr1.appendChild(tb2);
   tr1.appendChild(tb3);
   tr1.appendChild(tb4);
   tr1.appendChild(tb5);
   tr1.appendChild(tb6);
   tr1.appendChild(tb7);
   tr1.appendChild(tb8);
   tr1.appendChild(tb9);
   tr1.appendChild(tb10);
      tbody.appendChild(tr1);
});


/*修改用户数据*/
password = Array.from(document.querySelectorAll(".password"));
for (let i in password){
   password[i].ondblclick = function () {
      this.innerHTML = `<input type="text" value="${decode(this.textContent)}">`;
      this.children[0].onblur = function () {
         var str = this.value;
         this.parentNode.innerHTML = encode(str);
      }
   }
}


/*修改用户数据*/
text = Array.from(document.querySelectorAll(".text"));
for (let i in text){
   text[i].ondblclick = function () {
      this.innerHTML = `<input type="text" value="${this.textContent}">`;
      this.children[0].onblur = function () {
         var str = this.value;
         this.parentNode.innerHTML = str;
      }
   }
}

/*保存用户数据*/
const btnUpdate = document.querySelector(".btn-warning");

btnUpdate.addEventListener("click",function () {
   let insert = document.querySelector(".last");
   if(insert != null){
      const one = document.querySelector(".one");
      const two = document.querySelector(".two");
      const three = document.querySelector(".three");
      const four = document.querySelector(".four");
      var u = document.getElementById("table_alter");
	  u = u.innerHTML;
	  alert(u);
	  var url = `&accountNumber=${one.value}&deeppwd=${encode(two.value)}&ownerNumber=${three.value}&explain=${three.value}`;
		console.log(u + url);
         location.href = u + url;
   }else{
		let c = document.querySelectorAll(".form-check-input")
	   let checks = Array.from(c);
	   for (let i in checks){
			 if (checks[i].checked) {
				let parentTr = checks[i].parentNode.parentNode.parentNode.parentNode.children;
				const accountNumber = parentTr[2].innerText;
				const deeppwd = parentTr[3].innerText;
				const ownerDepartment= parentTr[5].innerText;
				const ownerRole = parentTr[6].innerText;
				const ownerNumber = parentTr[7].innerText;
				const valid = parentTr[8].innerText;
				const explain = parentTr[9].innerText;
				
				var u = document.getElementById("table_alter");
				u = u.innerHTML;
				alert(u);
				var url = "&accountNumber="+accountNumber+"&deeppwd="+deeppwd+"&ownerDepartment="+ownerDepartment+"&ownerRole="+ownerRole+"&ownerNumber="+ownerNumber+"&explain="+explain;
						  
				
						  
				console.log(u + url);
				location.href = u + url;
			 }
	   }  
   }
	

});


function over(id){
    var pass = document.getElementById(id);
	console.log(pass.innerHTML);
    pass.innerHTML = decode(pass.innerHTML); 
}

function on(id, str){
	var sp = document.getElementById(id);
	sp.innerHTML = str;
	console.log(sp.innerHTML);
}

function out(id){
    var pass = document.getElementById(id);
    pass.innerHTML = encode(pass.innerHTML);
}

//解码
function encode(password){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();
	var ff = date.getMilliseconds();
	if (month < 10) {
		month = "0" + month;
	}
	if (day < 10) {
		day = "0" + day;
	}
	if (hour < 10) {
		hour = "0" + hour;
	}
	if (min < 10) {
		min = "0" + min;
	}
	if (sec < 10) {
		sec = "0" + sec;
	}
	if (ff < 10 && ff >=0) {
		ff = "00" + ff;
	}
	
	if(ff >=10 && ff < 100){
		ff = "0" + ff;
	}
	
	var nowDate = year + "" + month + "" + day + "" + hour + "" + min + "" + sec + "" +ff;
	var deep = password+""+nowDate;
	
	var deeppwd = window.btoa(deep);
	
	return deeppwd;
}  	


function decode(deeppwd){
    var deep = window.atob(deeppwd);
    
    var salt = deep.substring(deep.length-17, deep.length);
    var password = deep.replace(salt, "");
    
    return password;
}