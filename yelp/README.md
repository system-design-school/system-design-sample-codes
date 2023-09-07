# Getting Started
### 
need Mysql, Redis and Elasticsearch.

module `com.example.service.business`, port 8082, provide "/business/{id}"

module `com.example.service.location`, port 8081, provide "/location/nearby?latitude=xx&longitude=xx&radius=xx"

module `com.example.service.search`, port 8083, provide "/search?keyword=xx&latitude=xx&longitude=xx&distance=xx"
use Elasticsearch to support full-text search.
Elasticsearch 7.17.7 (recommended version).


### Save Mock Data to DB
./init-script/db.sql
./init-script/redis.sh
### Mock Data
37.44427469718641, -122.17056344747748  120m
McDonald's
Classic, long-running fast-food chain known for its burgers & fries.

37.455694682376944, -122.1868547727396  ~1.8km
FEY Restaurant   
Spicy Sichuan fare & other Chinese eats in a swanky space with whimsical decor, a bar & a VIP room.

37.47087608557759, -122.22254493314958  ~4km
KFC
Restaurant chain known for its buckets of fried chicken, plus wings & sides.

37.317071950610156, -121.91571040872087  ~20km
Tasso's Restaurant & Bar
Greek restaurant


### Run Project
run main in `com.example.service.location.LocationApplication.java`
run main in `com.example.service.business.BusinessApplication.java`
run main in `com.example.service.search.SearchApplication.java`

#### Test URL & Result
##### http://127.0.0.1:8081/location/nearby?latitude=37.50303679697866&longitude=-122.2609020006688&radius=20
3
KFC
Restaurant chain known for its buckets of fried chicken, plus wings & sides.
4.9252 KILOMETERS

2
FEY Restaurant
Spicy Sichuan fare & other Chinese eats in a swanky space with whimsical decor, a bar & a VIP room.
8.3932 KILOMETERS

1
McDonald's
Classic, long-running fast-food chain known for its burgers & fries.
10.3108 KILOMETERS
##### http://127.0.0.1:8083/search?keyword=chinese&latitude=37.443758990418964&longitude=-122.17125603023388&distance=2
[{"id":2,"name":"FEY Restaurant","speciality":"Spicy Sichuan fare & other Chinese eats in a swanky space with whimsical decor, a bar & a VIP room.","longitude":0.0,"latitude":0.0,"geoPoint":{"lat":37.455694682376944,"lon":-122.1868547727396}}]


