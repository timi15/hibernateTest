
# hibernetTest           [![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)

Simple Spring Boot Restful CRUD application with Hibernate and Oracle database connection

## Run Locally

Follow these steps to run the project locally:

1. **Clone the repository:**

    ```bash
    git clone [repository_url]
    ```

2. **Navigate to the project directory:**

    ```bash
    cd [project-directory]
    ```

3. **Build the project:**

    ```bash
    mvn clean install
    ```

4. **Run the project:**

    ```bash
    mvn spring-boot:run
    ```

5. **Access the application:**

    Once the application is running, you can access it at:

    ```plaintext
    http://localhost:8080
    ```


## Some API Reference

#### Get all teachers

```http
  GET /api/v1/teacher/
``` 
#### Get teacher by id

```http
  GET /api/v1/teacher/{id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int` | **Required**. Id of teacher to fetch |

#### Create teacher

```http
  POST /api/v1/teacher/addTeacher
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required** |
|`lastname` | `String` | **Required** |
|`firstname` | `String` | **Required** |
|`dateOfBirth` | `LocalDate` |**Required**  |
| `placeOfBirth` | `String` | **Required** |

#### Delete teacher by id

```http
  DELETE /api/v1/teacher/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Id of teacher to delete |




## For API document using OpenAPI UI


```bash
  http://localhost:8080/swagger-ui/index.html
```


## Authors

- [@timi15](https://github.com/timi15)


## License

[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
