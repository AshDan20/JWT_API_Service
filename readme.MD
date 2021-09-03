# **JWT API Service**


- /api/info, where authenticated users can receive responses
- /api/auth, where users can obtain authentication tokens

**GET** http://localhost:8080/api/info - it would give 401

***

this would fail -  as it needs password to be sent
**POST** ON  http://localhost:8080/api/auth with 
data -> {"username": "test"}

***


This should give the JSON Token

**POST** ON  http://localhost:8080/api/auth
with data 
{"username": "test", "password": "test"}
it would give the token like 
_`eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjMwODY5NjAxfQ.UdnZ8xG1fOXntN6_xp_2EDKD_5bQJnyt-2AUTdTWYso`_

***

use the token to hit api/info API and it would return user details like below

`{
  "id": 1,
  "username": "test",
  "role": "USER"
}`

`curl -X GET 
-H 'Content-Type: application/json' 
-H 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiZXhwIjoxNjMwODY5NjAxfQ.UdnZ8xG1fOXntN6_xp_2EDKD_5bQJnyt-2AUTdTWYso' 
-i http://localhost:8080/api/info `

***

further reading - secret keys should not be exposed into application code rather 
it should be fetched from secure storage or we can use spring vault 
https://spring.io/projects/spring-vault#overview


@Configuration 
@EnableWebSecurity