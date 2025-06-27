package tests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LetgoTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setup() throws Exception {
        System.out.println("Test başlatılıyor: Android cihaz bağlantısı sağlanıyor...");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "RM6Y3XGV3M"); // benim kişisel telefonum
        caps.setCapability("platformVersion", "13");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("appPackage", "com.abtnprojects.ambatana");
        caps.setCapability("appActivity", "com.abtnprojects.ambatana.MainActivity");
        caps.setCapability("noReset", true);
        caps.setCapability("ignoreHiddenApiPolicyError", true);

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println(" Cihaz bağlantısı başarılı, test başlıyor.");
    }

    @Test
    public void searchBikeApplyFilterAndFavorite() {
        try {
            // search bar ı bulup ona tıklar
            System.out.println("Arama çubuğuna 'bisiklet' yazılıyor...");
            WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Araba, telefon, bisiklet ve daha fazlası...\"]")));
            searchBar.click();

            // sendkeys metodu ile bisiklet yazar
            WebElement editBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.EditText")));
            editBar.sendKeys("bisiklet");

            // bisiklet yazınca bir kaç saniye sonra ilk sırada bisiklet ana kategori çıkıyor, bu elementin visible olduğunu verify eder
            System.out.println("'Bisiklet' kategorisi görüntüleniyor mu kontrol ediliyor...");
            WebElement bisikletKategori = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.accessibilityId("Bisiklet Aksesuar & Yedek Parça\nSpor & Outdoor > Bisiklet\nKategori")));

            // arama butonu vb bir element yok, klavyeden OK tuşu gibi olan tuşa tıklar, bu da android kütüphanesinde Enter demektir
            driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

            // Üstte çıkan Filtre butonuna tıklar
            System.out.println("Filtreleme başlatılıyor...");
            WebElement filtreButon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Filtrele\n1\"]")));
            filtreButon.click();

            Thread.sleep(3000);

            // Fiyat filtre seçeneğine tıklar
            System.out.println("Fiyat aralığı belirleniyor: 5000 - 15000 TL");
            WebElement fiyatFiltreButon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Fiyat\"]")));
            fiyatFiltreButon.click();

            /* Bu satırları yorumdan çıkararsanız Filtre Ekranında 2 switch i ON yapar. Cüzdanım Güvende İlanları switch butonu ON yapar
            //Appium inspector da Bu elementin swtich kısmının elementi yok, yazıya tıklayınca switch değişmiyor, bu yüzden x,y koordinat ile tıkladım
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 925, 541));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(tap));

            Thread.sleep(3000);

            // Ücretsiz Kargo switch butonu ON yapar
            //Appium inspector da Bu elementin swtich kısmının elementi yok, yazıya tıklayınca switch değişmiyor, bu yüzden x,y koordinat ile tıkladım
            PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap2 = new Sequence(finger, 1);
            tap2.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 918, 734));
            tap2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(tap2));  */

            System.out.println("Fiyat aralığı belirleniyor: 5000 - 15000 TL");
            // "En Düşük" isimli box a send keys ile text gönderir / xpath diğer elementlere göre daha stabil olduğu için bunu kullandım
            WebElement dusukFiyat = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.EditText[contains(@hint, 'En Düşük')]")));
            dusukFiyat.click();
            dusukFiyat.sendKeys("5000");

            // "En Yüksek" isimli box a send keys ile text gönderir / xpath diğer elementlere göre daha stabil olduğu için bunu kullandım
            WebElement yuksekFiyat = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.EditText[contains(@hint, 'En Yüksek')]")));
            yuksekFiyat.click();
            yuksekFiyat.sendKeys("15000");

            // Klavyeden OK tuşu gibi olan tuşa tıklar
            driver.pressKey(new KeyEvent().withKey(AndroidKey.ENTER));

            // "Uygula" butonuna basılır
            WebElement uygulaButon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.Button[@content-desc=\"Uygula\"]")));
            uygulaButon.click();

            Thread.sleep(2000);

            // Filtre ekranına döner, "Uygula(... ilan)" butonuna basılır.
            WebElement uygula_ilanButon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.Button[contains(@content-desc, 'Uygula')]")));
            uygula_ilanButon.click();

            //Filtre uygula butonuna basınca manuel 3 sn bekleme için ekledim
            Thread.sleep(3000);
            //Ekran görüntüsü alınır
            MobileUtils.takeScreenshot(driver, "filter_result");

            //Filtre butonu yanında "2" yazmasını bekliyorum
            WebElement filtreElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Filtrele\n2\"]")));
            String contentDesc = filtreElement.getAttribute("content-desc");
            Assert.assertTrue(contentDesc.contains("2"), "Filtre sayısı doğru değil");
            System.out.println("Filtre başarıyla uygulandı.");

            //İlk ürünün visible olduğunu kontrol eder
            WebElement firstProduct = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("(//android.view.View[contains(@content-desc, 'TL')])[1]")));
            firstProduct.click();
            System.out.println("İlk ürün detayına girildi.");

            // İlk ürünün üstünde ki Kalp butonuna tıklar (koordinat ile)
            Thread.sleep(2000);
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1);
            tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 1008, 350));
            tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(List.of(tap));

            Thread.sleep(2000);

            //Scroll metodu kullanılır
            MobileUtils.scrollDownSmall(driver);

            //ilan numarası değişken olarak alınır ve sonra kıyaslanmak için bir değere atanır
            WebElement ilanNoElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc, 'İlan No')]")));
            String ilanNo = ilanNoElement.getAttribute("content-desc").split("\n")[1];
            System.out.println("Detay sayfasındaki ilan no: " + ilanNo);

            //Telefonun geri(back) tuşuna 2 kere basar
            MobileUtils.pressBack(driver);
            Thread.sleep(1000);
            MobileUtils.pressBack(driver);
            Thread.sleep(2000);

            // Anasayfa kontrolü yapılır
            WebElement ilanlarimButon = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"İlanlarım\"]")));
            ilanlarimButon.click();
            System.out.println("Anasayfaya dönüldü ve 'İlanlarım' açıldı.");

            // Favorilerim sekmesine tıklar
            WebElement favorilerim = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc, 'Favorilerim')]")));
            favorilerim.click();
            //Ekran görüntüsü alınır
            MobileUtils.takeScreenshot(driver, "favorilerim_result");

            // Favoriler listesinde ilk ürünü kontrol eder
            WebElement ilkUrunFavorilerim = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.xpath("//android.view.View[@clickable='true' and @enabled='true' and contains(@content-desc, 'TL')]")));
            ilkUrunFavorilerim.click();

            Thread.sleep(2000);
            //Scroll metodu kullanılır
            MobileUtils.scrollDownSmall(driver);

            // ilan numarası favorilerdeki ürün için alınır
            WebElement ilanNoElementFav = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.xpath("//android.view.View[contains(@content-desc, 'İlan No')]")));
            String ilanNoFav = ilanNoElementFav.getAttribute("content-desc").split("\n")[1];
            System.out.println("Favorilerdeki ürün ilan no: " + ilanNoFav);

            // İlan numarası eşleşmesi kontrol edilir
            if (ilanNo.equals(ilanNoFav)) {
                System.out.println("Ürün doğru şekilde favorilere eklenmiş ve doğrulanmıştır.");
            } else {
                System.err.println("Favoriye eklenen ürün ile karşılaştırılan ürün farklı.");
            }

        } catch (Exception e) {
            System.err.println("Test sırasında hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        System.out.println("Test tamamlandı, cihaz bağlantısı sonlandırılıyor...");
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Test sonlandırıldı.");
    }
}
