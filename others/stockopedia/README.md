# Stockopedia Assignment

Following APIs are part of this implementation

| API | URL | Method |
| --- | --- | --- |
| Gets all plans | `/plans` | GET |
| Queries for a specific plan | `/plans/:id` | GET |
| Calculates the subscription cost | `/subscriptions/costs` | POST |
| Allows the user to save their desired plans | `/subscriptions` | POST |

## Setup

To run the application follow the below steps:

- Install Docker
  - [Linux](https://docs.docker.com/engine/install/#server)
  - [Windows](https://docs.docker.com/docker-for-windows/install/)
  - [Mac](https://docs.docker.com/docker-for-mac/install/)
- Go to the root of application
- Execute command `docker-compose up`

Above steps will create two docker containers, one for MySQL and another
one for PHP + Apache

APIs can be invoked on `http://localhost` using the above mentioned URLs.

To connect to database directly, use the following credentials:
- Username: stockopedia
- Password: stockopedia

## Unit Tests

To run the unit tests:

- Setup the application as described in the previous section.
- Execute the following command in a new command-line tab:

  `docker exec stockopedia-web phpunit -c /var/www/html/application/tests`