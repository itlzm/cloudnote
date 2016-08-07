//封装笔记相关操作
//加载笔记列表信息操作
function loadBookNotes(){
	//设置笔记本li选中效果,选中前去掉所有a的选中状态
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//切换笔记本时清空编辑笔记区域的内容
	$("#input_note_title").val("");
	um.setContent("");
	//获取请求参数
	var bookId = $(this).data("bookId");
	//检查请求参数格式(无需处理)
	//发送ajax请求
	$.ajax({
		url:base_path+"/note/loadnotes.do",
		type:"post",
		data:{"bookId":bookId},
		dataType:"json",
		success:function(result){
			//console.log(result.data);
			//显示笔记列表中的noteTile之前，清空li中的内容
			$("#note_ul li").remove();
			if(result.status==0){
				//获取服务器返回的笔记集合信息
				var notes = result.data;
				for(var i=0;i<notes.length;i++){
					var noteId = notes[i].cn_note_id;
					var noteTitle = notes[i].cn_note_title;
					//console.log(noteId+":"+noteTitle);
					createNoteLi(noteId, noteTitle);
				}
			}
		},
		error:function(){
			alert("笔记列表加载失败");
		}
	});
}
//加载笔记信息操作
function loadNote(){
	//设置笔记选中状态
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取参数,使用jQuery的data函数，提取每个笔记li元素绑定的noteId
	var noteId = $(this).data("noteId");
	//console.log(noteId);
	//检查参数格式 无需处理
	//发送ajax请求
	$.ajax({
		url:base_path + "/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//获取服务器返回result中的data数据
				var note = result.data;
				var noteTitle = note.cn_note_title;
				var noteBody = note.cn_note_body;
				//console.log("noteTitle:"+noteTitle);//笔记title信息
				//console.log("noteBody:"+noteBody);//笔记body信息
				//console.log(um);//浏览器实例化的um编辑器，通过setContent函数，写入笔记body信息
				$("#input_note_title").val(noteTitle);
				um.setContent(noteBody);
			}
		},
		error:function(){
			alert("笔记内容加载异常");
		}
	});
}
//更新Note操作
function updateNote(){
	//获取编辑笔记输入框内容,去掉误输入的前后空格符
	//获取全局变量um的内容
	var noteTitle = $("#input_note_title").val().trim();
	var noteBody = um.getContent();
	//获取要更新笔记的noteId,该值绑定在每个note_ul的li里
	//可以通过li中a标签的选中状态,找到其父元素li,调用jQuery的data函数得到该值
	var $li = $("#note_ul a.checked").parent();
	var noteId = $li.data("noteId");
	console.log(noteTitle+":"+noteBody+":"+noteId);
	//检查参数格式之前，清空之前遗留的提示信息
	$("#note_title_span").html("");
	//检查参数格式
	if($li.length==0){//通过判断$li的长度，确定有无取到noteId值
		//若长度为0，则为选中note_ul的li
		alert("请选择要保存的笔记");
	}else if(noteTitle==""){
		$("#note_title_span").html("<font color='red'>笔记名称不能为空</font>");
	}else{//发送ajax请求
		$.ajax({
			url:base_path + "/note/update.do",
			type:"post",
			data:{"noteTitle":noteTitle,"noteBody":noteBody,"noteId":noteId},
			dataType:"json",
			success:function(result){
				//该result只封装了status和msg
				if(result.status==0){
					alert(result.msg);
					console.log("noteTitle:"+noteTitle+"noteBody:"+noteBody);
					//更新整个note_ul中选中的a标签的内容
					var sli = "";
					sli += '			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle;
					sli += 				'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
					sli += '				<i class="fa fa-chevron-down"></i>';
					sli += '			</button>';
					$("#note_ul a.checked").html(sli);
				}else if(result.status==1){
					alert(result.msg);
				}
			},
			error:function(){
				alert("保存笔记异常");
			}
		});
	}
}
function sureAddNote(){
	//获取参数
	//获取用户的uesrId，该值存储在cookie中
	var userId = getCookie("uid");
	console.log(userId);
	//input_note 输入框笔记名称
	var noteTitle = $("#input_note").val().trim();
	console.log(noteTitle);
	//用户选中笔记本id，即booId，为用户选中checked状态的笔记本
	//该bookId绑定在book_ul中每个li里，
	var $li = $("#book_ul a.checked").parent();
	var bookId = $li.data("bookId");
	//bookId在弹出对话框之前已被检查，若$li.length为0，对话框不会弹出
	console.log($li.length);
	console.log(bookId);
	//检查格式前，清空input_note_span中提示内容
	$("#input_note_span").html("");
	//检查参数格式
	if(userId==null){
		//存储的cookie失效，无法提取cookie中userId值
		window.location.href = "log_in.html";
	}else if(noteTitle==""){
		$("#input_note_span").html("<font color='red'>笔记名称不能为空</font>");
	}else{
		//发送ajax请求
		$.ajax({
			url:base_path + "/note/add.do",
			type:"post",
			data:{"noteTitle":noteTitle,"bookId":bookId,"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					//关闭对话框
					closeAlertWindow();
					//生成笔记列表
					var noteId = result.data;
					createNoteLi(noteId, noteTitle);
					//弹出提示
					alert(result.msg);
				}else if(result.status==1){
					alert(result.msg);
				}
			},
			error:function(){
				alert("笔记创建异常");
			}
		});
	}
}
//构建笔记信息li元素
function createNoteLi(noteId,noteTitle){
	var sli = "";
	sli += '<li class="online">';
	sli += '	<a>';
	sli += '			<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle;
	sli += 				'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down">';
	sli += '				<i class="fa fa-chevron-down"></i>';
	sli += '			</button>';
	sli += '	</a>';
	sli += '	<div class="note_menu" tabindex="-1">';
	sli += '		<dl>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至...">';
	sli += '				<i class="fa fa-random"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享">';
	sli += '				<i class="fa fa-sitemap"></i></button></dt>';
	sli += '			<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除">';
	sli += '				<i class="fa fa-times"></i></button></dt>';
	sli += '		</dl>';
	sli += '	</div>';
	sli += '</li>';
	var $li = $(sli);
	//每个li都带有一个noteId
	$li.data("noteId",noteId);
	$("#note_ul").append($li);
}
