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
//关闭对话框操作，对所有对话框有效
function closeAlertWindow(){
	//关闭操作
	$("#can").empty();//清空对话框
	$(".opacity_bg").hide();//隐藏背景div
}