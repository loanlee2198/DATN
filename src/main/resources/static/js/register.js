$(document).ready(function () {
    var btnAdd = $("#add");
    var btnSub = $("#sub");
    var quanity = $("#quantity");
  
    //bat su kien

    btnAdd.click(function (event) {
        event.preventDefault();
        //getValue()
        let valueAmount = quanity.val()

      
        valueAmount = ++valueAmount;
        quanity.val(valueAmount)
       
    });

    btnSub.click(function (event) {
        event.preventDefault();
        let valueAmount = quanity.val()

      
        if (valueAmount > 1) {
            valueAmount = --valueAmount;
            quanity.val(valueAmount)
        } else {
            alert("Số lượng sản phẩm tối thiểu là 1")
        }
    });
  
    $(document).on('keypress', function (e) {
        if (e.which == 13) {
            let valueAmount = quanity.val()

            if (valueAmount >= 1) {
                quanity.val(valueAmount)
                            } else {
                alert("Số lượng sản phẩm tối thiểu là 1")
            }
        }
    });

})