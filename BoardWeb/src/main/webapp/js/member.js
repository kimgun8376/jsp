/**
 * member.js 
 */
//삭제함수.
function deleteRow(btn, id) {
    console.log(id);
    fetch("removerMember.do?mid=" + id)
        .then((result) => {
            return result.json(); // 응답을 JSON 형식으로 파싱
        })
        .then((result) => {
            console.log(result);
            if (result.retCode == "OK") {
                // 삭제 버튼이 속한 행을 삭제
                btn.closest("tr").remove();
            } else if (result.retCode == "NG") {
                alert('삭제 오류 발생');
            } else {
                alert('알 수 없는 코드입니다.');
            }
        })
        .catch((error) => {
            // 네트워크 오류 처리
            console.error("삭제 요청 중 오류 발생:", error);
            alert("삭제 요청 중 오류가 발생했습니다.");
        });
} // end of deleteRow

fetch("testDate.do")
.then(function(result){
	return result.json(); // stream ->object		
})
.then(function(result){
	const memberAry = result;
	console.log(member);
	memberAry.forEach(function(member){
		const target = document.querySelector('#list');
		const html =`<tr>
		             <td>${member.memberId}</td>
					 <td>${member.passwd}</td>
					 <td>${member.memberName}</td>
					 <td>${member.responsibility}</td>
					 <td><button onclick="deleteRow('${member.memberId}')" class="btn btn-danger">삭제</button</td>
					 </tr>`;
		target.insertAdjacentHTML('beforeend',html)
	});
})