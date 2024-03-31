$(document).ready(function() {
    $.ajax({
        url: 'inventory.json',
        dataType: 'json',
        success: function(data) {
            displayInventory(data);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.error("Error fetching data: " + errorThrown);
        }
    });

    $("#addItemForm").submit(function(event) {
        event.preventDefault();
        var itemName = $("#itemName").val();
        var itemQuantity = $("#itemQuantity").val();
        if (itemName && itemQuantity) {
            inventory.push({ name: itemName, quantity: parseInt(itemQuantity) });
            displayInventory(inventory);
            $("#addItemForm")[0].reset();
        }
    });

    $(document).on("click", ".deleteBtn", function() {
        var index = $(this).closest(".item").index();
        inventory.splice(index, 1);
        displayInventory(inventory);
    });

    function displayInventory(inventory) {
        $("#inventoryList").empty();
        inventory.forEach(function(item) {
            $("#inventoryList").append(
                "<div class='item'>" +
                "<span>" + item.name + " - " + item.quantity + "</span>" +
                "<button class='deleteBtn'>Delete</button>" +
                "</div>"
            );
        });
    }
});
