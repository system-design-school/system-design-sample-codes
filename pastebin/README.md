# Getting Started
### 
need Mysql.

module `com.example.service.id-generation`, port 8079, provide:

  - `/id` , to get an id;

  - `/status` , to check system status.

module `com.example.service.write`, port 8081, provide:

  - `/paste` , use request body to upload text, request body:

```json
{
  "text": "The text to be saved."
}
```

module `com.example.service.read`, port 8082, provide:

 - `/{id}`, to get the paste content.

### Run Project
run main in `com.example.service.id-generation.IDGenerationServiceApplication.java`
run main in `com.example.service.write.WriteServiceApplication.java`
run main in `com.example.service.read.ReadServiceApplication.java`
