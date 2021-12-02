<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <base href="${pageContext.servletContext.contextPath}/">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <style>

body {
  background: linear-gradient(45deg, #6dc749, #23ac74);
}

.bolck-coupon {
  max-width: 1100px;
  margin: 0px auto;
  background: rgb(255, 255, 255);
  padding: 40px;
  border-radius: 4px;
}
.coupon {
  background: linear-gradient(45deg, #6dc749, #23ac74);
}
.article-content {
  padding-bottom: 40px;
}
.coupon .kanan {
  border-left: 1px dashed rgb(255, 255, 255);
  width: 40% !important;
  position: relative;
}

.coupon .kanan .info::after,
.coupon .kanan .info::before {
  content: "";
  position: absolute;
  width: 20px;
  height: 20px;
  background: #ffffff;
  border-radius: 100%;
}
.coupon .kanan .info::before {
  top: -10px;
  left: -10px;
}

.coupon .kanan .info::after {
  bottom: -10px;
  left: -10px;
}
.coupon .time {
  font-size: 1.6rem;
}

.lead {
  color: #fff;
}

.btn-block {
  background-color: #ffffff;
  color: #222;
  border: none;
}
.btn-block:hover {
  background: rgb(53, 248, 102);
  color: #222;
}
.container-img {
  width: 100%;

  margin: 0 auto; /* to center the container */
  margin: 20px 0;
}
.banner {
  max-width: 100%;
  height: auto;
}
.done {
  position: absolute;
  left: 0; top: 0; right: 0;
  text-align: center;
  opacity: 0;
  transform: translateY(-1em);
  color: #000;
  transition: all .500s;
}
.copied .done {
  opacity: 1;
  transform: translateY(-2em);
}
h1 {
  margin: 1.75em auto 1.25em;
}
textarea,
button {
  font-size: 1em;
  font-family: "Open Sans", Helvetica, Arial, sans-serif;
}
textarea {
  display: block;
  width: 700px;
  max-width: 100%;
  height: 75px;
  margin: 2em auto 1.5em;
  background: #F2F2F6;
  border: 1px solid #ddd;
  padding: 10px 15px;
  resize: vertical;
}
[id="empty-field"] {
  margin-top: 3em;
}
textarea:focus {
  border-color: red;
}
button {
  position: relative;
  padding: 8px 20px;
  border: 0;
  font-size: 0.835em;
  text-transform: uppercase;
  letter-spacing: 0.125em;
  font-weight: bold;
  color: #FFF;
  background: #e60023;
  transition: background .275s;
}
button:hover,
button:focus {
  background: #212121;
}
.code-discount {
  color: #fff;
  font-weight: bold;
  margin-bottom: 10px;
  border: none;
  background: none;
  width: 40px;
  text-align: center;	
outline: none;
}
    </style>
</head>
<body>
    
    <div class="container my-5">

        <div class="bolck-coupon">
            <div class="title">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb">
                      <li class="breadcrumb-item"><a href="index.html">Home</a></li>
                      <li class="breadcrumb-item active" aria-current="page">Discount</li>
                      <li class="breadcrumb-item active" aria-current="page">${discount.name }</li>
                    </ol>
                  </nav>
                <h1>${discount.name }</h1>
                <div class="container-img">
                    <img class="banner" src="UploadFiles/Discount/${discount.image }	" alt="">
                </div>
                <div class="article-content">
                    <div class="rte">

                        		${discount.description }												
                        </div>
                </div>
            </div>
            <div class="col-sm-12">
                <div class="coupon bg-white rounded mb-3 d-flex justify-content-between">
                    <div class="kiri p-3">
                        <div class="icon-container ">
                            <div class="icon-container_box">
                                <img src="data:image/png;base64,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" width="85" alt="totoprayogo.com" class="" />
                            </div>
                        </div>
                    </div>
                    <div class="tengah py-3 d-flex w-100 justify-content-start">
                        <div>
                            <span class="badge badge-success">Valid</span>
                            <h3 class="lead">Coupon Detail goes here </h3>
                            <p class="text-muted mb-0">Short info about voucher</p>
                        </div>
                    </div>
                    <div class="kanan">
                        <div class="info m-3 d-flex align-items-center">
                            <div class="w-100">
                                <div class="block">
                           
                            
                                    
                                 
                                </div>
                         
                                <input class="code-discount" id="copy-text" spellcheck="false" value="${discount.id }" readonly="true">
                                
 
<button id="copy" type="button">Copy to clipboard<span class="done" aria-hidden="true">Copied</span></button>

                                    
                               
                                

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
    <script>

var toCopy  = document.getElementById( 'copy-text' ),
    btnCopy = document.getElementById( 'copy' )
   
 
btnCopy.addEventListener( 'click', function(){

  toCopy.select();

  
  if ( document.execCommand( 'copy' ) ) {
      btnCopy.classList.add( 'copied' );
  
      var temp = setInterval( function(){
        btnCopy.classList.remove( 'copied' );
        clearInterval(temp);
      }, 600 );
    
  } else {
    console.info( 'document.execCommand went wrong…' )
  }
  
  return false;
} );
    </script>
</body>
</html>