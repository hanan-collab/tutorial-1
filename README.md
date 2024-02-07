Nama : Hanan Adipratama
NPM : 2206081824
Kelas : B

# Tutorial 1
## Refleksi 1

Dalam implementasi dua fitur baru menggunakan Spring Boot, saya mengevaluasi standar penulisan kode yang telah dipelajari dalam modul ini. Berikut adalah prinsip-prinsip penulisan kode bersih dan praktik keamanan yang telah saya terapkan pada kode saya:

### Prinsip-prinsip Penulisan Kode Bersih:
Naming Conventions:
1. Menggunakan nama variabel, fungsi, dan kelas yang deskriptif. Contoh: delete, edit
2. Menyusun nama dengan format camelCase. Contoh: EditProduct.html

Modularisasi:
1. Membagi kode menjadi modul-modul yang terpisah berdasarkan tanggung jawab fungsional tiap branch. Contoh fitur edit pada edit-product dan delete pada delete-product

Menggunakan Framework Fitur:
1. Memanfaatkan fitur-fitur Spring Boot ataupun plugin seperti dependency injection untuk meningkatkan kemudahan pemeliharaan. Contohnya Getter dan Setter plugin dari Lombok
Praktik Keamanan:
Validasi Input:
1. Melakukan validasi input pengguna untuk mencegah masukan yang tidak valid. Contoh: validasi apakah ada product dengan id yang diinput pada delete dan edit

### Perbaikan Potensial:
Error Handling:
1. Memperbarui manajemen kesalahan dengan menyediakan tanggapan yang informatif dan aman untuk pengguna dan mencoba mengaplikasikan try and catch dibanding if else. Contoh pada kode berikut:
```
public Product delete(String productId) {
        Product deletedProduct = null;
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getProductId().equals(productId)) {
                iterator.remove();
                deletedProduct = product;
                break;
            }
        }
        return deletedProduct; // Product with the specified ID was not found
    }

    public Product edit(String productId, Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getProductId().equals(productId)) {
                productData.set(i, updatedProduct);
                return updatedProduct;
            }
        }
        return null; // Product with the specified ID was not found
    }

    public Product findById(String productId) {
        for (Product product : productData) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Product with the specified ID was not found
    }
```
Unit Testing:
1. Menambahkan pengujian unit untuk memastikan fungsi-fungsi inti bekerja sebagaimana diharapkan.

Pembersihan Kode:
1. Menghilangkan potongan kode yang tidak digunakan atau komentar yang tidak perlu.
