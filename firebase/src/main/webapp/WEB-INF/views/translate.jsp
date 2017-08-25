<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
</head>
<body>
   <form id="translate">
   <div>
   언어선택:
   <select name="input" id="inputtype">
      <option value="ko-KR">한국어</option>
      <option value="en-US">영어</option>
      <option value="cmn-Hans-CN">중국어</option>
   </select>
   <button onclick="eylem()" type="button" >누르고 말해</button>
   </div>
      
   입력:<textarea rows="10" cols="100" id="inputtext"></textarea>
   
   <button type="submit" id="submit">번역</button>
   </form>
   <br>
   번역언어:
   <select name="output" id="outputtype">
      <option value="ko">한국어</option>
      <option value="en">영어</option>
      <option value="zh-CN">중국어</option>
   </select>
   
   해석:<span id="outputtext"></span>
   
<script>
function eylem(){
    var lang =  $("#inputtype").val();
    var ses = new webkitSpeechRecognition();
    ses.lang= lang;
    ses.onresult=function(e){
       if(event.results.length>0){
          sonuc = event.results[event.results.length-1];
          if(sonuc.isFinal){
             $("textarea").val(sonuc[0].transcript);
          }
       }      
    }
    ses.start();
    return false;
 }
   $("#translate").submit(function(e){
      
      e.preventDefault();
      $.ajax({
         url : '/translateok',
         type :'POST',
         data :{
            text : $("#inputtext").val(),
            input : $("#inputtype").val(),
            output : $("#outputtype").val()
         },
         datatype: 'text'
      }).done(function (result) {
         
         $("#outputtext").html(result);
      })
      
   });
   
   
</script>
</body>
</html>