Nama : Hanan Adipratama
NPM : 2206081824
Kelas : B

# Tutorial 3
## Refleksi 1
### Explain what principles you apply to your project!
1. SRP menjabarkan bahwasannya satu kelas seharusnya hanya memiliki satu alasan untuk berubah. Artinya, kelas seharusnya hanya memiliki satu tanggung jawab utama atau fungsi. Contoh pengaplikasikan SRP adalah dengan memisahkan class `CarController.java`, `ProductController.java`, and `HomePageController.java` serta menghilangkan extend dari CarController.
2. DIP menjabarkan bahwasannya modul tingkat tinggi seharusnya tidak bergantung pada modul tingkat rendah. Keduanya seharusnya bergantung pada abstraksi. Contoh pengaplikasikan DIP adalah dengan mengubah kode pada `CarController.java`,
   ```
   ...
   private CarServiceImpl carservice;
   ...
   ```
   menjadi
   ```
   ...
   private CarService carservice;
   ...
   ```
3. ISP menjabarkan bahwasannya class tidak boleh dipaksa untuk mengimplementasikan interface yang tidak digunakan. Prinsip ini menyarankan untuk memisahkan antarmuka agar klien hanya perlu bergantung pada metode yang dibutuhkannya. Mengaplikasikan ISP dengan membedakan interface `Car` berupa `CarService` dan `Product` berupa `ProductService`.
### Explain the advantages of applying SOLID principles to your project with examples.
1. **Mantainability**, Penerapan SRP memastikan setiap kelas memiliki satu tanggung jawab utama. Misalnya, dengan memisahkan CarController dan ProductController, memudahkan mantainability karena perubahan pada satu bagian tidak memengaruhi yang lain.
2. **Mengurangi Dependancy**, DIP mengurangi ketergantungan antara modul tingkat tinggi dan rendah dengan bergantung pada abstraksi. Mengubah dependensi di CarController menjadi CarService mengurangi ketergantungan dan memudahkan pergantian implementasi.
3. **Relevansi Interface**, Melalui penerapan ISP setiap antarmuka hanya berisi metode yang diperlukan. Hal ini memastikan implementasi hanya fokus pada metode yang relevan. Contohnya Car dengan CarService dan Product dengan ProductService, keduanya fokus pada tujuannya masing-masing.
### Explain the disadvantages of not applying SOLID principles to your project with examples.
1. **Mantainibility yang lebih susah**, Tanpa SRP, satu kelas dapat memiliki beberapa alasan untuk berubah, membuat kode menjadi sulit dipelihara. Contohnya, jika satu kelas ditambahkan atau diubah fungsionalitas kodenya, perubahan pada salah satu aspek dapat memengaruhi yang lain.
2. **Meningkatan Ketergantungan pada Implementasi**, Mengabaikan DIP dapat meningkatkan ketergantungan pada implementasi kelas konkret, sulit untuk mengganti atau mengubah implementasi tanpa memengaruhi seluruh sistem. Ini meningkatkan ketergantungan pada kelas konkret daripada abstraksi.
3. **Interface tidak efisien**, Tanpa ISP, antarmuka mungkin berisi lebih banyak metode dari yang diperlukan, menyebabkan pencemaran antarmuka dan kesulitan pemeliharaan sistem.

# Tutorial 2
## Refleksi 1
### List Fixed Code Quality Issue's
1. Saya menambahkan unit test ada beberapa function yang sudah terdapat test namun masih ada missed pipeline, berikut salah satu contoh test untuk mencover kasus else pada function edit di `ProductRepository.java`
```
@Test
    void testEditProductNotFound() {
        ProductRepository productRepository = new ProductRepository();
        String productId = "nonexistent-id";
        Product updatedProduct = new Product();
        updatedProduct.setProductId("some-id");

        Product result = productRepository.edit(productId, updatedProduct);

        assertNull(result);
    }
```

### CI/CD Workflow
Saat ini, implementasi proyek telah memenuhi dasar CI/CD. Dengan GitHub Workflows, setiap push perubahan kode akan **menjalankan unit test otomatis dan proses deployment**. Uji coba melibatkan penggunaan `ci.yml` dan _testing_ kode dengan `PMD`. Setelah itu, kode digabungkan ke dalam branch utama dan diunggah secara otomatis ke PaaS Koyeb menggunakan `scorecard.yml`.

# Tutorial 1
## Refleksi 1

Dalam implementasi dua fitur baru menggunakan Spring Boot, saya mengevaluasi standar penulisan kode yang telah dipelajari dalam modul ini. Berikut adalah prinsip-prinsip penulisan kode bersih dan praktik keamanan yang telah saya terapkan pada kode saya:

### Prinsip-prinsip Penulisan Clean Code:
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

## Refleksi 2
1. Setelah menulis unit test, saya cukup pusing karena walaupun sudah terbantu dengan pembuatan kode sebelumnya yang mengikuti kaidah clean code. Jumlah unit test bervariasi, minimal satu per fungsi atau tiap jenis return yang mungkin diberi fungsi. Pastikan unit test mencakup berbagai skenario. Code coverage membantu mengukur seberapa banyak kode yang diuji, bukan menjamin tidak adanya bug.

2. Terkait functional test baru, perlu dievaluasi kebersihan kode. Duplikasi kode, setup yang tidak efisien, dan kurangnya struktur mungkin masalah. Untuk meningkatkan kebersihan, cek dan hapus duplikasi kode, pastikan setup efisien, dan struktur yang jelas.
