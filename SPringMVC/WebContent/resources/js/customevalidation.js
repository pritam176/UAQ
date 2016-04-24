$().ready(
		function() {
			// validate the comment form when it is submitted
			$("#requestForm").validate({
				rules : {
					prouducttype : "required",
					prouductSubtype : "required",
					name : "required",
					"address.adress1" : "required",
					
					mobileno : {
						required : true,
						number : true,
						minlength : 10,
						maxlength : 10
					},
					email : {
						required : true,
						email : true
					}

				},
				messages : {
					prouducttype : "Please select Producttype.",
					prouductSubtype : "Please select Producttype.",
					name : "Please enter your name",
					"address.adress1" : "Please enter your Address",
					mobileno : {
						required : "Please enter your mobileno",
						number : "Provide No only",
						minlength : "10 digit",
						maxlength : "10 digit"
					},
					
					email : {
						required : "Please enter your email",
						email : "Please provide valid email ID"
					}

				}
			});

			$("#prouducttype").change(
					function() {
						// alert( ctx+$(this).val() );
						var key = $(this).val();
						$.ajax({
							type : 'POST',
							url : ctx + "/getSubType.html?key=" + key,

							success : function(data) {
								// alert(data);
								$("#prouductSubtype").find('option').remove();
								$('#prouductSubtype').append(
										$("<option></option>").attr(
												"value", "").text(
												"Please Select"));
								if (data) {
									var datamap = $.parseJSON(data);
									// $("#prouductSubtype ").remove();
									$.each(datamap, function(key, value) {
										//console.log(key + ": " + value);
										$('#prouductSubtype').append(
												$("<option></option>").attr(
														"value", key).text(
														value));
									});

								}
							}
						});

					});

		});