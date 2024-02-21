// Togle class active humbergur menu
$("#hamburger-menu").click(() => {
  $(".navbar-nav").toggleClass("active");
});

// Togle class active search form
$("#search-button").click((e) => {
  $(".search-form").toggleClass("active");
  $("#search-box").focus();
  e.preventDefault();
});

// Togle class active shopping cart
$("#shopping-cart-button").click((e) => {
  $(".shopping-cart").toggleClass("active");
  e.preventDefault();
});

// Klik diluar elemen
$(document).click((e) => {
  const hm = $("#hamburger-menu")[0];
  const sb = $("#search-button")[0];
  const sc = $("#shopping-cart-button")[0];

  if (!hm.contains(e.target) && !$(".navbar-nav")[0].contains(e.target)) {
    $(".navbar-nav").removeClass("active");
  }
  if (!sb.contains(e.target) && !$(".search-form")[0].contains(e.target)) {
    $(".search-form").removeClass("active");
  }
  if (!sc.contains(e.target) && !$(".shopping-cart")[0].contains(e.target)) {
    $(".shopping-cart").removeClass("active");
  }
});

// Modal Box
const $ItemDetailModal = $("#item-detail-modal");
const $ItemDetailButtons = $(".item-detail-button");

$ItemDetailButtons.click((e) => {
  $ItemDetailModal.css("display", "flex");
  e.preventDefault();
});

// Klik tombol Modal Box
$(".modal .close-icon").click((e) => {
  $ItemDetailModal.css("display", "none");
  e.preventDefault();
});

// Klik di luar modal
$(window).click((e) => {
  if (e.target === $ItemDetailModal[0]) {
    $ItemDetailModal.css("display", "none");
  }
});
