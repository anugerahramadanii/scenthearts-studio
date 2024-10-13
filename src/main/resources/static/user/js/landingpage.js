async function getAllCategoriesIndexAPI() {
  var settings = {
    url: "/api/category",
    method: "GET",
    timeout: 0,
  };

  return $.ajax(settings);
}

async function getAllProductsIndexAPI() {
  var settings = {
    url: "/api/product",
    method: "GET",
    timeout: 0,
  };

  return $.ajax(settings);
}

async function getProductsCategoryIndexByCatIdAPI(categoryId) {
  var settings = {
    url: "/api/product/byCategoryId?categoryId=" + categoryId,
    method: "GET",
    timeout: 0,
  };

  return $.ajax(settings);
}

function renderCategories(categories) {
  const categoriesSlider = $(".categories__slider");
  categoriesSlider.empty(); // Kosongkan slider sebelum menambahkan kategori baru

  categories.forEach((category) => {
    const imageUrl = category.image_path;
    const categoryHTML = `
        <div class="col-lg-3">
          <div class="categories__item set-bg" data-setbg="${imageUrl}">
            <h5><a href="${category.url}">${category.name}</a></h5>
          </div>
        </div>`;
    categoriesSlider.append(categoryHTML);
  });

  // Atur background setelah render
  setBackgroundImages();

  // Inisialisasi ulang Owl Carousel setelah konten di-render
  initializeOwlCarousel();
}

function setBackgroundImages() {
  $(".set-bg").each(function () {
    var bg = $(this).data("setbg");
    $(this).css("background-image", "url(" + bg + ")");
  });
}

function initializeOwlCarousel() {
  const $carousel = $(".categories__slider");
  if ($carousel.length) {
    $carousel.owlCarousel("destroy"); // Menghapus Owl Carousel sebelumnya untuk mencegah inisialisasi ganda
  }

  $carousel.owlCarousel({
    loop: true,
    margin: 0,
    items: 4,
    dots: false,
    nav: true,
    navText: [
      "<span class='fa fa-angle-left'><span/>",
      "<span class='fa fa-angle-right'><span/>",
    ],
    animateOut: "fadeOut",
    animateIn: "fadeIn",
    smartSpeed: 1200,
    autoHeight: false,
    autoplay: true,
    responsive: {
      0: {
        items: 1,
      },
      480: {
        items: 2,
      },
      768: {
        items: 3,
      },
      992: {
        items: 4,
      },
    },
  });
}

function renderCategoriesInFilter(categories) {
  const categoryFilter = $("#category-filter");
  categoryFilter.empty(); // Kosongkan filter sebelum menambahkan kategori baru

  // Tambahkan kategori dinamis ke dalam filter
  categories.forEach((category) => {
    const categoryHTML = `<li data-category-id="${category.id}">${category.name}</li>`;
    categoryFilter.append(categoryHTML);
  });

  // Tambahkan opsi untuk melihat semua produk (tanpa filter kategori)
  categoryFilter.prepend(`<li data-category-id="">All Products</li>`);
}

async function loadAllCategories() {
  try {
    const response = await getAllCategoriesIndexAPI();
    if (response.code === 200) {
      renderCategories(response.data);
      renderCategoriesInFilter(response.data);
    } else {
      console.error("Error fetching categories:", response.message);
    }
  } catch (error) {
    console.error("Error fetching categories:", error);
  }
}

// Function to render products
function renderProducts(products) {
  const productContainer = $(".featured__filter");
  productContainer.empty(); // Kosongkan container produk sebelum menambahkan produk baru

  products.forEach((product) => {
    const productHTML = `
        <div class="col-lg-3 col-md-4 col-sm-6">
          <div class="featured__item">
            <div class="featured__item__pic set-bg" data-setbg="${product.image_path}">
              <ul class="featured__item__pic__hover">
                <li><a href=""><i class="fa fa-heart"></i></a></li>
                <li><a href=""><i class="fa fa-retweet"></i></a></li>
                <li><a href=""><i class="fa fa-shopping-cart"></i></a></li>
                <li><a href=""><i class="fa fa-eye"></i></a></li>
              </ul>
            </div>
            <div class="featured__item__text">
              <h6>${product.name}</h6>
              <h5>$${product.price}</h5>
            </div>
          </div>
        </div>`;
    productContainer.append(productHTML);
  });

  // Atur background setelah render
  setBackgroundImages();
}

async function loadAllProducts() {
  try {
    const response = await getAllProductsIndexAPI();
    if (response.code === 200) {
      renderProducts(response.data);
    } else {
      console.error("Error fetching products:", response.message);
    }
  } catch (error) {
    console.error("Error fetching products:", error);
  }
}

$(document).ready(async function () {
  // Panggil fungsi untuk memuat semua kategori dan produk saat halaman siap
  await loadAllCategories();
  await loadAllProducts();
});

// Fungsi untuk memuat dan merender produk dari kategori yang dipilih
async function loadProductsByCategory(categoryId) {
  try {
    const response = await getProductsCategoryIndexByCatIdAPI(categoryId);
    console.log(response);
    if (response.code === 200) {
      renderProducts(response.data); // Tampilkan produk berdasarkan kategori
    } else {
      console.error("Error fetching products by category:", response.message);
    }
  } catch (error) {
    console.error("Error fetching products by category:", error);
  }
}

// Menggunakan event delegation untuk kategori yang dinamis
$(document).on("click", "#category-filter li", async function () {
  $("#category-filter li").removeClass("active");
  $(this).addClass("active");

  var categoryId = $(this).attr("data-category-id"); // Ambil ID kategori
  if (categoryId) {
    await loadProductsByCategory(categoryId); // Panggil produk berdasarkan kategori yang dipilih
  } else {
    await loadAllProducts(); // Tampilkan semua produk jika tidak ada kategori yang dipilih
  }
});

// Inisialisasi MixItUp setelah elemen filter tersedia
if ($(".featured__filter").length > 0) {
  var containerEl = document.querySelector(".featured__filter");
  var mixer = mixitup(containerEl);
}
