function mostrarPassword(){
		var cambioNueva = document.getElementById("txtNewPassword");
		var cambioRNueva = document.getElementById("txtRNewPassword");
		var cambioVieja = document.getElementById("txtLastPassword");
		if(cambioNueva.type == "password"){
			cambioNueva.type = "text";
			$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			cambioNueva.type = "password";
			$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
		if(cambioVieja.type == "password"){
			cambioVieja.type = "text";
			$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			cambioVieja.type = "password";
			$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
		if(cambioRNueva.type == "password"){
			cambioRNueva.type = "text";
			$('.icon').removeClass('fa fa-eye-slash').addClass('fa fa-eye');
		}else{
			cambioRNueva.type = "password";
			$('.icon').removeClass('fa fa-eye').addClass('fa fa-eye-slash');
		}
	} 
	
	
	$(document).ready(function () {
	//CheckBox mostrar contraseña
	$('#ShowPassword').click(function () {
		$('#Password').attr('type', $(this).is(':checked') ? 'text' : 'password');
	});
});