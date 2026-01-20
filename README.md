LogAnalyzer

LogAnalyzer, Java ile yazılmış basit bir log analiz uygulamasıdır.
Amaç; text tabanlı access log dosyalarının okunması, parse edilmesi ve temel istatistiklerin çıkarılması sürecini sade bir mimariyle göstermektir.

Proje; karmaşık framework’ler, asenkron yapılar veya dağıtık mimari hedeflemez.
Okunabilirlik ve akışın netliği önceliklidir.

Genel Yaklaşım

Uygulama şu akışı izler:

Log dosyası okunur

Her satır tek tek parse edilmeye çalışılır

Başarılı parse edilen kayıtlar analiz edilir

Parse edilemeyen satırlar sayılır

Toplanan verilerden özet istatistikler üretilir

Tüm analiz, tek geçişlik ve sayaç (counter) tabanlı bir yaklaşımla yapılır.

Mimari Yapı

Kod tabanı bilinçli olarak küçük parçalara ayrılmıştır:

CLI / Entry Point
Uygulamanın çalıştırılması ve parametrelerin alınmasından sorumludur.

IO Katmanı
Log dosyasının okunmasını soyutlar.
Dosya okuma mantığı analizden ayrıdır.

Parser
Ham log satırını alır, beklenen formata göre parse eder.
Hatalı veya eksik satırlar bu aşamada elenir.

Model
Parse edilmiş bir log kaydını temsil eden basit veri yapıları içerir.

Analyzer / Report
Parse edilmiş kayıtlar üzerinden istatistikleri üretir
(frekans sayımı, dağılımlar, özet metrikler).

Bu ayrım, kodun:

okunmasını

test edilmesini

gerektiğinde genişletilmesini
kolaylaştırmak için yapılmıştır.

Tasarım Tercihleri

Log dosyası tamamen belleğe alınır

Analiz in-memory yapılır

Log formatı katıdır, esneklik hedeflenmemiştir

Hatalı veriler tolere edilir ancak raporlanır

Bu tercihler bilinçli kısıtlardır; production gereksinimleri hedeflenmemiştir.

Amaç ve Kapsam

Bu proje:

log analizi problemini sade şekilde ele almak

Java’da basit bir CLI uygulama mimarisi göstermek

parse → analyze → report akışını net biçimde ortaya koymak

için yazılmıştır.

Production ortamları, büyük log dosyaları veya esnek formatlar için uygun değildir.
Örnek çıktı;
Parsed entries: 50
Skipped lines : 4

== Top IPs ==
1) 10.0.0.5 -> 25
2) 192.168.1.10 -> 20
3) 172.16.0.3 -> 5

== Top Endpoints ==
1) /products -> 20
2) /api/profile -> 10
3) /api/login -> 9
4) / -> 5
5) /contact -> 3

== Status Code Distribution ==
200 -> 43
401 -> 1
404 -> 3
500 -> 3

== Bytes ==
Total bytes   : 13857
Avg bytes/req : 277,14
