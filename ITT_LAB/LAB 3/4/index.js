function combinations(str) {
  let result = [];
  for(let i = 0; i < str.length; i++) {
      for(let j = i + 1; j < str.length; j++) {
          for(let k = j + 1; k < str.length; k++) {
              result.push(str[i] + str[j] + str[k]);
          }
      }
  }
  return result;
}

function permutation() {
  let text = document.getElementById("input").value;
  let output = combinations(text).join(",");
  document.getElementById("output").textContent = output;
}
