const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

const lang = urlParams.get('language')
var messError = "";
var messEmpty ="";
var alertmess = "";
console.log(lang);
if(lang == null || lang == "vi"){
    messError = "*So dien thoai khong dung";
    messEmpty = "*Truong nay khong duoc bo trong";
    alertmess = "Nhap so dien thoai phu hop";
}else if(lang == "en") {
    messError = "*Invalid phone number";
    messEmpty = "*This field is required";
    alertmess = "Please enter the correct phone number";
}

function validateInput(){
    var phonenumber = document.getElementById("phonenumber").value;
    var validate = document.getElementById("validate");

    var check = /((09|03|07|08|05)+([0-9]{8})\b)/g.test(phonenumber);
    
    if(!check){
        document.getElementById("validate").innerHTML = messError;
        return false;
    }else {
        validate.innerHTML = "";
        return true;
    }
}

function checkEmpty(){
    var phonenumber = document.getElementById("phonenumber").value;
    
    if(phonenumber==""){
        document.getElementById("validate").innerHTML = messEmpty;
    }
}


function validateForm(){
    if(!validateInput()) {
        alert(alertmess);
        return false;
    }
}



