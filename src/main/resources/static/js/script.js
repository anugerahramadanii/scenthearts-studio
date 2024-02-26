// Togle class active humbergur menu
$("#hamburger-menu").click(() => {
  $(".navbarr-nav").toggleClass("active");
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

  if (!hm.contains(e.target) && !$(".navbarr-nav")[0].contains(e.target)) {
    $(".navbarr-nav").removeClass("active");
  }
  if (!sb.contains(e.target) && !$(".search-form")[0].contains(e.target)) {
    $(".search-form").removeClass("active");
  }
  if (!sc.contains(e.target) && !$(".shopping-cart")[0].contains(e.target)) {
    $(".shopping-cart").removeClass("active");
  }
});

// Modal Box
const itemDetailModal = $("#item-detail-modal");
const itemDetailButtons = $(".item-detail-button");

itemDetailButtons.click((e) => {
  itemDetailModal.css("display", "flex");
  e.preventDefault();
});

// Klik tombol Modal Box
$(".modal .close-icon").click((e) => {
  itemDetailModal.css("display", "none");
  e.preventDefault();
});

// Klik di luar modal
$(window).click((e) => {
  if (e.target === itemDetailModal[0]) {
    itemDetailModal.css("display", "none");
  }
});

$(document).ready(function () {
  $('a[href="#category"]').click(function (e) {
    e.preventDefault(); // Mencegah default aksi tautan
    window.location.href = "/#category";
  });

  $('a[href="#products"]').click(function (e) {
    e.preventDefault();
    window.location.href = "/#products";
  });

  $('a[href="#contact"]').click(function (e) {
    e.preventDefault();
    window.location.href = "/#contact";
  });
});

window.addEventListener("scroll", function () {
  var navbarHeight = document.getElementById("navbarr").offsetHeight; // mengambil tinggi navbar
  var backToTopButton = document.getElementById("back-to-top"); // tombol back to top

  if (window.pageYOffset > navbarHeight) {
    backToTopButton.style.display = "block"; // jika posisi scroll di bawah navbar, tampilkan tombol
  } else {
    backToTopButton.style.display = "none"; // jika posisi scroll di atas navbar, sembunyikan tombol
  }
});
