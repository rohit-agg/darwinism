# Set base image
FROM php:7.4-apache

# Set working directory
WORKDIR /var/www/html

# Copy all code
COPY . .

# Install php extensions
RUN apt-get update && \
    docker-php-ext-install mysqli && \
    docker-php-ext-enable mysqli && \
    a2enmod rewrite
