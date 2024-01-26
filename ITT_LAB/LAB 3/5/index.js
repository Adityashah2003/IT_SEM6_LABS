var i;

function squarecube() {
    for (i = 0; i <= 9; i++) {
        var sq = i * i;
        var cu = i * i * i;
        var cubeId = "cu-" + i;
        var squareId = "sq-" + i;

        document.getElementById(squareId).innerHTML = sq;
        document.getElementById(cubeId).innerHTML = cu;
    }
}
