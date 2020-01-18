var form = document.getElementById("filter-form");

document.getElementById("sorterNameAsc").onclick = function(ev) {
    document.getElementById("sorter").value = this.value;
    form.submit()
}

document.getElementById("sorterNameDesc").onclick = function(ev) {
    document.getElementById("sorter").value = this.value;
    form.submit()
}

document.getElementById("sorterPriceAsc").onclick = function(ev) {
    document.getElementById("sorter").value = this.value;
    form.submit()
}

document.getElementById("sorterPriceDesc").onclick = function(ev) {
    document.getElementById("sorter").value = this.value;
    form.submit()
}

document.getElementById("numberItems5").onclick = function(ev) {
    document.getElementById("numberItems").value = this.value;
    form.submit()
}

document.getElementById("numberItems10").onclick = function(ev) {
    document.getElementById("numberItems").value = this.value;
    form.submit()
}

document.getElementById("numberItems15").onclick = function(ev) {
    document.getElementById("numberItems").value = this.value;
    form.submit()
}

function isNumber(n) { return /^-?[\d.]+(?:e-?\d+)?$/.test(n); }

var start = [];
$(document).ready(function(){
    var min =  $('input[name*="minPrice"]').val();
    var max =  $('input[name*="maxPrice"]').val();
    if (isNumber (min) && isNumber (max)){
        start[0] = min;
        start[1] = max;
    }
    else{
        start[0] = 0;
        start[1] = 4000;
    }
});

//------- Price Range slider -------//

$(function() {

  if(document.getElementById("price-range")){

    var nonLinearSlider = document.getElementById('price-range');

    noUiSlider.create(nonLinearSlider, {
        connect: true,
        behaviour: 'tap',
        start: start,
        range: {
            // Starting at 0, step the value by 500,
            // until 4000 is reached. From there, step by 1000.
            'min': [ 0 ],
            '10%': [ 500, 100 ],
            '50%': [ 2500, 250 ],
            'max': [ 5000 ]
        }
    });

    var nodes = [
        document.getElementById('lower-value'), // 0
        document.getElementById('upper-value')  // 1
    ];

    // Display the slider value and how far the handle moved
    // from the left edge of the slider.
    nonLinearSlider.noUiSlider.on('update', function ( values, handle, unencoded, isTap, positions ) {
        nodes[handle].innerHTML = values[handle];
    });

  }
  });

  $('#filter-form').submit(function(){
    $('input[name*="minPrice"]').val($('div.noUi-handle.noUi-handle-lower').attr('aria-valuetext'));
    $('input[name*="maxPrice"]').val($('div.noUi-handle.noUi-handle-upper').attr('aria-valuetext'));
  });