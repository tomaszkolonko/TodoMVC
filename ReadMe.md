# TodoMVC
*********

## Proconditions
* docker must be installed
* some postgres db must be running

## Postgres through Docker
Pull the docker image (or simply run it) from docker hub: https://hub.docker.com/_/postgres
and follow instructions to set it up:

    $ docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

and with port-forwarding (second port is the inside port) it would be something like this:

    $ docker run --name some-postgres -p 5430:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=todomvc -d postgres
        --name:     assign a name to the container
        -p:         publish container's port(s) to the host
        -e:         set environment variables
        -d:         run container in background and print container id

In this case you don't need to build postgres anymore because the images has been pulled
and is ready out of the box.

Now, you need to build the image of your application before you can run it:

    $ docker build -t todo-app . // builds an image with a tag
        -t:         tag
        . :         uses Dockerfile in current directory

Then run it:

    $ docker run todo-app

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

## Docker-Compose Setting
This is the preferred way to set up the developer environment. This way your application
can be run as in production in a very consistent and defined way and your environment does
not get polluted over time with unnecessary stuff. Keep it clean and containerized. All your
dependencies reside within your container and once you remove that, you remove everything.
Using docker hub or any other registry server you are able to push your container and pull it
into production exactly as is.

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

## Docker Tips and Tricks
Show all current docker images:

    $ docker images

Deleting a docker image:

     $ docker image rm todo-cli

Show all running / not running docker containers:

    $ docker ps     // show only running containers
    $ docker ps -a  // show them all

Delete all stopped containers, not used networks and dangling images

    $ docker system prune // removes:
        - all stopped containers
        - all networks not used by at least one container
        - all dangling images
        - all dangling build cache

## Background and Theory

In your Dockerfile you write down the instructions [FROM, PULL, RUN, CMD] to build docker images.

You will start your Dockerfile with the FROM instruction which will start from a certain base image
like ubuntu or node... then you will go from there and add things to your image as you need them,
comparable to inheritance.

A Docker image is a file with a lot of instructions. When they are executed, a docker container is created,
which basically is a process in it's own environment running the application with all the other stuff
that is needed like databases, backend and frontend stuff.

First you create a Dockerfile, then you build it with the following command:

    $ docker build -t docker-image-name .

A container is a lightweight isolated environment for running an application whereas a virtual machine is
an abstraction of a machine. The main disadvantage of a VM is that it needs a full blown and
licensed OS (overkill) to run the application, so it makes it super slow and resource intensive.


## Be aware of

If you have your postgres in docker running and your application in intellij you are
connecting to your postgres instance through your laptop, your host. Thus you need 
to use this connection string:

    spring.datasource.url=jdbc:postgresql://localhost:5430/todomvc

If you use docker-compse you are running both the app and the postgres instance within
docker virtual machine, thus you need to use postgres host name (e.g. some-postgres)
and the internal port (!!!) to connect to it.