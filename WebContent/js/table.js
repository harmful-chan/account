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
   tb10.innerHTML = `*`;
   tb9.innerHTML =  `*`;
   tb8.innerHTML =  `*`;
   tb7.innerHTML =  `<input type="text" class="form-control four">`;
   tb6.innerHTML =  `*`;
   tb5.innerHTML =  `<input type="text" class="form-control three">`;
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
/*password = Array.from(document.querySelectorAll(".password"));
for (let i in password){
   password[i].ondblclick = function () {
      this.innerHTML = `<input type="text" >`;
      this.children[0].onblur = function () {
         var str = this.value;
         this.parentNode.innerHTML = str;
      }
   }
}*/


/*修改用户数据*/
/*text = Array.from(document.querySelectorAll(".text"));
for (let i in text){
   text[i].ondblclick = function () {
      this.innerHTML = `<input type="text" value="${this.textContent}">`;
      this.children[0].onblur = function () {
         var str = this.value;
         this.parentNode.innerHTML = str;
      }
   }
}*/

/*删除数据*/
const btnRemove = document.querySelector(".btn-danger");
btnRemove.addEventListener("click",function () {
	   let c = document.querySelectorAll(".form-check-input")
	   let checks = Array.from(c);
	   for (let i in checks){
			 if (checks[i].checked) {
				let parentTr = checks[i].parentNode.parentNode.parentNode.parentNode.children;
				const accountNumber = parentTr[3].innerText;
				const ownerNumber = parentTr[6].innerText;
				var u = document.getElementById("table_remove");
				u = u.innerHTML;
				alert(u);
				var url = "&accountNumber="+accountNumber+"&deeppwd=nonepassowrd&ownerNumber="+ownerNumber+"&explain=noneexplain";
				console.log(u + url);
				location.href = u + url;
			 }
	   }  

});

/*保存用户数据*/
const btnUpdate = document.querySelector(".btn-warning");

btnUpdate.addEventListener("click",function () {
   let insert = document.querySelector(".last");
   if(insert != null){
      const explain = document.querySelector(".one");
      const accountNumber = document.querySelector(".two");
      const password = document.querySelector(".three");
      const ownerNumber = document.querySelector(".four");
      var u = document.getElementById("table_alter");
	  u = u.innerHTML;
	  alert(u);
	  var url = `&explain=${explain.value}&accountNumber=${accountNumber.value}&deeppwd=${encode(password.value)}&ownerNumber=${ownerNumber.value}`;
		console.log(u + url);
         location.href = u + url;
   }
});


function over(id){
    var pass = document.getElementById(id);
	console.log(pass.type);
	pass.type="text";
    pass.value = decode(pass.value); 
}

function out(id){
    var pass = document.getElementById(id);
    console.log(pass.type);
	pass.type="password";
	pass.value = encode(pass.value); 
}

