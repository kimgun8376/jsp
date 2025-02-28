/**
 *  reply_dt.js
 */
let table = new DataTable('#example', {
	ajax: 'datatable.do?bno=' + bno,
	lengthMenu: [
		[5, 10, 25, 50, -1],
		[5, 10, 25, 50, 'All']
	]
});

//화면에서 row 추가.
let counter = 'hello'
function addNewRow() {
	//원본글(bno), replyer(logid), reply(#reply)
	let reply = document.querySelector('#reply').value;
	let param = { bno, reply, replyer: logid }
	// ajax호출.
	svc.addReply(param,
		function(result) {
			let rvo = result.retVal;
			table.row
				.add([
					rvo.replyNo,
					rvo.reply,
					rvo.replyer,
					rvo.replyDate,
				])
				.draw(false);
		},
		function(err) {
               console.log(err);
		}
	)

}

document.querySelector('#addReply').addEventListener('click', addNewRow);

// tr선택 / 선택해제 	

let delNo = 0;
table.on('click', 'tbody tr', (e) => {
	let classList = e.currentTarget.classList //['selecte']
	console.log(e.currentTarget.children[0].innerText);

	if (classList.contains('selected')) {
		classList.remove('selected');
	}
	else {
		table.rows('.selected').nodes().each((row) => row.classList.remove('selected'));
		classList.add('selected');
		delNo = e.currentTarget.children[0].innerText; // 댓글번호
	}
});

document.querySelector('#button').addEventListener('click', function() {
	svc.removeReply(delNo,
		function(result) {
			if (result.retCode == 'OK') {
				// 화면에서 선택된 행 삭제
				table.row('.selected').remove().draw(false);
			} else {
				alert('처리중 오류');
			}
		}, function(err) {
			console.log(err);
		});
});
