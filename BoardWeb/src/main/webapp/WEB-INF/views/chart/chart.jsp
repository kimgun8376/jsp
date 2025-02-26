<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      

        let aryData = []; //[[[[[[]]]]]]
        fetch('chartData.do')
        .then(result = > result.json())
        .then(result = > {
        	aryData.push(['부서명','인원']);
        	result.forEach(item => {
        		aryData.push([item.dept_name, item.dept_count]);
        	});
        	console.log(aryData);
        	google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);
        });
        .catch(err => console.log(err));
      function drawChart() {
       
        var data = google.visualization.arrayToDataTable(aryData);
        var options = {
          title: '부서별 인원 현황'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }
    </script>
  </head>
  <body>
    <div id="piechart" style="width: 900px; height: 500px;"></div>
  </body>
</html>
