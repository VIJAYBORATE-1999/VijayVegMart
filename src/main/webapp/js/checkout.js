function calculateTotal() {
    const prices = document.querySelectorAll('.item-price');
    let total = 0;

    prices.forEach(price => {
        total += parseFloat(price.innerText);
    });

    document.getElementById('total').innerText = total.toFixed(2);
}

// Call the function to calculate the initial total
calculateTotal();