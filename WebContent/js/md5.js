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