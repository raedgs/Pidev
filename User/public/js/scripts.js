const cartCounter = document.getElementById('cart-counter')
const total1 = document.getElementById('total1')
const total2 = document.getElementById('total2')

$(document).ready(function () {
  var base_url = window.location.origin

  url = base_url + '/fetchCart'
  fetch(url)
    .then((response) => response.json())
    .then((data) => {
      cartCounter.innerText = data.count
      total1.innerText = data.total + ' DT'
      total2.innerText = data.total + ' DT'
    })
    .catch((e) => alert(e))
})

function onClickAddToCart(e) {
  e.preventDefault()
  const url = this.href
  fetch(url)
    .then((respnose) => respnose.json())
    .then((data) => {
      cartCounter.innerText = data.count
      total1.innerText = data.total + ' DT'
      total2.innerText = data.total + ' DT'
    })
}

function onClickRemoveFromCart(e) {
  e.preventDefault()
  const url = this.href
  fetch(url)
    .then((respnose) => respnose.json())
    .then((data) => {
      cartCounter.innerText = data.count
      total1.innerText = data.total + ' DT'
      total2.innerText = data.total + ' DT'
      deleteRow(data.id)
      document.location.reload(true)
    })
}

document.querySelectorAll('a.addToCart').forEach(function (link) {
  link.addEventListener('click', onClickAddToCart)
})

document.querySelectorAll('a.removeFromCart').forEach(function (link) {
  link.addEventListener('click', onClickRemoveFromCart)
})

function deleteRow(rowid) {
  var row = document.getElementById(rowid)
  var table = row.parentNode
  while (table && table.tagName != 'TABLE') table = table.parentNode
  if (!table) return
  table.deleteRow(row.rowIndex)
}
