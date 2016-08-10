/*
 * book.js封装加载笔记本列表
 * */
//加载用户笔记本列表
function loadUserBooks(){
		//获取Cookie中的uid信息,即获取请求参数
			var userId = getCookie("uid");
		//检查格式,若没有取到userId，可能cookie失效，重定向到登录页面
		if(userId==null){
			window.location.href = "log_in.html";
		}else{//发送ajax请求
			$.ajax({
				url: base_path + "/book/loadbooks.do",
				type:"post",
				data:{"userId":userId},
				dataType:"json",
				success:function(result){
					
					if(result.status==0){
						//返回笔记本集合
						var books = result.data;
						//循环生成列表元素
						for(var i=0;i<books.length;i++){
							var bookId = books[i].cn_notebook_id;
							var bookName = books[i].cn_notebook_name; 
							creatBookLi(bookId, bookName);
						}
					}else if(result.status==1){
						alertAddBookWindow();
					}
				},
				error:function(){
					alert("加载笔记本失败");
				}
			});
		}
}
//添加book处理
function sureAddBook(){
	//获取参数,用户的uid存在cookie中,用户输入的笔记本名称id=input_notebook
	var userId = getCookie("uid");
	var bookName = $("#input_notebook").val().trim();
	//console.log(userId+":"+bookName);
	console.log(bookName);
	//检查参数格式前清空input_notebook_span中的提示信息
	$("#input_notebook_span").html("");
	//检查参数格式
	if(userId==null){
		//cookie超过设置的2小时，失效处理，重定向到登录页面
		window.location.href = "log_in.html";
	}else if(bookName==""){
		$("#input_notebook_span").html("<font color='red'>笔记本名称不能为空</font>")
	}else{
		//发送ajax请求
		$.ajax({
			url:base_path + "/book/add.do",
			type:"post",
			data:{"userId":userId,"bookName":bookName},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//关闭对话框
					closeAlertWindow();
					//笔记本创建成功处理，弹出对话框，向book_ul拼一个li，
					//也可调用loadUserBooks函数，但此方式需要同数据库交互
					alert(result.msg);
					var bookId = result.data.cn_notebook_id;
					//调用creatBookLi函数,拼一个li元素
					creatBookLi(bookId, bookName);
				}else if(result.status==1){
					alert(result.msg);
				}
			},
			error:function(){
				alert("创建笔记本异常");
			}
		});
	}
}
//笔记本重命名
function renameBook(){
	//获取参数
	var newName = $("#input_notebook_rename").val().trim();
	var userId = getCookie("uid");
	var bookId = $("#book_ul a.checked").parent().data("bookId");
	console.log(newName+",:"+userId+",:"+bookId);
	//检查参数格式
	if(userId==null){
		window.location.href = "log_in.html";
	}else if(newName==""){
		$("#input_notebook_rename_span").html("<font color='red'>名称不能为空</font>");
	}else{
		//发送ajax请求
		$.ajax({
			url:base_path + "/book/rename.do",
			type:"post",
			data:{"newName":newName,"bookId":bookId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					closeAlertWindow();
					var a = '<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+newName;
					$("#book_ul a.checked").html(a);
					alert(result.msg);
				}else if(result.status==1){
					alert(result.msg);
				}
			},
			error:function(){
				alert("笔记本重命名异常");
			}
		});
	}
}
//构建笔记本li函数
function creatBookLi(bookId,bookName){
	//构建列表li元素
	var sli = "";
	sli += '<li class="online">';
	sli += '	<a>';
	sli += '		<i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+bookName;
	sli += '	</a>';
	sli += '</li>';
	//将bookId绑定当li元素上,class="checked",选中状态
	//利用jQuery的data函数，可以将其绑定到li元素上，绑定前需要将li转换为jQuery对象
	var $li = $(sli);
	$li.data("bookId",bookId);
	//将li元素添加到ul列表上
	$("#book_ul").append($li);
}
//删除无笔记信息的笔记本
function deleteBook(){
	//获取笔记本的bookId
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	$.ajax({
		url:base_path + "/book/delete.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert(result.msg);
				$li.remove();
			}
		},
		error:function(){
			alert("删除笔记本异常");
		}
	});
}