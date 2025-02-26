/**
 * member.js 
 */
//삭제함수.
//DB삭제, 화면에서 지우기.
function deleteRow(btn, id) {
    console.log(id);
    fetch("removerMember.do?mid=" + id) // 서버처리.
        .then(function(result) {
            return result.json(); // 응답을 JSON 형식으로 파싱
        })
        .then((result) => {
            console.log(result);
            if (result.retCode == "OK") {
                // 삭제 버튼이 속한 행을 삭제
				document.querySelector('#tr_' + id).remove(); // 한건 지우기 
            } else if (result.retCode == "NG") {
                alert('삭제 오류 발생');
            } else {
                alert('알 수 없는 코드입니다.');
            }
        })
        
} // end of deleteRow

// 목록출력
fetch("testData.do")
.then(function(result){
	return result.json(); // stream ->object		
})
.then(function(result){
	const memberAry = result;
	memberAry.forEach(function(member){
		const target = document.querySelector('#list');
		const html =`<tr id = tr_${member.memberId}>
		             <td>${member.memberId}</td>
					 <td>${member.passwd}</td>
					 <td>${member.memberName}</td>
					 <td>${member.responsibility}</td>
					 <td><button onclick="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button</td>
					 </tr>`;
		target.insertAdjacentHTML('beforeend',html)
	});
})
//추가.
document.querySelector('#addMember').addEventListener('click', function(e) {
	alert('클릭됨');
})

