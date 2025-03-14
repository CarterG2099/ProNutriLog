# Project Design

## ERD Diagram
![ERD](https://github.com/user-attachments/assets/605a2dcc-fdd3-4696-b41e-460ab04c390c)

## System Design
![SystemDesign](https://github.com/user-attachments/assets/5d496864-66aa-43e2-89f2-3131bd93d434)

## Implementation Plan  

- **3/19**  
  ✅ Set up the project repository (backend and mobile app).  
  ✅ Define database schema (ERD design in SQL).  
  ✅ Set up Redis and PostgreSQL locally or in the cloud (e.g., AWS, Google Cloud, or Upstash for Redis).  

- **3/26**  
  ✅ Build backend API:  
    - Implement endpoints for retrieving store prices, managing shopping lists, and caching data in Redis.  
    - Develop a basic price scraper (start with one store like Walmart or Kroger). <br>
  ✅ Mobile App Integration:  
    - Create API calls for fetching prices.  
    - Implement basic UI for displaying item prices.  

- **4/2**  
  ✅ Expand scrapers/APIs to support multiple stores.  
  ✅ Optimize caching strategy (cache-aside, write-through, or query caching).  
  ✅ Enhance UI:  
    - Allow users to search for food items and compare store prices.  

- **4/9**  
  ✅ Implement user accounts & preferences (basic authentication).  
  ✅ Improve failover strategy:  
    - Test system resilience if Redis or a data source goes down.  
  ✅ Optimize for performance:  
    - Ensure 5000+ reads/writes per second are supported.  

- **4/16**  
  ✅ Finalize UI & UX enhancements.  
  ✅ Complete documentation & security analysis.  
  ✅ Deploy the system to a cloud server (e.g., Google Cloud, AWS).  
