/**
 * array.js
 * forEach, filterm map, reduce 메소드.
 * 
 */
let ary = [
    {id :100, name: "홍길동", score:345},
	{id :101, name: "김말숙", score:456},
	{id :102, name: "최선기", score:232},
	]
	
// reduce.
let result = ary.reduce((acc, item, idx, array) => {
	console.log(acc, item, idx, array);	 
    return acc + item.score; // acc: accumulator
}, 0);	
console.log('최종결과: ', result);

result = ary.reduce((acc, item) => {
	return acc > item.score ? acc : item.score;
}, 1000);
console.log('최종Min: ', result);

//filter : 300점 이상.
result = ary.reduce((acc, item) => {
   if (item.score > 300) {
	   acc.push(item); // [{}]
   }	
   return acc;
}, []);
console.log('최종결과: ', result);

result = ary.reduce((acc, item)=> {
	let il = document.createElement('li');
	li.innerHTML = 'id: ' + item.id + ', name: ' + item.name;
	acc.appendChild(li);
	return acc;
}, document.getElementById('list'));


/*	
ary.forEach((item, idx, array) => {
	 console.log(item, idx, array);	 
}) 

let filAry = ary.filter(item => {
	if (item.score > 400) {
	return true;
	}
	return false;
})
console.log(filAry);
// map(ping)
let mapAry = ary.map(item=> {
	// A:400, B:300, C:그외.
	if(item.score > 400) {
		item.group = 'A';	
	} else if (item.score > 300) {
		item.group = 'B';	
	} else {
		item.group = 'C';	
	}
	return item;
});
console.log(mapAry);
*/
