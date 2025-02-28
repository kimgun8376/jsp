/**
 * full.js 
 */
document.addEventListener('DOMContentLoaded', function() {

	let eventAll = [];
	fetch('fullData.do')
		.then(result => result.json())
		.then(result => {
			console.log(result);
			eventAll = result;  // Store the fetched events in the eventAll array
			fullCalendarFunc();  // Initialize the FullCalendar after data is fetched
		})
		.catch(err => console.log(err));  // Handle any errors

	// Initialize FullCalendar
	function fullCalendarFunc() {
		var calendarEl = document.getElementById('calendar');

		var calendar = new FullCalendar.Calendar(calendarEl, {
			headerToolbar: {
				left: 'prev,next today',
				center: 'title',
				right: 'dayGridMonth,timeGridWeek,timeGridDay'
			},
			initialDate: '2023-01-12',
			navLinks: true, // Can click day/week names to navigate views
			selectable: true,
			selectMirror: true,
			select: function(arg) {
				var title = prompt('Event Title:');
				console.log(title, arg.startStr, arg.endStr);

				// Add event to the calendar
				if (title) {
					calendar.addEvent({
						title: title,
						start: arg.start,
						end: arg.end,
						allDay: arg.allDay
					});
				}
				calendar.unselect(); // Unselect the date
			},
			eventClick: function(arg) {
				if (confirm('Are you sure you want to delete this event?')) {
					arg.event.remove(); // Remove event on click
				}
			},
			editable: true,
			dayMaxEvents: true, // Allow "more" link when too many events
			events: eventAll // Pass the fetched events here
		});

		calendar.render();  // Render the calendar
	}

});