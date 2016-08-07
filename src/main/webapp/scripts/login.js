/*
 * 封装登录和注册处理,异步检查用户名是否重复
 */
$(function(){//页面载入完毕
	//给登录按钮绑定单击事件
	//checkLogin() 调用
	//checkLogin   绑定
	$("#login").click(checkLogin);
	//给注册按钮绑定单击处理
	$("#regist_button").click(checkRegist);
	//为用户名输入框绑定失去焦点事件，实现异步检查用户名是否重复
	$("#regist_username").blur(checkUserName);
});
//登录处理
function checkLogin(){
	//alert("-------");//测试单击事件是否触发
	//获取请求参数，
	//trim(),jQuery函数，去除输入框内容前后空格
	var name = $("#count").val().trim();
	var password = $("#password").val().trim();
	console.log(name+":"+password);
	//检测请求参数，
	//检测前，清空之前的提示信息
	$("#count_span").html("");
	$("#password_span").html("");
	var ok = true;//检测是否通过的开关
	if(name==""){
		ok = false;
		$("#count_span").html("用户名为空");
	}
	if(password==""){
		ok = false;
		$("#password_span").html("密码为空");
	}
	//发送Ajax请求
	if(ok){
		$.ajax({
			//url:"user/login.do",//相对路径
			url:base_path + "/user/login.do",
			type:"post",
			data:{"name":name,"password":password},
			dataType:"json",
			success:function(result){
				//result为服务器返回的JSON结果，自动转换为了JS对象
				if(result.status==0){//成功
					var user = result.data;//获取返回的user信息
					//写入Cookie,指定Cookie的名字，内容，有效时间(单位小时)
					addCookie("uid",user.cn_user_id,2);
					addCookie("uname",user.cn_user_name,2);
					window.location.href="edit.html";
				}else if(result.status==1){
					$("#count_span").html(result.msg);
				}else if(result.status==2){
					$("#password_span").html(result.msg);
				}
			},
			error:function(){
				alert("登录异常");
			}
		});
	}
}
//注册处理
function checkRegist(){
	//获取请求参数
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var f_password = $("#final_password").val().trim();
	console.log(name+":"+nick+":"+password+":"+f_password);
	//检查格式前将用户名后span中提示信息清空
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	var ok = true;
	//检查格式
	if(name==""){
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户名为空");
	}
	if(password==""){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码为空");
	}else if(password.length<6){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("密码长度过短");
	}
	if(f_password==""){
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("密码为空");
	}else if(f_password!=password){
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("与密码不一致");
	}
	//发送Ajax请求
	if(ok){
		$.ajax({
			url:base_path + "/user/add.do",
			type:"post",
			data:{"name":name,"nick":nick,"password":password},
			dataType:"json",
			success:function(result){
				//status为0，表示注册成功,返回到登录页面
				if(result.status==0){
					//window.location.href="log_in.html";
					//在当前页面返回到登录界面，无需刷新页面，发送请求
					alert(result.msg);
					$("#back").click();
				}else if(result.status==1){
					//status为1，表示用户名被占用，显示提示信息
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error:function(){
				alert("注册异常");
			}
		});
	}
}
//检查用户名是否重复
function checkUserName(){
	//获取输入框中数据,注：删掉用户开头或结尾误输入的空格符
	var name = $("#regist_username").val().trim();
	//检查数据格式
	//检查前清空提示信息
	$("#warning_1 span").html("");
	var ok = true;
	if(name==""){
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("用户名为空");
	}
	//发送Ajax请求
	if(ok){
		$.ajax({
			url:base_path + "/user/check.do",
			type:"post",
			data:{"name":name},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					$("#warning_1").hide();
				}else if(result.status==1){
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error:function(){
				alert("注册异常");
			}
		});
	}
}