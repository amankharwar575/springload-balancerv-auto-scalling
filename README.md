🌐 Spring Boot Load Balancer with Auto-Scaling (Dynamic Backend Registration)

This project is a custom Load Balancer built using Spring Boot, designed to distribute traffic across multiple backend servers.
It also supports:

✅ Dynamic backend registration
✅ Health checks
✅ Load-based routing
✅ Auto-scaling simulation
✅ Simple round-robin fallback

🚀 Features
🔁 1. Custom Load Balancing Algorithm

The load balancer selects the backend with the lowest load, and falls back to round-robin if needed.

🩺 2. Health Check Endpoint

Checks the status of all backend nodes.

Endpoint:

GET /health


Sample Output:

[
  {"id":1,"load":1,"port":"8001"},
  {"id":2,"load":1,"port":"8002"},
  {"id":3,"load":0,"port":"8003"},
  {"id":4,"load":0,"port":"8004"}
]

⚙️ 3. Auto-Register Backend Servers

Each backend server registers itself with the load balancer on startup.

📡 4. Proxy Endpoint

The load balancer forwards requests to backend servers.

Example:

GET http://localhost:8080/


If all backends are offline →
➡️ Response: "Backend unreachable"

🔌 5. Multiple Backend Instances

Run each backend on different ports:

8001
8002
8003
8004

🏗 Project Structure
springload-balancerv-auto-scalling/
│
├── loadbalancer/    → Load balancer service (port 8080)
├── backend-server/  → Backend server template
│
└── README.md

▶️ How to Run
1️⃣ Start the Load Balancer
cd loadbalancer
mvn spring-boot:run

2️⃣ Start Backend Servers on Separate Ports
Backend 1:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8001"

Backend 2:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8002"

Backend 3:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8003"

Backend 4:
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8004"

🧪 Testing
✔ Check load balancer root
curl http://localhost:8080/

✔ Check backend response directly
curl http://localhost:8001/b_server1
curl http://localhost:8002/b_server2
curl http://localhost:8003/b_server3
curl http://localhost:8004/b_server4

✔ Check health
curl http://localhost:8080/health

🔄 Auto Scaling (Simulation)

You can simulate scaling by launching more backend instances:

mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8010"


They will auto-register and instantly start receiving traffic.

🛠 Tech Stack

Java 17

Spring Boot

RestTemplate

Maven

Custom load balancing

Dynamic backend pool



📜 License

This project is open-source and free to use.
