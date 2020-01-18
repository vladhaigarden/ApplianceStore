function increaseCart(el) {
    var indicator = el.parentNode.getElementsByTagName("input")[0]
    indicator.value++;
    var selectedProductId = el.value;
    increaseProductToCart(selectedProductId);
}

function decreaseCart(el) {
    var indicator = el.parentNode.getElementsByTagName("input")[0]
    if(indicator.value>0){
        indicator.value--;
        var selectedProductId = el.value;
        decreaseProductFromCart(selectedProductId,indicator);
    }
}

    function deleteProduct(el) {
        var indicator = el.parentNode.getElementsByTagName("input")[0]
        var selectedProductId = el.value;
        deleteProductFromCart(selectedProductId,indicator);
        
}

function addProduct(el) {
    var selectedProductId = el.value;
    addProductToCart(selectedProductId);
}

function clearCart(el) {
    $.ajax({
        url: 'clearCart',
        type: 'POST',
        data: {
            clear: true
        }
        ,
        success: function () {
            $(".tableProducts").remove();
            $(".bottom_button").remove();
            document.getElementById("sizeCart").innerHTML=0;
            document.getElementById("total").innerHTML = 0+"$";
        }
    });
    };



function addProductToCart(selectedProductId) {
$.ajax({
    url: 'cart',
    type: 'PUT',
    data: {
        productId: selectedProductId
        
    }
    ,
    success: function (data) {
        var amountProduct = document.getElementById('sizeCart').innerText;
        document.getElementById("sizeCart").innerHTML=++amountProduct;
        $('#myModal').modal();
    }
});
};


function increaseProductToCart(selectedProductId) {
    $.ajax({
        url: 'cart',
        type: 'PUT',
        data: {
            productId: selectedProductId
            
        }
        ,
        success: function (data) {
            var currentPrice = parseFloat($('button.reduced.items-count[value*="'+selectedProductId+'"]').closest('tr').find('input#priceProduct').val());
            var totalPrice = parseFloat(document.getElementById("valueTotal").value);
            var newTotalPrice = (totalPrice+currentPrice).toFixed(2);

            document.getElementById('valueTotal').value=newTotalPrice;
            document.getElementById("total").innerHTML = newTotalPrice+"$";
            var amountProduct = document.getElementById('sizeCart').innerText;
            document.getElementById("sizeCart").innerHTML=++amountProduct;
        }
    });
    };

function decreaseProductFromCart(selectedProductId,indicator) {
    $.ajax({
        url: 'cart',
        type: 'DELETE',
        data: {
            productId: selectedProductId
        }
        ,
        success: function (data) {
            var currentPrice = parseFloat($('button.reduced.items-count[value*="'+selectedProductId+'"]').closest('tr').find('input#priceProduct').val());
            var totalPrice = parseFloat(document.getElementById("valueTotal").value);
            var newTotalPrice = (totalPrice-currentPrice).toFixed(2);
            document.getElementById('valueTotal').value=newTotalPrice;
            document.getElementById("total").innerHTML = newTotalPrice+"$";
            var amountProduct = document.getElementById('sizeCart').innerText;
            document.getElementById("sizeCart").innerHTML=--amountProduct;
            if(indicator.value==0){
                $('button.reduced.items-count[value*="'+selectedProductId+'"]').closest('tr').remove();
            }
        }
    });
    };

    function deleteProductFromCart(selectedProductId,indicator) {
        $.ajax({
            url: 'cart',
            type: 'POST',
            data: {
                productId: selectedProductId
            }
            ,
            success: function (data) {

            var currentPrice = parseFloat($('button.reduced.items-count[value*="'+selectedProductId+'"]').closest('tr').find('input#priceProduct').val());
            var totalPrice = parseFloat(document.getElementById("valueTotal").value);
            var newTotalPrice = (totalPrice-currentPrice*indicator.value).toFixed(2);
            document.getElementById('valueTotal').value=newTotalPrice;
            document.getElementById("total").innerHTML = newTotalPrice+"$";
            $('button.reduced.items-count[value*="'+selectedProductId+'"]').closest('tr').remove();
            var amountProduct = parseInt(document.getElementById('sizeCart').innerText);
            document.getElementById("sizeCart").innerHTML=amountProduct-indicator.value;
            }
        });
        };
