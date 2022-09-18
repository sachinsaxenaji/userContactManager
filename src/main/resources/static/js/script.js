const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});


// This is for seach functionality

function search() {

	let query = document.getElementById("input-search").value;
	let rs = document.getElementById("result-search");

	

	if (query == "") {
		// $("#result-search").hide;
		rs.style.display = "none";

	}
	else
	{
		let url = `http://localhost:8081/search/${query}`;

		fetch(url).then((responce)=>{
			return responce.json();	
		}).then((data)=>{
			console.log(data);

			let text = `<div class='list-group'>`;

			data.forEach((contact) => {
				text += `<a href = '/user/${contact.cId}/contact' class = 'list-group-item list-group-item-action'>${contact.name} </a>`
			});

			text += `</div>`;
			document.getElementById("result-search").innerHTML = text;
			// $("#result-search").html(text);
			// $("#result-search").show;
			rs.style.display = "block";
		});
		

		
	}
  }
console.log("THis is Payment JS");
  function PaymentStart() {

  	console.log("PaymentStart");

    var amount = document.getElementById("payment_feild").value;

  	// var amount = $("#payment_feild").val();

  	console.log(amount);
  	 if(amount == "" || amount == null)
  	 {
  	 	alert("amount is required");
  	 	return;
  	 }

  	 // we will user ajex to send request to server to create order - jquery
  	 $.ajax(
  	 {
  	 	url: "/create_order",
  	 	data:JSON.stringify({amount:amount, info:'order_request'}),
  	 	contentType:'application/json',
  	 	type:'POST',
  	 	dataType:'json',
  	 	success: function(response){
  	 		console.log(response);
        if(response.status == "created")
        {
          let options = {
          key: "rzp_test_TUshSQF35AENnm", // Enter the Key ID generated from the Dashboard
          amount: response.amount, // Amount is in currency subunits. Default currency is INR. Hence, 50000 refers to 50000 paise
          currency: "INR",
          name: "Saxena corp",
          description: "Test Transaction",
          image: "https://example.com/your_logo",
          order_id: response.order_id, //This is a sample Order ID. Pass the `id` obtained in the response of Step 1
          "handler": function (response){
              alert(response.razorpay_payment_id);
              alert(response.razorpay_order_id);
              alert(response.razorpay_signature)
              console.log("order success")

              updatePaymentOnServer(response.razorpay_payment_id,response.razorpay_order_id,"pain");

             
          },
          prefill: {
              name: "",
              email: "",
              contact: ""
          },
          notes: {
              address: "Subhash Nagar Office"
          },
          theme: {
              color: "#3399cc"
          },
          };

          var rzp1 = new Razorpay(options);
          rzp1.on('payment.failed', function (response){
        alert(response.error.code);
        alert(response.error.description);
        alert(response.error.source);
        alert(response.error.step);
        alert(response.error.reason);
        alert(response.error.metadata.order_id);
        alert(response.error.metadata.payment_id);
        });
          rzp1.open();
        }
  	 	},
  	 	error:function(error){
  	 		console.log(error)
  	 		alert("something went wrong!!")
  	 	}

  	 }
  	 	)
  };
  function updatePaymentOnServer(payment_id,order_id,status){
     $.ajax(
     {
      url: "/update_order",
      data:JSON.stringify({payment_id:payment_id, order_id:order_id, status:status}),
      contentType:'application/json',
      type:'POST',
      dataType:'json',
      success:function(response){
        swal("Payment success !!");
      },
      error:function(error){
        swal("Payment error !!");
      }


    });


  }




  // var x = document.getElementById("input-search").value;
  // document.getElementById("result-search").innerHTML = "results: " + x;

// $(document).ready(function(){
// const search = ()=> {

// 	let query = $("#input-search").val;

// 	if (query == "") {
// 		$("#result-search").hide;

// 	}
// 	else
// 	{

// 		let url = 'http://localhost:9090/search/{query}'

// 		fatch(url).then((responce)=>{
// 			return responce.json();
// 		}).then((data)=>{
// 			console.log(data);
// 		})

// 		$("#result-search").show;
// 	}
// };
// });