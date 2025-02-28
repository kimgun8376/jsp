/**
 * full.js 
 */
document.addEventListener('DOMContentLoaded', function() {
   
	let eventAll= [];
	fetch('fullData.do')
	.then(result => result.json())
	.then(result => {
		console.log(result);
	})
.catch(err => console.log(err));
	
	var calendarEl = document.getElementById('calendar');
   
   var calendar = new FullCalendar.Calendar(calendarEl, {
     headerToolbar: {
       left: 'prev,next today',
       center: 'title',
       right: 'dayGridMonth,timeGridWeek,timeGridDay'
     },
     initialDate: '2023-01-12',
     navLinks: true, // can click day/week names to navigate views
     selectable: true,
     selectMirror: true,
     select: function(arg) {
       var title = prompt('Event Title:');
       if (title) {
         calendar.addEvent({
           title: title,
           start: arg.start,
           end: arg.end,
           allDay: arg.allDay
         })
       }
       calendar.unselect()
     },
     eventClick: function(arg) {
       if (confirm('Are you sure you want to delete this event?')) {
         arg.event.remove()
       }
     },
     editable: true,
     dayMaxEvents: true, // allow "more" link when too many events
     events: eventAll 
      
	 })
   calendar.render();
   });



