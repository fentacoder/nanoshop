  var cart = [];
  
  

  if (sessionStorage.getItem("shoppingCart") != null) {
    cart = JSON.parse(sessionStorage.getItem('shoppingCart'));
  }
  
   function Item(id, name, price, count) {
	this.id=id;
    this.name = name;
    this.price = price;
    this.count = count;
  }
  
  var totalp=0; 
  var totalc=0;

  
  var output = "<h4>Cart <span class='price' style='color:black'><i class='fa fa-shopping-cart'></i> <b></b></span></h4>";
  for(var i in cart) {
	  var pay=cart[i].price*cart[i].count;
	   totalc += cart[i].count;
    output +="<p><a data-id="+cart[i].id+" href='#'>"+cart[i].name+"("+cart[i].count+")"+"</a> <span class='price'>$"+pay+"</span></p>";
	  totalp+=pay;
  }
  output+="<hr><p>Total <span class='price' style='color:black'><b>$"+totalp+"</b></span></p>";
  $('.cart-primary').html(output);
  $('.fa-shopping-cart').html("<b>"+totalc+"</b>");

  
document.getElementById("price").value=totalp;


 
  