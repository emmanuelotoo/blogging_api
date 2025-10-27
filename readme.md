# Blogging API

RESTful API with basic CRUD operations for a blogging platform, including image upload and management functionality.

## Installation and Setup

### Prerequisites
- Java 21 or higher
- Maven 3.6 or higher

### Steps to Run
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd blogging-api
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080`

## Usage

### Available Endpoints

#### Posts
- `GET /api/v1/posts` - Get all posts
- `GET /api/v1/posts/{id}` - Get post by ID
- `POST /api/v1/post` - Create a new post
- `PUT /api/v1/posts/{id}` - Update an existing post
- `DELETE /api/v1/posts/{id}` - Delete a post

#### Images
- `POST /api/v1/images/upload` - Upload an image
- `GET /api/v1/images/{id}` - Get image by ID

### Example Requests

Using a REST client like Postman or your browser:

**Create a new post:**
- Method: `POST`
- URL: `http://localhost:8080/api/post`
- Headers: `Content-Type: application/json`
- Body:
```json
{
  "title": "My First Post",
  "content": "This is the content of my first blog post.",
  "category": "Technology",
  "tags": [ "tag 1", "tag 2" ]
}
```