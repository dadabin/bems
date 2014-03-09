// Calendar

$(document).ready(function() {
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		var calendar = $('#Shortcuts_Calendar_content').fullCalendar({
			header: {
				left: 'title',
				right: 'prev,next, today, month,agendaWeek,agendaDay'
			},
			selectable: true,
			selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('为事件添加标题:');
				
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				
//				alert("个人安排添加成功！"+start+"\n===\n"+end+"\n==\n"+title);
				
				calendar.fullCalendar('unselect');
			},
			editable: true,
			events: [
				{
					title: '晚间会议',
					start: new Date(y, m, 8)
				},
				{
					title: '维修二楼水表',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2)
				},
				{
					title: '紧急通知',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: '#'
				}
			]
	});
});
