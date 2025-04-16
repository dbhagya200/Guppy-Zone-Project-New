
# 🐠 GuppyZone 🌿  
🌊 Dive into the World of Aquatic Wonders!

🚀 The ultimate online marketplace for fish lovers, aquarium hobbyists, and aquatic sellers! 💙

---

## 📜 Project Description

**GuppyZone** isn’t just another classifieds site—it's your **underwater universe**! 🌏🐟 Whether you're a beginner setting up your first tank or a collector hunting for rare beauties, this platform is for you.

🎯 Designed for:
- 🧑‍🌾 Fish breeders & aquatic plant sellers
- 💡 Aquarium equipment stores
- 🤝 Buyers & hobbyists of all levels
- 🧼 Aquarium service providers

🌟 **Mission**:  
“To make aquarium trading fun, social, and secure while building a thriving aquatic community!” 🌿✨

---

## 📸 Screenshot Highlights

1. 🏠 **Homepages**  
   Discover fish, gear, and advice — all in one splash! 💦
   
  ![Screenshot from 2025-04-16 19-04-24](https://github.com/user-attachments/assets/9ac5b3d9-e92f-4e5b-b4fb-d0a2425583c2)

  ![Screenshot from 2025-04-16 19-08-51](https://github.com/user-attachments/assets/80fabacc-f38e-4489-84e7-c66f8dd1231c)

  ![Screenshot from 2025-04-16 19-09-17](https://github.com/user-attachments/assets/7daecfe4-1010-4f74-b3b0-d3dd5273d366)




3. 📦 **Product Listings**  
   Browse fish, tanks, plants, and more! 🌊🌿
   
  ![Screenshot from 2025-04-16 19-12-24](https://github.com/user-attachments/assets/6006200b-daf5-4c83-9ee3-2d8e0cd92bdb)

![Screenshot from 2025-04-16 19-12-37](https://github.com/user-attachments/assets/f417bc46-eb8b-4279-926b-ecaf133ea38d)




4. 📊 **Seller Dashboard**  
   Track listings, and performance — all from one tank-top view! 🧠🐠
   
  ![Screenshot from 2025-04-16 19-14-15](https://github.com/user-attachments/assets/2e7cc292-7d4d-4012-9877-7e4b2c8f66a2)

![Screenshot from 2025-04-16 19-14-23](https://github.com/user-attachments/assets/62d42e37-b11e-4a54-97fc-cbc40b12c849)




5. 🛍️ **Add to Cart Page**
Your selected aquatic treasures, all in one basket 🧺🐠
🐡 Easily update quantities, remove items, and view a summary before diving into checkout!  

![Screenshot from 2025-04-16 19-18-00](https://github.com/user-attachments/assets/3fa302d1-efa3-4f72-a511-e40041fc840d)



6.💳 **Payment Page**
Smooth checkout powered by PayHere 🌐✨

💰 Choose your payment method, confirm, and splash — you're done!
💸 Includes order summary, shipping info, and secure payment options.

![Screenshot from 2025-04-16 19-18-09](https://github.com/user-attachments/assets/7ab80375-3a3f-4d00-b29b-365f4e2c9906)

---

## 🛠️ Setup in 3...2...1... GO! 🚦

### 🌐 Frontend + Backend (Spring Boot)

> 🧠 Everything is bundled in one awesome Spring Boot app — powered by Thymeleaf + REST!

#### 📥 Clone the project
```bash
git clone https://github.com/dbhagya200/Guppy-Zone-Project-New.git
cd guppyzone
```

#### 🐬 Create the MySQL Database
```sql
CREATE DATABASE guppyzone;
```

#### ⚙️ Configure your `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/guppyzone
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
jwt.secret=keep_it_secret_keep_it_safe
```

#### 🚀 Run the server
```bash
mvn clean install
mvn spring-boot:run
```

🔗 Visit: [http://localhost:8080](http://localhost:8080)

✨ Bonus: Enable WebSocket for live chat magic! 💬⚡

---

## 🎥 Demo Video

▶️ [GuppyZone_Demo_YourName_YourBatch](https://youtu.be/GuppyZoneDemo_YourName_YourBatch)  
_(2 minutes of aquatic awesomeness!)_

---

## 🌟 Why Aquarium Lovers ❤️ GuppyZone

| Feature                | Superpower 🦸‍♀️                        |
|------------------------|-----------------------------------------|
| Live Chat              | Talk to sellers in real-time 💬          |
| JWT Authentication     | Secure, token-based login 🔐            |
| Fishy Filters          | Find fish by tank size, type, or price 🐡 |
| Verified Sellers       | Build trust with ratings ⭐              |
| PayHere Integration    | Smooth onsite checkout 💳               |
| Hobby Blog             | Tips, guides, and how-to articles 📚     |
| Category Search        | Fish 🐠, Equipment 🧰, Plants 🌿, Services 🧼 |

---

## 🧠 Tech Stack

- 🖥️ **Frontend**: Spring Boot + Thymeleaf
- 🔗 **Backend**: Spring Boot REST API
- 🐬 **Database**: MySQL
- 🔐 **Auth**: JWT (Token-based)
- 📡 **Chat**: WebSocket (SockJS + STOMP)
- 💸 **Payments**: PayHere JavaScript SDK

---

## 🐳 Docker-Ready? (Optional)

```bash
docker-compose up --build -d
```

Preloaded with:
- 🐬 MySQL
- 🐳 Spring Boot
- 🔁 Auto-redeploy
- 🛡️ Secure tokens
- 🌈 Aquatic vibes included

---

## 💌 Let’s Connect!

 📧 [Email](#) 

👨‍💻 Built with love, fins, and lots of coffee ☕  
🎓 By IJSE Galle - 70th Batch

---
Reel in your dreams with **GuppyZone** today! 🎣💧✨
```
