function toastSuccess(message) {
  // Ubah konten toast dengan pesan yang diberikan
  const toastBodyContent = document.getElementById("toast-body-content");
  toastBodyContent.innerHTML = message;

  // Tampilkan toast
  const toastElement = document.getElementById("otp-success-toast");
  const toast = new bootstrap.Toast(toastElement);
  toast.show();
}
