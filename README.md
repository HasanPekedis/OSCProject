# **Omreon Study Case - Havayolu Biletleme Sistemi (OSCProject)**

Bu proje, havayolu biletleme işlemlerini gerçekleştiren bir sistemdir. Kullanıcılar, havaalanları, havayolları, uçuşlar, güzergahlar ve biletlerle ilgili işlemleri yapabilir. Bu sistem, bir API üzerinden uçuş rezervasyonları yapmayı, biletleri görüntülemeyi ve yönetmeyi sağlar. Uçuşlar arasındaki fiyat değişiklikleri, bilet satın alım işlemleri, havayolu ve havaalanı yönetimi gibi özellikleri içerir.

## **Postman Koleksiyonu:**

Proje için API testlerini kolaylaştırmak amacıyla, bir Postman Koleksiyonu yüklenmiştir. Bu koleksiyon, API'yi test etmek ve uçuşlar, biletler gibi temel işlemleri kolayca denemek için kullanılabilir.

Dosya, projenin kök klasöründe bulunan OSCProject.postman_collection.json dosyasındadir.

## **Proje Özeti**

Omreon Study Case - Havayolu Biletleme Sistemi, aşağıdaki işlevsellikleri sağlamak için geliştirilmiştir:

- **Havaalanları Yönetimi:** Yeni havaalanı ekleme, havaalanlarını listeleme, belirli havaalanlarını ID ya da kod ile arama.
- **Havayolları Yönetimi:** Havayolu bilgilerini ekleyebilme ve listeleyebilme.
- **Uçuş Yönetimi:** Uçuş ekleme, uçuşları listeleme, uçuşu ID ya da uçuş numarası ile sorgulama, uçuş güncelleme, uçuş silme işlemleri.
- **Rota Yönetimi:** Rota ekleme, rotaları listeleme, rotayi ID ya da kod ile sorgulama, rota silme işlemleri.
- **Bilet Yönetimi:** Bilet satın alma, biletleri listeleme, bilet silme, bilet güncelleme, bilet fiyatını artırma işlemleri.

## **Proje Yapısı**

Bu proje Spring Boot kullanılarak geliştirilmiştir ve H2 veritabanı kullanılmaktadır. Proje aşağıdaki modülleri içerir:

1. **Entities (Varlıklar):**
   - `Airport`: Havaalanları hakkında bilgi tutar (id, name, code, city, country).
   - `Airline`: Havayolu şirketleri hakkında bilgi tutar (id, name, country, code).
   - `Flight`: Uçuşlar hakkında bilgi tutar (id, flightNumber, departureTime, arrivalTime, totalSeats, bookedSeats, currentTicketPrice, baseTicketPrice, airline, route).
   - `Route`: Rotalari hakkında bilgi tutar (id, code, departure, destination).
   - `Ticket`: Biletler hakkında bilgi tutar (id, passengerName, passengerEmail, code, price, status, creditCard, flight).

2. **Servisler (Services):**
   - `AirportService`: Havaalanı verilerini yöneten iş mantığı.
   - `AirlineService`: Havayolu şirketleri verilerini yöneten iş mantığı.
   - `FlightService`: Uçuşlar ile ilgili iş mantığı.
   - `RouteService`: Güzergahlar ile ilgili iş mantığı.
   - `TicketService`: Biletler ile ilgili iş mantığı.

3. **Controller'lar (API endpointleri):**
   - `AirportController`: Havaalanları ile ilgili API işlemleri.
   - `AirlineController`: Havayolları ile ilgili API işlemleri.
   - `FlightController`: Uçuşlar ile ilgili API işlemleri.
   - `RouteController`: Güzergahlar ile ilgili API işlemleri.
   - `TicketController`: Biletler ile ilgili API işlemleri.

4. **Veritabanı:**
   - **H2 Veritabanı:** Projede H2 veritabanı kullanılmaktadır. Veritabanı, uçuşlar, havayolları, havaalanları, güzergahlar ve biletler gibi verileri depolar. Bu veritabanı, projeyi çalıştırırken otomatik olarak başlatılır.

### **Gereksinimler:**

- **Java 17** veya üzeri yüklü olmalıdır.
- **Maven** veya **Gradle** (Proje Maven ile yapılandırılmıştır).
- **Spring Boot** ve **Spring Data JPA**.
- **H2 Veritabanı**.
