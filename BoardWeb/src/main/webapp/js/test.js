/**
 *  js/test/js 
 * JSON 포맷(문자열-객체)
 */
console.log('경로정상.');

let json = `{"name" : "홍길동", "age":20}`;
let obj = JSON.parse(json);
document.querySelector('input[name="name"]').value = obj.name;
document.querySelector('input[name="age"]').value = obj.age;

// 서버(서블릿) - jsp페이지.
//Asynchronous.Javascript And Xml
console.log('1');

fetch('testData.do')
.then(function(result){
	console.log(result); //body : stream
	return result.json();//
})
  .then(function(result){
	   console.log(result);
	   document.querySelector('input[name="name"]').value = result.name;
	   document.querySelector('input[name="age"]').value = result.age;
	   console.log('2')

  })
  
 console.log('3');
  


