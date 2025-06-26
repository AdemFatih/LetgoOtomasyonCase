
📘 README - Letgo Mobil Otomasyon Testi

Test Adı: searchBikeApplyFilterAndFavorite
Platform: Android
Test Aracı: Appium + TestNG + Java
Uygulama: Letgo (com.abtnprojects.ambatana)

📋 Testin Amacı

Bu test, Letgo mobil uygulamasında:

Bisiklet araması yapar.

Fiyat filtresi uygular (5000–15000 TL arası).

İlk çıkan ürünü favorilere ekler.

Ürünün ilan numarasını alır.

Favorilerdeki ürün ile ilan numarasını karşılaştırır.

🛠 Gereksinimler

Appium Server (http://127.0.0.1:4723) çalışıyor olmalı.

Gerçek bir Android cihaz bağlı ve developer mode + USB debugging açık olmalı.

Testin çalışması için cihazda Letgo uygulaması kurulu olmalı.

Test Stepleri

(Local mobil cihaz ile adb bağlantısı yapıldığı için login şekilde varsayılır)
1. Uygulama açılır.
2. Arama çubuğuna "bisiklet" yazılır.
3. "Bisiklet" kategorisinin görünürlüğü doğrulanır.
4. Klavye üzerinden **Enter (OK)** tuşuna basılır.
5. Filtreler açılır:
   (Pasif konumda kod bloğudur; "Cüzdanım Güvende" filtresi etkinleştirilir.)
   (Pasif konumda kod bloğudur; "Ücretsiz Kargo" filtresi etkinleştirilir.)
   - Fiyat aralığı **5000 - 15000** olarak belirlenir.
6. Filtreler uygulanır.
7. Ekran görüntüsü alınır('filter_result')
8. Filtre sayısının "2" olduğu doğrulanır.
9. Arama sonuçlarında ilk ürünün görünürlüğü kontrol edilir.
10. Ürün favorilenir.
11. Ürünün detayına girilir, **İlan No** alınır.
12. Geri gidilerek ana sayfaya dönülür.
13. "İlanlarım" > "Favorilerim" sekmesine gidilir.
14. Favorilere eklenen ürünün görünürlüğü doğrulanır.
15. Ekran görüntüsü alınır('favorilerim_result')
16. Favorideki ürün detayına girilir.
17. **İlan No** ile doğrulama yapılır.


🚀 Çalıştırma Adımları

Appium başlatılır.

Android cihaz bağlanır.

Test sınıfı (LetgoTest) çalıştırılır.

Testin sonunda:

Filtre sonuçlarının ekran görüntüsü alınır (filter_result.png)

Favoriler ekran görüntüsü alınır (favorilerim_result.png)

🔍 Öne Çıkan Noktalar

XPath ve accessibility id karışık kullanılmış, dinamik UI uyumluluğu sağlanmıştır.

Bazı düğmelerde koordinat bazlı tıklama (PointerInput) yapılmıştır.

content-desc üzerinden İlan No bilgisi alınır.

Assert ile filtre sayısı ve ürün eşleşmesi kontrol edilir.

MobileUtils sınıfı ile yardımcı işlemler (örneğin scrollDownSmall, takeScreenshot, pressBack) yapılmaktadır.

🧪 Doğrulamalar

Filtre sayısı 2 olarak gösteriliyor mu?

İlk ürün detay sayfasında ve favorideki ürünle ilan no eşleşmesi var mı?

Favoriye eklenen ürün gerçekten listelenmiş mi?

📂 Ekran Görüntüleri

./screenshots/filter_result.png

./screenshots/favorilerim_result.png