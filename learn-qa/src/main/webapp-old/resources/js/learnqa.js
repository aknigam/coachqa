

function handleaddAnswerFormSubmit()
{
	var element = $("#wysiwygTextarea_ifr").contents().find("#editor").clone();
	element.find("script").each(function(index){
		var s;
		if($(this).prev().attr("class") ==  "MathJax_Display") {
			s= "$$"+ $(this).html()+"$$";
			
		} else {
			var s= "$"+ $(this).html()+"$";
		}
		$(this).replaceWith(s);
	});
	
	// handle MathJax_Preview elements
	element.find(".MathJax_Preview").remove();
	element.find(".MathJax_Display").remove();
	element.find(".MathJax").remove();
	$("#wysiwygTextarea").html(element.html()); // this line is as good as the above statement.
	// alert($("#wysiwygTextarea").html());
  /*
	Inline is show as below:
		------------------------
			span.MathJax_Preview
			span.MathJax_Element-<n>-Frame
			script.MathJax_Element-<n>
			
		Newline math expressions are show as below:
		-------------------------------------------
			span.MathJax_Preview
			div.MathJax_Display
				span.MathJax-Element-<n>-Frame #MathJax
			script.MathJax-Element-<n>
	*/		
}

function bold() {
	var x = document.getElementById("wysiwygTextarea_ifr");
    var y = (x.contentWindow || x.contentDocument);
    if (y.document)y = y.document;
    
    // y.body.style.backgroundColor = "red";
    
	var strng = y.createElement("strong")
	var node = y.createTextNode("This is new.");
	strng.appendChild(node);
	var element = y.getElementById("editor");
	element.appendChild(strng);
}

function insertMath()
{

	document.getElementById('light').style.display='none';
	document.getElementById('fade').style.display='none';
	
	// source 
	var mathTextToBeInserted = document.getElementById("texteditor").value;
	
	
	var x = document.getElementById("wysiwygTextarea_ifr");
    var y = (x.contentWindow || x.contentDocument);
    if (y.document)y = y.document;
    
    // div element is created in the destination
    
	var insertMathdiv = y.createElement("div")
	// content = document.getElementById("previewPara").innerHTML;
	insertMathdiv.innerHTML = mathTextToBeInserted;
	var element = y.getElementById("editor");
	element.appendChild(insertMathdiv);
	
	document.getElementById('wysiwygTextarea_ifr').contentWindow.refreshMath();
	
}

function textAreaOnInput()
{
	
	var content = document.getElementById("texteditor").value;
	
	document.getElementById("previewPara").innerHTML = content;
	
	MathJax.Hub.Queue(["Typeset",MathJax.Hub]);	
	
}






