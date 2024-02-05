function num() {
    let input = document.getElementById("in").value;
    let reversedNum = Number(input.toString().split('').reverse().join(''));
    document.getElementById("out").innerHTML = reversedNum;
  }
  