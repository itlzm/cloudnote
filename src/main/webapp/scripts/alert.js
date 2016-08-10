//封装对话框处理
//弹出创建笔记本对话框
function alertAddBookWindow(){
	//弹出对话框,通过load函数，将html加载到指定div中
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
}
//弹出重命名笔记本对话框
function alertRenameBookWindow(){
	$("#can").load("alert/alert_rename.html");
	$(".opacity_bg").show();
}
//弹出删除笔记本对话框
function alertDeleteBookWindow(){
	$("#can").load("alert/alert_delete_notebook.html");
	$(".opacity_bg").show();
}
//弹出创建笔记对话框
function alertAddNoteWindow(){
	//必须选中笔记本才能弹出对话框
	var $a = $("#book_ul a.checked");
	if($a.length==0){
		alert("请选择笔记本");
	}else{
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
}
//弹出删除笔记对话框
function alertDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
}
//弹出确认删除笔记对话框，彻底删除笔记
function alertSureDeleteNoteWindow(){
	//为回收站选中的笔记设置选中状态
	var $a = $(this).parent();
	//选中之前清除回收站中所有笔记的选中状态
	$("#delete_note_ul a").removeClass("checked");
	$a.addClass("checked");
	$("#can").load("alert/alert_delete_rollback.html");
	$(".opacity_bg").show();
}
//弹出还原回收站笔记对话框
function alertRepalyNoteWindow(){
	//为回收站选中的笔记设置选中状态
	var $a = $(this).parent();
	//选中之前清除回收站中所有笔记的选中状态
	$("#delete_note_ul a").removeClass("checked");
	$a.addClass("checked");
	$("#can").load("alert/alert_replay.html",function(){
		var lis = $("#book_ul li");
		for(var i=0;i<lis.length;i++){
			var li = lis[i];
			var bookId = $(li).data("bookId");
			//console.log(bookId);
			var bookTitle = $(li).find("a").text().trim();
			//console.log(bookTitle);
			var option = '<option value="'+bookId+'">'+bookTitle+'</option>';
			//直接将bookId设置到option的value中
			$("#replaySelect").append(option);
		}
	});
	$(".opacity_bg").show();
}
//弹出转移笔记对话框
function alertMoveNoteWindow(){
	//获取book_ul中的li，遍历得到每个li绑定的bookId，
	//和li的子元素a的text内容，即bookTitle
	//在alert_move.html载入完成之后，将bookId和bookTitle写入到下拉选中
	$("#can").load("alert/alert_move.html",function(){
		var lis = $("#book_ul li");
		for(var i=0;i<lis.length;i++){
			var li = lis[i];
			var bookId = $(li).data("bookId");
			//console.log(bookId);
			var bookTitle = $(li).find("a").text().trim();
			//console.log(bookTitle);
			var option = '<option value="'+bookId+'">'+bookTitle+'</option>';
			//直接将bookId设置到option的value中
			$("#moveSelect").append(option);
		}
	});
	$(".opacity_bg").show();
}
//弹出收藏笔记对话框
function alertLikeNoteWindow(){
	$("#can").load("alert/alert_like.html");
	$(".opacity_bg").show();
}
//关闭对话框操作，对所有对话框有效
function closeAlertWindow(){
	//关闭操作
	$("#can").empty();//清空对话框
	$(".opacity_bg").hide();//隐藏背景div
}