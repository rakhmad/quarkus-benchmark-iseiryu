# Quarkus Benchmark based on iSeiryu

This is my attempt to test Quarkus' performance using the iSeiryu test case.
I am using the latest community version of Quarkus (3.10) and OpenJDK 21 Temurin.

Test command:
```bash
hey -n 200000 -c 100 -m POST -H 'Content-Type: application/json' -d '{
  "from": {
      "id": "b1b9b3b1-3b1b-3b1b-3b1b-3b1b3b1b3b1b",
      "firstName": "John",
      "lastName": "Doe",
      "address": {
          "street": "123 Main St",
          "city": "Anytown",
          "state": "CA",
          "zip": "12345"
      },
      "email": "john.doe@somewhere.com"
  },
  "to": {
      "id": "7eb53909-8977-4a7d-8e91-f1bfcfe812e2",
      "firstName": "Jane",
      "lastName": "Doe",
      "address": {
          "street": "456 Elm St",
          "city": "Anytown",
          "state": "FL",
          "zip": "12345"
      },
      "email": "jane.doe@somewhereelse.com"
  },
  "amount": 30.14,
  "sendOn": "2024-06-01T12:00:00"
}' http://localhost:8080/send-money
```

There are two versions of this API: synchronous and reactive. Running on my test machine (MBP 14 M1 Max, 64GB RAM, macOS 14.5), the results are below:
- Synchronous

  
![Synchronous](https://github.com/rakhmad/quarkus-benchmark-iseiryu/assets/195559/59948995-63a0-4c72-926a-06d9d33ae9fb)
- Reactive

  
![photo_2024-05-28_06-59-34](https://github.com/rakhmad/quarkus-benchmark-iseiryu/assets/195559/17d1dcbb-34de-4494-bd58-41744ab25b58)

I did another benchmark on my Linux Machine (Fedora 40, AMD Ryzen™ 9 5900X × 24, 128 GB RAM) with OpenJDK 21 and Quarkus native image. The results are below
only for reactive API.

- JVM Mode

![JVM Mode](https://github.com/rakhmad/quarkus-benchmark-iseiryu/assets/195559/6c802412-c435-4d79-abfe-c85acc803a17)


- Native Mode

![Native Mode](https://github.com/rakhmad/quarkus-benchmark-iseiryu/assets/195559/d701fae1-0615-48d3-9fd0-cf1981cc0d21)

Memory usage for reactive native mode is 100MB (using `htop`)
