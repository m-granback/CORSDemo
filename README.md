# CORS Demonstration Application

This Spring Boot application demonstrates Cross-Origin Resource Sharing (CORS) in action. It shows how CORS policies can block or allow cross-origin requests based on server configuration.

## 🚀 How to Run

1. **Start the Spring Boot application:**
   ```bash
   mvn spring-boot:run
   ```
   The application will start on `http://localhost:8080`

2. **Open the CORS demo HTML file:**
   - Open `cors-demo.html` directly in your web browser
   - **Important:** Don't open it through Spring Boot - open the file directly to see CORS in action!

## 🌐 What You'll See

### Current Setup
- **Global CORS Configuration:** Enabled in `SecurityConfig.java` for specific origins
- **Multiple Endpoints:** Different CORS policies for different endpoints
- **Interactive Testing:** HTML interface to test various CORS scenarios

### CORS Scenarios Demonstrated

1. **🔒 Global CORS (SecurityConfig)**
   - Endpoints: `/test`, `/api/data`, `/api/submit`
   - Allows requests from: `http://127.0.0.1:8080`, `http://localhost:8080`, `null`
   - **Expected Result:** ✅ Success (when CORS is properly configured)

2. **🚫 Restricted CORS**
   - Endpoint: `/restricted`
   - Only allows: `http://localhost:8080` origin
   - **Expected Result:** ❌ Failure (when accessed from different origin)

3. **🌍 Open CORS**
   - Endpoint: `/open`
   - Allows: Any origin (`*`)
   - **Expected Result:** ✅ Success (but not recommended for production)

## 🧪 Testing CORS

### Step 1: Start the Application
```bash
mvn spring-boot:run
```

### Step 2: Open the Demo HTML
- Open `cors-demo.html` directly in your browser
- You should see your current origin information (likely `null` or `file://`)

### Step 3: Test Different Scenarios
1. **Click "Test /test (POST)"** - Should work if CORS is configured
2. **Click "Test /restricted (POST)"** - Should fail due to origin restriction
3. **Click "Test /open (GET)"** - Should work from any origin

## 📊 Expected Results

| Scenario | Origin | Expected Result | Why? |
|----------|---------|----------------|------|
| Same Origin | `http://localhost:8080` | ✅ Success | No CORS restrictions |
| Cross Origin (Allowed) | `file://` or `null` | ✅ Success | CORS headers allow it |
| Cross Origin (Blocked) | Different domain | ❌ Failure | CORS policy blocks it |

## 🔧 CORS Configuration Options

### 1. Global Configuration (SecurityConfig.java)
```java
http.cors(c -> {
    CorsConfigurationSource ccs = request -> {
        CorsConfiguration cc = new CorsConfiguration();
        cc.setAllowedOrigins(List.of("http://localhost:8080"));
        cc.setAllowedMethods(List.of("POST", "GET", "OPTIONS"));
        cc.setAllowedHeaders(List.of("*"));
        return cc;
    };
    c.configurationSource(ccs);
});
```

### 2. Method-Level Configuration (@CrossOrigin)
```java
@CrossOrigin(origins = {"http://localhost:8080"})
@PostMapping("/restricted")
public String restricted() { ... }
```

### 3. Allow All Origins (Not Recommended)
```java
@CrossOrigin(origins = "*")
@GetMapping("/open")
public String open() { ... }
```

## 🚨 Common CORS Errors

- **"No 'Access-Control-Allow-Origin' header"** - Server doesn't send CORS headers
- **"Request blocked by CORS policy"** - Browser blocks cross-origin request
- **"Preflight request failed"** - OPTIONS request failed

## 💡 Learning Points

1. **CORS is a security feature** that prevents malicious websites from accessing APIs
2. **Same-origin requests** always work (no CORS restrictions)
3. **Cross-origin requests** require explicit permission via CORS headers
4. **Preflight requests** (OPTIONS) are sent for complex requests
5. **CORS configuration** should be as restrictive as possible for security

## 🔍 Debugging CORS

Check the browser's Developer Tools → Network tab to see:
- Request headers (including `Origin`)
- Response headers (including CORS headers)
- Preflight OPTIONS requests
- CORS error messages in Console

## 📚 Further Reading

- [MDN CORS Documentation](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)
- [Spring CORS Documentation](https://docs.spring.io/spring-framework/reference/web/webmvc-cors.html)
- [CORS Security Best Practices](https://owasp.org/www-project-web-security-testing-guide/latest/4-Web_Application_Security_Testing/11-Client_Side_Testing/07-Testing_Cross_Origin_Resource_Sharing)
