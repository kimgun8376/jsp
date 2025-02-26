/**
 * replyService.js 
 */
const svc = {
	name: "Hong",
	showName: function() {
		return this.name;
	},
	// 목록메소드
	replyList: function(param = { bno: boardno, page: pagecno }, successCallback, errorCallback) {
		fetch('replyList.do?bno=' + param.bno + '&page=' + param.page)
			.then(result => result.json()) // 화살표 함수.
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.
	},

	// 등록메소드.
	addReply(param = { bno, reply, replyer }, successCallback, errorCallback) {
		fetch('addReply.do?bno=' + param.bno + '&reply=' + param.reply + '&replyer=' + param.bno)
			.then(result => result.json()) // 화살표 함수.	
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.
	},
	// 삭제메소드.
	removeReply(rno = 1, successCallback, errorCallback) {
		fetch('replyList.do?bno=' + rno)
			.then(result => result.json()) // 화살표 함수.
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.
	},

	// 페이징 계산.
	makePaging(bno = 1, successCallback, errorCallback) {
		fetch('getReplyCnt.do?bno=' + bno)
			.then(result => result.json()) // 화살표 함수.
			.then(successCallback) // 정상처리시 실행함수.
			.catch(errorCallback) // 에러시 실행할 함수.
	}

}
