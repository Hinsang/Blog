let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{ // ()=>{} this를 바인딩하기 위해서!!
			this.save();
		});
		$("#btn-update").on("click", ()=>{ // ()=>{} this를 바인딩하기 위해서!!
			this.update();
		});
	},
	
	save: function() {
		
		let data = {
			username: $("#username").val(), // document.querySelector("#username");
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		// console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터 타입(MIME)
			// dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 게 json이라면) => javascript오브젝트로 변경 (AJAX 패치로 생략 가능)
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			// console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // Ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 Insert 요청
		
	},
	
	update: function() {
		
		let data = {
			id: $("#id").val(), // document.querySelector("#username");
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=utf-8", // body 데이터 타입(MIME)
			// dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴 게 json이라면) => javascript오브젝트로 변경 (AJAX 패치로 생략 가능)
		}).done(function(resp){
			alert("회원수정이 완료되었습니다.");
			// console.log(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // Ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 Insert 요청
		
	}
	
}

index.init();
