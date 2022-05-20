# TodoMVC
*********

### Proconditions
* docker must be installed
* some postgres db must be running

### Postgres through Docker
Pull the docker image from docker hub: https://hub.docker.com/_/postgres
and follow instructions to set it up:

    $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

and with port-forwarding (second port is the inside port) it would be something like this:

    $ docker run --name some-postgres -p 5430:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todomvc -d postgres
        --name:     assign a name to the container
        -p:         publish container's port(s) to the host
        -e:         set environment variables
        -d:         run container in background and print container id

If you need to build the image before running it then do this:

    $docker build -t project/todo-app . // builds an image with a tag
        -t:         tag
        . :         uses Dockerfile in current directory

After running having build and run that image creating a container, you still need
to add the right datasource to your applications.properties like:

    spring.datasource.url=jdbc:postgresql://localhost:5430/todomvc
- localhost means that you are trying to reach your postgres from outside the dvm

Connect to the database with intellij and check if you can write and read from it.

    $ docker images // to show all images
    $ docker ps // show only running containers
    $ docker ps -a // show all containers

If you need to remove a docker container do:

    $ docker rm -f // force delete container even if running
    $ docker rm -fv // force remove dangling containers

### Docker-Compose Setting
This is the preferred way to set up the developer environment. This way your application
can be run as in production.


docker-compose down && mvn clean install -DskipTests && docker rmi -f todo-mvc && docker build -t todo-mvc . && docker-compose up

mvn clean install -DskipTests // clear creates jar with tests skipped
&& docker build -t todo-mvc . // use the dockerfile in this dir
&& docker-compose down // shuts down everything in this context + deletes containers
&& docker-compose up

Be aware that you have your ports "hard-coded" in the jar of your application
that's why you might need to mvn clean install it after changing

ports in conflict, simply choose others 

downlaoding the docker images -> go into docker for windwos and check the TLS checkbox

`docker run --name todomvc -e POSTGRES_PASSWORD=postgres -p 5444:5432 -d postgres`


having the app and the db within docker is better than having only the db in docker
because then you run your application as if in production.
==========================================================================

### Docker Tips and Tricks
In your Dockerfile you write down the instructions [FROM, PULL, RUN, CMD] to build docker images.

A Docker image is a file with a lot of instructions. When they are executed, a docker container is created.

First you create a Dockerfile, then you build it with the following command:

`$ docker build -t docker-image-name .`