/*
 * Old javascript functions
 */


	function handleDetailedReportdata(data)
	{
		
		
		$( "#rightpanel" ).empty();
        var areaReport =data.assessmentAreaReports;
		
		$.each( areaReport, function( key, val ) {
    		var questionReports = val.questionReports;
    		
    		$.each( questionReports, function( key, val ) {
    		
    			var gdata = new google.visualization.DataTable();
    			
        		gdata.addColumn('string', 'Reviewer');
        		gdata.addColumn('number', 'Rating');
        		
        		$( "#rightpanel" ).append( "<div id="+"qc"+val.questionId+" class=grid_4></div>" );
        		
    			$( "#rightpanel" ).append( "<div id="+"q"+val.questionId+">test</div>" );
    			var rank = val.rank;
    			gdata.addRow(['Self', rank.selfRating]);
    			gdata.addRow(['Manager', rank.managerRating]);
    			gdata.addRow(['Peer', rank.peerRating]);
    			gdata.addRow(['Direct reports', rank.directReportRating]);
    			gdata.addRow(['Indirect reports', rank.indirectReportRating]);
    			gdata.addRow(['Others', rank.otherRating]);
    			// alert(rank);
    			
    					// Set chart options
        		var options = {'title':val.question,
                       'width':500,
                       'height':200,
                       bar: {
    						groupWidth: '100%'
						}};

        		// Instantiate and draw our chart, passing in some options.
        		var chart = new google.visualization.BarChart(document.getElementById('q'+val.questionId));
        		chart.draw(gdata, options);
    		});
  		});


	
	}
	
	

	function handleSummaryReportdata(data)
	{
		
		var areaReport =data.assessmentAreaReports;
		
		$.each( areaReport, function( key, val ) {
    		var questionReports = val.questionReports;
    		$.each( questionReports, function( key, val ) {
    			var rank = val.rank;
    		});
  		});
		
		var gdata = new google.visualization.DataTable();
        gdata.addColumn('string', 'Question');
        gdata.addColumn('number', 'Rating');
        
		for (var i = 0; i < data.charData.length; i++) {
    		var object = data.charData[i];
    		gdata.addRow(object);
    		
		}
		
        // data.addRows(data.charData)

		// Set chart options
        var options = {'title':'Others ratings',
                       'width':400,
                       'height':300};
        
        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.BarChart(document.getElementById('rightpanel'));
        chart.draw(gdata, options);
	
	}
	

	function handleFrequencyReportdata(data)
	{
		$( "#rightpanel" ).empty();
		for (var i = 0; i < data.others.length; i++) {
    		var object = data.others[i].mcFrequency;
    		var question = data.others[i].question;
    		var questionId = data.others[i].questionId;
    		var x=  i+1;
    		var id = 'd'+x;
    		addFrequencyChart(id,question,questionId, object);
		}
		
      
	}
	
	function addFrequencyChart(id,question,questionId,data)
	{
		$( "#rightpanel" ).append( "<div id="+"qfc"+questionId+">test</div>" );
		var gdata = new google.visualization.DataTable();
		gdata.addColumn('string', 'Choice');
        gdata.addColumn('number', 'Frequency');
        
        gdata.addRows(data);
        
        var options = {'title':question,
                       'width':600,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        // var chart = new google.visualization.PieChart(document.getElementById(id));
        var chart = new google.visualization.PieChart(document.getElementById('qfc'+questionId));
        
        
        chart.draw(gdata, options);
	}
	
	

	
	function handleSearcResults(data)
	{
		var html = '';
		
		html += '<table >';
		html += '		  <tr>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >S. No</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewer name</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewer designation</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewer email</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewee name</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewee designation</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Reviewee email</td>';
		html += '		  	<td style="background-color: rgb(195, 195, 195); font-weight:bold" >Status </td>';
		html += '		  </tr>';
		
		var rownum = 0;
         $.each(data.data, function(entryIndex, entry) {
           
            html += '<tr>';
            html += '<td style="background-color: rgb(153, 215, 234)" >'+ rownum+'</td>';
   			html += '<td style="background-color: rgb(153, 215, 234)" >'+ entry.reviewerName+'</td>';
	        html += '<td style="background-color: rgb(153, 215, 234)" >'+entry.reviewerDesignation+'</td>';
	        html += '<td style="background-color: rgb(153, 215, 234)" >'+entry.reviewerEmail+'</td>';
	   		
	   		html += '<td style="background-color: rgb(239, 228, 176)" >'+entry.revieweeName+'</td>';
	        html += '<td style="background-color: rgb(239, 228, 176)" >'+entry.revieweeDesignation+'</td>';
	        html += '<td style="background-color: rgb(239, 228, 176)" ><a href="/assess/UserFeedback/questions?&feedbackId='+entry.customerReviewerId+'">'+entry.revieweeEmail+'</a></td>';
	        html += '<td style="background-color: rgb(239, 228, 176)" >'+entry.feedbackStatus+'<td>';
	        html += '</tr>';
	        
	        rownum = rownum +  1;
           
		});
		
		html += '</table>'
		
		
		
		$('#tablerows').html(html);
	
	}