/**
 * api.js
 */
let centerAll = [];

// 이번트(select) 등록
document.getElementById('centerList').addEventListener('change', function(e){
    let sidoName = e.target.value; 
	console.log(centerAll, sidoName);
	
	let filterSido = [];
	filterSido = centerAll.filter(item => {
		if (item.sido == sidoName) {
		return true;
	} 
	return false;
});
	console.log(filterSido);
    makeCenterList(filterSido);		
}); //change 이벤트.

function makeCenterList (centerAry = []) {
	let fieds = ['id', 'centerName', 'phoneNumber', 'sido'];
	//기존목록 삭제.
	document.getElementById('list').innerHTML = '';
	//센터정보.
	centerAry.forEach(center => {
		// tr > td*4	
		let tr = document.createElement('tr');
		tr.addEventListener('click',function() {
			console.log(center.lat, center.lng);
			window.open('map.do?lat='+ center.lat +'&lng=' + center.lat);
		});
		
		
		for (let i = 0; i < fieds.length; i++) {
			let td = document.createElement('td');
			td.innerHTML = center[fieds[i]];
			tr.appendChild(td);
		}
		document.getElementById('list').appendChild(tr);
	});
	
} // end of makeCenterList.

// Ajax.
fetch('https://api.odcloud.kr/api/15077586/v1/centers?page=1&perPage=284&returnType=json&serviceKey=c3DGZ80BSOpLzTFj45Odli%2By6rsKr%2BXNf9KequWiSVCr735fxgQ%2BdiEnhJFUG%2FjnSD7WFt4SKXcJBsi3buZ4xQ%3D%3D')
.then(result => result.json())
.then(result => {
	console.log(result.data);
	centerAll = result.data;
	makeSidoList();
    
})
.catch(err => console.log(err));

// 시도정보 중복제거 후 화면 출력.
function makeSidoList() {
	let sidoList = []; // ['서울특별시', '인천광역시', '대전광역시', '광주광역시'...]
    for(let i = 0; i<centerAll.length; i++) {
	 if(sidoList.indexOf(centerAll[i].sido)	== -1 ) {
		sidoList.push(centerAll[i].sido);
	 }
	}
	console.log(sidoList.sort());
    sidoList.forEach(sido =>  {
		let opt = document.createElement('option');
		opt.innerHTML = sido; // <option value="서울특별시"> 서울특별시 </option>
		document.getElementById('centerList').appendChild(opt);
	})
};





