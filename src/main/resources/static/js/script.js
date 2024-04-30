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
  if (e.target === itemDetailModal) {
    itemDetailModal.css("display", "none");
  }
});

// LINK NAVBAR
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

// BACK TO TOPz
window.addEventListener("scroll", function () {
  var navbarHeight = document.getElementById("navbarr").offsetHeight; // mengambil tinggi navbar
  var backToTopButton = document.getElementById("back-to-top"); // tombol back to top

  if (window.pageYOffset > navbarHeight) {
    backToTopButton.style.display = "block"; // jika posisi scroll di bawah navbar, tampilkan tombol
  } else {
    backToTopButton.style.display = "none"; // jika posisi scroll di atas navbar, sembunyikan tombol
  }
});

// side bar accordion
$(function () {
  var Accordion = function (el, multiple) {
    this.el = el || {};
    this.multiple = multiple || false;

    // Variables privadas
    var links = this.el.find(".link");
    // Evento
    links.on("click", { el: this.el, multiple: this.multiple }, this.dropdown);
  };

  Accordion.prototype.dropdown = function (e) {
    var $el = e.data.el;
    ($this = $(this)), ($next = $this.next());

    $next.slideToggle();
    $this.parent().toggleClass("open");

    if (!e.data.multiple) {
      $el.find(".submenu").not($next).slideUp().parent().removeClass("open");
    }
  };

  var accordion = new Accordion($(".accordion"), false);
});

// PRICE RANGE
// $("#price-range-submit").hide();

$("#min_price,#max_price").on("change", function () {
  // $("#price-range-submit").show();

  var min_price_range = parseInt($("#min_price").val());

  var max_price_range = parseInt($("#max_price").val());

  if (min_price_range > max_price_range) {
    $("#max_price").val(min_price_range);
  }

  $("#slider-range").slider({
    values: [min_price_range, max_price_range],
  });
});

$("#min_price,#max_price").on("paste keyup", function () {
  // $("#price-range-submit").show();

  var min_price_range = parseInt($("#min_price").val());

  var max_price_range = parseInt($("#max_price").val());

  if (min_price_range == max_price_range) {
    max_price_range = min_price_range + 100;

    $("#min_price").val(min_price_range);
    $("#max_price").val(max_price_range);
  }

  $("#slider-range").slider({
    values: [min_price_range, max_price_range],
  });
});

$(function () {
  $("#slider-range").slider({
    range: true,
    orientation: "horizontal",
    min: 0,
    max: 10000,
    values: [0, 10000],
    step: 100,

    slide: function (event, ui) {
      if (ui.values[0] == ui.values[1]) {
        return false;
      }

      $("#min_price").val(ui.values[0]);
      $("#max_price").val(ui.values[1]);
    },
  });

  $("#min_price").val($("#slider-range").slider("values", 0));
  $("#max_price").val($("#slider-range").slider("values", 1));
});

$("#slider-range,#price-range-submit").click(function () {
  var min_price = $("#min_price").val();
  var max_price = $("#max_price").val();

  // $("#searchResults").text("Here List of products will be shown which are cost between " + min_price + " " + "and" + " " + max_price + ".");
});

// PASSWORD HIDE AND UNHIDE
$(".toggle-password").click(function () {
  $(this).toggleClass("fa-eye fa-eye-slash");
  const inputLogin = $("#input-password-login");
  const inputRegist1 = $("#input-pass-register");
  const inputRegist2 = $("#input-pass-register-confirm");
  if (inputLogin.attr("type") == "password" || inputRegist1.attr("type") == "password" || inputRegist2.attr("type") == "password") {
    inputLogin.attr("type", "text");
    inputRegist1.attr("type", "text");
    inputRegist2.attr("type", "text");
  } else {
    inputLogin.attr("type", "password");
    inputRegist1.attr("type", "password");
    inputRegist2.attr("type", "password");
  }
});

// REGISTER
// SCRIPT COUNTDOWN
let countdownValue = 60;

function updateCountdown() {
  $("#btn-send-otp").html(`Resend OTP in ${countdownValue}`);
  $("#btn-send-otp").prop("disabled", true);

  countdownValue--;

  if (countdownValue < 0) {
    $("#btn-send-otp").html(`Resend OTP`);
    $("#btn-send-otp").prop("disbled", false);
    countdownValue = 60;
  } else {
    setTimeout(updateCountdown, 1000);
  }
}

$("#btn-send-otp").click(function () {
  updateCountdown();
});
