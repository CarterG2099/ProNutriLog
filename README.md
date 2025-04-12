# Project Design

I first created this app about two years ago. At the time, I was trying to eat healthy and support my workouts in the gym, but as a college student, I was very conscious of my finances. I couldn’t find an app that solved this problem for me—let alone a free one. So I built one myself to help track budget-friendly, nutritious foods. Over the course of the project, I’ve refactored nearly every file to automate the process. The app now supports individual user accounts, the ability to query popular grocery stores and return the results based on price.

## Key Learnings:
- Real-Time and Remote Data Sync
  - Integrated with Supabase PostgREST to perform secure CRUD operations
  - Learned the importance of RLS to control access to user data.
- Full-Stack Development Experience
  - Gained hands-on experience building both frontend UI in Jetpack Compose and backend data handling with Supabase.
  - Learned how to structure clean, maintainable Kotlin code.
  - Learned the process of publishing an app to Google Play
- API Integrations
  - Learned new networking libraries like Retrofit
  - Discovered dependency injection libraries like Hilt wrapper for Dagger.
  - Connected to Supabase backend for user authentication.
- Time Estimations and Planning
  - I learned a ton about how much time it takes me personally to accomplish these tasks. Most of the time was spent getting used to the technologies. If I were to do restart this project it would be exponentially faster.

## Authentication:
- Primarily handled by Supabase. I take advantage of the auth sessions so users don't have to login every time they open the app. Supports email and password sign in as well as Google Oauth.

## Failover Strategy:
Again, Supabase has features built in for this as the product requires scalability. Also using Retrofit I can implement retry mechanisms for the API requests.



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
