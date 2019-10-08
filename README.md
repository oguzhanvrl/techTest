# techTest
# Not

Projenin isterlerini elimden geldigince tamamlamayaca calistim, hatali fonkisyonlar olabilir. Zaman darligindan dolayi duzeltemedigim bir cok yapi var, calismayan yerlerin kod yapisini incelemenizi isterim, daha genis bir vakitte cozulebilir.

## Basliyoruz

Docker Mac'e kurulum asamasi tamamlandi.

Docker seleniumu konfigüre etmenin birden fazla yolu var. ( network & compose)

Bu repoda network uzerinden ilerliyorum, proje icerisine docker-compose.yml dosyasinida ayrica ekledim.

> docker network olusturma

```bash
docker network create trendyolGrid
```

> docker hub olusturma

```bash
docker run -d -p 4444:4444 --net trendyolGrid --name selenium-hub selenium/hub:3.11.0-dysprosium
```

> docker hub'a node ekleme (chrome&firefox)

```bash
docker run -d --net trendyolGrid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-chrome:3.11.0-dysprosium
```
```bash
docker run -d --net trendyolGrid -e HUB_HOST=selenium-hub -v /dev/shm:/dev/shm selenium/node-firefox:3.11.0-dysprosium
```
> http://localhost:4444/grid/console > Selenium Hub ve Node' larimizin ayaga kalktigini gorebilmek ne mutlu :)

> docker network 'ine bakarsak, eklediğimiz containerları görebiliriz.

```bash
docker network inspect trendyolGrid
```


## Test projesine gecis

```bash
Java + TestGN + Selenium WebDriver + XPath/CSSselector + LOG4j + Allure(eklenmedi)
vb. gelistirme araclari kullanildi.

Test projesi mimari yapisin da PageObjectModel patterni kullanildi, kod yapisinda ise OOP temel ilkeleri Soyutlama(Abstraction) + Sarmalama(Encapsulation) + Çok-Şekillilik(Polymorphism) ve Miras Alma/Kalıtım(Inheritance)
kullanilmaya ozen gosterilmistir.

> Test projesinde her testClass birer BaseTestCase(abstract) class ile iliskilidir.
> Ayni sekilde her testPage birer BasePage(abstract) class ile iliskilidir.
```
![alt text](https://github.com/oguzhanvrl/techTest/blob/master/ss.png)

## BoutiqueTestCases
Challenge testcaselerini calistirdigimizda ilgili path'e 2 adet csv uzantili dosya ekleyecektir.

> System.getProperty("user.dir") + "/target/" + "csvTestResult";

Login testlerinde data driven test kosabilmesi icin excel reader eklemistim, hata aldim excel surumu yuzunden windos da problem olmuyordu mac de excel 2007 surum uyusmadigi hatasini aldim onu duzelttim ama yine hata cikti o yuzden uzerinde fazla duramadim, bilginize. 

> Class 'i incelemek isterseniz CustomIOReader(excelReader) Common > CustomCut altinda bulunuyor. 


## Api testi
Api testi daha once yapmamistim, local ime kurulum islemleri devam ederken .Net c# denemeler yaptim, ekran goruntuleri asagidadir. Api test projesi repo adresi : **[github.com/oguzhanvrl/techTestApi](https://github.com/oguzhanvrl/techTestApi)**


![alt text](https://github.com/oguzhanvrl/techTestApi/blob/master/apiTest.PNG)
