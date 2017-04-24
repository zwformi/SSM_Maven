
$(document).ready(function(){
	var url="/user/showAllUser.do"; 
	var data={}
	$.post(url,data,function(dat){
		var Data = {"userlist":dat};
		$('#div_tbody').html(template("UserList", Data)); 
		
		$('.data-table').dataTable({
			/*		基本参数设置，以下参数设置和默认效果一致 */ 
			        "bPaginate": true, //翻页功能  
			        "bLengthChange": true, //改变每页显示数据数量  
			        "bFilter": true, //过滤功能  
			        "bSort": true, //排序功能  
			        "bInfo": true,//页脚信息  
			        "bAutoWidth": true,//自动宽度  
			        /*默认排序设置 */ 
			        "aaSorting": [[ 2, "desc" ]],//设置第3个元素为默认排序  
			        /*默认翻页样式设置  */
			        "sPaginationType": "full_numbers",  
			        /*是否开启主题 */ 
			        "bJQueryUI": true, 
			        /*表格dom构造*/
			        "sDom": '<"top"l>rt<"bottom"fip><"clear">',
			        /*语言设置 */ 
			        "oLanguage": {  
			            "sLengthMenu": "每页显示 _MENU_条",  
			            "sZeroRecords": "没有找到符合条件的数据",  
			            "sProcessing": "<img src=’./loading.gif’ />",  
			            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",  
			            "sInfoEmpty": "木有记录",  
			            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",  
			            "sSearch": "搜索：",  
			            "oPaginate": {  
			                "sFirst": "首页",  
			                "sPrevious": "前一页",  
			                "sNext": "后一页",  
			                "sLast": "尾页"  
			            }  
			        }  
					
				});
	})

	$(".exportExl").on("click",function(){
		var form = $("<form>");
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","get");
		form.attr("action","/user/dowmLoadExl.do");
		var input1 = $("<input>");
		input1.attr("type","hidden");
		input1.attr("name","strZipPath");
		input1.attr("value","zzzw");
		$("body").append(form);
		form.append(input1);
		form.submit();
		form.remove();
	})
	
	$('input[type=checkbox],input[type=radio],input[type=file]').uniform();
	
	$('select').select2();
	
	$("span.icon input:checkbox, th input:checkbox").click(function() {
		var checkedStatus = this.checked;
		var checkbox = $(this).parents('.widget-box').find('tr td:first-child input:checkbox');		
		checkbox.each(function() {
			this.checked = checkedStatus;
			if (checkedStatus == this.checked) {
				$(this).closest('.checker > span').removeClass('checked');
			}
			if (this.checked) {
				$(this).closest('.checker > span').addClass('checked');
			}
		});
	});	
});
