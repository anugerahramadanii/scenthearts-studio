document.addEventListener("alpine:init", () => {
  Alpine.data("products", () => ({
    items: [
      { id: 1, name: "Cincin 1", img: "j1.jpg", price: 20000 },
      { id: 2, name: "Kalung 2", img: "j2.jpg", price: 30000 },
      { id: 3, name: "Gelang 3", img: "j3.jpg", price: 40000 },
      { id: 4, name: "Anting 4", img: "j4.jpg", price: 50000 },
      { id: 5, name: "Pendant 5", img: "j5.jpg", price: 60000 },
    ],
  }));

  Alpine.store("shoppingCart", {
    items: [],
    total: 0,
    quantity: 0,
    add(newItem) {
      // cek apakah ada barang yang sama atau tidak di cart
      const cartItem = this.items.find((item) => item.id === newItem.id);

      // jika belum ada/ cart masi kosong
      if (!cartItem) {
        this.items.push({ ...newItem, quantity: 1, total: newItem.price });
        this.quantity++;
        this.total += newItem.price;
      } else {
        // jika barang sudah ada, cek apakah barang beda atau sama dengan yang ada di cart
        this.items = this.items.map((item) => {
          // jika barang berbeda
          if (item.id !== newItem.id) {
            return item;
          } else {
            // jika barang sudah ada, tambahkan quantity dan total Price
            item.quantity++;
            item.total = item.price * item.quantity;
            this.quantity++;
            this.total += item.price;
            return item;
          }
        });
      }
    },
    remove(id) {
      // get item yang ingin diremove berdasarkan id
      const carItem = this.items.find((item) => item.id === id);

      // jika item lebih dari satu
      if (carItem.quantity > 1) {
        // telusuri 1 1
        this.items = this.items.map((item) => {
          // jika bukan barang yang diklik
          if (item.id !== id) {
            return item;
          } else {
            item.quantity--;
            item.total = item.price * item.quantity;
            this.quantity--;
            this.total -= item.price;
            return item;
          }
        });
      } else if (carItem.quantity === 1) {
        // jika barangnya sisa 1
        this.items = this.items.filter((item) => item.id !== id);
        this.quantity--;
        this.total -= cartItem.price;
      }
    },
  });
});

// convert to IDR
const rupiah = (number) => {
  return new Intl.NumberFormat("id-ID", {
    style: "currency",
    currency: "IDR",
    // minimumFractionDigits: 0, // untuk menghilangkan 2 digit 0 dibelakang koma
  }).format(number);
};
