function fibonacci(num) {
    let a = 0, b = 1, c, i;
    let fibonums = [a, b];
    for (i = 2; i < num; i++) {
        c = a + b;
        fibonums.push(c);
        a = b;
        b = c;
    }
    return fibonums;
}

let num = prompt("Enter a number: ");
let fibonums = fibonacci(num);
document.getElementById("out").innerHTML = fibonums.join(", ");
