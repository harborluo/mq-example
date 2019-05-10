
local_container_name=mq_example_local
docker stop $local_container_name
docker rm $local_container_name

docker build -t mq_example . 
docker run -dit -p 19527:19527 --name $local_container_name mq_example
