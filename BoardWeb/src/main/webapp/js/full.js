/**
 * full.js
 */

document.addEventListener('DOMContentLoaded', function() {
	// event에 사용할 데이터.
	let eventAll = [];

	// Ajax 호출.
	fetch('fullData.do')
		.then(result => result.json())
		.then(result => {
			console.log(result);
			eventAll = result; // 결과를 eventAll에 저장.
			fullCalendarFunc();
		})
		.catch(err => console.log(err));

	// fullCalendar 호출.
	function fullCalendarFunc() {
		var calendarEl = document.getElementById('calendar');
		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			initialDate: new Date(),
			navLinks: true, // can click day/week names to navigate views
			selectable: true,
			selectMirror: true,
			select: function(arg) {
				var title = prompt('Event Title:');
				console.log(title, arg.startStr, arg.endStr);
				// Ajax 출력.
				fetch('addData.do?title=' + title + '&start=' + arg.startStr + '&end=' + arg.endStr)
					.then(result => result.json())
					.then(result => {
						if (result.retCode == "OK") {
							// 화면출력.
							if (title) {
								calendar.addEvent({
									title: title,
									start: arg.start,
									end: arg.end,
									allDay: arg.allDay
								})
							}
							calendar.unselect(); // 화면출력.
						} else {
							alert('처리오류!');
						}
					})

			},
			eventClick: function(arg) {
				console.log(arg);
				// Ajax 호출 -> 컨트롤 -> 삭제 -> 화면삭제.
				if (confirm('이벤트를 삭제하겠습니까?')) {
					fetch('removeData.do?title='+arg.event.title+'&start='+arg.event.start+'&end='+arg.event.end)
					.then(result => result.json())
					.then(result => {
						if(result.retCode == 'OK') {
							arg.event.remove() // 화면 event 삭제
						} else {
							alert('삭제중 예외.');
						}
						
					})
                       .catch(err=> console.log(err));
				}
			},
			editable: true,
			dayMaxEvents: true, // allow "more" link when too many events
			events: eventAll
		});
		calendar.render();
	}


});

